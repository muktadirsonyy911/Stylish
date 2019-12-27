package com.example.onlinedatabasecrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<AuthUI.IdpConfig> providers;
    private  static  final int RC_SIGN_IN=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN  && resultCode == RESULT_OK) {
            // Successfully signed in
            String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
            SharedPreferences pref = getApplicationContext().getSharedPreferences("login", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();

            editor.putString("uid", key); // Storing string

            editor.commit();
            Intent intent=new Intent(MainActivity.this,Register.class);
            startActivity(intent);
            Log.i("OTP","keyId:"+key);
            // ...
        } else {

        }
    }
}
