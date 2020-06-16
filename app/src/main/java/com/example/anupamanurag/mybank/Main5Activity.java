package com.example.anupamanurag.mybank;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2,e;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        b1=(Button)findViewById(R.id.button8);
        b2=(Button)findViewById(R.id.button9);
        e= (EditText) findViewById(R.id.editText9);
        e1= (EditText) findViewById(R.id.editText6);

        e2= (EditText) findViewById(R.id.editText7);


        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                try {
                    db = openOrCreateDatabase("Banking1", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    Cursor c = db.rawQuery("Select * from bank", null);

                    c.moveToFirst();
                    String temp = "";
                    String s = e.getText().toString();
                    String s1 = e1.getText().toString();
                    String s2 = e2.getText().toString();
                    int flag = 0;

                    while (!c.isAfterLast()) {

                        String s3 = c.getString(0);
                        if (s3.equals(s)) {
                            flag = 1;
                        }
                        c.moveToNext();
                        if (flag == 1) {
                            Toast.makeText(Main5Activity.this, " updated!", Toast.LENGTH_LONG).show();
                            e.setText("");
                            e1.setText("");
                            e2.setText("");
                        }
                    }
                    if (flag == 0) {
                        Toast.makeText(Main5Activity.this, "employee id does not exist!", Toast.LENGTH_LONG).show();
                    }
                    ContentValues updatedValues = new ContentValues();
                    // Assign values for each row.
                    updatedValues.put("type", s1);
                    updatedValues.put("bal", s2);
                    String where = "id = ?";
                    db.update("bank", updatedValues, where, new String[]{s});
                } catch (SQLiteException e) {
                }

            }
        });
    }
}
