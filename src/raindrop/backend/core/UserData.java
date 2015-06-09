package raindrop.backend.core;

import java.io.Serializable;

public class UserData implements Serializable {
	
	/**
	 * A instance of UserData contains the password and eigenvalue 
	 * of a registered user there maybe one or more UserData instances 
	 * depends on the numbers of registered users. 
	 */
	
	private static final long serialVersionUID = 1L;
	private String password = null;
	private int holdPattern = -1;
	private int pressurePattern = -1;
	private int digraph = -1;
	private int enabledPatternHPD = -1;
	
	//Currently we only support Hold && Pressure as eigenvalue
	public UserData(String password, int enabledPatternHPD, 
			int holdPattern, int pressurePattern){
		this.password = password;
		this.enabledPatternHPD = enabledPatternHPD;
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
	
	public int getDigraph() {
		return digraph;
	}

	public int getEnabledPatternHPD() {
		return enabledPatternHPD;
	}
}
