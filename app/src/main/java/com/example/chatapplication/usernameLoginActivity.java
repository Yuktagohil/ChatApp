package com.example.chatapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chatapplication.Utils.FirebaseUtil;
import com.example.chatapplication.model.userModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class usernameLoginActivity extends AppCompatActivity {

    EditText username;
    Button letmeinBtn;
    ProgressBar progressBar;
    String phoneNumber;
    userModel usermodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_username_login);

        username = findViewById(R.id.loginUsrname);
        letmeinBtn = findViewById(R.id.letmeinBtn);
        progressBar = findViewById(R.id.progressBar);

        phoneNumber = getIntent().getExtras().getString("phone");
        getUsername();

        letmeinBtn.setOnClickListener(v -> {
            setUsername();
        });

    }

    void setUsername() {
        String Username = username.getText().toString();
        if(Username.isEmpty() || Username.length()<3){
            username.setError("Username should be atleast 3 characters long");
            return;
        }
        setInProgress(true);
        if(usermodel != null){
            usermodel.setUsername(Username);
        } else {
            usermodel = new userModel(phoneNumber,Username, Timestamp.now(),FirebaseUtil.currentUserId());
        }

        FirebaseUtil.currentUserDetails().set(usermodel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    Log.d(TAG, "Username successfully set. Navigating to MainActivity.");
                    Intent intent = new Intent(usernameLoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Log.e(TAG, "Error setting username: ", task.getException());
                }
            }
        });
    }

    void  getUsername(){
        setInProgress(true);
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    usermodel = task.getResult().toObject(userModel.class);
                    if (usermodel != null) {
                        username.setText(usermodel.getUsername());
                    }
                }
            }
        });
    }

    void setInProgress(boolean inProgress) {
        if(inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            letmeinBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            letmeinBtn.setVisibility(View.VISIBLE);
        }
    }

}