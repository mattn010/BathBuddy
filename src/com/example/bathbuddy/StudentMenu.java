package com.example.bathbuddy;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class StudentMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
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
    
    public void goToMap(View view) {
    	Intent intent = new Intent(this, Map.class) ;
    	startActivity(intent) ;
    }
    
    public void goToTimetable(View view) {
    	Intent intent = new Intent(this, Timetable.class) ;
    	startActivity(intent) ;
    }
    private void pageJump() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPrefs.getString("prefUserType", "NULL").equals("2"))
        {
        	Intent intent = new Intent(this, TourActivity.class) ;
        	startActivity(intent) ;
        }
    }
}
