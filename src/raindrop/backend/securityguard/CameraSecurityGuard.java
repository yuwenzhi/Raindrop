package raindrop.backend.securityguard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import raindrop.backend.core.UserData;
import raindrop.global.Utils;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.util.Log;

public class CameraSecurityGuard extends SecurityGuard {

	private final String CLASS_NAME = CameraSecurityGuard.class.toString();
	private int cameraId = 0;
	private Camera camera = null;
	
	public CameraSecurityGuard() {
		init();
	}
	
	@Override
	public void init() {
		String METHOD_NAME = "init";
		String DEBUG_TAG = Utils.assembleLogDebugTag(this.CLASS_NAME, METHOD_NAME);
		cameraId = findFrontFacingCamera();
		if(-1 == cameraId){
			Log.e(DEBUG_TAG, "Can not find any camera on this device!");
			return;
		}else{
			camera = Camera.open(cameraId);
		}
	}
	
	private int findFrontFacingCamera(){
		int camId = -1;
		int numberOfCameras = Camera.getNumberOfCameras();
		for(int i = 0; i < numberOfCameras; i++){
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if(info.facing == CameraInfo.CAMERA_FACING_FRONT){
				camId = i;
				break;
			}
		}
		return camId;
	}

	@Override
	public void record(UserData strangerData) {
		String METHOD_NAME = "record";
		String DEBUG_TAG = Utils.assembleLogDebugTag(this.CLASS_NAME, METHOD_NAME); 
		if(null == camera){
			Log.e(DEBUG_TAG, " Camera is not initialized!");
			return;
		}
		camera.takePicture(null, null, new PictureSaver());
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}


class PictureSaver implements PictureCallback {
	
	private final String CLASS_NAME = PictureSaver.class.toString();
	
	public void onPictureTaken(byte[] data, Camera camera) {
		String METHOD_NAME = "onPictureTaken";
		String DEBUG_TAG = Utils.assembleLogDebugTag(this.CLASS_NAME, METHOD_NAME);
		File SDRootDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		if(!SDRootDir.exists() && !SDRootDir.mkdirs()){
			Log.e(DEBUG_TAG, "Failed to create directory for saving stranger picture on SD card!");
			return;
		}
		String date = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		String pictureFileName = "Stranger_" + date + ".jpg";
		File pictureFile = new File(SDRootDir + File.separator + pictureFileName);
		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			Log.d(DEBUG_TAG, "Taking picture of stranger: " + pictureFileName);
			fos.write(data);
			fos.close();
		} catch (FileNotFoundException e) {
			Log.d(DEBUG_TAG, e.getMessage());
		} catch (IOException e) {
			Log.d(DEBUG_TAG, e.getMessage());
		}
	}
	
}
