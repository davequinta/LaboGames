package com.quintanilla00025815.labogames;

import android.graphics.Rect;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David on 20-Jun-17.
 */

public class PlayersAdapter extends ArrayAdapter<Player> {


    ArrayList<Player> jugador;
    String ip ="10.45.12.48";
    String url_players="http://"+ip+"/WebServer/jugadoresjuego.php?namegame=";

    public PlayersAdapter(Context context,  List<Player> objects) {

        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.player_item,
                    parent,
                    false);
        }

        //referencias de vistas de list_item_friend.xml
        SmartImageView avatar = (SmartImageView) convertView.findViewById(R.id.img);
        TextView name = (TextView) convertView.findViewById(R.id.Name);
        TextView msg = (TextView) convertView.findViewById(R.id.lastMsg);
        //TextView date = (TextView) convertView.findViewById(R.id.date);
        String TAG="Lista: ";
        Player p= getItem(position);
        Log.d(TAG, "getView:"+p+"");

        Rect rect =new Rect(avatar.getLeft(),avatar.getTop(),avatar.getRight(),avatar.getBottom());

        avatar.setImageUrl(p.getImgJugador(),rect);

        name.setText(p.getNikcname());
        msg.setText(p.getNomJugador());

        return convertView;
    }
}
