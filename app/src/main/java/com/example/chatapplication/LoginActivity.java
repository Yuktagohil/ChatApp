package com.example.chatapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    ProgressBar progressBar;
    Button OtpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        countryCodePicker = findViewById(R.id.loginCountrycode);
        phoneInput = findViewById(R.id.loginMobile);
        progressBar = findViewById(R.id.progressBar);
        OtpBtn = findViewById(R.id.otpBtn);

        progressBar.setVisibility(View.GONE);

        countryCodePicker.registerCarrierNumberEditText(phoneInput);

        OtpBtn.setOnClickListener( (v) -> {
            if (!countryCodePicker.isValidFullNumber()) {
                phoneInput.setError("Enter valid phone number");
                return;
            }
            Intent intent = new Intent(LoginActivity.this,OTPloginActivity.class);
            intent.putExtra("phone",countryCodePicker.getFullNumberWithPlus());
            startActivity(intent);
        });

    }
}