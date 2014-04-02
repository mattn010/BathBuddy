package com.example.bathbuddy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Map extends MapActivity {

	
private MapView mapView;
private MapController controller;
private String[] locationNames;
private GeoPoint[] locationPoints;
private OverlayItem[] overlayItems;

private Intent i;
private Resources res;
private String dialogValue;

private Drawable defaultIcon;
private Drawable startIcon;
private Drawable destinationIcon;
private Drawable eduIcon;
private Drawable suIcon;
private Drawable houseIcon;
private Drawable myHomeIcon;
private Drawable cashIcon;

List<PopUpItemizedOverlay> localOverlays;
PopUpItemizedOverlay unassignedOverlay;
PopUpItemizedOverlay eduOverlay;
PopUpItemizedOverlay suOverlay;
PopUpItemizedOverlay startOverlay;
PopUpItemizedOverlay destinationOverlay;
PopUpItemizedOverlay cashMachineOverlay;
PopUpItemizedOverlay housingOverlay;
PopUpItemizedOverlay myHouseOverlay;

private locationSet[] locationArray = {
		new locationSet("Eastwood South[32]", 51380851,-2324461, 'h'),
		new locationSet("Eastwood North[1]", 51381009,-2326114, 'h'),
		new locationSet("Marlsborough Court", 51380634,-2324418, 'h'),
		new locationSet("Solsbury Court", 51380132,-2324166, 'h'),
		new locationSet("Woodland Court", 51380707,-2322847, 'h'),
		new locationSet("East Accomodation Centre", 51380376,-2323008),
		new locationSet("East Car Park", 51379793,-2322831),
		new locationSet("Sports Training Village", 51378109,-2324901, 'e'),
		new locationSet("ICIA Arts Theatre", 51378561,-2324783, 'e'),
		new locationSet("Campus Uniconnect Bus Stop", 51379084,-2325186),
		new locationSet("Campus First Bus Stop", 51378879,-2325513),
		new locationSet("East Building", 51379047,-2322836, 'e'),
		new locationSet("University Library", 51379984,-2327943, 'e'),
		new locationSet("1WN", 51380433,-2328587, 'e'),
		new locationSet("1W", 51380125,-2328705, 'e'),
		new locationSet("3WN", 5138044,-2329262, 'e'),
		new locationSet("3W", 51380105,-2329327, 'e'),
		new locationSet("5W", 51380265,-2330679, 'e'),
		new locationSet("5W[2.1]", 51379837,-2330153, 'e'),
		new locationSet("7W", 51380627,-2331183, 'e'),
		new locationSet("9W", 51380272,-2331365, 'e'),
		new locationSet("8W[2.1]", 51379837,-2330153, 'e'),
		new locationSet("8W[2.2]", 51379971,-2331226, 'e'),
		new locationSet("2W", 51379221,-2328640, 'e'),
		new locationSet("6W", 51379228,-2329971, 'e'),
		new locationSet("8W", 51379824,-2330775, 'e'),
		new locationSet("1E", 51379763,-2327385, 'e'),
		new locationSet("3E", 51380245,-2326870, 'e'),
		new locationSet("2E", 51379408,-2327557, 'e'),
		new locationSet("4E", 51379348,-2326816, 'e'),
		new locationSet("6E", 51379281,-2326226, 'e'),
		new locationSet("8E", 51378853,-2326366, 'e'),
		new locationSet("SU", 51379837,-2326741, 's'),
		new locationSet("Cash Machine - SU", 51379783,-2326763, 'c'),
		new locationSet("Cash Machine - Fresh", 51380045,-2329842, 'c'),
		new locationSet("Fresh", 51380205,-2330067)
};

	@Override
	protected boolean isRouteDisplayed(){
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		
		// Overlay Handlers
		localOverlays = new ArrayList<PopUpItemizedOverlay>();
		List<Overlay> mapOverlays = mapView.getOverlays();
		
		//Get Intent and Resources
		i = getIntent();
		res = this.getResources();
		
		//Get Icons
		defaultIcon = res.getDrawable(R.drawable.locico);
		startIcon = res.getDrawable(R.drawable.staico);
		destinationIcon = res.getDrawable(R.drawable.desico);
		eduIcon = res.getDrawable(R.drawable.eduico);
		suIcon = res.getDrawable(R.drawable.stuico);
		cashIcon = res.getDrawable(R.drawable.casico);
		houseIcon = res.getDrawable(R.drawable.houico);
		myHomeIcon = res.getDrawable(R.drawable.myhico);
		
		//Generate Overlays
		unassignedOverlay = createOverlay(defaultIcon,false);
		eduOverlay = createOverlay(eduIcon,false);
		suOverlay = createOverlay(suIcon,false);
		startOverlay = createOverlay(startIcon,true);
		destinationOverlay = createOverlay(destinationIcon,true);
		cashMachineOverlay = createOverlay(cashIcon,false);
		housingOverlay = createOverlay(houseIcon,false);
		myHouseOverlay = createOverlay(myHomeIcon,true);
				
		locationNames = new String[locationArray.length];
		locationPoints = new GeoPoint[locationArray.length];
		overlayItems = new OverlayItem[locationArray.length];
		for(int val = 0; val < locationArray.length; val++){
			locationPoints[val] = new GeoPoint(locationArray[val].getX(),locationArray[val].getY());
			locationNames[val] = locationArray[val].getName();
			overlayItems[val] = new OverlayItem(locationPoints[val], locationNames[val], "");			
		}
		
		String destination = i.getStringExtra("destination");
		String start = i.getStringExtra("start");

		for(int val = 0; val < overlayItems.length; val++){
			 getOverlayFromSet(locationArray[val]).addOverlay(overlayItems[val]);
		}

		if(destination != null){
			int x = Arrays.asList(locationNames).indexOf(destination);
			if(x >= 0){
				destinationOverlay.addOverlay(overlayItems[x]);
				mapOverlays.add(destinationOverlay);
				if(start != null){
					int y = Arrays.asList(locationNames).indexOf(start);
					if(y >= 0){
						startOverlay.addOverlay(overlayItems[y]);
						mapOverlays.add(startOverlay);
						LineOverlay directionalLine = new LineOverlay(mapView.getProjection(),
								locationPoints[x], locationPoints[y]);
						mapOverlays.add(0,directionalLine);
					}
				}
			}
		} else {
			for(PopUpItemizedOverlay overlay: localOverlays){
				if(overlay.size() > 0){
					mapOverlays.add(overlay);
				}
			}
		}

		GeoPoint centerPoint = new GeoPoint(51380091, -2328308);
		

		
		controller = mapView.getController();
		controller.setZoom(19);
		controller.setCenter(centerPoint);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
    {
 
        switch (item.getItemId())
        {
        	case R.id.map_display_items:
        		resetMap();
        		return true;
        	case R.id.map_set_start:
        		inputDialog("Set Waypoint","Start Location:");
        		return true;
        	case R.id.map_set_destination:
        		inputDialog("Set Waypoint","Destination :");
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	public void resetMap(){
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapOverlays.clear();
		for(PopUpItemizedOverlay overlay: localOverlays){
			if(overlay.size() > 0){
				mapOverlays.add(overlay);
			}
		}
		mapView.invalidate();
	}
	
	private void inputDialog(String title, String message){
		AlertDialog.Builder inputBox = new AlertDialog.Builder(this);
		
		inputBox.setTitle(title);
		inputBox.setMessage(message);
		
		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		inputBox.setView(input);
		
		inputBox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialogValue = input.getText().toString();
			}
		});
		
		inputBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialogValue = "";
			}
		});
		inputBox.show();
		
	}
	
	private PopUpItemizedOverlay getOverlayFromSet(locationSet location){
		char iconCode = location.getIcon();
		switch (iconCode) {
			case 'a': return startOverlay;
			case 'b': return destinationOverlay;
			case 'c': return cashMachineOverlay;
			case 'e': return eduOverlay;
			case 'h': return housingOverlay;
			case 'm': return myHouseOverlay;
			case 's': return suOverlay;
			default: return unassignedOverlay;
		}
	}
	
	private PopUpItemizedOverlay createOverlay(Drawable icon, boolean isUnique){
		PopUpItemizedOverlay overlay = new PopUpItemizedOverlay(icon,this);
		if(!isUnique){
			localOverlays.add(overlay);
		}
		return overlay;
	}

}
