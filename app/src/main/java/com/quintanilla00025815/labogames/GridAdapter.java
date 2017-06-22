package com.quintanilla00025815.labogames;

import android.content.Context;
import android.graphics.Rect;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

import static com.quintanilla00025815.labogames.R.id.imageView;

/**
 * Created by David on 20-Jun-17.
 */

public class GridAdapter extends ArrayAdapter<ImagesJuego> {
    ArrayList<ImagesJuego> imgList = new ArrayList<>();
    //SmartImageView img;

    public GridAdapter(Context context, int textViewResourceId,ArrayList<ImagesJuego> images) {
        super(context,textViewResourceId,images);
        imgList = images;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);

        //img=(SmartImageView)convertView.findViewById(imageView);

        SmartImageView img = (SmartImageView) v.findViewById(R.id.imageView);

        Rect rect =new Rect(img.getLeft(),img.getTop(),img.getRight(),img.getBottom());
        ImagesJuego i = getItem(position);
        img.setImageUrl(i.getUrl(),rect);

        return v;

    }
}
