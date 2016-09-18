package com.example.wilson.memorygame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
    }

    public void goToGame(View view)
    {
        Intent intent = new Intent(this, Game.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void goToRules(View view)
    {
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
