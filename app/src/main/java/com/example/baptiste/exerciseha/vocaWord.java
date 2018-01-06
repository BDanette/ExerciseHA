package com.example.baptiste.exerciseha;

/**
 * Created by tangu on 14/12/2017.
 */


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * "vocaWord"
 *
 * The vocabulary class,
 * which contained the vocabulary word (String) to repeat,
 * and the state of the trials (well repeated or not...).
 *
 */
public class vocaWord implements Parcelable{

    /***** PARAMETERS *******/

    // The vocabulary word contained
    private String p_word = "";
    private int p_imgID;
    private int p_imgShadID;
    private int p_imgViewID;
    private int p_soundID;
    private int p_trial;

    // state of the trials
    // -1 = no trials,    0 = tried but failed,    1 = success
    private int p_isSuccess = -1;

    // weird variable to help Parcelable contents
    public static final Parcelable.Creator CREATOR = new Creator() {

        public Object createFromParcel(Parcel source) {
            return new vocaWord(source);
        }

        public Object[] newArray(int size) {
            return new vocaWord[size];
        }
    };


    /***** PARCELABLE *******/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.v(TAG, "writeToParcel..."+ flags);
        dest.writeString(p_word);
        dest.writeInt(p_imgID);
        dest.writeInt(p_imgShadID);
        dest.writeInt(p_imgViewID);
        dest.writeInt(p_soundID);
        dest.writeInt(p_trial);
        dest.writeInt(p_isSuccess);

    }


    /***** CONSTRUCTORS *******/

    vocaWord(String m_word, int m_imgID, int m_imgShadID, int m_imgViewID, int m_soundID){
        if(m_word != null)
            this.setWord(m_word);
        else
            this.setWord("");

        this.setImageID(m_imgID);
        this.setImageShadID(m_imgShadID);
        this.setImageViewID(m_imgViewID);
        this.setSoundID(m_soundID);
        this.setSuccess(-1);
        this.setVocaTrial(0);
    }

    vocaWord(Parcel source){
        Log.v(TAG, "ParcelData(Parcel source): time to put back parcel data");
        p_word = source.readString();
        p_imgID = source.readInt();
        p_imgShadID = source.readInt();
        p_imgViewID = source.readInt();
        p_soundID = source.readInt();
        p_trial = source.readInt();
        p_isSuccess = source.readInt();
    }


    /***** GETTERS & SETTERS *******/

    String getWord(){ return this.p_word; }
    public void setWord(String m_word){
        this.p_word = m_word;
    }

    int getImageShadID(){ return this.p_imgShadID; }
    public void setImageShadID(int m_imgShadID){
        this.p_imgShadID = m_imgShadID;
    }

    int getImageViewID(){ return this.p_imgViewID; }
    public void setImageViewID(int m_imgViewID){
        this.p_imgViewID = m_imgViewID;
    }

    int getImageID(){ return this.p_imgID; }
    public void setImageID(int m_imgID){
        this.p_imgID = m_imgID;
    }

    int getSoundID(){ return this.p_soundID; }
    public void setSoundID(int m_soundID){
        this.p_soundID = m_soundID;
    }

    int getSuccess(){ return this.p_isSuccess; }
    void setSuccess(int m_isSuccess){
        this.p_isSuccess = m_isSuccess;
    }

    int getVocaTrial() { return this.p_trial; }
    void setVocaTrial(int m_trial){
        this.p_trial = m_trial;
    }


    /***** OTHER METHODS *******/

    // getTrial() method read the m_isSuccess flag value and
    //      return a more explicit String than the flag value.
    String getTrial(){
        switch(p_isSuccess)
        {
            case -1:
                return "never tried";
            case 0:
                return "not passed";
            case 1:
                return "already passed";
            default:
                break;
        }
        return "ERROR";
    }

}
