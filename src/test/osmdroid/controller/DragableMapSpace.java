package test.osmdroid.controller;

import org.osmdroid.views.MapView;

import test.osmdroid.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * This is a special DragableSpace that does not intercept
 * touch events when the touch area is over the map.
 * 
 *  This means that the map is still dragable.
 *  The map drag area is passed in using setMapArea
 *  method.
 *
 */
public class DragableMapSpace extends DragableSpace {

	private static final String TAG = "DragableMapSpace";
	private MapView mOsmv;
	private int topY, bottomY, bar, difference = 0;

	public DragableMapSpace(Context context) {
		super(context);
	}
	
	public DragableMapSpace(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/*
	 * Override onMeasure, so that we can mark as area of the map 
	 * as "non-dragable", so that the dragable space can get a chance
	 * to get the drag event. 
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		View mapBottomBarBgView = (View) findViewById(R.id.dragbar);
		bar = mapBottomBarBgView.getHeight();
		// Make the drag space area bigger for large fingers
		difference = (int) (bottomY - (bar * 1.5));
		Log.d(TAG, "################################################################");
		Log.d(TAG, "height: " + bottomY + "  bar: " + bar + " diff: " + difference);
		Log.d(TAG, "################################################################");
	}
	
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	Log.d(TAG, "mCurrent is " + mCurrentScreen);
    	if (this.mCurrentScreen == 1 
    			&& event.getY() >= topY && event.getY() <= difference){ 
    		Log.d(TAG, "touch received inside Map Area");
    		return mOsmv.onTouchEvent(event);
    	}
    	Log.d(TAG, "touch received outside Map Area");
    	return super.onTouchEvent(event);
    }

	public void setMapRef(MapView mOsmv) {
		this.mOsmv = mOsmv;
	}

	public void setMapArea(int topYaxis, int bottomYaxis) {
		this.topY = topYaxis;
		this.bottomY = bottomYaxis;
		Log.d(TAG, "Height: " + topY + " barHeight: " + bottomYaxis);
	}
}
