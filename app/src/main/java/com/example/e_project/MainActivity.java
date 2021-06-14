package com.example.e_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText uname;
    EditText pass;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("employee", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("e_id") && sharedPreferences.contains("username"))
        {
            Intent intent = new Intent(this, Employee_profile.class);
            startActivity(intent);
        }






        uname = findViewById(R.id.username);
        pass = findViewById(R.id.password);



        }

        public void login_func(View view)
        {

            if(uname.length()==0)
            {
                uname.requestFocus();
                uname.setError("FIELD CANNOT BE EMPTY");
            }

            else if(pass.length()==0)
            {
                pass.requestFocus();
                pass.setError("FIELD CANNOT BE EMPTY");
            }

            else if (uname.length()!=0 && pass.length()!=0) {

                String user_text = uname.getText().toString();
                String pass_text = pass.getText().toString();
                String type = "login";

                Background_Worker background_worker = new Background_Worker(this);
                background_worker.execute("login", user_text, pass_text);
            }

        }

    public void manager_login(View view)
    {
        Intent intent = new Intent(this, manager_login.class);
        startActivity(intent);
    }




}