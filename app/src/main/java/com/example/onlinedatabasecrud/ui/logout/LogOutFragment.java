package com.example.onlinedatabasecrud.ui.logout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.onlinedatabasecrud.MainActivity;
import com.example.onlinedatabasecrud.R;
import com.google.firebase.auth.FirebaseAuth;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class LogOutFragment extends Fragment {

    private LogOutViewModel logOutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModel =
                ViewModelProviders.of(this).get(LogOutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        final TextView textView = root.findViewById(R.id.nav_logout);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*
                Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                SharedPreferences pref = getActivity().getSharedPreferences("login", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("uid", null);
                editor.commit();

               // FirebaseAuth.getInstance().signOut();

                Log.i("logout:","uidTesting:"+FirebaseAuth.getInstance().getUid());
                Log.i("login","local uid save:"+pref.getString("uid",null));
                Intent loginActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(loginActivity);

               // ((Activity) getActivity()).overridePendingTransition(0, 0);
*/
            }
        });

        logOutViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}