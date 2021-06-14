package com.example.e_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class admin_dashboard extends AppCompatActivity {
    ImageView img;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        img = findViewById(R.id.menuImg);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        navigationView.setItemIconTintList(null);


//        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
//        NavigationUI.setupWithNavController(navigationView,navController);

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.employees_admin:
                        Intent intent = new Intent( admin_dashboard.this , employee_admin.class);
                        startActivity(intent);
                        return true;
                    case R.id.managers_admin:
                        Intent intent2 = new Intent(admin_dashboard.this, manager_admin.class);
                        startActivity(intent2);
                        return true;
//                    case R.id.summary:
//                        Intent intent3 = new Intent(Employee_Dashboard.this, summary1.class);
//                        startActivity(intent3);
//                        return true;
//



                }
                return true;
            }
        });
    }
}