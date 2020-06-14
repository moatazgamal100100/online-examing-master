package com.example.onlineexamnigsystemprof;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Level1Subjects extends AppCompatActivity {

    Button subject;
    ListView subjectList;
    ArrayList<String> subjectNames;
    ArrayAdapter subjectNamesAdapter;
    DatabaseReference subjectNameRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_subjects);

        subjectNameRef = FirebaseDatabase.getInstance().getReference("abdo").child("level 1").child("general").child("subjects");

        subjectList = findViewById(R.id.level_subjects_list);
        subjectNames = new ArrayList<>();
        subjectNamesAdapter = new ArrayAdapter<String>(Level1Subjects.this,android.R.layout.simple_list_item_1,subjectNames);
        subjectList.setAdapter(subjectNamesAdapter);


        subjectNameRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.getValue(String.class);
                subjectNames.add(name);
                subjectNamesAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                subjectNamesAdapter.notifyDataSetChanged();

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Level1Subjects.this,SubjectContent.class);
                startActivity(intent);
            }
        });

        subject = findViewById(R.id.subject);
        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level1Subjects.this,SubjectContent.class);
                startActivity(intent);
            }
        });



    }

    class customListAdapter extends ArrayAdapter<String>{

        Context context;
        String subName;
        customListAdapter(Context c,String s){
                super(c,R.layout.row,R.id.subjec_name);
                this.context=c;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.row,parent,false);
            TextView name = row.findViewById(R.id.subjec_name);





            return row;
        }
    }
}
