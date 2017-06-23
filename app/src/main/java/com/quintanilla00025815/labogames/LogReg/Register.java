package com.quintanilla00025815.labogames.LogReg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quintanilla00025815.labogames.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Register extends Activity {

    private EditText etName,etMobile, etAddress;
    private Button btnSubmit;
    private ProgressDialog pDialog;
    private JSONObject json;
    private int success=0;
    private HTTPURLConnection service;
    private String strname ="", strMobile ="",strAddress="";
    //Initialize webservice URL
    private String path = "http://gamespm-com.stackstaging.com/WebServer/registrar/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName= (EditText) findViewById(R.id.etName);
        etMobile= (EditText) findViewById(R.id.etMobile);
        //etAddress= (EditText) findViewById(R.id.etAddress);
        btnSubmit= (Button) findViewById(R.id.btnSubmit);

        //Initialize HTTPURLConnection class object
        service=new HTTPURLConnection();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etName.getText().toString().equals("") && !etMobile.getText().toString().equals("")) {
                    strname = etName.getText().toString();
                    strMobile = etMobile.getText().toString();
                    strAddress = "lolicon.png";
                    //Call WebService
                    new PostDataTOServer().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter all fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {

        String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            postDataParams=new HashMap<String, String>();
            postDataParams.put("name", strname);
            postDataParams.put("password", strMobile);
            postDataParams.put("photo", "lolicon.png");
            //Call ServerData() method to call webservice and store result in response
            response= service.ServerData(path,postDataParams);
            try {
                json = new JSONObject(response);
                //Get Values from JSONobject
                System.out.println("success=" + json.get("success"));
                success = json.getInt("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(success==1) {
                Toast.makeText(getApplicationContext(), "User Added successfully..!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
                Register.this.finish();
            }
        }
    }
}
