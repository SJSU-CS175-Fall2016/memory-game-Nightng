package com.example.wilson.memorygame;

import android.content.*;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.*;

public class Game extends AppCompatActivity {
    static final String STATE_SCORE = "playerScore";
    private ArrayList<ImageButton> l;
    private int score;
    private boolean match;
    private boolean flipOnce;
    private ImageButton ibPrevious;
    private ImageButton ibCurrent;
    TypedArray imgs;
    private boolean didStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        Log.i("lifecycle", "onCreate called");
        if (savedInstanceState != null)
                score = savedInstanceState.getInt(STATE_SCORE);
        else
            startNew();
        match = true;
        flipOnce = false;
        Resources res = getResources();
        imgs = res.obtainTypedArray(R.array.imagesArray);
    }

    public void startNew()
    {
        Log.i("lifecycle", "onCreate notSaved");
        score = 0;
        l = new ArrayList<ImageButton>();
        addList(l);
        setRandomImages(l);
        TextView v = (TextView) findViewById(R.id.tvScore);
        v.setText("0");
        didStart = false;
    }

    public void askUser()
    {
        new AlertDialog.Builder(this)
            .setMessage("Do you want to Resume Game or start a New Game?")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startNew();
                    }
                })
                .setNegativeButton("Resume Game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
        .show();
    }

    public void addList(ArrayList<ImageButton> l)
    {
        l.add((ImageButton) findViewById(R.id.imageButton1));
        l.add((ImageButton) findViewById(R.id.imageButton2));
        l.add((ImageButton) findViewById(R.id.imageButton3));
        l.add((ImageButton) findViewById(R.id.imageButton4));
        l.add((ImageButton) findViewById(R.id.imageButton5));
        l.add((ImageButton) findViewById(R.id.imageButton6));
        l.add((ImageButton) findViewById(R.id.imageButton7));
        l.add((ImageButton) findViewById(R.id.imageButton8));
        l.add((ImageButton) findViewById(R.id.imageButton9));
        l.add((ImageButton) findViewById(R.id.imageButton10));
        l.add((ImageButton) findViewById(R.id.imageButton11));
        l.add((ImageButton) findViewById(R.id.imageButton12));
        l.add((ImageButton) findViewById(R.id.imageButton13));
        l.add((ImageButton) findViewById(R.id.imageButton14));
        l.add((ImageButton) findViewById(R.id.imageButton15));
        l.add((ImageButton) findViewById(R.id.imageButton16));
        l.add((ImageButton) findViewById(R.id.imageButton17));
        l.add((ImageButton) findViewById(R.id.imageButton18));
        l.add((ImageButton) findViewById(R.id.imageButton19));
        l.add((ImageButton) findViewById(R.id.imageButton20));
    }

    public void setRandomImages(ArrayList<ImageButton> l)
    {
        String[] img = getResources().getStringArray(R.array.imgArray);
        Integer[] arr1 = new Integer[10];
        Integer[] arr2 = new Integer[10];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i;
            arr2[i] = i;
        }
        int j = 0;
        Collections.shuffle(Arrays.asList(arr1));
        Collections.shuffle(Arrays.asList(arr2));
        for(int i = 0; i < l.size(); i+=2){
            int resID = getResources().getIdentifier(img[arr1[j]], "drawable",  getPackageName());
            l.get(i).setImageResource(resID);
            l.get(i).setTag(resID);
            l.get(i).setVisibility(View.VISIBLE);
            j++;
        }
        j = 0;
        for(int i = 1; i < l.size(); i+=2){
            int resID = getResources().getIdentifier(img[arr2[j]], "drawable",  getPackageName());
            l.get(i).setImageResource(resID);
            l.get(i).setTag(resID);
            l.get(i).setVisibility(View.VISIBLE);
            j++;
        }
        for(int i = 0; i < l.size(); i++) l.get(i).setImageResource(R.drawable.question);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(STATE_SCORE, score);
        Log.i("lifecyle", "SavingInstance called");
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        score = savedInstanceState.getInt(STATE_SCORE);
        Log.i("lifecycle", "onRestore called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecyle", "OnStop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecyle", "onPause called");

    }

    public void onBackPressed() {
        //moveTaskToBack(true);
        Intent intent = new Intent(this, Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        Log.i("lifecycle", "onBackPressed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(didStart)askUser();
//        didStart = true;
        Log.i("lifecyle", "onResume called");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecyle", "onStart called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecyle", "onDestroy called");
    }

    public void incrementScore(View view){
        score++;
        TextView tvScore = (TextView) findViewById(R.id.tvScore);
        tvScore.setText(Integer.toString(score));
        if(score % 10 == 0)
        {
            setRandomImages(l);
        }
        didStart = true;
    }

    public void show(View view) {
        ImageButton ib1 = (ImageButton) findViewById(view.getId());
//        if (ib1.getId() != ibPrevious.getId() || ibPrevious == null) {
            if (!match) {
                ibPrevious.setImageResource(R.drawable.question);
                ibCurrent.setImageResource(R.drawable.question);
                match = true;
            }
            ib1.setImageResource((Integer) ib1.getTag());
            if (!flipOnce) {
                flipOnce = true;
                ibPrevious = (ImageButton) findViewById(view.getId());
            } else {
                if (ib1.getTag().equals(ibPrevious.getTag()) && ib1.getId() != ibPrevious.getId()) {
                    ib1.setVisibility(View.INVISIBLE);
                    ibPrevious.setVisibility(View.INVISIBLE);
                    match = true;
                    incrementScore(view);
                } else {
                    ibCurrent = (ImageButton) findViewById(view.getId());
                    match = false;
                }
                flipOnce = false;
            }
        }
//    }
}
