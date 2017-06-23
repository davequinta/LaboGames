package com.quintanilla00025815.noticias.noticias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.quintanilla00025815.labogames.R;

import java.util.ArrayList;

public class NoticiasPrincipal extends AppCompatActivity {
    String url="http://192.168.1.7/WebService/noticias/noticias.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        final RecyclerView rv= (RecyclerView) findViewById(R.id.mRecycler);
        StaggeredGridLayoutManager StaggManager = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
        rv.setLayoutManager(StaggManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        ArrayList<NoticiasClass> Noticias = new ArrayList<>();
        
        /* FALTA LLENAR EL ARREGLO DE NOTICIAS CON LOS DATOS DEL WEBSERVICE  
        
        Downloader d=new Downloader(NoticiasPrincipal.this,url,rv);
        d.execute();
        
        */
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
}
