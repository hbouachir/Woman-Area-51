package tn.esprit.spring.womanarea51.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JavaMailSender javaMailSender;
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

	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> logout(@PathVariable("id") long id) {
		User u=userService.findOne(id);
		if (u!=null)
		{
		return
				ResponseEntity
						.ok()
						.body(new MessageResponse("user deleted"));}
		else return ResponseEntity.badRequest().body(new MessageResponse("user doesn't exist"));
	}
	/*@GetMapping("/user/{id}")
	public User findUser(@PathVariable("id") long id) {
		User u=userService.findOne(id);
		return u;
	}*/

	
	@GetMapping("/users")
	public List<User> findUser() {
		return userService.findAll();
	}



	@PostMapping("/logout")
	public ResponseEntity<?> logout( HttpServletResponse response,Authentication authentication) {
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

		return
		ResponseEntity
				.ok()
				.body(new MessageResponse("See you soon "+U.getFirstName()));



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
					.body(new MessageResponse(" Your account is Disabled by Admin!"));
		}
		
		if (!U.isEnabled()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(" Please Confirm your Account, We've sent you a confirmation email"));
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

		//cookie.setMaxAge(60*60);
		cookie.setSecure(false);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
	response.addCookie(cookie);

//String jwt=
	response.addHeader("access_token", access_token.getAccessToken());
		//response.addHeader("access_token",access_token );
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


