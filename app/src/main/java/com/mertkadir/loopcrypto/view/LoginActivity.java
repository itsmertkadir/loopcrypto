package com.mertkadir.loopcrypto.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mertkadir.loopcrypto.databinding.ActivityLoginActivtiyBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginActivtiyBinding binding;
    FirebaseAuth auth;
    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLoginActivtiyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);

        auth = FirebaseAuth.getInstance();

    }
    public void login (View view) {
        email = binding.editTextEmailLogin.getText().toString();
        password = binding.editTextTextPassword.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter Email and Password!", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent goToMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(goToMain);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Register (View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void resetPassword (View view) {

        Intent intent = new Intent(LoginActivity.this, ResetActivity.class);
        startActivity(intent);
        finish();

    }


}