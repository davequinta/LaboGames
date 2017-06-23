package com.quintanilla00025815.noticias.noticias;

/**
 * Created by Ale Campos on 21/6/2017.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;
import com.quintanilla00025815.labogames.R;

import java.util.ArrayList;

public class StaggeredGridLayoutAdapter extends CustomRecyclerViewAdapter {

    private Context activity;
    private ArrayList<NoticiasClass> noticias;
    private int screenWidth;
    private int screenHeight;
    static final String TITULO="titulo", SUBTITULO="subtitulo", DESC="descripcion", IMG="imagen";
    private static final int TYPE_FULL = 0;
    private static final int TYPE_HALF = 1;

    public StaggeredGridLayoutAdapter(Context activity, ArrayList<NoticiasClass> series) {
        this.activity = activity;
        this.noticias = series;
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }


    // Aqui se hace que los elementos que esta en posicion multiplo de 3
    // y la primera sea type_full y ocupen toda la row
    @Override
    public int getItemViewType(int position) {
        final int modeEight = position % 3;
        if(modeEight == 0){
            return TYPE_FULL;
        }
        return TYPE_HALF;
    }


    //Con esta modificacion se permite que el tamño de las cartas pueda usar una row
    // completa, o partes de una row
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent,final int viewType) {
        final View view = LayoutInflater.from(activity)
                .inflate(R.layout.modelnoticias, parent, false);

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                final int type = viewType;
                final ViewGroup.LayoutParams lp = view.getLayoutParams();
                if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams sglp =
                            (StaggeredGridLayoutManager.LayoutParams) lp;
                    switch (type) {
                        case TYPE_FULL:
                            sglp.setFullSpan(true);
                            break;
                        case TYPE_HALF:

                            sglp.setFullSpan(false);
                            sglp.width = (screenWidth/2)-5;
                            break;
                    }

                    //Alto del item
                    sglp.height = screenHeight/3;

                    view.setLayoutParams(sglp);

                    final StaggeredGridLayoutManager lm =
                            (StaggeredGridLayoutManager) ((RecyclerView) parent).getLayoutManager();

                    lm.invalidateSpanAssignments();

                }
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomRecycleViewHolder holder, int position) {
        final ViewHolder myHolder = (ViewHolder) holder;
        /*



        myHolder.poster.setImageResource(series.get(position).getImgId());

        SETEAR IMAGEN EN EL IMAGEVIEW "myHolder.poster" AQUI



        */
        Rect rect =new Rect(myHolder.poster.getLeft(),myHolder.poster.getTop(),myHolder.poster.getRight(),myHolder.poster.getBottom());
        myHolder.poster.setImageUrl(noticias.get(position).getImgNoticia(),rect);
        myHolder.title.setText(noticias.get(position).getTitulo());
        myHolder.subtitle.setText((noticias.get(position).getSubtitulo()));
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoticiasClass articulo = noticias.get(position);
                Intent intent = new Intent(activity,News.class);
                intent.putExtra(TITULO,articulo.getTitulo());
                intent.putExtra(SUBTITULO,articulo.getSubtitulo());
                intent.putExtra(DESC, articulo.getDescNoticia());
                intent.putExtra(IMG,articulo.getImgNoticia());
                activity.startActivity(intent);
            }
        });
        
    }


    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public class ViewHolder extends CustomRecycleViewHolder {
        private SmartImageView poster;
        private TextView title;
        private TextView subtitle;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = (SmartImageView) itemView.findViewById(R.id.NewsMImg);
            title = (TextView) itemView.findViewById(R.id.headline);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            cardView = (CardView) itemView.findViewById(R.id.NewsCardView);
        }
    }

}
