package com.hansellramos.tod.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HelpActivity extends AppCompatActivity {

    private static final String TAG = "HelpActivity";
    private final int RC_SIGN_IN = 2905;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFirebaseAuth != null && mFirebaseAuthStateListener != null) {
            mFirebaseAuth.addAuthStateListener(mFirebaseAuthStateListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAuth != null && mFirebaseAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mFirebaseAuthStateListener);
        }
    }

    private void init() {
        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(HelpActivity.this, SignInActivity.class));
                    finish();
                }
            }
        };
    }
}
