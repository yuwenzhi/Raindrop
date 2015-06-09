package raindrop.global;

public class Constants {
		
	public static final String SERIALIZATION_FILE_PATH = "C://user_data.ser";
	public static final String STRANGER_TRYING_UNLOCK_RECORD_FILE = "Stranger_Trying_Unlock.log";
	
	public static final String APP_MAIN_LOG_NAME = "strangerTryingUnlock";
	public static final String APP_MAIN_LOG_FILE = "C://stranger.log";

	class HoldPattern{
		/**
		 * Use 4-digit binary to present Hold pattern,
		 * 'S' stands for "short" while 'L' stands for "long" 
		 */
		public static final int SSSS = 0;
		public static final int SSSL = 1;
		public static final int SSLS = 2;
		public static final int SSLL = 3;
		public static final int SLSS = 4;
		public static final int SLSL = 5;
		public static final int SLLS = 6;
		public static final int SLLL = 7;
		public static final int LSSS = 8;
		public static final int LSSL = 9;
		public static final int LSLS = 10;
		public static final int LSLL = 11;
		public static final int LLSS = 12;
		public static final int LLSL = 13;
		public static final int LLLS = 14;
		public static final int LLLL = 15;
	}
	
	class PressurePattern{
		/**
		 * Use 4-digit binary to present Pressure pattern,
		 * 'L' stands for "lightly" while 'H' stands for "heavily"
		 */
		public static final int LLLL = 0;
		public static final int LLLH = 1;
		public static final int LLHL = 2;
		public static final int LLHH = 3;
		public static final int LHLL = 4;
		public static final int LHLH = 5;
		public static final int LHHL = 6;
		public static final int LHHH = 7;
		public static final int HLLL = 8;
		public static final int HLLH = 9;
		public static final int HLHL = 10;
		public static final int HLHH = 11;
		public static final int HHLL = 12;
		public static final int HHLH = 13;
		public static final int HHHL = 14;
		public static final int HHHH = 15;
	}

	class EnabledPatternHPD{
		/**
		 * Use 4-digit binary to present enabled eigenvalue types,
		 * 'D' stands for "disabled" while 'E' stands for "enabled".
		 */
		public static final int DDD = 0;
		public static final int DDE = 1;
		public static final int DED = 2;
		public static final int DEE = 3;
		public static final int EDD = 4;
		public static final int EDE = 5;
		public static final int EED = 6;
		public static final int EEE = 7;
	}
	
}
