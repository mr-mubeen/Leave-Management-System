package com.example.e_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Employee_Dashboard extends AppCompatActivity {
    ImageView img;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__dashboard);

        img = findViewById(R.id.menuImg);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        sharedPreferences = getSharedPreferences("employee", Context.MODE_PRIVATE);



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        Intent intent = new Intent( Employee_Dashboard.this , Employee_profile.class);
        startActivity(intent);



        navigationView.setItemIconTintList(null);


//        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
//        NavigationUI.setupWithNavController(navigationView,navController);

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.profile:
                        Intent intent = new Intent( Employee_Dashboard.this , Employee_profile.class);
                        startActivity(intent);
                        return true;
                    case R.id.leave:
                        Intent intent2 = new Intent(Employee_Dashboard.this, apply_leave.class);
                        startActivity(intent2);
                        return true;
                    case R.id.summary:
                        Intent intent3 = new Intent(Employee_Dashboard.this, summary1.class);
                        startActivity(intent3);
                        return true;
                    case R.id.calendar:
                        Intent intent4 = new Intent(Employee_Dashboard.this, Calendar.class);
                        startActivity(intent4);
                        return true;
                    case R.id.logout:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("Id");
                        editor.remove("username");
                        editor.clear();

                        editor.commit();
                        Intent intent5 = new Intent(Employee_Dashboard.this,MainActivity.class);
                        startActivity(intent5);
                        return true;



                }
                return true;
            }



        });

    }

}