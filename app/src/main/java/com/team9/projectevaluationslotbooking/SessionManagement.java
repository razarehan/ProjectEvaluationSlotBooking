package com.team9.projectevaluationslotbooking;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = null;

    public SessionManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String username) {
        editor.putString(SESSION_KEY, username).apply();
    }

    public String getSession() {
        return sharedPreferences.getString(SESSION_KEY,null);
    }

    public void destroySession() {
        editor.putString(SESSION_KEY,null).commit();
    }
}
