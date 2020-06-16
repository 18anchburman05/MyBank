package com.example.anupamanurag.mybank;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {

    Button b1,b2;
    EditText e;
    SQLiteDatabase db;
    //SQLiteOpenHelper d;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        b1=(Button)findViewById(R.id.button10);
        b2=(Button)findViewById(R.id.button11);
        e=(EditText)findViewById(R.id.editText8);
        final Context context=this;
        try
        {
            db=openOrCreateDatabase("Banking1",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        }
        catch(SQLiteException e)
        {
            e.printStackTrace();
            System.out.print("ERROR.............");
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t=(e.getText().toString());


                try
                {
                    String d="DELETE FROM bank WHERE id="+t;
                    db.execSQL(d);
                }
                catch(Exception e)
                {
                    System.out.print("Error..................");
                }

                e.setText("");
                Toast.makeText(Main6Activity.this, "Deleted...",Toast.LENGTH_LONG).show();
             //   Intent i=new Intent(context,Main3Activity.class);
               // startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                String p = (e.getText().toString());
                try {
                    db = openOrCreateDatabase("Banking1", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    Cursor c = db.rawQuery("Select * from bank where id=" + p, null);

                    c.moveToFirst();
                    String temp = "";
                    while (!c.isAfterLast()) {
                        String s1 = c.getString(0);
                        String s2 = c.getString(1);
                        String s3 = c.getString(2);
                        String s4 = c.getString(3);
                        // temp = temp + "\n id:" + s1 + "\n name:" + s2 + "A/C Type:" + s3 + "Balance" + s4;
                        c.moveToNext();


                        ContentValues values = new ContentValues();
                        values.put("id", s1);
                        values.put("name", s2);
                        values.put("type", s3);
                        values.put("bal", s4);
                        if ((db.insert("bank", null, values)) != 1) {
                            Toast.makeText(Main6Activity.this, "Retrieved!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Main6Activity.this, "Error while recycling!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //  t1.setText(temp);

                } catch (SQLException e) {

                }

            }
        });
    }
}
