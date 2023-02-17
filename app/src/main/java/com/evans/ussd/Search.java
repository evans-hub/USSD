package com.evans.ussd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    ArrayList<Model> arrayList;
    RecyclerView recyclerView;
    FloatingActionButton actionButton, addButton, deleteButton;
    DatabaseHelper database_helper;
    CardView cardView;
    EditText ss;
    adapters mdisplayadapter;
    TextView tt, ttt;
    Boolean allVisible = false;
    private int requestcodes = 1;
    EditText search1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        actionButton = (FloatingActionButton) findViewById(R.id.add);

        database_helper = new DatabaseHelper(this);
        display();
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Search.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        main();
    }

public void vv(){
       Intent ii=new Intent(Search.this,Search.class);
       startActivity(ii);
}

    private void main() {
        if (ContextCompat.checkSelfPermission(Search.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Search.this, new String[]{Manifest.permission.CALL_PHONE}, requestcodes);
        } else {

        }

    }

    public void display() {

        arrayList = new ArrayList<>(database_helper.getUSSDs());
            GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapters adapter = new adapters(getApplicationContext(), this, arrayList);
            recyclerView.setAdapter(adapter);


        }






}