/*
	@PostMapping("/sendOTP")
	public void sendOTP(@RequestBody PasswordResetRequest passwordResetRequest){


		twilioOTPService.sendOTPForPasswordReset(passwordResetRequest);	}
*/

	@PostMapping("/sendOTP")
	public void sendOTP(@RequestBody PasswordResetRequest passwordResetRequest) throws MessagingException {



			User user = userRepository.findByUsername(passwordResetRequest.getUsername()).orElse(null);

			String recipientAddress = user.getEmail();
			String subject = "RESET PASSWORD";
			String a=twilioOTPService.generateOTP();
			StringBuilder buf = new StringBuilder();
			buf.append("\"<html>\n" + "  <head>\n" + "    <meta name=\"viewport\" content=\"width=device-width\" />\n"
					+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
					+ "    <title>Simple Transactional Email</title>\n" + "    <style>\n"
					+ "      /* -------------------------------------\n" + "          GLOBAL RESETS\n"
					+ "      ------------------------------------- */\n" + "      \n"
					+ "      /*All the styling goes here*/\n" + "      \n" + "      img {\n" + "        border: none;\n"
					+ "        -ms-interpolation-mode: bicubic;\n" + "        max-width: 100%; \n" + "      }\n" + "\n"
					+ "      body {\n" + "        background-color: #f6f6f6;\n" + "        font-family: sans-serif;\n"
					+ "        -webkit-font-smoothing: antialiased;\n" + "        font-size: 14px;\n"
					+ "        line-height: 1.4;\n" + "        margin: 0;\n" + "        padding: 0;\n"
					+ "        -ms-text-size-adjust: 100%;\n" + "        -webkit-text-size-adjust: 100%; \n" + "      }\n"
					+ "\n" + "      table {\n" + "        border-collapse: separate;\n" + "        mso-table-lspace: 0pt;\n"
					+ "        mso-table-rspace: 0pt;\n" + "        width: 100%; }\n" + "        table td {\n"
					+ "          font-family: sans-serif;\n" + "          font-size: 14px;\n"
					+ "          vertical-align: top; \n" + "      }\n" + "\n"
					+ "      /* -------------------------------------\n" + "          BODY & CONTAINER\n"
					+ "      ------------------------------------- */\n" + "\n" + "      .body {\n"
					+ "        background-color: #f6f6f6;\n" + "        width: 100%; \n" + "      }\n" + "\n"
					+ "      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n"
					+ "      .container {\n" + "        display: block;\n" + "        margin: 0 auto !important;\n"
					+ "        /* makes it centered */\n" + "        max-width: 580px;\n" + "        padding: 10px;\n"
					+ "        width: 580px; \n" + "      }\n" + "\n"
					+ "      /* This should also be a block element, so that it will fill 100% of the .container */\n"
					+ "      .content {\n" + "        box-sizing: border-box;\n" + "        display: block;\n"
					+ "        margin: 0 auto;\n" + "        max-width: 580px;\n" + "        padding: 10px; \n"
					+ "      }\n" + "\n" + "      /* -------------------------------------\n"
					+ "          HEADER, FOOTER, MAIN\n" + "      ------------------------------------- */\n"
					+ "      .main {\n" + "        background: #ffffff;\n" + "        border-radius: 3px;\n"
					+ "        width: 100%; \n" + "      }\n" + "\n" + "      .wrapper {\n"
					+ "        box-sizing: border-box;\n" + "        padding: 20px; \n" + "      }\n" + "\n"
					+ "      .content-block {\n" + "        padding-bottom: 10px;\n" + "        padding-top: 10px;\n"
					+ "      }\n" + "\n" + "      .footer {\n" + "        clear: both;\n" + "        margin-top: 10px;\n"
					+ "        text-align: center;\n" + "        width: 100%; \n" + "      }\n" + "        .footer td,\n"
					+ "        .footer p,\n" + "        .footer span,\n" + "        .footer a {\n"
					+ "          color: #999999;\n" + "          font-size: 12px;\n" + "          text-align: center; \n"
					+ "      }\n" + "\n" + "      /* -------------------------------------\n" + "          TYPOGRAPHY\n"
					+ "      ------------------------------------- */\n" + "      h1,\n" + "      h2,\n" + "      h3,\n"
					+ "      h4 {\n" + "        color: #000000;\n" + "        font-family: sans-serif;\n"
					+ "        font-weight: 400;\n" + "        line-height: 1.4;\n" + "        margin: 0;\n"
					+ "        margin-bottom: 30px; \n" + "      }\n" + "\n" + "      h1 {\n" + "        font-size: 35px;\n"
					+ "        font-weight: 300;\n" + "        text-align: center;\n"
					+ "        text-transform: capitalize; \n" + "      }\n" + "\n" + "      p,\n" + "      ul,\n"
					+ "      ol {\n" + "        font-family: sans-serif;\n" + "        font-size: 14px;\n"
					+ "        font-weight: normal;\n" + "        margin: 0;\n" + "        margin-bottom: 15px; \n"
					+ "      }\n" + "        p li,\n" + "        ul li,\n" + "        ol li {\n"
					+ "          list-style-position: inside;\n" + "          margin-left: 5px; \n" + "      }\n" + "\n"
					+ "      a {\n" + "        color: #3498db;\n" + "        text-decoration: underline; \n" + "      }\n"
					+ "\n" + "      /* -------------------------------------\n" + "          BUTTONS\n"
					+ "      ------------------------------------- */\n" + "      .btn {\n"
					+ "        box-sizing: border-box;\n" + "        width: 100%; }\n"
					+ "        .btn > tbody > tr > td {\n" + "          padding-bottom: 15px; }\n"
					+ "        .btn table {\n" + "          width: auto; \n" + "      }\n" + "        .btn table td {\n"
					+ "          background-color: #ffffff;\n" + "          border-radius: 5px;\n"
					+ "          text-align: center; \n" + "      }\n" + "        .btn a {\n"
					+ "          background-color: #ffffff;\n" + "          border: solid 1px #3498db;\n"
					+ "          border-radius: 5px;\n" + "          box-sizing: border-box;\n"
					+ "          color: #3498db;\n" + "          cursor: pointer;\n" + "          display: inline-block;\n"
					+ "          font-size: 14px;\n" + "          font-weight: bold;\n" + "          margin: 0;\n"
					+ "          padding: 12px 25px;\n" + "          text-decoration: none;\n"
					+ "          text-transform: capitalize; \n" + "      }\n" + "\n" + "      .btn-primary table td {\n"
					+ "        background-color: #3498db; \n" + "      }\n" + "\n" + "      .btn-primary a {\n"
					+ "        background-color: #3498db;\n" + "        border-color: #3498db;\n"
					+ "        color: #ffffff; \n" + "      }\n" + "\n" + "      /* -------------------------------------\n"
					+ "          OTHER STYLES THAT MIGHT BE USEFUL\n" + "      ------------------------------------- */\n"
					+ "      .last {\n" + "        margin-bottom: 0; \n" + "      }\n" + "\n" + "      .first {\n"
					+ "        margin-top: 0; \n" + "      }\n" + "\n" + "      .align-center {\n"
					+ "        text-align: center; \n" + "      }\n" + "\n" + "      .align-right {\n"
					+ "        text-align: right; \n" + "      }\n" + "\n" + "      .align-left {\n"
					+ "        text-align: left; \n" + "      }\n" + "\n" + "      .clear {\n" + "        clear: both; \n"
					+ "      }\n" + "\n" + "      .mt0 {\n" + "        margin-top: 0; \n" + "      }\n" + "\n"
					+ "      .mb0 {\n" + "        margin-bottom: 0; \n" + "      }\n" + "\n" + "      .preheader {\n"
					+ "        color: transparent;\n" + "        display: none;\n" + "        height: 0;\n"
					+ "        max-height: 0;\n" + "        max-width: 0;\n" + "        opacity: 0;\n"
					+ "        overflow: hidden;\n" + "        mso-hide: all;\n" + "        visibility: hidden;\n"
					+ "        width: 0; \n" + "      }\n" + "\n" + "      .powered-by a {\n"
					+ "        text-decoration: none; \n" + "      }\n" + "\n" + "      hr {\n" + "        border: 0;\n"
					+ "        border-bottom: 1px solid #f6f6f6;\n" + "        margin: 20px 0; \n" + "      }\n" + "\n"
					+ "      /* -------------------------------------\n"
					+ "          RESPONSIVE AND MOBILE FRIENDLY STYLES\n"
					+ "      ------------------------------------- */\n"
					+ "      @media only screen and (max-width: 620px) {\n" + "        table[class=body] h1 {\n"
					+ "          font-size: 28px !important;\n" + "          margin-bottom: 10px !important; \n"
					+ "        }\n" + "        table[class=body] p,\n" + "        table[class=body] ul,\n"
					+ "        table[class=body] ol,\n" + "        table[class=body] td,\n"
					+ "        table[class=body] span,\n" + "        table[class=body] a {\n"
					+ "          font-size: 16px !important; \n" + "        }\n" + "        table[class=body] .wrapper,\n"
					+ "        table[class=body] .article {\n" + "          padding: 10px !important; \n" + "        }\n"
					+ "        table[class=body] .content {\n" + "          padding: 0 !important; \n" + "        }\n"
					+ "        table[class=body] .container {\n" + "          padding: 0 !important;\n"
					+ "          width: 100% !important; \n" + "        }\n" + "        table[class=body] .main {\n"
					+ "          border-left-width: 0 !important;\n" + "          border-radius: 0 !important;\n"
					+ "          border-right-width: 0 !important; \n" + "        }\n"
					+ "        table[class=body] .btn table {\n" + "          width: 100% !important; \n" + "        }\n"
					+ "        table[class=body] .btn a {\n" + "          width: 100% !important; \n" + "        }\n"
					+ "        table[class=body] .img-responsive {\n" + "          height: auto !important;\n"
					+ "          max-width: 100% !important;\n" + "          width: auto !important; \n" + "        }\n"
					+ "      }\n" + "\n" + "      /* -------------------------------------\n"
					+ "          PRESERVE THESE STYLES IN THE HEAD\n" + "      ------------------------------------- */\n"
					+ "      @media all {\n" + "        .ExternalClass {\n" + "          width: 100%; \n" + "        }\n"
					+ "        .ExternalClass,\n" + "        .ExternalClass p,\n" + "        .ExternalClass span,\n"
					+ "        .ExternalClass font,\n" + "        .ExternalClass td,\n" + "        .ExternalClass div {\n"
					+ "          line-height: 100%; \n" + "        }\n" + "        .apple-link a {\n"
					+ "          color: inherit !important;\n" + "          font-family: inherit !important;\n"
					+ "          font-size: inherit !important;\n" + "          font-weight: inherit !important;\n"
					+ "          line-height: inherit !important;\n" + "          text-decoration: none !important; \n"
					+ "        }\n" + "        #MessageViewBody a {\n" + "          color: inherit;\n"
					+ "          text-decoration: none;\n" + "          font-size: inherit;\n"
					+ "          font-family: inherit;\n" + "          font-weight: inherit;\n"
					+ "          line-height: inherit;\n" + "        }\n" + "        .btn-primary table td:hover {\n"
					+ "          background-color: #34495e !important; \n" + "        }\n"
					+ "        .btn-primary a:hover {\n" + "          background-color: #34495e !important;\n"
					+ "          border-color: #34495e !important; \n" + "        } \n" + "      }\n" + "\n"
					+ "    </style>\n" + "  </head>\n" + "  <body class=\"\">\n"
					+ "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>\n"
					+ "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\n"
					+ "      <tr>\n" + "        <td>&nbsp;</td>\n" + "        <td class=\"container\">\n"
					+ "          <div class=\"content\">\n" + "\n" + "            <!-- START CENTERED WHITE CONTAINER -->\n"
					+ "            <table role=\"presentation\" class=\"main\">\n"
					+ "                    <img src=\"C:\\Users\\HAMZA\\Desktop\\test1\\demo\\src\\main\\resources\\static\\images\\post1.gif\"></a>\n" + "\n"
					+ "              <!-- START MAIN CONTENT AREA -->\n" + "              <tr>\n"
					+ "                <td class=\"wrapper\">\n"
					+ "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
					+ "                    <tr>\n" + "                      <td>\n"
					+ "                        <p>Bonjour ,</p>\n"
					+ "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\n"
					+ "                          <tbody>\n" + "                            <tr>\n"
					+ "                              <td align=\"left\">\n"
					+ "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
					+ "                                  <tbody>\n" + "                                  </tbody>\n"
					+ "                                </table>\n" + "                              </td>\n"
					+ "                            </tr>\n" + "                          </tbody>\n"
					+ "                        </table>\n" + "                        <h3>Bienvenue chez Women Area 51!</h3>\n"
					+ "                        <p>your otp is:</p>\n"
					+ "                        <p>"+ a +"</p>\n"
					+ "                        <p></p>\n"
					+ "                        <p>Cordialement.</p>\n"
					+ "                        <p>Women Area 51.</p>\n" + "                      </td>\n"
					+ "                    </tr>\n" + "                  </table>\n" + "                </td>\n"
					+ "              </tr>\n" + "\n" + "            <!-- END MAIN CONTENT AREA -->\n"
					+ "            </table>\n" + "            <!-- END CENTERED WHITE CONTAINER -->\n" + "\n"
					+ "            <!-- START FOOTER -->\n" + "            <div class=\"footer\">\n"
					+ "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
					+ "                <tr>\n" + "                  <td class=\"content-block\">\n"
					+ "                    <span class=\"apple-link\">Woman Area 51,El-ghazela, Tunisie</span>\n"
					+ "                  </td>\n" + "                </tr>\n" + "                <tr>\n"
					+ "                  <td class=\"content-block powered-by\">\n"
					+ "                    Powered by <a href=\"http://htmlemail.io\">HTMLemail</a>.\n"
					+ "                  </td>\n" + "                </tr>\n" + "              </table>\n"
					+ "            </div>\n" + "            <!-- END FOOTER -->\n" + "\n" + "          </div>\n"
					+ "        </td>\n" + "        <td>&nbsp;</td>\n" + "      </tr>\n" + "    </table>\n" + "  </body>\n"
					+ "</html>\"");


			SimpleMailMessage mail = new SimpleMailMessage();
			String messaage = buf.toString();
			//// ********************************////
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(recipientAddress);

			helper.setFrom("test.esprit2021@gmail.com");
			helper.setSubject(subject);
			helper.setText(messaage, messaage);
			javaMailSender.send(message);
		    this.twilioOTPService.otpMap.put(passwordResetRequest.getUsername(), a);
		}






	@PostMapping("/validateOTP")
	public void sendOTP(@RequestBody ActivateOtpRequest activateOtpRequest){

		twilioOTPService.validateOTP(activateOtpRequest.getUserInputOtp(),activateOtpRequest.getUsername(),activateOtpRequest.getNewPassword());	}


	@PreAuthorize("hasRole('ROLE_SUPER_USER')")
	@PostMapping("addUserAffectRole")
	public void addUserAffectRole(@RequestParam  long idRole, @RequestParam long idUser) {
		userService.addUserAffectRole(idRole, idUser);}



	@DeleteMapping("deleteUser")
	public void deleteRole(@RequestParam long userId){
		userService.deleteUser(userId);



	}


	@GetMapping("/user/{username}")
	public User findOneByUsename( @PathVariable("username") String username) {

		User U=new User();
		U=userRepository.findByUsername(username).orElse(null);



		return U;
	}







}
