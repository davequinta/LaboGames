package com.quintanilla00025815.labogames;

/**
 * Created by David on 19-Jun-17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class Tab1Contacts extends  Fragment{

    TextView title;
    TextView desc;
    SmartImageView image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_contacts, container, false);

        String data=getArguments().getString("namegame");
        //La condicion para loaddata
        String condition = "game";
        title=(TextView)rootView.findViewById(R.id.gameName);
        desc=(TextView)rootView.findViewById(R.id.gameDescription);
        image=(SmartImageView)rootView.findViewById(R.id.gameImg);

        try {
            new LoadData(this.getContext(),title,desc,image,data,condition).execute().get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException f) {
            f.printStackTrace();
        }

        return rootView;
    }
}
