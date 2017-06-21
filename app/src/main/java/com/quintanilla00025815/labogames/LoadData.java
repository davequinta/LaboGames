package com.quintanilla00025815.labogames;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
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
    PlayersAdapter adapter;
    ListView list;

    //Arreglos para el top
    private ArrayList sname;
    private ArrayList snickname;
    private ArrayList simg;

    //Para la img del juego
    private ImageView gamesimg;

    static final String TAG = "LoadData";
    private Context context;
    private ProgressDialog pDialog;

    private String line;
    private String response = "";
    private String gameName;
    private String parameter;

    //Arreglo de jugadores

    ArrayList<Player> jugadores = new ArrayList<>();

    String ip ="10.45.12.48";


    //URLS para acceder a Webserver
    String url_images_games="http://"+ip+"/WebServer/imagenes/games_icons/";
    String url_images_players="http://"+ip+"/WebServer/imagenes/players_img/";
    String url_images_lol="http://"+ip+"/WebServer/imagenes/Lol_img/";
    String url_images_dota="http://"+ip+"/WebServer/imagenes/Dota_img/";
    String url_images_csgo="http://"+ip+"/WebServer/imagenes/CSGO_img/";

    //URLS para acceder a php
    String url_game="http://"+ip+"/WebServer/informacionjuego.php?namegame=";
    String url_players="http://"+ip+"/WebServer/jugadoresjuego.php?namegame=";
    String url_images="http://"+ip+"/WebServer/imagenesjuego.php?namegame=";
    String url_news="http://"+ip+"/WebServer/noticiasjuego.php";


    //Constructor para descripcion del juego
    public LoadData(Context c, TextView namegame, TextView desc,SmartImageView img,String nameGame,String condition) {

        context = c;
        game = namegame;
        description = desc;
        imgGame = img;
        gameName = nameGame;
        parameter = condition;
    }
    public LoadData(){}
    //Constructor para top jugadores de ese juego
   //* public LoadData(Context c,String nameGame,String condition){

        /*context = c;
        name = player;
        nickname = nplayer;
        img = imgPlayer;
        gameName = nameGame;
        parameter = condition;
    }*/
    public LoadData(Context c,PlayersAdapter adaptador,ListView lista,String game, String condition){
        context = c;
        gameName=game;
        adapter=adaptador;
        list=lista;
        parameter=condition;
    }
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

        switch (parameter){
            case "game":
                response = enviarjuegosGET(gameName,url_game,parameter);
                break;
            case "player":
                response = enviarjuegosGET(gameName,url_players,parameter);
                break;
            case "images":
                response = enviarjuegosGET(gameName,url_images,parameter);
                break;
            case "news":
                response = enviarjuegosGET(gameName,url_news,parameter);
        }
        pDialog.dismiss();
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        //getGameDescriptionJSON(response);

        switch (parameter){
            case "game":
                getGameDescriptionJSON(response);
                break;
            case "player":
                try {
                    getTopPlayers(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    //La funcion que envia el formato json
    public String enviarjuegosGET(String namegame,String url,String parameter){
        URL uri = null;
        String parametro = parameter;
        String linea ="";
        StringBuilder result = null;
        int respuesta = 0;
        Log.d(TAG, "enviarjuegosGET: Todo cool "+namegame+"");
        try {
            if (parametro.equals("news")){
                uri = new URL(url);
            }
            else {
                uri = new URL(url+namegame+"");
            }
            Log.d(TAG, "enviarjuegosGET: "+uri+"");
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
    //Funcion para la descripcion del juego
    public void getGameDescriptionJSON(String jsoncad){
        try{
            //convirtiendo json string a json object
            JSONObject jsonObj = new JSONObject(jsoncad);
            game.setText(jsonObj.getString("title"));
            description.setText(jsonObj.getString("description"));

            Rect rect =new Rect(imgGame.getLeft(),imgGame.getTop(),imgGame.getRight(),imgGame.getBottom());
            String url =url_images_games+jsonObj.getString("img")+"";

            imgGame.setImageUrl(url,rect);
        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,"formato de json incorrecto");
        }
    }
    //Funcion para la lista de jugadores
    public void getTopPlayers(String jsoncad) throws JSONException {

        JSONArray jsonArr = new JSONArray(jsoncad);
        Log.d(TAG, "ArrayJson:"+jsonArr+"");
        Log.d(TAG, "Posc1:"+jsonArr.getJSONObject(0).getInt("idJugador")+"");
        for (int i=0;i<jsonArr.length();i++){
            jugadores.add(new Player(jsonArr.getJSONObject(i).getInt("idJugador"),
                    jsonArr.getJSONObject(i).getString("nomJugador"),
                    url_images_players+jsonArr.getJSONObject(i).getString("imgJugador"),
                    jsonArr.getJSONObject(i).getString("nickname")));


            Log.d(TAG, "getTopPlayers: "+jugadores.get(i).getIdJugador()+"");
            Log.d(TAG, "getTopPlayers: "+jugadores.get(i).getImgJugador()+"");
            Log.d(TAG, "getTopPlayers: "+jugadores.get(i).getNikcname()+"");
            Log.d(TAG, "getTopPlayers: "+jugadores.get(i).getNomJugador()+"");

        }
       // Log.d(TAG, "getTopPlayers: "+jugadores.get(0)+"");
        list.setAdapter(adapter);
    }


    public String prueba(){
        String comentario="Se mega va";
        return comentario;
    }
    /*public void getTopJson(String jsoncad){
        JSONObject jsonObj =new JSONObject(jsoncad);
        JSONArray json = new JSONArray();
        for (int i=0;i<json.length();i++){
            sname.add(jsonObj.getJSONObject(i).getString("nomJugador"));
        }

    }*/
    }
