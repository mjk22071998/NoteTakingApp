package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText name, email, password, confirm;
    MaterialButton signUp;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        progressDialog=new ProgressDialog(this);
        confirm=findViewById(R.id.cpassword);
        signUp=findViewById(R.id.sign_up);
        auth=FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if(password.getText().toString().equals(confirm.getText().toString())){
                    auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            authResult.getUser().sendEmailVerification();
                            Intent intent=new Intent(SignUpActivity.this,WelcomeActivity.class);
                            intent.putExtra("email",authResult.getUser().getEmail());
                            startActivity(intent);
                            finish();
                            progressDialog.dismiss();
                        }
                    });
                } else {
                    Toast.makeText(SignUpActivity.this, "Password Does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}