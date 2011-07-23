package test.osmdroid;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import test.osmdroid.controller.DragableMapSpace;
import test.osmdroid.controller.SnapListener;


import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

public class OsmInDragableActivity extends Activity implements SnapListener {
    private MapView mapView;
	private DragableMapSpace ds;
	private MapController mc;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ds = (DragableMapSpace) findViewById(R.id.drag_space);
        mapView = (MapView) findViewById(R.id.mapview);
		ds.setMapRef(this.mapView);

		// Get the size of the screen, so we can work out 
		// what parts of the screen to set as the map's draggable.
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		// Set the area of the map. This tells the draggable space to ignore
		// drags in the area, so that the map can scroll.
		ds.setMapArea(0, height);
		
 		mc = mapView.getController(); 	
 		// Go to St Pauls
 		mc.setZoom(14);
 		mc.setCenter(new GeoPoint(51.515099,-0.097572));
 		mapView.invalidate();
    }

	@Override
	public void afterSnap(int space) {
		Toast.makeText(this, "Snapped to " + space, Toast.LENGTH_SHORT).show();
	}
}