package com.quintanilla00025815.labogames.LogReg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.quintanilla00025815.labogames.R;

//import com.squareup.picasso.Picasso;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        String url = "http://192.168.1.7/WebService/login/imgs/prueba1.png";
        ImageView iv = (ImageView) findViewById(R.id.img_success);

        //Picasso.with(this).load(url).into(iv);

    }
}
