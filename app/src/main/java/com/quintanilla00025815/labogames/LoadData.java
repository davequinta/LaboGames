package com.quintanilla00025815.labogames;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
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
import com.quintanilla00025815.noticias.noticias.*;

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

    //Para imagenes
    GridAdapter adapter2;
    GridView list2;

    //Para las noticias
    StaggeredGridLayoutAdapter sAdapter;
    RecyclerView rView;

    //Para la img del juego
    private ImageView gamesimg;

    //Para la

    static final String TAG = "LoadData";
    private Context context;
    private ProgressDialog pDialog;

    private String line;
    private String response = "";
    private String gameName;
    private String parameter;

    //Arreglo de jugadores

    ArrayList<Player> jugadores = new ArrayList<>();

    //Arreglo de imagenes
    ArrayList<ImagesJuego> images = new ArrayList<>();

    //Arreglo de noticias
    ArrayList<News> news =new ArrayList<>();

    //String ip ="10.45.12.48";
    //String ip = "192.168.1.3";

    String ip = "gamespm-com.stackstaging.com";

    //URLS para acceder a Webserver
    String url_images_games="http://"+ip+"/WebServer/Imagenes/games_icons/";
    String url_images_players="http://"+ip+"/WebServer/Imagenes/players_img/";
    String url_images_lol="http://"+ip+"/WebServer/Imagenes/Lol_img/";
    String url_images_dota="http://"+ip+"/WebServer/Imagenes/Dota_img/";
    String url_images_csgo="http://"+ip+"/WebServer/Imagenes/CSGO_img/";
    String url_images_games2="http://"+ip+"/WebServer/Imagenes/games_images/";
    String url_images_news="http://"+ip+"/WebServer/Imagenes/games_images/";

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
    public LoadData(Context c, PlayersAdapter adaptador,ListView lista, String game, String condition){
        context = c;
        gameName=game;
        adapter=adaptador;
        list=lista;
        parameter=condition;
    }
    public LoadData(Context c,GridAdapter adaptador,GridView lista,String game, String condition){
        context = c;
        gameName=game;
        adapter2=adaptador;
        list2=lista;
        parameter=condition;
    }
    public LoadData(Context c, StaggeredGridLayoutAdapter adapter, RecyclerView recycler,String game,String condition){
        context=c;
        sAdapter=adapter;
        rView=recycler;
        gameName=game;
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
            case "images":
                try {
                    getGameImages(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "news":
                try {
                    getNews(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
        adapter = new PlayersAdapter(context,jugadores);
        list.setAdapter(adapter);
    }

    //Funcion para las imagenes de cada juego
    private void getGameImages(String jsoncad) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsoncad);
        Log.d(TAG, "getGameImages: Entra"+jsonArr+"");

        Log.d("matus", "getGamesURL:"+jsonArr.getJSONObject(0).getString("url")+"");
        for (int i=0;i<jsonArr.length();i++){
            images.add(new ImagesJuego(
                    url_images_games2+jsonArr.getJSONObject(i).getString("url")));


            Log.d("Prro","getGamesImages: "+images.get(i).getUrl()+"");
        }
        adapter2 = new GridAdapter(context,R.layout.grid_view_items,images);
        list2.setAdapter(adapter2);

    }

    //Funcion para las noticias
    private void getNews(String jsoncad) throws JSONException{
        JSONArray jsonArr =new JSONArray();
        Log.d(TAG, "getNews: "+jsonArr+" and "+jsoncad+"");
        for (int i=0;i<jsonArr.length();i++){
            news.add(new News(jsonArr.getJSONObject(i).getInt("idNoticia"),
                    jsonArr.getJSONObject(i).getInt("idJuego"),
                    jsonArr.getJSONObject(i).getString("titulo"),
                    jsonArr.getJSONObject(i).getString("subtitulo"),
                    //Cambiar url por la de la carpeta correspondiente
                    url_news+jsonArr.getJSONObject(i).getString("imgNoticia"),
                    jsonArr.getJSONObject(i).getString("descNoticia")));
        }
        /*sAdapter =new StaggeredGridLayoutAdapter(context,R.layout.activity_news,news);
        rView.setAdapter(sAdapter);*/
    }
}
