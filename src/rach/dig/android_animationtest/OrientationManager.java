package rach.dig.android_animationtest;

public class OrientationManager {

	private int previousWidth;
	private int previousHeight;

	private int currentWidth;
	private int currentHeight;

	public OrientationManager() {

	}

	public int getPreviousWidth() {
		return previousWidth;
	}

	public void setPreviousWidth(int previousWidth) {
		this.previousWidth = previousWidth;
	}

	public int getPreviousHeight() {
		return previousHeight;
	}

	public void setPreviousHeight(int previousHeight) {
		this.previousHeight = previousHeight;
	}

	public int getCurrentWidth() {
		return currentWidth;
	}

	public void setCurrentWidth(int currentWidth) {
		this.previousWidth = this.currentWidth;
		this.currentWidth = currentWidth;
	}

	public int getCurrentHeight() {
		return currentHeight;
	}

	public void setCurrentHeight(int currentHeight) {
		this.previousHeight = this.currentHeight;
		this.currentHeight = currentHeight;
	}



}
