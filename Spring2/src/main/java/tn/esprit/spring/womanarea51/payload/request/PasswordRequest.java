package tn.esprit.spring.womanarea51.payload.request;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordRequest {
	
	@NotBlank
    @Size(min = 6, max = 40)
    private String password;
	
	@NotBlank
    @Size(min = 6, max = 40)
    @Transient
    private String passwordConfirm;
	
	
    @Transient
    private String oldPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	

}
