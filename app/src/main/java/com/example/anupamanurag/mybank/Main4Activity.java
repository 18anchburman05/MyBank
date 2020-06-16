package com.example.anupamanurag.mybank;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {
    Button b1, b2;
    EditText e1;
    TextView t1;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        b1=(Button)findViewById(R.id.button7);
        b2=(Button)findViewById(R.id.button12);
        e1=(EditText) findViewById(R.id.editText5);
        t1=(TextView) findViewById(R.id.textView2);
       b1.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("WrongConstant")
           @Override
           public void onClick(View v) {

               try {
                   db = openOrCreateDatabase("Banking1", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                   Cursor c = db.rawQuery("Select * from bank", null);

                   c.moveToFirst();
                   String temp = "";
                   while (!c.isAfterLast()) {
                       String s1 = c.getString(0);
                       String s2 = c.getString(1);
                       String s3 = c.getString(2);
                       String s4 = c.getString(3);
                       temp = temp + "\n id:" + s1 + "\t name:" + s2 + "\t A/C Type:" + s3 + "\t Balance" + s4;
                       c.moveToNext();
                   }
                   t1.setText(temp);

               } catch (SQLException e) {
               }
           }
       });

      b2.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("WrongConstant")
           @Override
           public void onClick(View v) {
               String p=(e1.getText().toString());
               try {
                   db = openOrCreateDatabase("Banking1", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                   Cursor c = db.rawQuery("Select * from bank where id="+p, null);

                   c.moveToFirst();
                   String temp = "";
                   while (!c.isAfterLast()) {
                       String s1 = c.getString(0);
                       String s2 = c.getString(1);
                       String s3 = c.getString(2);
                       String s4 = c.getString(3);
                       temp = temp + "\n id:" + s1 + "\n name:" + s2 + "A/C Type:" + s3 + "Balance" + s4;
                       c.moveToNext();
                   }
                   t1.setText(temp);

               } catch (SQLException e) {

               }
           }
       });
    }
}