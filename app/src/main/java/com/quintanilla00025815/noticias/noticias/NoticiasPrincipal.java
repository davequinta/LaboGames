package com.quintanilla00025815.noticias.noticias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quintanilla00025815.labogames.R;

public class NoticiasPrincipal extends AppCompatActivity {
    String url="http://192.168.1.7/WebService/noticias/noticias.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        final RecyclerView rv= (RecyclerView) findViewById(R.id.mRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

                Downloader d=new Downloader(NoticiasPrincipal.this,url,rv);
                d.execute();

    }
}