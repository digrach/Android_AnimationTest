package rach.dig.android_animationtest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	PlaceholderFragment placeHolderFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onCreate");

		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment(),"canvasfrag").commit();
		}
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onStart");
		placeHolderFragment = (PlaceholderFragment)getFragmentManager().findFragmentByTag("canvasfrag");
	}
	@Override
	public void onResume() {
		super.onResume();
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onResume");

		MySurfaceView mySurfaceView = (MySurfaceView)placeHolderFragment.getView();
		mySurfaceView.resume();
	}
	@Override
	public void onPause() {
		super.onPause();
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onPause");

		MySurfaceView mySurfaceView = (MySurfaceView)placeHolderFragment.getView();
		mySurfaceView.pause();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onDestroy");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onCreateOptionsMenu");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onOptionsItemSelected");
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Log.d(">>>>>>>>>>>>>>>>","Fragment.onCreateView");

			setRetainInstance(true);
			return new MySurfaceView(getActivity());
		}
	}

}
