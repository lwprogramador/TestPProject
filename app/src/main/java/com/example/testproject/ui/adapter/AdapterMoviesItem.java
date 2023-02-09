package com.example.testproject.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testproject.GenericActivity;
import com.example.testproject.R;
import com.example.testproject.dto.DTOMovies;
import com.example.testproject.http.AppService;
import com.example.testproject.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class AdapterMoviesItem extends BaseAdapter {
    private List<DTOMovies> itemMovies;
    private MainActivity activity;

    public AdapterMoviesItem(List<DTOMovies>pMovies, MainActivity pActivity){
        itemMovies = pMovies;
        activity = pActivity;
    }

    public int getCount(){
        return itemMovies.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DTOMovies item = itemMovies.get(position);

        LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.activity_main_item_grid, parent, false);

        ImageView moviesIcon = (ImageView) convertView.findViewById(R.id.iv_movies_item);
        Picasso.get().load(AppService.getInstance().getProperties().getProperty("api_image") + item.getPoster_path()).into(moviesIcon);
        moviesIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View viewBtnShowControls) {
                AlertDialog alertInfoProduct;
                AlertDialog.Builder infoProduct;
                View viewAlertInfo = activity.getLayoutInflater().inflate(R.layout.alert_item_grid, null);

                ImageView movieIconVal = viewAlertInfo.findViewById(R.id.item_detail_avatar);
                Picasso.get().load(AppService.getInstance().getProperties().getProperty("api_image") + item.getPoster_path()).into(movieIconVal);


                TextView movieNameVal = viewAlertInfo.findViewById(R.id.item_detail_name);
                movieNameVal.setText(item.getTitle());

                TextView movieDetailVal = viewAlertInfo.findViewById(R.id.item_detail);
                movieDetailVal.setText(item.getOverview());

                infoProduct = new AlertDialog.Builder(activity);
                infoProduct.setView(viewAlertInfo);
                infoProduct.setPositiveButton(activity.getString(R.string.ms_ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                alertInfoProduct = infoProduct.create();
                alertInfoProduct.show();

            }
        });

        Log.e("Imagen URL", AppService.getInstance().getProperties().getProperty("api_image") + item.getPoster_path());
        return convertView;
    }
}
