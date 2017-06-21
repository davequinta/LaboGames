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
import java.util.concurrent.ExecutionException;

public class Tab3Online extends Fragment{
    GridView simpleList;
    GridAdapter adapter;
    ArrayList imgList =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_online, container, false);

        simpleList = (GridView) rootView.findViewById(R.id.simpleGridView);
        String game = getArguments().getString("namegame");
        String condition = "images";
        try {
            new LoadData(this.getContext(),adapter,simpleList,game,condition).execute().get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException f) {
            f.printStackTrace();
        }
        return rootView;
    }
}
