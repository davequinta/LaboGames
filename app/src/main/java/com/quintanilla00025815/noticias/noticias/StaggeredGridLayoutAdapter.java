package com.quintanilla00025815.noticias.noticias;

/**
 * Created by Ale Campos on 21/6/2017.
 */


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.quintanilla00025815.labogames.R;

import java.util.ArrayList;

public class StaggeredGridLayoutAdapter extends CustomRecyclerViewAdapter {

    private Activity activity;
    private ArrayList<NoticiasClass> noticias;
    private int screenWidth;
    private int screenHeight;

    private static final int TYPE_FULL = 0;
    private static final int TYPE_HALF = 1;

    public StaggeredGridLayoutAdapter(Activity activity, ArrayList<NoticiasClass> series) {
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
                            sglp.width = 545;
                            break;
                    }

                    //Alto del item
                    sglp.height = 500;

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
        myHolder.title.setText(noticias.get(position).getTitulo());
        myHolder.subtitle.setText((noticias.get(position).getSubtitulo()));
    }


    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public class ViewHolder extends CustomRecycleViewHolder {
        private ImageView poster;
        private TextView title;
        private TextView subtitle;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.NewsMImg);
            title = (TextView) itemView.findViewById(R.id.headline);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            cardView = (CardView) itemView.findViewById(R.id.NewsCardView);
        }
    }

}
