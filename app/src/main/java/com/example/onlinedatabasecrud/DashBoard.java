package com.example.onlinedatabasecrud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashBoard extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    FirebaseFirestore db;
    List<com.google.firebase.auth.UserInfo>userlist=new ArrayList<>();
    FirebaseUser CurrentUser;
    FirebaseAuth mAuth;
    String Name;
    String mail,name;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        name=pref.getString("name", null); // getting String
        mail=pref.getString("mail", null); // getting String

        Intent intent = getIntent();
        Name = intent.getStringExtra("Display");
        Log.i("Name:","Name:"+name);
        Toast.makeText(this, "DisplayName:"+Name, Toast.LENGTH_SHORT).show();

       // userlist=UserInfoList();

        updateNavHeader();
        //UserInfoList();

        //initialization
        mAuth=FirebaseAuth.getInstance();
        CurrentUser=mAuth.getCurrentUser();
        db=FirebaseFirestore.getInstance();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_setting,
                R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        Log.i("testing","UserName:"+userlist.get(0).getDisplayName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public List<UserInfo> UserInfoList(){

        //Read
        db.collection("UserInfo")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            com.google.firebase.auth.UserInfo user=documentSnapshot.toObject(com.google.firebase.auth.UserInfo.class);
                            userlist.add(user);
                            // Recyler View
                        }
                        Log.i("DisplayName:",userlist.get(0).getDisplayName());
                        Log.i("Mail:",userlist.get(0).getEmail());

                    }
                });
        return userlist;
    }

        public void updateNavHeader()
        {
            NavigationView navigationView = findViewById(R.id.nav_view);
            View headerView=navigationView.getHeaderView(0);

            TextView navName=headerView.findViewById(R.id.nav_username);
            navName.setText(name);
            TextView navEmail=headerView.findViewById(R.id.nav_email);
            navEmail.setText(mail);

            //GLide
            ImageView imageView=headerView.findViewById(R.id.nav_photo);
            Glide.with(this).load(storageReference).into(imageView);
            Log.i("StoreRef","store:"+storageReference);
        }



        }

