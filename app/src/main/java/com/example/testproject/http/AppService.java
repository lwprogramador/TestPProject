package com.example.testproject.http;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.example.testproject.GenericActivity;
import com.example.testproject.R;
import com.example.testproject.dto.DTOMovies;
import com.example.testproject.dto.DTOPageMovies;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class AppService {

    private static AppService instance = null;
    private Properties properties;
    private Context context;
    private Resources r;
    private GenericActivity genericActivity;
    private ConHTTP conHttp;

    public AppService(){
    }

    public static AppService getInstance() {
        if (instance == null) {
            instance = new AppService();
        }
        return instance;
    }

    public void setCurrentActivity(GenericActivity ga){
        genericActivity = ga;
    }

    public GenericActivity getCurrentActivity(){
        return genericActivity;
    }

    public void setContext(Context context) {
        this.context = context;

        conHttp = new ConHTTP();
        getProperties();
        r = context.getResources();
    }


    public Properties getProperties() {
        if (properties == null) {
            try {
                Resources resources = context.getResources();
                AssetManager assetManager = resources.getAssets();
                InputStream inputStream = assetManager.open("app.properties");
                properties = new Properties();
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }


    public List<DTOMovies> getMoviesPopulate(){
        try{
            if(conHttp.getMoviesPopulate().getStatus() == ConHTTP.STATUS_OK){
                genericActivity.showToastMessage(String.format(r.getString(R.string.toast_movies_dis), conHttp.getMoviesPopulate().getResult().size()));
                return conHttp.getMoviesPopulate().getResult();
            }else{
                genericActivity.showToastMessage(conHttp.getMoviesPopulate().getStatus() + ": " + conHttp.getMoviesPopulate().getMessage());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
