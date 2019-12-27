package com.example.onlinedatabasecrud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Register extends AppCompatActivity {

    EditText rgName,rgEmail,rgAddress,rgPhone;
    String Name,Email,Address,Phone;
    Button sign;
    RadioButton rb;
    ImageView rgimage;
    FirebaseStorage storage;
    StorageReference storageReference;


    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    Uri pickedImageUri;

    FirebaseFirestore db;
    List<UserInfo>userlist=new ArrayList<>();


    private Button btnChoose, btnUpload;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;



    String gender="Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        db=FirebaseFirestore.getInstance();
        init();

        //Read
    //RegisterDataList();

    }

    private void RegisterDataList() {

        db.collection("UserInfo")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            UserInfo user=documentSnapshot.toObject(UserInfo.class);
                            userlist.add(user);

                            // Recyler View
                        }
                        Log.i("UserList",userlist.toString());

                    }
                });



    }

    private void init() {

        //find view  xml
        rgName=findViewById(R.id.NameET);
        rgEmail=findViewById(R.id.EmailET);
        rgAddress=findViewById(R.id.AddressET);
        rgPhone=findViewById(R.id.PhoneET);
        rgimage=findViewById(R.id.imageView);

        rgimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        // button
        sign=findViewById(R.id.signBTN);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name=rgName.getText().toString();
                Email=rgEmail.getText().toString();
                Address=rgAddress.getText().toString();
                Phone=rgPhone.getText().toString();

                //Verify all the fields

                if (Name.isEmpty() || Email.isEmpty() || Address.isEmpty() || Phone.isEmpty())
                {
                    Toast.makeText(Register.this, "Please verify all the fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    CreateUser();
                    uploadImage();
                }

            }
        });

        //Radio Group




        //rb=findViewById(R.id.NameET);

    }


    private void CreateUser() {

        // Firebase Auth
        String userAuthId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        // key
        //String userId= UUID.randomUUID().toString();


        Map<String,Object> userInfo=new HashMap<>();
        userInfo.put("uAuthId",userAuthId);
        userInfo.put("uName",Name);
        userInfo.put("uPhone",Phone);
        userInfo.put("uMail",Email);
        userInfo.put("uAddress",Address);
        userInfo.put("uGender",gender);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("name",Name); // Storing string
        editor.putString("mail",Email); // Storing string
        editor.commit(); // commit changes

        db.collection("UserInfo")
                .document(userAuthId)
                .set(userInfo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Progress Bar
                        // Lovely Dailog
                        Toast.makeText(Register.this, "Register is Successfull! ", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), DashBoard.class);
                        intent.putExtra("Display:",Name);
                        startActivity(intent);
                    }
                });
    }

    public void Gender(View view) {


        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonM:
                if (checked)
                    // Pirates are the best
                    gender="Male";
                    Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radioButtonF:
                if (checked)
                    // Ninjas rule
                    gender="Female";
                    Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
                    break;
        }

    }


    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Register.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Register.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                rgimage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
