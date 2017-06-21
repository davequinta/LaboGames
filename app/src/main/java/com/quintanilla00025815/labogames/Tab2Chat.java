package com.quintanilla00025815.labogames;

/**
 * Created by David on 19-Jun-17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Tab2Chat extends Fragment{

    private ListView listaPlayers;
    private PlayersAdapter listaPlayersAdaptador;
    //private friendsRepository repository;

    ArrayList pList =new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_chat, container, false);

        listaPlayers = (ListView) rootView.findViewById(R.id.Top);


        // Inicializar el adaptador con la fuente de datos.
        //repository = new friendsRepository();
/*
        pList.add(new Player(1,"Matus",R.drawable.user5,"Sagitario"));
        pList.add(new Player(2,"César",R.drawable.user6,"Yuusha"));
        pList.add(new Player(3,"Emilio",R.drawable.user7,"FalseDace"));
*/      String player="player";
        String data=getArguments().getString("namegame");

        listaPlayersAdaptador = new PlayersAdapter(this.getContext(), pList);
        //Relacionando la lista con el adaptador
        listaPlayers.setAdapter(listaPlayersAdaptador);

        try {
            new LoadData(this.getContext(),data,player).execute().get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException f) {
            f.printStackTrace();
        }
        return rootView;
    }
}