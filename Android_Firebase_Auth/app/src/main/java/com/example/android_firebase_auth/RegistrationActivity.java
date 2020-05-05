package com.example.android_firebase_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    private EditText emailTV, passwordTV,name,confirm_password,phone_number;
    private Button regBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        name=findViewById(R.id.Name);
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        phone_number = findViewById(R.id.phone_number);

        regBtn = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }



    ///its not complete please read carefully


    private void registerNewUser() {


        String nametext,email, password,confirm_pass,number;
        nametext=name.getText().toString();
        email = emailTV.getText().toString().trim();
        password = passwordTV.getText().toString().trim();
        confirm_pass = confirm_password.getText().toString().trim();
        number = phone_number.getText().toString().trim();


////////////email validation code

        if (TextUtils.isEmpty(email)) {

            emailTV.setError("Email is required");

             return;
        }
        else
        {
            ////email pattern code

            String emialPattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if(!email.matches(emialPattern))
            {
                emailTV.setError("Please enter valid email");
                return;
            }


       }


////////////email validation code





////////////password validation code
        if(TextUtils.isEmpty(password)) {
            passwordTV.setError("Password is required");
                        return;
        }
        else
        {
            if(passwordTV.length()<=6)
            {
                passwordTV.setError("Password must be < 6 ");
              return;
            }

        }

////////////password validation code end

        if(!password.equals(confirm_pass))
        {
            confirm_password.setError("Password doesn't match ");
            return;
        }


        if(phone_number.length() == 10)
        {

        }
        else
        {
            phone_number.setError("phonenumber should be 10 digit");
        }



        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
