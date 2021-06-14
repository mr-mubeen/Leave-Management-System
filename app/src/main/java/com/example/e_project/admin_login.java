package com.example.e_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class admin_login extends AppCompatActivity {

    EditText aname;
    EditText apass;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        sharedPreferences = getSharedPreferences("admin", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("aid") && sharedPreferences.contains("aname"))
        {
            Intent intent = new Intent(this, admin_dashboard.class);
            startActivity(intent);
        }



        aname = findViewById(R.id.a_user);
        apass = findViewById(R.id.a_pass);

    }

    public void m_login(View view)
    {
        String a_user_text = aname.getText().toString();
        String a_pass_text = apass.getText().toString();
        String type = "alogin";

        Background_Worker background_worker = new Background_Worker(this);
        background_worker.execute("alogin" , a_user_text , a_pass_text);


    }
}