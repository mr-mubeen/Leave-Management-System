package com.example.e_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class manager_dashboard extends AppCompatActivity {
    ImageView img;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

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

        sharedPreferences = getSharedPreferences("manager", Context.MODE_PRIVATE);
//        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
//        NavigationUI.setupWithNavController(navigationView,navController);

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.leave_id_click:
                        Intent intent = new Intent( manager_dashboard.this , leaves.class);
                        startActivity(intent);
                        return true;
                    case R.id.myteam:
                        Intent intent2 = new Intent(manager_dashboard.this, team.class);
                        startActivity(intent2);
                        return true;
                    case R.id.mlogout:
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("Id");
                    editor.remove("username");
                    editor.clear();

                    editor.commit();
                    Intent intent5 = new Intent(manager_dashboard.this,manager_login.class);
                    startActivity(intent5);
                    return true;






                }
                return true;
            }
        });
    }




}