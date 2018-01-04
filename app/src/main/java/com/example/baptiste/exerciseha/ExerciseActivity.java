package com.example.baptiste.exerciseha;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;



public class ExerciseActivity extends AppCompatActivity {


    private ImageView imgCentre;

    public ArrayList<ImageView> iconesList;

    public int listIter = 0;
    public ArrayList<vocaWord> vocaList;
    {
        vocaList = new ArrayList<vocaWord>() {{
            add(new vocaWord("leg", R.drawable.leg, R.raw.audio_leg));
            add(new vocaWord("neck", R.drawable.neck, R.raw.audio_neck));
            add(new vocaWord("shoulders", R.drawable.shoulders, R.raw.audio_shoulders));
            add(new vocaWord("stomach", R.drawable.stomach, R.raw.audio_stomach));
            add(new vocaWord("toes", R.drawable.toes, R.raw.audio_toes));
            add(new vocaWord("elbow", R.drawable.elbow, R.raw.audio_elbow));

        }};
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        imgCentre = findViewById(R.id.imageCentre);

        iconesList = new ArrayList<ImageView>() {{

            add((ImageView) findViewById(R.id.image0));
            add((ImageView) findViewById(R.id.image1));
            add((ImageView) findViewById(R.id.image2));
            add((ImageView) findViewById(R.id.image3));
            add((ImageView) findViewById(R.id.image4));
            add((ImageView) findViewById(R.id.image5));
        }};
    }



    public void onImageX(View view) {
        listIter = Integer.parseInt(view.getTag().toString());
        imgCentre.setImageResource(vocaList.get(listIter).getImageID());
        iconesList.get(listIter).setImageResource(vocaList.get(listIter).getImageID());
    }

    // launch vocal recognition
    public void onStartClick(View view){

        if(view.getId() == R.id.imageCentre){
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), vocaList.get(listIter).getSoundString());
            mp.start();
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException ex) {
                android.util.Log.d("HA", ex.toString());
            }

            getSpeechInput();
        }
    }

    public void getSpeechInput(){

        // init the vocal recognition
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB");
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


