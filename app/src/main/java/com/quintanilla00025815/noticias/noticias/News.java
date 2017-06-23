package com.quintanilla00025815.noticias.noticias;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImage;
import com.github.snowdream.android.widget.SmartImageView;
import com.quintanilla00025815.labogames.R;

/**
 * Created by Ale Campos on 22/6/2017.
 */

public class News extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_news);
        String title = getIntent().getStringExtra("titulo");
        String subtitle= getIntent().getStringExtra("subtitulo");
        String body= getIntent().getStringExtra("descripcion");
        String imagen=getIntent().getStringExtra("imagen");
        TextView headline = (TextView) findViewById(R.id.NewsTitle);
        TextView sub = (TextView) findViewById(R.id.NewsSubtitle);
        TextView desc = (TextView) findViewById(R.id.NewsDetails);
        SmartImageView image = (SmartImageView) findViewById(R.id.NewsImg);
        headline.setText(title);
        sub.setText(subtitle);
        desc.setText(body);
        
    
        //SUSTITUIR POR PICASSO "Picasso.with(this).load("URL"+imagen).into(image)
        image.setImageResource(R.drawable.kennen);
        Rect rect =new Rect(image.getLeft(),image.getTop(),image.getRight(),image.getBottom());
        image.setImageUrl(imagen,rect);

        //

    }
}
