package com.example.baptiste.exerciseha;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;



public class ExerciseActivity extends AppCompatActivity {

    private ImageView img0;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView imgCentre;
    public int listIter = 0;
    public ArrayList<vocaWord> insectsList;
    {
        insectsList = new ArrayList<vocaWord>() {{
            add(new vocaWord("arm", img0));
            add(new vocaWord("finger", img1));
            add(new vocaWord("foot", img2));
            add(new vocaWord("hand", img3));
            add(new vocaWord("head", img4));
        }};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        img0 = findViewById(R.id.image0);
        insectsList.get(0).setImage(img0);

        img1 = findViewById(R.id.image1);
        insectsList.get(1).setImage(img1);

        img2 = findViewById(R.id.image2);
        insectsList.get(2).setImage(img2);

        img3 = findViewById(R.id.image3);
        insectsList.get(3).setImage(img3);

        img4 = findViewById(R.id.image4);
        insectsList.get(4).setImage(img4);

        imgCentre = findViewById(R.id.imageCentre);
    }



    public void onImage0(View view) {
        imgCentre.setImageDrawable(insectsList.get(0).getImage().getDrawable());
        listIter = 0;
    }

    public void onImage1(View view) {
        imgCentre.setImageDrawable(insectsList.get(1).getImage().getDrawable());
        listIter = 1;
    }

    public void onImage2(View view) {
        imgCentre.setImageDrawable(insectsList.get(2).getImage().getDrawable());
        listIter = 2;
    }

    public void onImage3(View view) {
        imgCentre.setImageDrawable(insectsList.get(3).getImage().getDrawable());
        listIter = 3;
    }

    public void onImage4(View view) {
        imgCentre.setImageDrawable(insectsList.get(4).getImage().getDrawable());
        listIter = 4;
    }

    // launch vocal recognition
    public void onStartClick(View view){

        if(view.getId() == R.id.imageCentre){
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
                if(result.get(0).equals(insectsList.get(listIter).getWord() ) ){

                    insectsList.get(listIter).setSuccess(1);
                    Toast.makeText(ExerciseActivity.this,
                            "It is good",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    insectsList.get(listIter).setSuccess(0);
                    Toast.makeText(ExerciseActivity.this,
                            "It Iznogoude",
                            Toast.LENGTH_SHORT).show();
                }
            }
                break;
        }
    }
}


