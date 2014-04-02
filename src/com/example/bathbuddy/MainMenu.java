package com.example.bathbuddy;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.SharedPreferences;

public class MainMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        pageJump();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
    
    public void goToStudent(View view) {
    	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	Editor editor = sharedPrefs.edit();
    	editor.putString("prefUserType", "1");
    	editor.commit();
    	Intent intent = new Intent(this, StudentMenu.class) ;
    	startActivity(intent) ;
    }
    
    public void goToTour(View view) {
    	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	Editor editor = sharedPrefs.edit();
    	editor.putString("prefUserType", "2");
    	editor.commit();
    	Intent intent = new Intent(this, TourActivity.class) ;
    	startActivity(intent) ;
    }
    
    private void pageJump() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPrefs.getString("prefUserType", "NULL").equals("1"))
        {
        	Intent intent = new Intent(this, StudentMenu.class) ;
        	startActivity(intent) ;
        }
        if (sharedPrefs.getString("prefUserType", "NULL").equals("2"))
        {
        	Intent intent = new Intent(this, TourActivity.class) ;
        	startActivity(intent) ;
        }
    }
}
