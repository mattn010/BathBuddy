package com.example.bathbuddy;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TourActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.bathbuddy.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour);
		// Show the Up button in the action bar.
		setupActionBar();
		Spinner spinner = (Spinner) findViewById(R.id.tours_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.tours, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		pageJump();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
 
        case R.id.menu_settings:
            Intent i = new Intent(this, UserSettingActivity.class);
            startActivityForResult(i, 1);
            break;
        }
        return true;
    }
    
    private void pageJump() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPrefs.getString("prefUserType", "NULL").equals("1"))
        {
        	Intent intent = new Intent(this, StudentMenu.class) ;
        	startActivity(intent) ;
        }
    }
    
    public void goToOpenDay(View view) {
    	Intent intent = new Intent(this, Map.class) ;
    	intent.putExtra(EXTRA_MESSAGE, "1");
    	startActivity(intent) ;
    }

}
