package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddNoteActivity extends AppCompatActivity {

    TextInputEditText title, noteText;
    MaterialButton add;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        title=findViewById(R.id.title);
        noteText =findViewById(R.id.note);
        add=findViewById(R.id.add);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Note note=new Note();
                note.setNote(noteText.getText().toString());
                note.setTitle(title.getText().toString());
                firestore.collection(auth.getCurrentUser().getEmail()).add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();
                        startActivity(new Intent(AddNoteActivity.this,WelcomeActivity.class));
                        finishAffinity();
                    }
                });
            }
        });
    }
}