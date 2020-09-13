package com.example.ejshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, passwordInput, emailInput, confPasswordInput;
    Button btnRegister;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        mAuth = FirebaseAuth.getInstance();

            nameInput = findViewById(R.id.nameRegInput);
            passwordInput = findViewById(R.id.passwordRegInput);
            emailInput = findViewById(R.id.emailRegInput);
            confPasswordInput = findViewById(R.id.confPasswordRegInput);
            progressBar = findViewById(R.id.progressBar);
            btnRegister = findViewById(R.id.toRegister);

            /*if(mAuth.getCurrentUser()!=null){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }*/

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toReg();
                }
            });
        }
    private void toReg(){
        progressBar.setVisibility(View.VISIBLE);
        String email = emailInput.getText().toString(); //.trim()
        String password = passwordInput.getText().toString(); //.trim()
        String name = nameInput.getText().toString(); //.trim()
        String confPass = confPasswordInput.getText().toString();

        if(TextUtils.isEmpty(name)){
            nameInput.setError("Enter Name");
            return;
        }
        if(TextUtils.isEmpty(email)){
            emailInput.setError("enter Email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInput.setError("Not correct email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            passwordInput.setError("Enter password");
            return;
        }
        if(TextUtils.isEmpty(confPass)){
            confPasswordInput.setError("Enter password");
            return;
        }
        if(!password.equals(confPass)){
            confPasswordInput.setError("not the same password");
            return;
        }
        if(password.length()<5){
            passwordInput.setError("Password should be > 5");
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}