package com.example.notetakingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {


    MaterialToolbar toolbar;
    String email;
    FirebaseFirestore firestore;
    ArrayList<Note> notes;
    RecyclerView notesView;
    NotesAdapter adapter;
    FloatingActionButton add;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        email=auth.getCurrentUser().getEmail();
        notes=new ArrayList<>();
        add=findViewById(R.id.add);
        progressDialog=new ProgressDialog(this);
        progressDialog.show();
        notesView=findViewById(R.id.notes_view);
        firestore.collection(email).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots){
                    Note note=queryDocumentSnapshot.toObject(Note.class);
                    notes.add(note);
                }
                adapter=new NotesAdapter(notes);
                notesView.setAdapter(adapter);
                notesView.setLayoutManager(new LinearLayoutManager(WelcomeActivity.this));
                progressDialog.dismiss();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,AddNoteActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}