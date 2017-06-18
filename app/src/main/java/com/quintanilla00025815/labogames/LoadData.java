package com.quintanilla00025815.labogames;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.net.Uri;

/**
 * Created by hmanr on 17/6/2017.
 */

public class LoadData extends AsyncTask<Void, Void, String> {

        private TextView text1;
        private TextView text2;

    static final String TAG = "LoadData";
    private Context context;
    private ProgressDialog pDialog;

    private final String json_url  = "http://dei.uca.edu.sv/estacionDEI/estacionDeiWebService.php";
    private String line;
    private String response = "";
    private String gameName;

    public LoadData(Context c, TextView game, TextView desc,String nameGame){

        context = c;
        text1=game;
        text2=desc;
        gameName=nameGame;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pDialog = new ProgressDialog(context) ;
        pDialog.setMessage("Cargando informacion. por favor espere...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d(TAG, "doInBackground:"+response+"");
        response = enviarjuegosGET(gameName);
        pDialog.dismiss();
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        getdataJSON(response);
    }

    //La funcion que envia el formato json
    public String enviarjuegosGET(String namegame){
        URL uri = null;
        String linea ="";
        StringBuilder result = null;
        int respuesta = 0;
        Log.d(TAG, "enviarjuegosGET: Todo cool "+namegame+"");
        try {
            uri = new URL("http://192.168.0.13/WebServer/informacionjuego.php?namegame="+namegame+"");
            HttpURLConnection httpCon = (HttpURLConnection)uri.openConnection();
            httpCon.setReadTimeout(20000);
            httpCon.setConnectTimeout(20000);
            httpCon.setDoInput(true);
            httpCon.setDoOutput(true);
            respuesta =httpCon.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK){
                Log.d(TAG, "enviarjuegosGET: Funciona");
                InputStream in =new BufferedInputStream(httpCon.getInputStream());
                BufferedReader read = new BufferedReader(new InputStreamReader(in));
                while ((linea=read.readLine())!=null){
                    result.append(linea);
                }
            }
        }
        catch (Exception e){

        }
        Log.d(TAG, "enviarjuegosGET:"+result.toString()+"");
        return result.toString();
    }
    //Ver si se viene vacio el json
    public int obtDatosJSON(String response){
        int res = 0;
        try {
            JSONArray json=new JSONArray(response);
            if (json.length()>0){
                res = 1;
            }
        }catch (Exception e){}
        return res;
    }

    public void getdataJSON(String jsoncad){
        try{
            //convirtiendo json string a json object
            JSONObject jsonObj = new JSONObject(jsoncad);
            text1.setText(jsonObj.getString("title"));
            text2.setText(jsonObj.getString("description"));
        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,"formato de json incorrecto");
        }
    }
    }
