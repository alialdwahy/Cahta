package com.hrhera.cahdcaht.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hrhera.cahdcaht.R;
import com.hrhera.cahdcaht.ui.home.HomeFrag;
import com.hrhera.cahdcaht.ui.login.LogeinFrag;
import com.hrhera.cahdcaht.utl.DataMannger;
import com.hrhera.cahdcaht.utl.model.OnUserData;
import com.hrhera.cahdcaht.utl.model.User;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hidActionBar();
        OnUserData onUserData=new OnUserData(){
            @Override
            public void onDone() {
                AttatchFrag(new HomeFrag());
            }

            @Override
            public void onFail(String Msg) {
                Toast.makeText(MainActivity.this, Msg, Toast.LENGTH_SHORT).show();
                AttatchFrag(new LogeinFrag());
            }
        };

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    if (DataMannger.getInstance().isRemember(MainActivity.this)){
                        String phone = DataMannger.getInstance().getPhone();
                        String password = DataMannger.getInstance().getPass();
                        User user = new User();
                        user.setPhone(phone);
                        user.setPassword(password);
                        DataMannger.getInstance().startLogin(user, onUserData, MainActivity.this);
                        return;
                    }
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AttatchFrag(new LogeinFrag());
                    }
                });
            }
        }.start();


    }


    public void AttatchFrag(Fragment fragment) {
        findViewById(R.id.splashScreen).setVisibility(View.GONE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_conttiner, fragment).commit();

    }


    public void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
            actionBar.setTitle(title);
        }


    }

    public void hidActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


    public void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }



}
