package com.example.bathbuddy;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class Timetable extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        
        TextView link = (TextView) findViewById(R.id.button1);
        String linkText = "<a href='http://notlate.co.uk'>Click here to Download your timetable.</a>";
        link.setText(Html.fromHtml(linkText));
        link.setMovementMethod(LinkMovementMethod.getInstance());
        
        TextView link1 = (TextView) findViewById(R.id.button2);
        String linkText1 = "<a href='http://google.com/calendar'>Click here to View your timetable.</a>";
        link1.setText(Html.fromHtml(linkText1));
        link1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_timetable, menu);
        return true;
    }
}
