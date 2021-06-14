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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class apply_leave extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText ed1 ,ed2  ;

    int eid;
    String username;
    int mid;
    SharedPreferences sharedPreferences;
    TextView t1 , t2 , t3 , t4 , t5;

    ImageView img;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
DatePicker d1 ,d2;

    Spinner spinner;
    ArrayList<String> data = new ArrayList<>();
//    ArrayAdapter<String> typeadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);


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


        t4=(TextView)findViewById(R.id.textView38);
        t5=(TextView)findViewById(R.id.textView39);
        d1=(DatePicker)findViewById(R.id.datePicker1);
        d2=(DatePicker)findViewById(R.id.datePicker1);







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
                        Intent intent = new Intent( apply_leave.this , Employee_profile.class);
                        startActivity(intent);
                        return true;
                    case R.id.leave:
                        Intent intent2 = new Intent(apply_leave.this, apply_leave.class);
                        startActivity(intent2);
                        return true;
                    case R.id.summary:
                        Intent intent3 = new Intent(apply_leave.this, summary1.class);
                        startActivity(intent3);
                        return true;
                    case R.id.calendar:
                        Intent intent4 = new Intent(apply_leave.this, Calendar.class);
                        startActivity(intent4);
                        return true;
                    case R.id.logout:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("Id");
                        editor.remove("username");
                        editor.clear();

                        editor.commit();
                        Intent intent5 = new Intent(apply_leave.this,MainActivity.class);
                        startActivity(intent5);
                        return true;



                }
                return true;
            }



        });



        spinner = findViewById(R.id.spinnerleavetype);

        ed1 = findViewById(R.id.note);
        d1 = findViewById(R.id.datePicker1);
        d2 = findViewById(R.id.datePicker2);
        t1 = findViewById(R.id.username_id);
        t2 = findViewById(R.id.manager_id);
        t3 = findViewById(R.id.employee_id);




        sharedPreferences = getSharedPreferences("employee", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("e_id") && sharedPreferences.contains("username")&& sharedPreferences.contains("mid") )
        {
            eid = sharedPreferences.getInt("e_id",0);
            username=sharedPreferences.getString("username",null);
            mid = sharedPreferences.getInt("mid",0);


        }
        else
        {
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
        }

        t1.setText(username);
        t2.setText(String.valueOf(eid));
        t3.setText(String.valueOf(mid));




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource ( this, R.array.leavetype , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



    }
    public void apply_leave(View view){

        if(ed1.length()==0)
        {
            ed1.requestFocus();
            ed1.setError("FIELD CANNOT BE EMPTY");
        }

        else if(ed1.length()!=0) {
            t4.setText(d1.getDayOfMonth()+"-"+ (d1.getMonth() + 1)+"-"+d1.getYear());
            t5.setText(d2.getDayOfMonth()+"-"+ (d2.getMonth() + 1)+"-"+d2.getYear());

            String s_date = t4.getText().toString();
            String e_date = t5.getText().toString();
            String note = ed1.getText().toString();
            String lt = spinner.getSelectedItem().toString();
            String eid = t2.getText().toString();
            String m_id = t3.getText().toString();



            Background_Worker bgworker = new Background_Worker(this);
            bgworker.execute("applyleave",note,s_date, e_date ,lt , eid , m_id);
        }





    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}