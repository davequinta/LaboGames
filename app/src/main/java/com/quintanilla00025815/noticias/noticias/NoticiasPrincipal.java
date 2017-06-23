package com.quintanilla00025815.noticias.noticias;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.quintanilla00025815.labogames.R;
import com.quintanilla00025815.labogames.TabbedGames;

import java.util.ArrayList;

public class NoticiasPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    String url="http://192.168.1.7/WebServer/noticias/noticias.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_menu);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final RecyclerView rv= (RecyclerView) findViewById(R.id.mRecycler);
        StaggeredGridLayoutManager StaggManager = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
        rv.setLayoutManager(StaggManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        ArrayList<NoticiasClass> Noticias = new ArrayList<>();
        
        // FALTA LLENAR EL ARREGLO DE NOTICIAS CON LOS DATOS DEL WEBSERVICE
        
        Downloader d=new Downloader(NoticiasPrincipal.this,url,rv);
        d.execute();
        

        /*
        String news="news";
                try {
            new LoadData(this.getContext(),StaggManager,rv,"",news).execute().get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException f) {
            f.printStackTrace();
        }
        * */
        rv.setAdapter(new StaggeredGridLayoutAdapter(this, Noticias));
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


        } else if (id == R.id.lol) {
            String game="League%20of%20Legends";
            Intent intent= new Intent(this,TabbedGames.class);
            intent.putExtra("namegame",game);
            startActivity(intent);
        } else if (id == R.id.dota) {
            String game="DOTA";
            Intent intent= new Intent(this,TabbedGames.class);
            intent.putExtra("namegame",game);
            startActivity(intent);
        } else if (id == R.id.csgo) {
            String game="Counter Strike Go";
            Intent intent= new Intent(this,TabbedGames.class);
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
