package raindrop.backend.core;

public class Credential {
	/**
	 * This class encapsulates the password and eigenvalue of a user
	 */
	
	private String password = null;
	private int holdPattern = -1;
	private int pressurePattern = -1;
	private int digraphPattern = -1;
	
	//Currently we only support hold && pressure pattern as eigenvalue
	public Credential(String password, int holdPattern, int pressurePattern){
		this.password = password;
		this.holdPattern = holdPattern;
		this.pressurePattern = pressurePattern;
	}

	public String getPassword() {
		return password;
	}

	public int getHoldPattern() {
		return holdPattern;
	}

	public int getPressurePattern() {
		return pressurePattern;
	}
	
	public int getDigraphPattern() {
		return digraphPattern;
	}
}
