package raindrop.backend.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import raindrop.global.Constants;


public class UserManager {
	/**
	 * UserManager is a singleton which manages user data
	 */
	
	private static final UserManager INSTANCE = new UserManager();
	
	private UserManager(){
		registeredUserDataList = new ArrayList<UserData>();
		try {
			initUserDataListFromDiskFile(Constants.SERIALIZATION_FILE_PATH);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserManager getInstance(){
		return INSTANCE;
	}
	
	private ArrayList<UserData> registeredUserDataList = null;
	
	private void initUserDataListFromDiskFile(String filePath) throws IOException, ClassNotFoundException{
		File inFile = new File(filePath);
		if(!inFile.exists()){
			return;
		}
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
		UserData data = null;
		//Use null object as flag
		while((data = (UserData)objectInputStream.readObject()) != null){
			registeredUserDataList.add(data);
		}
		objectInputStream.close();
	}
	
	public void addUserData(UserData data) {
		registeredUserDataList.add(data);
		try {
			saveUserDataListToDiskFile(Constants.SERIALIZATION_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveUserDataListToDiskFile(String filePath) throws IOException {
		File file = new File(filePath);
		if(file.exists()){
			//We may need to do some check here to make sure the file has been deleted
			file.delete();
		}
		ObjectOutputStream objectOutputStream = 
				new ObjectOutputStream(new FileOutputStream(filePath));
		for(UserData data : registeredUserDataList){
			objectOutputStream.writeObject(data);
		}
		//Write a null object at the end of file as a flag
		objectOutputStream.writeObject(null);
		objectOutputStream.close();
	}
	
	public ArrayList<UserData> getUserDataList(){
		return registeredUserDataList;
	}
	
}
