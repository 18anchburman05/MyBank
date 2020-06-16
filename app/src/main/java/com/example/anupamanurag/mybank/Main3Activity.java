package com.example.anupamanurag.mybank;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    Button b1;
    EditText e1,e2,e3,e4;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b1=(Button)findViewById(R.id.button6);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText4);
        e4=(EditText)findViewById(R.id.editText3);
        final Context context=this;
        try{
            db=openOrCreateDatabase("Banking1",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            db.execSQL("create table bank(id integer Primary Key,name text,type text,bal integer)");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting values through edit text to insert into database.
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                String s4=e4.getText().toString();

                ContentValues values=new ContentValues();
                values.put("id",s1);
                values.put("name",s2);
                values.put("type",s3);
                values.put("bal",s4);
                if((db.insert("bank",null,values))!=1)
                {
                    Toast.makeText(Main3Activity.this,"Account created!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Main3Activity.this,"Error!",Toast.LENGTH_SHORT).show();
                }
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                Intent i=new Intent(context,Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
