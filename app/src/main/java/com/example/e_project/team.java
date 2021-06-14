package com.example.e_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

public class team extends AppCompatActivity {

    ListView m_team_list;
    int m_t_id;

    TextView mt1;
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    String manager_t_id;

    ImageView img;
    NavigationView navigationView;
    DrawerLayout drawerLayout;


    ArrayList<String> tmdata = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
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
                        Intent intent = new Intent( team.this , leaves.class);
                        startActivity(intent);
                        return true;
                    case R.id.myteam:
                        Intent intent2 = new Intent(team.this, team.class);
                        startActivity(intent2);
                        return true;
                    case R.id.mlogout:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("Id");
                        editor.remove("username");
                        editor.clear();

                        editor.commit();
                        Intent intent5 = new Intent(team .this,manager_login.class);
                        startActivity(intent5);
                        return true;






                }
                return true;
            }
        });
        m_team_list = findViewById(R.id.m_team_list);
        mt1 = findViewById(R.id.m_team_id);

        sharedPreferences = getSharedPreferences("manager", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("mid") && sharedPreferences.contains("mname"))  {
            m_t_id = sharedPreferences.getInt("mid", 0);

//          mid = sharedPreferences.getInt("mid",0);

            mt1.setText(String.valueOf(m_t_id));
            manager_t_id = mt1.getText().toString();

            fetchusermanager();

        }




    }
    private void fetchusermanager() {
        class bgWorker extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {




                String type = strings[0];


                if (type.equals("manager_team")) {



                    try {



                        String fetch_url = "http://lms-php.000webhostapp.com/LMS_PHP/manager_team_fetch.php";
                        URL url = new URL(fetch_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("manager_t_id", "UTF-8") + "=" + URLEncoder.encode(manager_t_id, "UTF-8");
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

            }

            @Override
            protected void onPostExecute(String s) {
                if (s != null) {

//


                    if (s.equals("Failed")) {
                        alertDialog.setMessage("Details not found");
                        alertDialog.show();
                    }
                    else {
                        try
                        {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject jsonObject = null;
                            tmdata.clear();

                            for (int i=0; i<jsonArray.length(); i++)
                            {
                                jsonObject = jsonArray.getJSONObject(i);
                                String row = //jsonObject.getInt("uid")+" "+

                                        "Employee ID" + "\t \t \t \t" +
                                                jsonObject.getString("e_id")+"\n \n"+
                                                "Employee Name" + "\t \t \t \t" +
                                                jsonObject.getString("emp_name")+"\n \n"+
                                                "Employee Joining Date" + "\t \t \t \t" +
                                                //jsonObject.getString("upassword")+" "+
                                                jsonObject.getString("emp_joining_date")+"\n \n"+
                                                "Employee Manager ID" + "\t \t \t \t" +
                                                jsonObject.getString("m_id")+"\n ---------------------------------------------------------------------- \n";


                                tmdata.add(row);
                            }


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,tmdata);
                            m_team_list.setAdapter(adapter);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }


//


                }
            }




        }
        bgWorker bg = new bgWorker();
        bg.execute("manager_team");

    }


}