package raindrop.backend.securityguard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import raindrop.backend.core.UserData;
import raindrop.global.Constants;
import raindrop.global.Utils;

import android.os.Environment;
import android.util.Log;

public class MessageSecurityGuard extends SecurityGuard {

	private final String CLASS_NAME = MessageSecurityGuard.class.toString();
	private FileWriter strangerRecord = null;
	
	public MessageSecurityGuard(){
		init();
	}
	
	@Override
	public void init() {
		String METHOD_NAME = "init";
		String DEBUG_TAG = Utils.assembleLogDebugTag(this.CLASS_NAME, METHOD_NAME);
		try {
			File SDRootDir = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_DOWNLOADS);
			if(!SDRootDir.exists() && !SDRootDir.mkdirs()){
				Log.e(DEBUG_TAG, 
						"Failed to create directory to save stranger message on SD card!");
				return;
			}
			strangerRecord = 
					new FileWriter(SDRootDir + File.separator + 
							Constants.STRANGER_TRYING_UNLOCK_RECORD_FILE, true);
		} catch (IOException e) {
			Log.d(this.CLASS_NAME + "_" + METHOD_NAME, e.getMessage());
		}
	}

	@Override
	public void record(UserData strangerData) {
		String METHOD_NAME = "record";
		String DEBUG_TAG = Utils.assembleLogDebugTag(this.CLASS_NAME, METHOD_NAME);
		if(null == strangerRecord){
			Log.e(DEBUG_TAG, "strangerRecord is not initialized!");
			return;
		}		
		
		String date = new SimpleDateFormat("yyyymmddhhmmss", Locale.CHINA).format(new Date());
		String strangerRecordMessage = new String(
				"有陌生人试图解锁您的手机!" + 
				" 陌生人使用的密码：" + strangerData.getPassword() +
				" 陌生人Hold节奏：" + strangerData.getHoldPattern() +
				" 陌生人Pressure节奏: " + strangerData.getPressurePattern());
		
		try {
			Log.d(DEBUG_TAG, "Recording stranger: " + strangerRecordMessage);
			strangerRecord.write(date + " " + strangerRecordMessage + '\n');
			strangerRecord.flush();
		} catch (IOException e) {
			Log.d(DEBUG_TAG, e.getMessage());
		}
	}

	@Override
	public void tearDown() {
		String METHOD_NAME = "tearDown";
		String DEBUG_TAG = Utils.assembleLogDebugTag(this.CLASS_NAME, METHOD_NAME);
		try {
			strangerRecord.close();
		} catch (IOException e) {
			Log.e(DEBUG_TAG, "Failed to tear down MessageSecurityGuard: " + e.getMessage());
		}
	}

}
