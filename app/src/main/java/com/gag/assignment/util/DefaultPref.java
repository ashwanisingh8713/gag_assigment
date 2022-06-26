package com.gag.assignment.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.gag.assignment.app.ProductApplication;

/**
 * Created by ashwanisingh on 26/06/22.
 */

public class DefaultPref {

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private static DefaultPref mUser;

    private DefaultPref() {
        try {
            mPreferences = ProductApplication.getContext().getSharedPreferences("DefaultPref.xml", Context.MODE_PRIVATE);
            mEditor = mPreferences.edit();
        } catch (NumberFormatException e) {

        }
    }

    public static final DefaultPref getInstance() {
        if(mUser == null) {
            try {
                mUser = new DefaultPref();
            } catch (Exception e) {

            }
        }
        return mUser;
    }

    public void setIsUserLoggedIn(boolean isUserLoggedIn) {
        mEditor.putBoolean("isUserLoggedIn", isUserLoggedIn);
        mEditor.apply();
    }

    public boolean isUserLoggedIn() {
        return mPreferences.getBoolean("isUserLoggedIn", false);
    }

}
