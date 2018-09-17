package com.example.wk.sigah.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kharisma on 17/04/2018.
 */

public class SharedPrefManager {
    public static final String SP_SIGAH_APP="spSigahApp";

    public static final String SP_ID_PEL="spIdPel";
    public static final String SP_EMAIL="spEmail";

    public static final String SP_ALREADY_LOGIN ="spAlreadyLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp=context.getSharedPreferences(SP_SIGAH_APP,Context.MODE_PRIVATE);
        spEditor=sp.edit();
    }

    public void saveSPString(String keySP,String value){
        spEditor.putString(keySP,value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP,int value){
        spEditor.putInt(keySP,value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public  String getSpIdPel() {
        return sp.getString(SP_ID_PEL,"");
    }

    public  String getSpEmail() {
        return sp.getString(SP_EMAIL,"");
    }

    public  Boolean getSpAlreadyLogin() {
        return sp.getBoolean(SP_ALREADY_LOGIN,false);
    }
}
