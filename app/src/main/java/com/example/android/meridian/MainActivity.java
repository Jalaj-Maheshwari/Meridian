package com.example.android.meridian;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.meridian.chat.ChatActivity;
import com.example.android.meridian.elevation.ElevationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pwa = (Button) findViewById(R.id.meridian_pwa);
        pwa.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                Uri meridianPwaUri = Uri.parse("https://weather-by-meridian.firebaseapp.com");

                // Create a new intent to view the PWA URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, meridianPwaUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        Button calamity = (Button) findViewById(R.id.natural_calamity);
        calamity.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                // Create a new intent to open the {@link NumbersActivity}
                Intent earthquakeIntent = new Intent(MainActivity.this, com.example.android.meridian.calamity.EarthquakeActivity.class);

                // Start the new activity
                startActivity(earthquakeIntent);
            }
        });

        Button elevation = (Button) findViewById(R.id.elevation);
        elevation.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                // Create a new intent to open the {@link NumbersActivity}
                Intent elevationIntent = new Intent(MainActivity.this, ElevationActivity.class);

                // Start the new activity
                startActivity(elevationIntent);
            }
        });

        FloatingActionButton chat = (FloatingActionButton) findViewById(R.id.fab_chat);
        chat.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                // Create a new intent to open the {@link NumbersActivity}
                Intent chatIntent = new Intent(MainActivity.this, ChatActivity.class);

                // Start the new activity
                startActivity(chatIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sos_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sos_button) {
            Intent sosIntent = new Intent(this, com.example.android.meridian.sos.SOSActivity.class);
            startActivity(sosIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

