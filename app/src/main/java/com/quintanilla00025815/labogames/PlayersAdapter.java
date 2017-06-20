package com.quintanilla00025815.labogames;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David on 20-Jun-17.
 */

public class PlayersAdapter extends ArrayAdapter<Player> {
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
        ImageView avatar = (ImageView) convertView.findViewById(R.id.img);
        TextView name = (TextView) convertView.findViewById(R.id.Name);
        TextView msg = (TextView) convertView.findViewById(R.id.lastMsg);
        //TextView date = (TextView) convertView.findViewById(R.id.date);

        Player p= getItem(position);

        avatar.setImageResource(p.getImgJugador());
        name.setText(p.getNikcname());
        msg.setText(p.getNomJugador());

        return convertView;
    }
}
