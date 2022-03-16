package tn.esprit.spring.womanarea.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import tn.esprit.spring.womanarea.demo.Entities.ERole;
import tn.esprit.spring.womanarea.demo.Entities.Role;
import tn.esprit.spring.womanarea.demo.Entities.User;
import tn.esprit.spring.womanarea.demo.Entities.VerificationToken;
import tn.esprit.spring.womanarea.demo.Payload.request.LoginRequest;
import tn.esprit.spring.womanarea.demo.Payload.request.PasswordRequest;
import tn.esprit.spring.womanarea.demo.Payload.request.ProfileRequest;
import tn.esprit.spring.womanarea.demo.Payload.request.SignupRequest;
import tn.esprit.spring.womanarea.demo.Payload.request.Twilio.ActivateOtpRequest;
import tn.esprit.spring.womanarea.demo.Payload.request.Twilio.PasswordResetRequest;
import tn.esprit.spring.womanarea.demo.Payload.response.JwtResponse;
import tn.esprit.spring.womanarea.demo.Payload.response.MessageResponse;
import tn.esprit.spring.womanarea.demo.Repositories.RoleRepository;
import tn.esprit.spring.womanarea.demo.Repositories.UserRepository;
import tn.esprit.spring.womanarea.demo.Services.OnRegistrationCompleteEvent;
import tn.esprit.spring.womanarea.demo.Services.UserService;
import tn.esprit.spring.womanarea.demo.Services.TwilioOTPService;

import tn.esprit.spring.womanarea.demo.security.jwt.JwtUtils;
import tn.esprit.spring.womanarea.demo.security.services.UserDetailsImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TwilioOTPService twilioOTPService;

	Cookie cookie=new Cookie("access_token","");
	LocalDateTime loginTime=null;
	LocalDateTime logoutTime=null;


/*	@Autowired
	TwilioOTPHandler twilioOTPHandler;*/
	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;

	@PostMapping("/logout")
	public void logout( HttpServletResponse response,Authentication authentication) {
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
		User U=userRepository.findByUsername(U1.getUsername()).orElse(null);

		response.setContentType(null);
		cookie.setMaxAge(0);


		response.addCookie(cookie);
		logoutTime=LocalDateTime.now();


		int diff= (int) ChronoUnit.SECONDS.between(loginTime,logoutTime);
		if (diff<3600){
		int a=U.getLoginTime()+diff;
		System.out.println(diff);
		System.out.println(a);

		U.setLoginTime(a);}
		else {U.setLoginTime(3600);}
		loginTime=null;
		logoutTime=null;
		userRepository.save(U);





	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User U = userRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userDetails.getUsername()));
	
		
		if (!userDetails.getEtatAcc()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Your account is Disabled by Admin!"));
		}
		
		if (!U.isEnabled()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Please Confirm your Account, We've sent you a confirmation email"));
		}
		System.out.println(userDetails.getEtatAcc());
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		Map<String, String> tokens = new HashMap<>();

		JwtResponse access_token=new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles);

		tokens.put("access_token", access_token.getAccessToken());
		cookie=new Cookie("access_token", access_token.getAccessToken());
		cookie.setMaxAge(60*60);
		//cookie.setSecure(true);
		//cookie.setHttpOnly(true);
		response.addCookie(cookie);


		response.setHeader("access_token", access_token.getAccessToken());
		loginTime=LocalDateTime.now();

		return ResponseEntity.ok(access_token);
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request ) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),signUpRequest.getFirstName()
							 ,signUpRequest.getLastName(),signUpRequest.getAddress(),signUpRequest.getDateN(),signUpRequest.getTel()
							 ,signUpRequest.getSexe());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;


				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		String appUrl = request.getContextPath();
		User registered= user;
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
		request.getLocale(), appUrl));

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<?> UpdateUser(@Valid @RequestBody ProfileRequest profileRequest
			,Authentication authentication) {
		
		// update user's account
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();		
		User U = userRepository.findByUsername(U1.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		U.setFirstName(profileRequest.getFirstName());
		U.setLastName(profileRequest.getLastName());
		U.setAddress(profileRequest.getAddress());
		U.setDateN(profileRequest.getDateN());
		U.setTel(profileRequest.getTel());
		U.setEmail(profileRequest.getEmail());
		
		userService.updateUser(U);

		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
	}
	
	
	@PutMapping("/updatepassword")
	public ResponseEntity<?> UpdatePassword(@Valid @RequestBody PasswordRequest PasswordRequest, Authentication authentication) {
		
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();		
		User U = userRepository.findByUsername(U1.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		
			if(PasswordRequest.getPassword().equals(PasswordRequest.getPasswordConfirm()))
			{
				U.setPassword(encoder.encode(PasswordRequest.getPassword()));
				userService.updateUser(U);

				return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));
			}
			else 		
				return ResponseEntity.ok(new MessageResponse("password and confirm password does not match!"));
		 	
		
	}
	
	@GetMapping("/regitrationConfirm/token/{token}")
	public ResponseEntity<?> confirmRegistration
	  (WebRequest request, Model model, @PathVariable(value = "token") String token) {
	  
	    Locale locale = request.getLocale();
	     
	    VerificationToken verificationToken = userService.getVerificationToken(token);
	    if (verificationToken == null) {
			return ResponseEntity.ok(new MessageResponse("InvaLID vERFICATION Token"));

	    }
	     
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return ResponseEntity.ok(new MessageResponse("Link Expired!"));

	    } 
	     
	    user.setEnabled(true); 
	    userService.updateUser(user);
		return ResponseEntity.ok(new MessageResponse("Account Confirmed with succes"));
	}



	@PostMapping("/sendOTP")
	public void sendOTP(@RequestBody PasswordResetRequest passwordResetRequest){

		twilioOTPService.sendOTPForPasswordReset(passwordResetRequest);	}


	@PostMapping("/validateOTP")
	public void sendOTP(@RequestBody ActivateOtpRequest activateOtpRequest){

		twilioOTPService.validateOTP(activateOtpRequest.getUserInputOtp(),activateOtpRequest.getUsername(),activateOtpRequest.getNewPassword());	}





}
