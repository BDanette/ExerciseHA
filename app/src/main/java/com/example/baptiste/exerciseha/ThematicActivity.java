package com.example.baptiste.exerciseha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

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

    private int buttonIDList[] = { R.id.image0, R.id.image1, R.id.image2,
            R.id.image3, R.id.image4, R.id.image5,
            R.id.image6, R.id.image7, R.id.image8,
            R.id.image9, R.id.image10, R.id.image11 };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thematic);


       /* buttonEx1 = (Button) findViewById(R.id.ex1);
        buttonEx2 = (Button) findViewById(R.id.ex2);
        buttonEx3 = (Button) findViewById(R.id.ex3);
        buttonExBoss = (Button) findViewById(R.id.exBoss);*/

        vocaList = new ArrayList<vocaWord>() {{
            // lvl 1
            add(new vocaWord("arm", R.drawable.arm, R.drawable.shad_arm,
                    0, R.raw.audio_arm, 1));
            add(new vocaWord("back", R.drawable.back, R.drawable.shad_back,
                    0, R.raw.audio_back, 1));
            add(new vocaWord("chest", R.drawable.chest, R.drawable.shad_chest,
                    0, R.raw.audio_chest, 1));
            add(new vocaWord("finger", R.drawable.finger, R.drawable.shad_finger,
                    0, R.raw.audio_finger, 1));
            add(new vocaWord("foot", R.drawable.foot, R.drawable.shad_foot,
                    0, R.raw.audio_foot, 1));
            add(new vocaWord("hair", R.drawable.hair, R.drawable.shad_hair,
                    0, R.raw.audio_hair, 1));
            add(new vocaWord("knee", R.drawable.knee, R.drawable.shad_knee,
                    0, R.raw.audio_knee, 1));
            add(new vocaWord("leg", R.drawable.leg, R.drawable.shad_leg,
                    0, R.raw.audio_leg, 1));
            add(new vocaWord("neck", R.drawable.neck, R.drawable.shad_neck,
                    0, R.raw.audio_neck, 1));

            // lvl 2
            add(new vocaWord("buttocks", R.drawable.buttocks, R.drawable.shad_buttocks,
                    0, R.raw.audio_buttocks, 2));
            add(new vocaWord("elbow", R.drawable.elbow, R.drawable.shad_elbow,
                    0, R.raw.audio_elbow, 2));
            add(new vocaWord("hand", R.drawable.hand, R.drawable.shad_hand,
                    0, R.raw.audio_hand, 1));
            add(new vocaWord("head", R.drawable.head, R.drawable.shad_head,
                    0, R.raw.audio_head, 2));
            add(new vocaWord("stomach", R.drawable.stomach, R.drawable.shad_stomach,
                    0, R.raw.audio_stomach, 2));
            add(new vocaWord("toes", R.drawable.toes, R.drawable.shad_toes,
                    0, R.raw.audio_toes, 2));

            // lvl 3
            add(new vocaWord("shoulders", R.drawable.shoulders, R.drawable.shad_shoulders,
                    0, R.raw.audio_shoulders, 3));
        }};

    }

    // TODO manage exercise 3 list when less than 18 words in vocaList
    public void onExerciseX(View view){

        if(vocaList.size() > 18){
            Toast.makeText(ThematicActivity.this,
                    "To much words in vocaList",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        int iterTag = Integer.parseInt(view.getTag().toString());
        if(iterTag != 0 || iterTag != 1 || iterTag != 2){
            Toast.makeText(ThematicActivity.this,
                    "You are not trying to open an exercise",
                    Toast.LENGTH_SHORT).show();

            return;
        }


        ArrayList<vocaWord> tmpVocaList = new ArrayList<>();

        for(int i=0 ; i<6 ; i++){

            tmpVocaList.add(new vocaWord(vocaList.get(6*iterTag + i).getWord(),
                    vocaList.get(6*iterTag + i).getImageID(),
                    vocaList.get(6*iterTag + i).getImageShadID(),
                    buttonIDList[i],
                    vocaList.get(6*iterTag + i).getSoundID(),
                    vocaList.get(6*iterTag + i).getLvl() ));
        }


        // send data to exercise activity
        Intent intent = new Intent(ThematicActivity.this, ExerciseActivity.class);
        intent.putExtra(EXTRA_ACTID, R.layout.activity_exercise);
        intent.putExtra(EXTRA_TRIAL, 5);
        intent.putParcelableArrayListExtra(EXTRA_VOCALIST, tmpVocaList);
        startActivity(intent);
    }

    public void onBoss(View view){

        String nameTag = view.getTag().toString();

        if(!nameTag.equals("boss")){
            Toast.makeText(ThematicActivity.this,
                    "You are not trying to open a boss",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // get some random pointers
        Vector<Integer> pointersList = new Vector<>();
        for (int i = 0; i < vocaList.size() ; i++) {
            pointersList.add(i);
        }
        int j;
        Random ran=new Random();

        ArrayList<vocaWord> tmpVocaListBoss = new ArrayList<>();

        for(int i=0 ; i<12 ; i++){

            j = ran.nextInt(pointersList.size());

            tmpVocaListBoss.add(new vocaWord(vocaList.get(pointersList.get(j)).getWord(),
                    vocaList.get(pointersList.get(j)).getImageID(),
                    vocaList.get(pointersList.get(j)).getImageShadID(),
                    buttonIDList[i],
                    vocaList.get(pointersList.get(j)).getSoundID(),
                    vocaList.get(pointersList.get(j)).getLvl() ));

            pointersList.remove(j);
        }

        // send data to boss activity
        Intent intent = new Intent(ThematicActivity.this, ExerciseActivity.class);
        intent.putExtra(EXTRA_ACTID, R.layout.activity_exboss);
        intent.putExtra(EXTRA_TRIAL, 2);
        intent.putParcelableArrayListExtra(EXTRA_VOCALIST, tmpVocaListBoss);
        startActivity(intent);
    }
}
