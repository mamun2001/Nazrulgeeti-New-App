package com.sleepingcat.nazrulgeeti_new;

//package com.example.nazrulgeetiapp;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.webkit.WebView;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sleepingcat.nazrulgeeti_new.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( ! CheckNetwork.isInternetAvailable(this)) //returns true if internet available
        {
            //if there is no internet do this
            setContentView(R.layout.activity_main);
            //Toast.makeText(this,"No Internet Connection, Chris",Toast.LENGTH_LONG).show();

            new AlertDialog.Builder(this) //alert the person knowing they are about to close
                    .setTitle("No internet connection available")
                    .setMessage("Please Check you're Mobile data or Wifi network.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    //.setNegativeButton("No", null)
                    .show();
        }else{
            bottomNavigationView = findViewById(R.id.bottom_navigation);
            loadFragment(new HomeFragment());

            bottomNavigationView.setOnItemSelectedListener(item -> {
                Fragment fragment = null;
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    fragment = new HomeFragment();
                } else if (id == R.id.nav_comments) {
                    fragment = new CommentsFragment();
                } else if (id == R.id.nav_contact) {
                    fragment = new ContactFragment();
                }
                if (fragment != null) {
                    loadFragment(fragment);
                }
                return true;
            });
        }
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        loadFragment(new HomeFragment());
//
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            Fragment fragment = null;
//            int id = item.getItemId();
//            if (id == R.id.nav_home) {
//                fragment = new HomeFragment();
//            } else if (id == R.id.nav_comments) {
//                fragment = new CommentsFragment();
//            } else if (id == R.id.nav_contact) {
//                fragment = new ContactFragment();
//            }
//            if (fragment != null) {
//                loadFragment(fragment);
//            }
//            return true;
//        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);

        if (currentFragment instanceof HomeFragment) {
            WebView webView = ((HomeFragment) currentFragment).getWebView();
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
                return;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> finishAffinity())
                .setNegativeButton("No", null)
                .show();
    }
}

class CheckNetwork {
    private static final String TAG = CheckNetwork.class.getSimpleName();
    public static boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info == null)
        {
            Log.d(TAG,"no internet connection");
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                Log.d(TAG," internet connection available...");
                return true;
            }
            else
            {
                Log.d(TAG," internet connection");
                return true;
            }
        }
    }
}
