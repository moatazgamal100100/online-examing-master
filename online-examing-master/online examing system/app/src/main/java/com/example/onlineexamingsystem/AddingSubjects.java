package com.example.onlineexamingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddingSubjects extends AppCompatActivity {

    ImageView back;
    Spinner professorSpinner,levelSpinner,departmentSpinner;
    Button addSubject;
    DatabaseReference addSubjectRef,professorsList;
    EditText subject;
    String levelName,departmentName,subjectName,professorName;

    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> profsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_subjects);

        //DATABASE
        addSubjectRef = FirebaseDatabase.getInstance().getReference();
        professorsList = FirebaseDatabase.getInstance().getReference("professorsList");
        // VIEWS
        back = findViewById(R.id.backImage);
        professorSpinner = findViewById(R.id.professor_spinner);
        levelSpinner = findViewById(R.id.subject_level_spinner);
        departmentSpinner = findViewById(R.id.subject_department_spinner);
        addSubject = findViewById(R.id.add_subject_Button);
        subject = findViewById(R.id.subject_name_editeText);


        // PROFESSOR SPINNER
        profsList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(AddingSubjects.this,R.layout.support_simple_spinner_dropdown_item,profsList);
        professorSpinner.setAdapter(adapter);
        professorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                professorName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        retrieve();

        // LEVEL SPINNER
        ArrayAdapter<CharSequence> levelAdapter =ArrayAdapter.createFromResource(this,
                R.array.levels, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // DEPARTMENT SPINNER
        ArrayAdapter<CharSequence> departmentAdapter =ArrayAdapter.createFromResource(this,
                R.array.departments, android.R.layout.simple_spinner_item);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departmentName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectName = subject.getText().toString();
                addSubjectRef.child("Subjects List").child(levelName).child(departmentName)
                        .child(subjectName).child("subject name").setValue(subjectName);

                addSubjectRef.child(professorName).child(levelName).child(departmentName)
                        .child(subjectName).child("subject name").setValue(subjectName);

                Toast.makeText(AddingSubjects.this, "data added", Toast.LENGTH_SHORT).show();
                subject.getText().clear();


            }
        });
    }
    public void retrieve(){

        professorsList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item:dataSnapshot.getChildren()){
                        profsList.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
