package com.example.ussd;

import static com.example.ussd.DatabaseHelper.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Model> arrayList;
    RecyclerView recyclerView;
    FloatingActionButton actionButton, addButton, deleteButton;
    DatabaseHelper database_helper;
    CardView cardView;
    EditText ss;
    displayAdapter mdisplayadapter;
    TextView tt, ttt;
    Boolean allVisible = false;
    private int requestcodes = 1;
    EditText search1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        actionButton = (FloatingActionButton) findViewById(R.id.add);
        TextView textView = findViewById(R.id.hey);
        tt = findViewById(R.id.t1);
        ttt = findViewById(R.id.t2);
        addButton = findViewById(R.id.addcode);
        deleteButton = findViewById(R.id.delete);

        database_helper = new DatabaseHelper(this);
        cardView = findViewById(R.id.cardd);
        display();
       search1 = findViewById(R.id.searchview);
        search1.getText().toString();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allVisible) {
                    tt.setVisibility(View.VISIBLE);
                    ttt.setVisibility(View.VISIBLE);
                    addButton.setVisibility(View.VISIBLE);
                    deleteButton.setVisibility(View.VISIBLE);
                    allVisible = true;
                    addButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showDialog();
                        }
                    });
                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(MainActivity.this,Search.class);

                            startActivity(intent);
                        }
                    });
                } else {
                    tt.setVisibility(View.GONE);
                    ttt.setVisibility(View.GONE);
                    addButton.setVisibility(View.GONE);
                    deleteButton.setVisibility(View.GONE);
                    allVisible = false;
                }


            }
        });
        main();

    }

  /*  private void nn() {
        search1.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                        arrayList.clear();
        String select_query="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(select_query,null);
        Model model=new Model();
                            if (String.valueOf(model.getDes()).toLowerCase().contains(s) || String.valueOf(model.getTitle()).toLowerCase().contains(s)) {
                                arrayList.add(model);


                        display();
                    }

                }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
*//*arrayList.clear();
database_helper.search(search1.getText().toString());
display();*//*
    }*/


    private void main() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, requestcodes);
        } else {

        }

    }

    public void display() {

        arrayList = new ArrayList<>(database_helper.getUSSDs());
        if (arrayList.isEmpty()) {
            cardView.setVisibility(View.VISIBLE);
        } else {
            cardView.setVisibility(View.GONE);
            GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            displayAdapter adapter = new displayAdapter(getApplicationContext(), this, arrayList);
            recyclerView.setAdapter(adapter);


        }


    }


    //display dialog
    public void showDialog() {
        final EditText title, des;
        Button submit;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.dialog);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        title = (EditText) dialog.findViewById(R.id.title);
        des = (EditText) dialog.findViewById(R.id.description);
        submit = (Button) dialog.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (title.getText().toString().isEmpty()) {
                    title.setError("Enter USSD");
                } else if (!title.getText().toString().startsWith("*")) {
                    title.setError("Invalid USSD");
                } else if (!title.getText().toString().endsWith("#")) {
                    title.setError("Invalid USSD");
                } else if (des.getText().toString().isEmpty()) {
                    des.setError("Enter USSD Description");
                } else {
                    database_helper.addUSSD(title.getText().toString(), des.getText().toString());
                    dialog.cancel();
                    display();
                }
            }
        });
    }

}