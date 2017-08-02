package com.saim.bloodrose.Activity;

import android.animation.Animator;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.saim.bloodrose.Model.ModelLocation;
import com.saim.bloodrose.R;
import com.saim.bloodrose.Utils.ApiURL;
import com.saim.bloodrose.Utils.MySingleton;
import com.saim.bloodrose.Utils.SharedPrefDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    CardView cardSignup, cardLogin;

    ProgressDialog progressDialog;

    //Login Layout Attributes
    EditText inputLoginEmailOrMobile, inputLoginPassword;
    Button btnLoginLogin;
    TextView txtLoginForgetPassword, txtLoginSignup;

    //Signup Layout Attributes
    TextView txtRegSignin;
    EditText inputRegFullName, inputRegEmail, inputRegMobile, inputRegBDate, inputRegGroup, inputRegAddress, inputRegLastDonateDate, inputRegPassword;
    Button btnRegSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {

        cardLogin = (CardView) findViewById(R.id.cardLogin);
        cardSignup = (CardView) findViewById(R.id.cardSignup);

        progressDialog = new ProgressDialog(this);

        //Login layout
        inputLoginEmailOrMobile = (EditText) findViewById(R.id.inputLoginEmailOrMobile);
        inputLoginPassword = (EditText) findViewById(R.id.inputLoginPassword);
        btnLoginLogin = (Button) findViewById(R.id.btnLoginLogin);
        txtLoginForgetPassword = (TextView) findViewById(R.id.txtLoginForgetPassword);
        txtLoginSignup = (TextView) findViewById(R.id.txtLoginSignup);

        //Signup Layout
        txtRegSignin = (TextView) findViewById(R.id.txtRegSignin);
        inputRegFullName = (EditText) findViewById(R.id.inputRegFullName);
        inputRegEmail = (EditText) findViewById(R.id.inputRegEmail);
        inputRegMobile = (EditText) findViewById(R.id.inputRegMobile);
        inputRegBDate = (EditText) findViewById(R.id.inputRegBDate);
        inputRegGroup = (EditText) findViewById(R.id.inputRegGroup);
        inputRegAddress = (EditText) findViewById(R.id.inputRegAddress);
        inputRegLastDonateDate = (EditText) findViewById(R.id.inputRegLastDonateDate);
        inputRegPassword = (EditText) findViewById(R.id.inputRegPassword);
        btnRegSignUp = (Button) findViewById(R.id.btnRegSignUp);

        ButtonAction();
    }

    public void ButtonAction(){
        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = inputLoginEmailOrMobile.getText().toString();
                String pass = inputLoginPassword.getText().toString();
                if (!user.isEmpty() && !pass.isEmpty()){
                    progressDialog.setTitle("Please Wait..");
                    progressDialog.setMessage("Checking login information.");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    GetUserLogin(user, pass);
                }else {
                    Toast.makeText(getApplicationContext(), "User or Password field is emplty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRegSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputRegEmail.getText().toString();
                String mobile = inputRegMobile.getText().toString();
                String password = inputRegPassword.getText().toString();
                String fullName = inputRegFullName.getText().toString();
                String address = inputRegAddress.getText().toString();
                String dob = inputRegBDate.getText().toString();
                String bloodGroup = inputRegGroup.getText().toString();
                String donateDate = inputRegLastDonateDate.getText().toString();

                if (!email.isEmpty() && !mobile.isEmpty() && !password.isEmpty() && !fullName.isEmpty() && !address.isEmpty() && !dob.isEmpty() && !bloodGroup.isEmpty() && !donateDate.isEmpty()){
                    progressDialog.setTitle("Please Wait..");
                    progressDialog.setMessage("Checking registration information.");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    GetUserRegistration(fullName, email, mobile, dob, bloodGroup, address, donateDate, password);
                }else {
                    Toast.makeText(getApplicationContext(), "Input field can not be empty!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtLoginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.SlideOutRight).duration(250).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cardLogin.setVisibility(View.GONE);
                        cardSignup.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.SlideInLeft).duration(250).playOn(findViewById(R.id.cardSignup));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(findViewById(R.id.cardLogin));
            }
        });


        txtRegSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.SlideOutLeft).duration(250).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cardSignup.setVisibility(View.GONE);
                        cardLogin.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.SlideInRight).duration(250).playOn(findViewById(R.id.cardLogin));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(findViewById(R.id.cardSignup));
            }
        });


        dateSelectFromDatePicker(inputRegBDate);
        dateSelectFromDatePicker(inputRegLastDonateDate);

        inputRegGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBloodGroupList("Seletc Blood Group");
            }
        });

        inputRegAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocationList("Select Locations");
            }
        });
    }


    public void GetUserLogin(final String username, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiURL.login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
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
                            new SharedPrefDatabase(getApplicationContext()).StorePassword(password);
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
                //get response body and parse with appropriate encoding
                if(error.networkResponse.data!=null) {
                    try {
                        progressDialog.dismiss();
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

                        showDialogMessage(user + "\n" + pass);
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


    public void GetUserRegistration(final String uFullName, final String uEmail, final String uMobile, final String uBday,
                                    final String uBgroup, final String uAddress, final String uLastDDay,
                                    final String uPassword) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiURL.register,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
                            showDialogMessage("Registration Success");
                            YoYo.with(Techniques.SlideOutLeft).duration(250).withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    cardSignup.setVisibility(View.GONE);
                                    cardLogin.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.SlideInRight).duration(250).playOn(findViewById(R.id.cardLogin));
                                    inputLoginEmailOrMobile.setText(uEmail);
                                    inputLoginPassword.setText(uPassword);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).playOn(findViewById(R.id.cardSignup));
                        }catch (Exception e){
                            Log.d("HDHD ", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error!=null && error.getMessage() !=null){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"error VOLLEY "+error.getMessage(),Toast.LENGTH_LONG).show();
                }
                else{
                    progressDialog.dismiss();
                    showDialogMessage("Registration Failed");
                    Toast.makeText(getApplicationContext(),"Already registered or something wrong!!!",Toast.LENGTH_LONG).show();

                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("emailAddress", uEmail);
                params.put("phoneNumber", uMobile);
                params.put("password", uPassword);
                params.put("fullName", uFullName);
                params.put("presentAddress", uAddress);
                params.put("birthDate", uBday);
                params.put("bloodGroup", uBgroup);
                params.put("donatedDate", uLastDDay);
                params.put("locationID", "1");

                return checkParams(params);
            }

            private Map<String, String> checkParams(Map<String, String> map){
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                    if(pairs.getValue()==null){
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
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



    public String currentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    public void dateSelectFromDatePicker(final EditText editText){
        Calendar newCalendar = Calendar.getInstance();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                editText.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    public void showBloodGroupList(String title) {

        LayoutInflater factory = LayoutInflater.from(this);
        final View infoDialogView = factory.inflate(R.layout.dialog_list, null);
        final AlertDialog infoDialog = new AlertDialog.Builder(this).create();
        infoDialog.setView(infoDialogView);
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        TextView txtDialog = (TextView) infoDialogView.findViewById(R.id.txtDialog);
        txtDialog.setText(title);

        ListView listDialog = (ListView) infoDialogView.findViewById(R.id.listDialog);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Splash.bloodGroupList);
        listDialog.setAdapter(arrayAdapter);
        listDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inputRegGroup.setText(Splash.bloodGroupList.get(position));
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }

    public void showLocationList(String title) {

        LayoutInflater factory = LayoutInflater.from(this);
        final View infoDialogView = factory.inflate(R.layout.dialog_list, null);
        final AlertDialog infoDialog = new AlertDialog.Builder(this).create();
        infoDialog.setView(infoDialogView);
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        TextView txtDialog = (TextView) infoDialogView.findViewById(R.id.txtDialog);
        txtDialog.setText(title);

        ListView listDialog = (ListView) infoDialogView.findViewById(R.id.listDialog);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Splash.bloodLocationList);
        listDialog.setAdapter(arrayAdapter);
        listDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inputRegAddress.setText(Splash.bloodLocation.get(position).getLocationName());
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }


    public void showDialogMessage(String title) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message");
        builder.setMessage(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
