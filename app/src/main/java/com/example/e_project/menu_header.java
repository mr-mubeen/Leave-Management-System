package com.example.e_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

public class menu_header extends AppCompatActivity {


    String textuser;
    TextView text41;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_header);

//        text41 = findViewById(R.id.textView41);
//
//        sharedPreferences = getSharedPreferences("employee", Context.MODE_PRIVATE);
//
//        if(sharedPreferences.contains("e_id") && sharedPreferences.contains("username")&& sharedPreferences.contains("mid") )
//        {
//
//            textuser=sharedPreferences.getString("username",null);
//
//
//
//        }
//        else
//        {
////            Intent intent = new Intent(this,MainActivity.class);
////            startActivity(intent);
//        }
//
//        text41.setText(textuser);



//        sharedPreferences = getSharedPreferences("manager", Context.MODE_PRIVATE);
//        if (sharedPreferences.contains("mid") && sharedPreferences.contains("mname")) {
//
//            textuser = sharedPreferences.getString("username", null);
////          mid = sharedPreferences.getInt("mid",0);
//
//            text3.setText(textuser);
//
//
//        }
    }
}