package com.quintanilla00025815.labogames;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImage;
import com.github.snowdream.android.widget.SmartImageView;

import java.util.concurrent.ExecutionException;

public class GamesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView gameTitle;
    TextView gameDescription;
    SmartImageView smartImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Instacio las variables de los textos
        gameTitle = (TextView)findViewById(R.id.gameName);
        gameDescription = (TextView)findViewById(R.id.gameDescription);
        smartImageView = (SmartImageView)findViewById(R.id.gameImg) ;
        String TAG = "GamesActivity";
        Bundle extras=getIntent().getExtras();
        String get=extras.getString("namegame");
        Log.d(TAG, "onCreate: "+get+"");
        /*
        try {
            new LoadData(this,gameTitle,gameDescription,smartImageView,get).execute().get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException f) {
            f.printStackTrace();
        }*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.news) {

        }else if (id == R.id.lol) {
            String game="League of Legends";
            Intent intent= new Intent(this,GamesActivity.class);
            intent.putExtra("namegame",game);
            startActivity(intent);
        } else if (id == R.id.dota) {
            String game="DOTA";
            Intent intent= new Intent(this,GamesActivity.class);
            intent.putExtra("namegame",game);
            startActivity(intent);
        } else if (id == R.id.csgo) {
            String game="Counter Strike Go";
            Intent intent= new Intent(this,GamesActivity.class);
            intent.putExtra("namegame",game);
            startActivity(intent);
        } else if (id == R.id.action_settings) {

        } else if (id == R.id.favorites) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
