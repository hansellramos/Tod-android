package com.hansellramos.tod.android;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        validateLogin();

    }

    private void init() {
        //Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    private void validateLogin() {
        if (mFirebaseAuth != null) {
            if (mFirebaseAuth.getCurrentUser() != null) {
                goTo(MainActivity.class);
            } else {
                goTo(SignInActivity.class);
            }
        } else {
            Toast.makeText(SplashScreenActivity.this, getText(R.string.error_initializing_auth), Toast.LENGTH_LONG);
        }
    }

    private void goTo(final Class activityClass) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, activityClass));
                finish();
            }
        },1000);
    }
}
