package com.example.android.music;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main class responsible for the landing page of the app
 */
public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    int savedPageNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(MusicActivity.STORENAME, MODE_PRIVATE);
        savedPageNo = preferences.getInt(MusicActivity.KEYNAME, 0);

        Button enterBtn = (Button) findViewById(R.id.btn_enter);
        assert enterBtn != null;
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });

        Button resumeBtn = (Button) findViewById(R.id.btn_resume);
        assert resumeBtn != null;
        resumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicActivity.startYourself(MainActivity.this, savedPageNo);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        savedPageNo = preferences.getInt(MusicActivity.KEYNAME, 0);
    }
}
