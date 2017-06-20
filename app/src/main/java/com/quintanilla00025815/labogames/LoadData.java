package com.quintanilla00025815.labogames;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.net.Uri;

import com.github.snowdream.android.widget.SmartImageView;

/**
 * Created by hmanr on 17/6/2017.
 */

public class LoadData extends AsyncTask<Void, Void, String> {

    //Para el menu juego
    private TextView game;
    private TextView description;
    private SmartImageView imgGame;

    //Para el top juego
    private TextView name;
    private TextView nickname;
    private ImageView img;

    //Arreglos para el top
    private ArrayList sname;
    private ArrayList snickname;
    private ArrayList simg;

    //Para la img del juego
    private ImageView gamesimg;

    static final String TAG = "LoadData";
    private Context context;
    private ProgressDialog pDialog;

    private final String json_url = "http://dei.uca.edu.sv/estacionDEI/estacionDeiWebService.php";
    private String line;
    private String response = "";
    private String gameName;

    //Constructor para descripcion del juego
    public LoadData(Context c, TextView namegame, TextView desc,SmartImageView img,String nameGame) {

        context = c;
        game = namegame;
        description = desc;
        imgGame = img;
        gameName = nameGame;
    }
    //Constructor para top jugadores de ese juego
    /*public LoadData(Context c,TextView player,TextView nplayer,ImageView imgPlayer,String nameGame){

        context = c;
        name = player;
        nickname = nplayer;
        img = imgPlayer;
        gameName = nameGame;
    }*/
   /* public LoadData(Context c,SmartImageView img){
        context =c;
        imgGame = img;
    }*/


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
    protected void onPostExecute(String result) {getGameDescriptionJSON(response);
    }

    //La funcion que envia el formato json
    public String enviarjuegosGET(String namegame){
        URL uri = null;
        String linea ="";
        StringBuilder result = null;
        int respuesta = 0;
        Log.d(TAG, "enviarjuegosGET: Todo cool "+namegame+"");
        try {
            uri = new URL("http://192.168.1.13/WebServer/informacionjuego.php?namegame="+namegame+"");
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

    public void getGameDescriptionJSON(String jsoncad){
        try{
            //convirtiendo json string a json object
            JSONObject jsonObj = new JSONObject(jsoncad);
            game.setText(jsonObj.getString("title"));
            description.setText(jsonObj.getString("description"));

            Rect rect =new Rect(imgGame.getLeft(),imgGame.getTop(),imgGame.getRight(),imgGame.getBottom());
            String url ="http://192.168.1.13/WebServer/Imagenes/games_icons/"+jsonObj.getString("img")+"";

            imgGame.setImageUrl(url,rect);
        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,"formato de json incorrecto");
        }
    }
    /*public void getTopJson(String jsoncad){
        JSONObject jsonObj =new JSONObject(jsoncad);
        JSONArray json = new JSONArray();
        for (int i=0;i<json.length();i++){
            sname.add(jsonObj.getJSONObject(i).getString("nomJugador"));
        }

    }*/
    }
