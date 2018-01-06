package com.example.baptiste.exerciseha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Parcelable;
import android.widget.Button;

import java.util.ArrayList;

public class ThematicActivity extends AppCompatActivity {

    public static final String EXTRA_ACTID = "com.example.baptiste.exerciseha.actid";
    public static final String EXTRA_TRIAL = "com.example.baptiste.exerciseha.trial";
    public static final String EXTRA_VOCALIST = "com.example.baptiste.exerciseha.vocalist";


    //private ExerciseActivity exActivity;
    /*private Button buttonEx1;
    private Button buttonEx2;
    private Button buttonEx3;
    private Button buttonExBoss;*/

    private ArrayList<vocaWord> vocaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thematic);


       /* buttonEx1 = (Button) findViewById(R.id.ex1);
        buttonEx2 = (Button) findViewById(R.id.ex2);
        buttonEx3 = (Button) findViewById(R.id.ex3);
        buttonExBoss = (Button) findViewById(R.id.exBoss);*/


    }

    public void onExerciseX(View view){

        vocaList = new ArrayList<vocaWord>() {{
            add(new vocaWord("leg", R.drawable.leg, R.drawable.shad_leg,
                    R.id.image0, R.raw.audio_leg));
            add(new vocaWord("neck", R.drawable.neck, R.drawable.shad_neck,
                    R.id.image1, R.raw.audio_neck));
            add(new vocaWord("shoulders", R.drawable.shoulders, R.drawable.shad_shoulders,
                    R.id.image2, R.raw.audio_shoulders));
            add(new vocaWord("stomach", R.drawable.stomach, R.drawable.shad_stomach,
                    R.id.image3, R.raw.audio_stomach));
            add(new vocaWord("toes", R.drawable.toes, R.drawable.shad_toes,
                    R.id.image4, R.raw.audio_toes));
            add(new vocaWord("elbow", R.drawable.elbow, R.drawable.shad_elbow,
                    R.id.image5, R.raw.audio_elbow));
        }};

        // send data to exercise activity
        Intent intent = new Intent(ThematicActivity.this, ExerciseActivity.class);
        intent.putExtra(EXTRA_ACTID, R.layout.activity_exercise);
        intent.putExtra(EXTRA_TRIAL, 5);
        intent.putParcelableArrayListExtra(EXTRA_VOCALIST, vocaList);
        startActivity(intent);
    }

    public void onBoss(View view){

        vocaList = new ArrayList<vocaWord>() {{
            add(new vocaWord("leg", R.drawable.leg, R.drawable.shad_leg,
                    R.id.image0, R.raw.audio_leg));
            add(new vocaWord("neck", R.drawable.neck, R.drawable.shad_neck,
                    R.id.image1, R.raw.audio_neck));
            add(new vocaWord("shoulders", R.drawable.shoulders, R.drawable.shad_shoulders,
                    R.id.image2, R.raw.audio_shoulders));
            add(new vocaWord("stomach", R.drawable.stomach, R.drawable.shad_stomach,
                    R.id.image3, R.raw.audio_stomach));
            add(new vocaWord("toes", R.drawable.toes, R.drawable.shad_toes,
                    R.id.image4, R.raw.audio_toes));
            add(new vocaWord("elbow", R.drawable.elbow, R.drawable.shad_elbow,
                    R.id.image5, R.raw.audio_elbow));

            // add these for boss exercise
            add(new vocaWord("back", R.drawable.back, R.drawable.shad_back,
                    R.id.image6, R.raw.audio_back));
            add(new vocaWord("buttocks", R.drawable.buttocks, R.drawable.shad_buttocks,
                    R.id.image7, R.raw.audio_buttocks));
            add(new vocaWord("chest", R.drawable.chest, R.drawable.shad_chest,
                    R.id.image8, R.raw.audio_chest));
            add(new vocaWord("finger", R.drawable.finger, R.drawable.shad_finger,
                    R.id.image9, R.raw.audio_finger));
            add(new vocaWord("head", R.drawable.head, R.drawable.shad_head,
                    R.id.image10, R.raw.audio_head));
            add(new vocaWord("knee", R.drawable.knee, R.drawable.shad_knee,
                    R.id.image11, R.raw.audio_knee));
        }};

        // send data to boss activity
        Intent intent = new Intent(ThematicActivity.this, ExerciseActivity.class);
        intent.putExtra(EXTRA_ACTID, R.layout.activity_exboss);
        intent.putExtra(EXTRA_TRIAL, 2);
        intent.putParcelableArrayListExtra(EXTRA_VOCALIST, vocaList);
        startActivity(intent);
    }
}
