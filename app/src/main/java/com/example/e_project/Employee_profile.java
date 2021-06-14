package com.example.e_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Employee_profile extends AppCompatActivity {
    ListView elist;
    int eid;
    String username;
    TextView et1;
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    String employeeid;
    TextView t10 , t11 , t12 ,t13 ,t14;


    ImageView img;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    ArrayList<String> edata = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

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


        t11 = findViewById(R.id.textView11);



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
                        Intent intent = new Intent( Employee_profile.this , Employee_profile.class);
                        startActivity(intent);
                        return true;
                    case R.id.leave:
                        Intent intent2 = new Intent(Employee_profile.this, apply_leave.class);
                        startActivity(intent2);
                        return true;
                    case R.id.summary:
                        Intent intent3 = new Intent(Employee_profile.this, summary1.class);
                        startActivity(intent3);
                        return true;
                    case R.id.calendar:
                        Intent intent4 = new Intent(Employee_profile.this, Calendar.class);
                        startActivity(intent4);
                        return true;
                    case R.id.logout:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("Id");
                        editor.remove("username");
                        editor.clear();

                        editor.commit();
                        Intent intent5 = new Intent(Employee_profile.this,MainActivity.class);
                        startActivity(intent5);
                        return true;



                }
                return true;
            }



        });





        sharedPreferences = getSharedPreferences("employee", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("e_id") && sharedPreferences.contains("username"))  {
            eid = sharedPreferences.getInt("e_id", 0);
            username = sharedPreferences.getString("username", null);
//          mid = sharedPreferences.getInt("mid",0);

            t11.setText(String.valueOf(eid));
            employeeid = t11.getText().toString();

            fetchusers();

        }



    }




    private void fetchusers() {
        class bgWorker extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {




                String type = strings[0];


                if (type.equals("employee_profile_fetch")) {



                    try {



                        String fetch_url = "http://lms-php.000webhostapp.com/LMS_PHP/fetchuser.php";
                        URL url = new URL(fetch_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("employeeid", "UTF-8") + "=" + URLEncoder.encode(employeeid, "UTF-8");
                        bufferedWriter.write(post_data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        String result = "";
                        String line = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            result += line;
                        }
                        bufferedReader.close();
                        inputStream.close();
                        httpURLConnection.disconnect();
                        return result;
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }


            @Override
            protected void onPreExecute() {

//                alertDialog = new AlertDialog.Builder(context).create();
//                alertDialog.setTitle("Apply Leave");
//                sharedPreferences = context.getSharedPreferences("Mypref", Context.MODE_PRIVATE);
            }

            @Override
            protected void onPostExecute(String s) {
                if (s != null) {

//                if (s.equals("Registered")) {
//                    alertDialog.setMessage("Employee Registered Succesfully");
//                    alertDialog.show();}
//                else {
//                    alertDialog.setMessage("Register Failed");
//                    alertDialog.show();
//                }


                    if (s.equals("Failed")) {
                        alertDialog.setMessage("Details not found");
                        alertDialog.show();
                    }
                    else {
                        try
                        {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject jsonObject = null;
                            edata.clear();

                            for (int i=0; i<jsonArray.length(); i++)
                            {
                                jsonObject = jsonArray.getJSONObject(i);


                                String name = jsonObject.getString("emp_name");
                                String jdate = jsonObject.getString("emp_joining_date");
                                String mid = jsonObject.getString("m_id");
                                String empid = jsonObject.getString("e_id");
                                t10 = findViewById(R.id.textView11);
                                t10.setText(empid);
                                t11 = findViewById(R.id.textView15);
                                t11.setText(name);
                                t12 = findViewById(R.id.textView17);
                                t12.setText(jdate);
                                t13 = findViewById(R.id.textView24);
                                t13.setText(mid);


//                                edata.add(row);
                            }

//
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,edata);
//                            elist.setAdapter(adapter);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }


//                    if (s.equals("inserted")) {
//                        alertDialog.setMessage("Applied Successfully");
//                        alertDialog.show();
//                    } else if (s.equals("Failed")) {
//                        alertDialog.setMessage("Invalid username or password");
//                        alertDialog.show();
//                    }


                }
            }




        }
        bgWorker bg = new bgWorker();
        bg.execute("employee_profile_fetch");

    }

    public void onBackPressed()
    {
        if(!(sharedPreferences.contains("e_id") && sharedPreferences.contains("username")))
        {
            Intent intent = new Intent(Employee_profile.this, Employee_profile.class);
            startActivity(intent);
        }
    }


}

