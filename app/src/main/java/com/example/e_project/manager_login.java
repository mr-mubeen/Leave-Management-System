package com.example.e_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class manager_login extends AppCompatActivity {
    EditText mname;
    EditText mpass;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        sharedPreferences = getSharedPreferences("manager", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("mid") && sharedPreferences.contains("mname"))
        {
            Intent intent = new Intent(this, leaves.class);
            startActivity(intent);
        }



        mname = findViewById(R.id.m_user);
        mpass = findViewById(R.id.m_pass);



    }

    public void m_login(View view)
    {
        if(mname.length()==0)
        {
            mname.requestFocus();
            mname.setError("FIELD CANNOT BE EMPTY");
        }

        else if(mpass.length()==0)
        {
            mpass.requestFocus();
            mpass.setError("FIELD CANNOT BE EMPTY");
        }
        else if (mname.length()!=0 && mpass.length()!=0) {
        String m_user_text = mname.getText().toString();
        String m_pass_text = mpass.getText().toString();
        String type = "mlogin";

        Background_Worker background_worker = new Background_Worker(this);
        background_worker.execute("mlogin", m_user_text, m_pass_text);
    }

    }

    public void employee_login(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
