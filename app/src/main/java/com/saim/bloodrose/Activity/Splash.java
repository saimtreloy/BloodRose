package com.saim.bloodrose.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.saim.bloodrose.Activity.LoginActivity;
import com.saim.bloodrose.Model.ModelLocation;
import com.saim.bloodrose.R;
import com.saim.bloodrose.Utils.ApiURL;
import com.saim.bloodrose.Utils.MySingleton;
import com.saim.bloodrose.Utils.SharedPrefDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Splash extends AppCompatActivity {

    public static ArrayList<String> bloodGroupList = new ArrayList<>();
    public static ArrayList<String> bloodLocationList = new ArrayList<>();
    public static ArrayList<ModelLocation> bloodLocation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        GetLocationList();
        /*Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        };
        timer.start();*/
    }



    public void GetLocationList() {
        bloodLocationList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiURL.location,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("HDHD ", response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code == 200){
                                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                                JSONArray jsonArray = jsonObjectData.getJSONArray("locations");
                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject jsonObjectLocation = jsonArray.getJSONObject(i);
                                    int locationID = jsonObjectLocation.getInt("locationID");
                                    String locationName = jsonObjectLocation.getString("locationName");
                                    ModelLocation modelLocation = new ModelLocation(locationID, locationName);
                                    bloodLocation.add(modelLocation);
                                    bloodLocationList.add(locationName);
                                }
                            }else{

                            }
                            GetBloodGroupList();
                        }catch (Exception e){
                            Log.d("HDHD ", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Cookie", ApiURL.cookie);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


    public void GetBloodGroupList() {
        bloodGroupList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiURL.bloodGroupList,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("HDHD ", response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");

                            if (code == 200){
                                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                                JSONArray jsonArray = jsonObjectData.getJSONArray("bloodGroups");
                                for (int i=0; i<jsonArray.length(); i++){
                                    bloodGroupList.add(jsonArray.getString(i));
                                }
                            }else{

                            }
                            if (new SharedPrefDatabase(getApplicationContext()).RetriveEmail()== null && new SharedPrefDatabase(getApplicationContext()).RetrivePassword() == null){
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }else if (!new SharedPrefDatabase(getApplicationContext()).RetriveEmail().isEmpty() && !new SharedPrefDatabase(getApplicationContext()).RetrivePassword().isEmpty()){
                                GetUserLogin(new SharedPrefDatabase(getApplicationContext()).RetriveEmail(), new SharedPrefDatabase(getApplicationContext()).RetrivePassword());
                            }else {
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                        }catch (Exception e){
                            Log.d("HDHD ", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Cookie", ApiURL.cookie);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }



    public void GetUserLogin(final String username, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiURL.login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("SAIM RESPONSE", response);

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                            JSONObject jsonObjectBloodDonor = jsonObjectData.getJSONObject("bloodDonor");
                            String bloodDonorID =  jsonObjectBloodDonor.getInt("bloodDonorID") + "";
                            String emailAddress =  jsonObjectBloodDonor.getString("emailAddress");
                            String phoneNumber =  jsonObjectBloodDonor.getString("phoneNumber");
                            String fullName =  jsonObjectBloodDonor.getString("fullName");
                            String presentAddress =  jsonObjectBloodDonor.getString("presentAddress");
                            String birthDate =  jsonObjectBloodDonor.getString("birthDate");
                            String bloodGroup =  jsonObjectBloodDonor.getString("bloodGroup");
                            String donatedDate =  jsonObjectBloodDonor.getString("donatedDate");
                            String registeredDate =  jsonObjectBloodDonor.getString("registeredDate");
                            String locationID =  jsonObjectBloodDonor.getString("locationID");
                            String locationName =  jsonObjectBloodDonor.getString("locationName");
                            String bloodDonorToken =  jsonObjectBloodDonor.getString("bloodDonorToken");


                            new SharedPrefDatabase(getApplicationContext()).StoreDonorID(bloodDonorID);
                            new SharedPrefDatabase(getApplicationContext()).StoreEmail(emailAddress);
                            new SharedPrefDatabase(getApplicationContext()).StorePhone(phoneNumber);
                            new SharedPrefDatabase(getApplicationContext()).StoreFullName(fullName);
                            new SharedPrefDatabase(getApplicationContext()).StoreAddress(presentAddress);
                            new SharedPrefDatabase(getApplicationContext()).StoreBirthday(birthDate);
                            new SharedPrefDatabase(getApplicationContext()).StoreBloodGroup(bloodGroup);
                            new SharedPrefDatabase(getApplicationContext()).StoreDonateDate(donatedDate);
                            new SharedPrefDatabase(getApplicationContext()).StoreRegDate(registeredDate);
                            new SharedPrefDatabase(getApplicationContext()).StoreLocationID(locationID);
                            new SharedPrefDatabase(getApplicationContext()).StoreLocationName(locationName);
                            new SharedPrefDatabase(getApplicationContext()).StoreToken(bloodDonorToken);

                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }catch (Exception e){
                            Log.d("HDHD ", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
                //get status code here
                String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                if(error.networkResponse.data!=null) {
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");

                        JSONObject jsonObject = new JSONObject(body);
                        JSONObject jsonObjectData = jsonObject.getJSONObject("data");

                        String user = "", pass = "";
                        try {
                            user = jsonObjectData.getString("username");
                        }catch (Exception e){

                        }

                        try {
                            pass = jsonObjectData.getString("password");
                        }catch (Exception e){

                        }
                        Log.d("SAIM VOLLY ERROR", body);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Cookie", ApiURL.cookie);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
