package com.example.onlineexamingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDepartment extends AppCompatActivity{

    Spinner level,department;
    Button addDepartment;
    ImageView back;
    DatabaseReference addDepartmentReference;
    String levelName,departmentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);

        // DATABASE REFERENCE
        addDepartmentReference = FirebaseDatabase.getInstance().getReference();

        back = findViewById(R.id.backImage);
        level = findViewById(R.id.levelSpinner);
        department = findViewById(R.id.departmentSpinner);
        addDepartment = findViewById(R.id.add_department);

        ArrayAdapter<CharSequence> levelAdapter =ArrayAdapter.createFromResource(this,
                R.array.levels, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(levelAdapter);
        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> departmentAdapter =ArrayAdapter.createFromResource(this,
                R.array.departments, android.R.layout.simple_spinner_item);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(departmentAdapter);
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        addDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDepartmentReference.child("Levels").child(levelName).child("level name").setValue(levelName);
                addDepartmentReference.child("Levels").child(levelName).child(departmentName).setValue(departmentName);


            }
        });
    }


}
