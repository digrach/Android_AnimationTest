package rach.dig.android_animationtest;


import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private MyParticleThread myParticleThread;
	private Context context;


	public MySurfaceView(Context context) {
		super(context);
		Log.d(">>>>>>>>>>>>>>>>","MySurfaceView.construct");
		this.context = context;
		getHolder().addCallback(this);
	}

	public void resume() { 
		Log.d(">>>>>>>>>>>>>>>>","MySurfaceView.resume");
		// Internally kicks off life cycle
		// Do not attempt to access the surface here as it has not been created yet.
	}  
	public void pause() {      
		Log.d(">>>>>>>>>>>>>>>>","MySurfaceView.pause");
		myParticleThread.setRunning(false);                      
		while(true) {
			try {
				myParticleThread.join();
				break;
			} catch (InterruptedException e) {
				// retry
			}
		}
	}       

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(">>>>>>>>>>>>>>>>","MySurfaceView.surfaceCreated");
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.d(">>>>>>>>>>>>>>>>","MySurfaceView.surfaceChanged");
		
		((AppSettingz)context.getApplicationContext()).setOrientationDimensions(width, height);

		if (myParticleThread==null) {  
			Log.d(">>>>>>>>>>>>>>>>","MySurfaceView.surfaceChanged creating new myParticleThread");
			
			((AppSettingz)context.getApplicationContext()).initialiseParticleManager();
			ParticleManager particleMangager = ((AppSettingz)context.getApplicationContext()).getParticleManager();
			
			myParticleThread = new MyParticleThread(holder,this.getContext(), particleMangager);  
			myParticleThread.setRunning(true);  
			myParticleThread.start();  
		}  
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(">>>>>>>>>>>>>>>>","MySurfaceView.surfaceDestroyed");

		boolean retry = true;  
		myParticleThread.setRunning(false);  
		while (retry) {  
			try {  
				myParticleThread.join();  
				retry = false;  
			} catch (InterruptedException e) {}  
		}  
	}


}
