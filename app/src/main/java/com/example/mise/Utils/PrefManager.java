package com.example.mise.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setAnswers(HashMap<String,String>answers,String key)
    {
        HashMap<String,String>answers_= new HashMap<>();
        answers_=answers;
        Gson gson = new Gson();
        String jsonText = gson.toJson(answers_);
        editor.putString(key, jsonText);
        editor.apply();
    }

    public HashMap<String,String> getAnswers(String key)
    {
        Gson gson = new Gson();
        String json = pref.getString(key, null);
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        return gson.fromJson(json, type);
    }

}