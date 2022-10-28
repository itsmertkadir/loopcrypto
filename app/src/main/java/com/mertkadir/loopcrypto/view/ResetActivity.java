package com.mertkadir.loopcrypto.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.mertkadir.loopcrypto.databinding.ActivityResetBinding;

public class ResetActivity extends AppCompatActivity {

    ActivityResetBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityResetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);

        auth = FirebaseAuth.getInstance();
    }

    public void ResetP (View view) {
        String email ;
        email = binding.editTextTextEmailAddress.getText().toString();

        if (email.equals("")) {
            Toast.makeText(this, "Enter Email!", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    binding.textViewLog.setText("Send mail!");
                    Intent intent = new Intent(ResetActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }


    }

    public void backMenu (View view) {
        Intent intent = new Intent(ResetActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}