package rach.dig.android_animationtest;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceHolder;

public class MyParticleThread extends Thread {
    
	private ParticleManager particleMangager;
	private SurfaceHolder holder;

	private int countDownTillNextParticle = 5;
	private boolean running = false;  
	private final int refresh_rate=16;    
	private Context context;
	
	public MyParticleThread(SurfaceHolder holder, Context context, ParticleManager particleManager) {  
		Log.d("------------ > MyParticleThread ", " construct");
		this.holder = holder;  
		this.context = context;
		this.particleMangager = particleManager;
	}  

//	public MyParticleThread(SurfaceHolder holder, Context context) {  
//		Log.d("------------ > MyParticleThread ", " construct");
//		this.holder = holder;  
//		this.context = context;
//	}  

//	public void setParticleManager() {  
//		((AppSettingz)context.getApplicationContext()).initialiseParticleManager();
//		particleMangager = ((AppSettingz)context.getApplicationContext()).getParticleManager();
//	}  

	@Override  
	public void run() { 
		Canvas canvas = null;
		while(running == true) {
			if(!holder.getSurface().isValid())
				continue;  

			try {  
				canvas = holder.lockCanvas();  

				synchronized (holder) {  
					int width = ((AppSettingz)context.getApplicationContext()).getOrientationManager().getCurrentWidth();
					int height = ((AppSettingz)context.getApplicationContext()).getOrientationManager().getCurrentHeight();

					draw(canvas,particleMangager.updateAllParticles(width,height));           
				}  
			}  
			finally {  
				if (canvas != null) {  
					holder.unlockCanvasAndPost(canvas);  
				}  
			}  
			try {   
				Thread.sleep(refresh_rate-5);   
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			} 

			if (countDownTillNextParticle == 0) {
				countDownTillNextParticle = 5 + (int)(Math.random()*15);
				particleMangager.addParticle();
			}
			countDownTillNextParticle --;

		}
	}

	private void draw(Canvas canvas, List<Particle> particles)  {  

		canvas.drawColor(Color.BLACK);  
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Style.FILL_AND_STROKE);
		for (int i=0; i<particles.size(); i++){   
			Particle p = particles.get(i);
			paint.setColor(Color.HSVToColor(128,p.getColor()));
			canvas.drawCircle((float)p.getPosX(),  
					(float)p.getPosY(),   
					(float)p.getSize(),  
					paint);  
		}  
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
