package com.example.onlineexamnigsystemprof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectContent extends AppCompatActivity {
    Button chapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        chapters = findViewById(R.id.chaptersButton);
        chapters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectContent.this,Chapters.class);
                startActivity(intent);
            }
        });


    }
}
