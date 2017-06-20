package com.quintanilla00025815.labogames;

/**
 * Created by David on 19-Jun-17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class Tab3Online extends Fragment{
    GridView simpleList;
    ArrayList imgList =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_online, container, false);

        simpleList = (GridView) rootView.findViewById(R.id.simpleGridView);
        imgList.add(new ImagesJuego(R.drawable.b1,"Assa",1));
        imgList.add(new ImagesJuego(R.drawable.b2,"Assa",1));
        imgList.add(new ImagesJuego(R.drawable.b3,"Assa",1));
        imgList.add(new ImagesJuego(R.drawable.b4,"Assa",1));
        imgList.add(new ImagesJuego(R.drawable.b5,"Assa",1));
        imgList.add(new ImagesJuego(R.drawable.b1,"Assa",1));
        imgList.add(new ImagesJuego(R.drawable.b2,"Assa",1));
        imgList.add(new ImagesJuego(R.drawable.b3,"Assa",1));



        GridAdapter myAdapter=new GridAdapter(this.getContext(),R.layout.grid_view_items,imgList);
        simpleList.setAdapter(myAdapter);
        return rootView;

    }
}
