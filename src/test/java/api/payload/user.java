package api.payload;

public class user {
	
	
	String loginUsername;
	String loginPassword;
	String unblockToken;
	
	public void loginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
	
	public void loginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	
	public void unblockToken(String unblockToken) {
		this.unblockToken = unblockToken;
	}


	
	public String loginUsername() {
		return loginUsername;
	}

	
	public String loginPassword() {
		return loginPassword;
	}
	
	public String unblockToken() {
		return unblockToken;
	}
}
