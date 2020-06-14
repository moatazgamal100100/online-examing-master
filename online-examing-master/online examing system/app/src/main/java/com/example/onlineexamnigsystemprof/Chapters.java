package com.example.onlineexamnigsystemprof;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Chapters extends AppCompatActivity {

    ImageView addChapter;
    ListView chaptersList;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);


        addChapter = findViewById(R.id.addChapterImage);
        chaptersList = findViewById(R.id.chapterList);

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.chapter_item_row,R.id.chapterListItem,arrayList);
        chaptersList.setAdapter(adapter);


        addChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBulider = new AlertDialog.Builder(Chapters.this);
                View mView = getLayoutInflater().inflate(R.layout.alert_dialoge,null);
                TextView addChapter = mView.findViewById(R.id.addChapterTextView);
                final EditText chapterName = mView.findViewById(R.id.chapterInputEditText);
                Button ok = mView.findViewById(R.id.okButton);
                Button cancel = mView.findViewById(R.id.cancelButton);

                mBulider.setView(mView);
                final AlertDialog dialog = mBulider.create();
                dialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (chapterName.getText().toString().isEmpty()){
                            Toast.makeText(Chapters.this, "please enter the chapter name", Toast.LENGTH_SHORT).show();
                        }else {
                            arrayList.add(chapterName.getText().toString());
                            dialog.dismiss();
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        adapter.notifyDataSetChanged();
    }
}
