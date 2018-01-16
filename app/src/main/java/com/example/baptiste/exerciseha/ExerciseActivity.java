package com.example.baptiste.exerciseha;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.baptiste.exerciseha.ThematicActivity.EXTRA_ACTID;
import static com.example.baptiste.exerciseha.ThematicActivity.EXTRA_TRIAL;
import static com.example.baptiste.exerciseha.ThematicActivity.EXTRA_VOCALIST;


public class ExerciseActivity extends AppCompatActivity {


    private ImageView imgCentre;

    public ArrayList<ImageView> iconesList;

    private int listIter = 0;
    private int trialNumber; // 5 for exercise       2 for boss exercise

    private int exID;
    //private int exID = R.layout.activity_exercise;
    //private int exID = R.layout.activity_exboss;

    private ArrayList<vocaWord> vocaList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get back data from thematic activity
        Intent intent = getIntent();
        exID = intent.getIntExtra(EXTRA_ACTID, 0);
        trialNumber = intent.getIntExtra(EXTRA_TRIAL, 0);
        vocaList = intent.getParcelableArrayListExtra(EXTRA_VOCALIST);

        setContentView(exID);

        imgCentre = findViewById(R.id.imageCentre);

        iconesList = new ArrayList<>();
        for(int i=0 ; i<vocaList.size() ; i++){
            iconesList.add((ImageView) findViewById(vocaList.get(i).getImageViewID()));
            iconesList.get(i).setImageResource(vocaList.get(i).getImageShadID());
        }

    }


    public void onImageX(View view) {
        listIter = Integer.parseInt(view.getTag().toString());
        imgCentre.setImageResource(vocaList.get(listIter).getImageID());
        imgCentre.setTag("ok");
        if(vocaList.get(listIter).getSuccess() < 1)
            iconesList.get(listIter).setImageResource(vocaList.get(listIter).getImageID());
    }


    // launch vocal recognition
    public void onStartClick(View view){

        if(view.getId() == R.id.imageCentre && imgCentre.getTag().toString().equals("ok")) {

            int vocaTrial = vocaList.get(listIter).getVocaTrial();

            // if less than five tries and no success ...
            // ... then try !!
            if (vocaTrial < trialNumber && vocaList.get(listIter).getSuccess() < 1) {

                // play word sound
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), vocaList.get(listIter).getSoundID());
                mp.start();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    android.util.Log.d("HA", ex.toString());
                }

                // get user response
                getSpeechInput();
                vocaList.get(listIter).setVocaTrial(vocaTrial +1);
            }
            // if more than 5 tries but no success ...
            // ... then set icone to RED and go on to the next picture
            else if(vocaTrial >= trialNumber && vocaList.get(listIter).getSuccess() < 1){

                Toast.makeText(ExerciseActivity.this,
                        "Sorry impossible to try more than " + trialNumber + " times",
                        Toast.LENGTH_SHORT).show();

                // set icone to shadow icone in RED
                iconesList.get(listIter).setImageResource(vocaList.get(listIter).getImageShadID());
                iconesList.get(listIter).setColorFilter(Color.RED);

                // go on to the next picture
                listIter = (listIter +1)%vocaList.size();
                onImageX(findViewById(vocaList.get(listIter).getImageViewID()));
            }
            // if already succussful ...
            // ... then go on to the next picture
            else{

                Toast.makeText(ExerciseActivity.this,
                        "already passed",
                        Toast.LENGTH_SHORT).show();
                // go on to the next picture
                listIter = (listIter +1)%6;
                onImageX(findViewById(vocaList.get(listIter).getImageViewID()));
            }
        }
    }



    public void getSpeechInput(){

        // init the vocal recognition
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB"); // "en-GB" "en-AU" Locale.getDefault()
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something !");

        // start the vocal recognition
        try {
            startActivityForResult(intent, 10);
        }
        catch(ActivityNotFoundException a) {
            Toast.makeText(ExerciseActivity.this,
                    "Sorry your device doesn't support speech language",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent intent){

        super.onActivityResult(request_code, result_code, intent);

        switch(request_code)
        {
            case 10: if(result_code == RESULT_OK && intent != null)
            {
                // retrieve the speech-to-text String
                ArrayList<String> result = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                // if it matchs with the word to repeat
                if(result.get(0).equals(vocaList.get(listIter).getWord() ) ){

                    vocaList.get(listIter).setSuccess(1);
                    Toast.makeText(ExerciseActivity.this,
                            "It is good",
                            Toast.LENGTH_SHORT).show();

                    iconesList.get(listIter).setImageResource(vocaList.get(listIter).getImageShadID());
                    iconesList.get(listIter).setColorFilter(Color.GREEN);
                }
                else {
                    vocaList.get(listIter).setSuccess(0);
                    Toast.makeText(ExerciseActivity.this,
                            "It Iznogoude. You said : " + result.get(0),
                            Toast.LENGTH_SHORT).show();
                }
            }
                break;
        }
    }
}


