package raindrop.backend.core;

import java.util.ArrayList;

public class CredentialChecker {
	/**
	 * When someone trying to unlock the screen, 
	 * we collect his credential(password & eigenvalue) and compared with 
	 * the UserData instances of the registered users to identify him.
	 */
	
	private ArrayList<UserData> targetUserDataList = null;
	
	public CredentialChecker(ArrayList<UserData> targetUserDataList){
		this.targetUserDataList = targetUserDataList;
	}
	
	class StatusCode{
		private static final int SUCCESS = 0;
		private static final int PASSWD_FAILED = 1;
		private static final int PATTERN_FAILED = 2;
		private static final int BOTH_FAILED = 3;
	}
	
	//Compare input credential with each registered user's UserData instance
	//to identify current input user
	public boolean verifyUserCredential(Credential cred){
		int enabledEigen = -1;
		for(UserData targetUserData : targetUserDataList){
			enabledEigen = targetUserData.getEnabledPatternHPD();
			if(checkCredential(cred, enabledEigen)){
				if(StatusCode.SUCCESS == attackTargetUserWithCurrentUserCredential(cred)){
					return true;
				}
			}
		}
		return false;
	}
	
	protected boolean checkCredential(Credential cred, int enabledEigen){
		
		String password = cred.getPassword();
		if(null == password || password.isEmpty() || password.length() != 4){
			return false;
		}
		
		int holdPattern = cred.getHoldPattern();
		int pressurePattern = cred.getPressurePattern();
		switch(enabledEigen){
		case 0:
			return !(isPatternSetProperly(holdPattern) || isPatternSetProperly(pressurePattern));
		case 2:
			return !isPatternSetProperly(holdPattern) && isPatternSetProperly(pressurePattern);
		case 4:
			return isPatternSetProperly(holdPattern) && !isPatternSetProperly(pressurePattern);
		case 6:
			return isPatternSetProperly(holdPattern) && isPatternSetProperly(pressurePattern);
		default:
			return false;
		}
	}
	
	protected boolean isPatternSetProperly(int pattern){
		if(pattern >= 0 && pattern <= 15){
			return true;
		}
		return false;
	}
	
	protected int attackTargetUserWithCurrentUserCredential(Credential cred){
		int ret = -1;
		String password = cred.getPassword();
		int holdPattern = cred.getHoldPattern();
		int pressurePattern = cred.getPressurePattern();
		boolean passwordVerified = false;
		boolean patternVerified = false;
		boolean holdPatternVerified = false;
		boolean pressurePatternVerified = false;
		
		for(UserData targetUserData : targetUserDataList){
			if(password.equals(targetUserData.getPassword())){
				passwordVerified = true;
			}
			
			if(holdPattern == targetUserData.getHoldPattern()){
				holdPatternVerified = true;
			}
			
			if(pressurePattern == targetUserData.getPressurePattern()){
				pressurePatternVerified = true;
			}
			
			patternVerified = holdPatternVerified && pressurePatternVerified;
			if(passwordVerified){
				if(patternVerified){
					ret = StatusCode.SUCCESS;
				}else{
					ret = StatusCode.PATTERN_FAILED;
				}
			}else{
				if(patternVerified){
					ret = StatusCode.PASSWD_FAILED;
				}else{
					ret = StatusCode.BOTH_FAILED;
				}
			}
		}
		return ret;
	}
}
