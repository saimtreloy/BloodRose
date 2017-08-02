package com.saim.bloodrose.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by saim on 3/1/17.
 */

public class SharedPrefDatabase {

    public static final String LINK_DONOR_ID = "LINK_DONOR_ID";
    public static final String LINK_EMAIL = "LINK_EMAIL";
    public static final String LINK_PHONE = "LINK_PHONE";
    public static final String LINK_PASSWORD = "LINK_PASSWROD";
    public static final String LINK_FULLNAME = "LINK_FULLNAME";
    public static final String LINK_PRESENT_ADDRESS = "LINK_PRESENT_ADDRESS";
    public static final String LINK_BIRTHDAY = "LINK_BIRTHDAY";
    public static final String LINK_BLOOD_GROUP = "LINK_BLOOD_GROUP";
    public static final String LINK_DONATE_DATE = "LINK_DONATE_DATE";
    public static final String LINK_REG_DATE = "LINK_REG_DATE";
    public static final String LINK_LOCATION_ID = "LINK_LOCATION_ID";
    public static final String LINK_LOCATION_NAME = "LINK_LOCATION_NAME";
    public static final String LINK_TOKEN = "LINK_TOKEN";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    public SharedPrefDatabase(Context ctx) {
        this.context = ctx;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = sharedPreferences.edit();
    }

    public void StoreDonorID(String data){
        editor.putString(LINK_DONOR_ID, data);
        editor.commit();
    }
    public String RetriveDonorID(){
        String text = sharedPreferences.getString(LINK_DONOR_ID, null);
        return text;
    }

    //Email ID
    public void StoreEmail(String data){
        editor.putString(LINK_EMAIL, data);
        editor.commit();
    }
    public String RetriveEmail(){
        String text = sharedPreferences.getString(LINK_EMAIL, null);
        return text;
    }

    public void StorePhone(String data){
        editor.putString(LINK_PHONE, data);
        editor.commit();
    }
    public String RetrivePhone(){
        String text = sharedPreferences.getString(LINK_PHONE, null);
        return text;
    }

    public void StorePassword(String data){
        editor.putString(LINK_PASSWORD, data);
        editor.commit();
    }
    public String RetrivePassword(){
        String text = sharedPreferences.getString(LINK_PASSWORD, null);
        return text;
    }

    public void StoreFullName(String data){
        editor.putString(LINK_FULLNAME, data);
        editor.commit();
    }
    public String RetriveFullName(){
        String text = sharedPreferences.getString(LINK_FULLNAME, null);
        return text;
    }

    public void StoreAddress(String data){
        editor.putString(LINK_PRESENT_ADDRESS, data);
        editor.commit();
    }
    public String RetriveAddress(){
        String text = sharedPreferences.getString(LINK_PRESENT_ADDRESS, null);
        return text;
    }

    public void StoreBirthday(String data){
        editor.putString(LINK_BIRTHDAY, data);
        editor.commit();
    }
    public String RetriveBirthday(){
        String text = sharedPreferences.getString(LINK_BIRTHDAY, null);
        return text;
    }

    public void StoreBloodGroup(String data){
        editor.putString(LINK_BLOOD_GROUP, data);
        editor.commit();
    }
    public String RetriveBloodGroup(){
        String text = sharedPreferences.getString(LINK_BLOOD_GROUP, null);
        return text;
    }

    public void StoreDonateDate(String data){
        editor.putString(LINK_DONATE_DATE, data);
        editor.commit();
    }
    public String RetriveDonateDate(){
        String text = sharedPreferences.getString(LINK_DONATE_DATE, null);
        return text;
    }

    public void StoreRegDate(String data){
        editor.putString(LINK_REG_DATE, data);
        editor.commit();
    }
    public String RetriveRegDate(){
        String text = sharedPreferences.getString(LINK_REG_DATE, null);
        return text;
    }

    public void StoreLocationID(String data){
        editor.putString(LINK_LOCATION_ID, data);
        editor.commit();
    }
    public String RetriveLocationID(){
        String text = sharedPreferences.getString(LINK_LOCATION_ID, null);
        return text;
    }

    public void StoreLocationName(String data){
        editor.putString(LINK_LOCATION_NAME, data);
        editor.commit();
    }
    public String RetriveLocationName(){
        String text = sharedPreferences.getString(LINK_LOCATION_NAME, null);
        return text;
    }

    public void StoreToken(String data){
        editor.putString(LINK_TOKEN, data);
        editor.commit();
    }
    public String RetriveToken(){
        String text = sharedPreferences.getString(LINK_TOKEN, null);
        return text;
    }
}
