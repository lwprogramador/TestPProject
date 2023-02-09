package com.example.testproject.ui;

import android.os.Bundle;
import android.widget.GridView;

import com.example.testproject.GenericActivity;
import com.example.testproject.R;
import com.example.testproject.dto.DTOMovies;
import com.example.testproject.http.AppService;
import com.example.testproject.ui.adapter.AdapterMoviesItem;

import java.util.List;

public class MainActivity extends GenericActivity {

    private List<DTOMovies> moviesList;
    private GridView gridItemsMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(getString(R.string.title_main_activity));

        gridItemsMovies = findViewById(R.id.gv_movies);
    }

    @Override
    public void onResume() {
        super.onResume();

        executeThread(new Thread() {
            public void run() {
                try {
                    moviesList = AppService.getInstance().getMoviesPopulate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.post(mUpdateView);
            }
        });
    }

    final Runnable mUpdateView = new Runnable() {
        public void run() {
            AdapterMoviesItem adapter = new AdapterMoviesItem(moviesList, MainActivity.this);
            gridItemsMovies.setAdapter(adapter);
            hideLoading();
        }
    };


}