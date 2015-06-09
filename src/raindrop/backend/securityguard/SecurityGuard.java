package raindrop.backend.securityguard;

import raindrop.backend.core.UserData;

public abstract class SecurityGuard {
	public abstract void init();
	public abstract void record(UserData strangerData);
	public abstract void tearDown();
}
