package rach.dig.android_animationtest;


import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder holder;
	private MyParticleThread myParticleThread;


	public MySurfaceView(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if (myParticleThread==null){  
			myParticleThread = new MyParticleThread(holder);  
			myParticleThread.setRunning(true);  
			myParticleThread.setSurfaceSize(width, height);  
			myParticleThread.start();  
		}  
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
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
