package com.example.testproject;

import android.app.ProgressDialog;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.http.AppService;

public class GenericActivity extends AppCompatActivity {

    private ProgressDialog loadingDialog;
    private String message;
    private Thread currentThread;
    public final Handler mHandler = new Handler();

    @Override
    public void onResume() {
        super.onResume();
        AppService.getInstance().setCurrentActivity(GenericActivity.this);
    }

    public void executeThread(Thread currentThread) {
        showLoading();
        this.currentThread = currentThread;
        currentThread.start();
    }

    public void showToastMessage(String message) {
        this.message = message;
        mHandler.post(showToastMessage);
    }

    public void showLoading(){
        try{
            if (loadingDialog == null) {
                loadingDialog = new ProgressDialog(GenericActivity.this);
                loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                loadingDialog.setMessage(getString(R.string.ms_loading));
            }
            loadingDialog.setCancelable(false);
            loadingDialog.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void hideLoading() {
        try{
            if (loadingDialog != null)
                loadingDialog.dismiss();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    final Runnable showToastMessage = new Runnable() {
        public void run() {
            Toast toast= Toast.makeText(GenericActivity.this, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }
    };

}
