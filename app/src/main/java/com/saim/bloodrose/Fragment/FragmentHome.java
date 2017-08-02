package com.saim.bloodrose.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.saim.bloodrose.Adapter.AdapterDoner;
import com.saim.bloodrose.Model.ModelDoner;
import com.saim.bloodrose.R;
import com.saim.bloodrose.Utils.ApiURL;
import com.saim.bloodrose.Utils.MySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentHome extends Fragment {

    View view;

    ProgressDialog progressDialog;

    ArrayList<ModelDoner> donerList = new ArrayList<>();
    RecyclerView recyclerDoner;
    RecyclerView.LayoutManager layoutManagerDoner;
    RecyclerView.Adapter donerAdapter;

    LinearLayout btnAPositive, btnBPositive, btnABPositive, btnOPositive, btnANegetive, btnBNegetive, btnABNegetive, btnONegetive;

    public FragmentHome() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();

        return view;
    }


    public void init(){
        progressDialog = new ProgressDialog(getContext());

        recyclerDoner = (RecyclerView) view.findViewById(R.id.recyclerViewDoner);
        layoutManagerDoner = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerDoner.setLayoutManager(layoutManagerDoner);
        recyclerDoner.setHasFixedSize(true);

        btnAPositive = (LinearLayout) view.findViewById(R.id.btnAPositive);
        btnBPositive = (LinearLayout) view.findViewById(R.id.btnBPositive);
        btnABPositive = (LinearLayout) view.findViewById(R.id.btnABPositive);
        btnOPositive = (LinearLayout) view.findViewById(R.id.btnOPositive);
        btnANegetive = (LinearLayout) view.findViewById(R.id.btnANegetive);
        btnBNegetive = (LinearLayout) view.findViewById(R.id.btnBNegetive);
        btnABNegetive = (LinearLayout) view.findViewById(R.id.btnABNegetive);
        btnONegetive = (LinearLayout) view.findViewById(R.id.btnONegetive);

        GetBloodDonerList();
        ButtonClicked();

    }

    public void ButtonClicked(){
        btnAPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                btnBPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnOPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnANegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnONegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                progressDialog.setMessage("Please wiat data featching...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                GetBloodDonerList("A+");
            }
        });
        btnBPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                btnABPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnOPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnANegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnONegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                progressDialog.setMessage("Please wiat data featching...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                GetBloodDonerList("B+");
            }
        });
        btnABPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                btnOPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnANegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnONegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                progressDialog.setMessage("Please wiat data featching...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                GetBloodDonerList("AB+");
            }
        });
        btnOPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnOPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                btnANegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnONegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                progressDialog.setMessage("Please wiat data featching...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                GetBloodDonerList("O+");
            }
        });
        btnANegetive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnOPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnANegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                btnBNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnONegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                progressDialog.setMessage("Please wiat data featching...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                GetBloodDonerList("A-");
            }
        });
        btnBNegetive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnOPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnANegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                btnABNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnONegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                progressDialog.setMessage("Please wiat data featching...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                GetBloodDonerList("B-");
            }
        });
        btnABNegetive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnOPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnANegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                btnONegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                progressDialog.setMessage("Please wiat data featching...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                GetBloodDonerList("AB-");
            }
        });
        btnONegetive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnOPositive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnANegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnBNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnABNegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnONegetive.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

                progressDialog.setMessage("Please wiat data featching...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                GetBloodDonerList("O-");
            }
        });
    }


    public void GetBloodDonerList() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiURL.bloodDonerList,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("SAIM DONOR LIST ", response.toString());

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                            JSONArray jsonArrayBloodDoner = jsonObjectData.getJSONArray("bloodDonors");

                            for (int i=0; i<jsonArrayBloodDoner.length(); i++){

                                JSONObject jsonObjectBloodDonor = jsonArrayBloodDoner.getJSONObject(i);

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

                                ModelDoner modelDoner = new ModelDoner(bloodDonorID, emailAddress, phoneNumber, fullName, presentAddress, birthDate, bloodGroup,
                                        donatedDate, registeredDate, locationID, locationName);
                                donerList.add(modelDoner);
                            }

                            donerAdapter = new AdapterDoner(donerList);
                            recyclerDoner.setAdapter(donerAdapter);

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
                //params.put("bloodGroup", "O+");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Cookie", ApiURL.cookie);
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


    public void GetBloodDonerList(final String bloodGroup) {
        donerList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiURL.bloodDonerList,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("SAIM DONOR LIST ", response.toString());
                            progressDialog.dismiss();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                            JSONArray jsonArrayBloodDoner = jsonObjectData.getJSONArray("bloodDonors");

                            for (int i=0; i<jsonArrayBloodDoner.length(); i++){

                                JSONObject jsonObjectBloodDonor = jsonArrayBloodDoner.getJSONObject(i);

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

                                ModelDoner modelDoner = new ModelDoner(bloodDonorID, emailAddress, phoneNumber, fullName, presentAddress, birthDate, bloodGroup,
                                        donatedDate, registeredDate, locationID, locationName);
                                donerList.add(modelDoner);
                            }

                            donerAdapter = new AdapterDoner(donerList);
                            recyclerDoner.setAdapter(donerAdapter);

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
                params.put("bloodGroup", bloodGroup);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Cookie", ApiURL.cookie);
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
