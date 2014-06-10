package rach.dig.android_animationtest;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

public class AppSettingz extends Application {

	public static final String PREFS_NAME = "myprefs";
	private SharedPreferences myprefs;

	private static String PREF_NAME_USERNAME = "username";
	private static String PREF_PRINT_LOG = "printlog";

	public static boolean printLogs;

	private ParticleManager particleManager;
	private OrientationManager oManager = new OrientationManager();

	public void setOrientationDimensions(int width, int height) {
		Log.d("-----AppSettingz------- > ", "AppSettingz.setOrientationDimensions ");

		oManager.setCurrentWidth(width);
		oManager.setCurrentHeight(height);
	}

	public OrientationManager getOrientationManager() {
		return oManager;
	}

	public void initialiseParticleManager() {
		Log.d("-----AppSettingz------- > ", "AppSettingz.initialiseParticleManager ");

		if (particleManager == null) {

			particleManager = new ParticleManager(oManager.getCurrentWidth(), oManager.getCurrentHeight(), oManager.getCurrentWidth(), oManager.getCurrentHeight());

		}

		//		if (particleManager == null) {
		//
		//			particleManager = new ParticleManager(oManager.getCurrentWidth(), oManager.getCurrentHeight(), oManager.getCurrentWidth(), oManager.getCurrentHeight());
		//
		//		} else if (oManager.getCurrentHeight() < oManager.getPreviousHeight()) {
		//
		//			particleManager.setSpawnFieldWidth(oManager.getCurrentWidth());
		//			particleManager.setSpawnFieldHeight(oManager.getCurrentHeight());
		//			particleManager.setCurrentScreenHeight(oManager.getPreviousHeight());
		//		}
		//		} else if (oManager.getCurrentHeight() > oManager.getPreviousHeight()) {
		//
		//			particleManager.setSpawnFieldWidth(oManager.getCurrentWidth());
		//			particleManager.setSpawnFieldHeight(oManager.getCurrentHeight());
		//			particleManager.setCurrentScreenHeight(oManager.getCurrentHeight());
		//
		//		}

		//		if (particleManager == null) {
		//			
		//			particleManager = new ParticleManager(oManager.getCurrentWidth(), oManager.getCurrentHeight(),oManager.getCurrentHeight());
		//			
		//		} else if (oManager.getCurrentHeight() < oManager.getPreviousHeight()) {
		//			
		//			particleManager.setSpawnFieldWidth(oManager.getCurrentWidth());
		//			particleManager.setSpawnFieldHeight(oManager.getCurrentHeight());
		//			particleManager.setCurrentScreenHeight(oManager.getPreviousHeight());
		//			
		//		} else if (oManager.getCurrentHeight() > oManager.getPreviousHeight()) {
		//			
		//			particleManager.setSpawnFieldWidth(oManager.getCurrentWidth());
		//			particleManager.setSpawnFieldHeight(oManager.getCurrentHeight());
		//			particleManager.setCurrentScreenHeight(oManager.getCurrentHeight());
		//			
		//		}

		//		if (particleManager == null) {
		//			particleManager = new ParticleManager(oManager.getCurrentWidth(), oManager.getCurrentHeight(),oManager.getCurrentHeight());
		//		} else if (oManager.getCurrentHeight() < oManager.getPreviousHeight()) {
		//			particleManager.setSpawnFieldWidth(oManager.getCurrentWidth());
		//			particleManager.setSpawnFieldHeight(oManager.getPreviousHeight());
		//			particleManager.setCurrentScreenHeight(oManager.getCurrentHeight());
		//		} else if (oManager.getCurrentHeight() > oManager.getPreviousHeight()) {
		//			particleManager.setSpawnFieldWidth(oManager.getCurrentWidth());
		//			particleManager.setSpawnFieldHeight(oManager.getCurrentHeight());
		//			particleManager.setCurrentScreenHeight(oManager.getCurrentHeight());
		//		}


	}

	public ParticleManager getParticleManager() {
		return particleManager;
	}

	public AppSettingz() {

	}

	public enum PREF_KEY_NAMES {
		USERNAME, PRINTLOG
	}

	public void setSettings(PREF_KEY_NAMES prefKeyName, Object prefKeyValue) {

		if (prefKeyValue != null) {
			myprefs = getSharedPreferences(PREFS_NAME,0);
			SharedPreferences.Editor editor = myprefs.edit();


			if (prefKeyName == PREF_KEY_NAMES.USERNAME) {
				editor.putString(PREF_NAME_USERNAME, prefKeyValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.PRINTLOG) {
				editor.putString(PREF_PRINT_LOG, prefKeyValue.toString());
				printLogs = Boolean.parseBoolean(prefKeyValue.toString());
			}

			editor.commit();
		}

	}

	public Object getSettings(PREF_KEY_NAMES prefKeyName, Object defaultValue) {

		if (prefKeyName != null) {
			myprefs = getSharedPreferences(PREFS_NAME,0);

			if (prefKeyName == PREF_KEY_NAMES.USERNAME) {
				return myprefs.getString(PREF_NAME_USERNAME, defaultValue.toString());
			}


			if (prefKeyName == PREF_KEY_NAMES.PRINTLOG) {
				return myprefs.getString(PREF_PRINT_LOG, defaultValue.toString());
			}

		}
		return null;
	}

}
