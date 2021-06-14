package com.example.e_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class manager_register extends AppCompatActivity {

    EditText ed1, ed2 , ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_register);

        ed1 = findViewById(R.id.emp_name);
        ed2 = findViewById(R.id.emp_pass);
        ed3 = findViewById(R.id.emp_j_date);
    }

    public void manager_register(View view)
    {
        String un = ed1.getText().toString();
        String ps= ed2.getText().toString();
        String jd= ed3.getText().toString();



        Background_Worker bgworker = new Background_Worker(manager_register.this);
        bgworker.execute("register",un,ps,jd);
    }
}