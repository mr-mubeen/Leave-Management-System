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
import android.view.Menu;
import android.view.MenuInflater;
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

public class leaves extends AppCompatActivity {

    ImageView img;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ListView ml;

    int m_lid;

    TextView mltext;
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    String mlid;



    ArrayList<String> mldata = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaves);

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
                        Intent intent = new Intent( leaves.this , leaves.class);
                        startActivity(intent);
                        return true;
                    case R.id.myteam:
                        Intent intent2 = new Intent(leaves.this, team.class);
                        startActivity(intent2);
                        return true;
                    case R.id.mlogout:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("Id");
                        editor.remove("username");
                        editor.clear();

                        editor.commit();
                        Intent intent5 = new Intent(leaves.this,manager_login.class);
                        startActivity(intent5);
                        return true;






                }
                return true;
            }
        });

        ml = findViewById(R.id.m_l_details);
        mltext = findViewById(R.id.m_l_id);

        sharedPreferences = getSharedPreferences("manager", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("mid") && sharedPreferences.contains("mname"))  {
            m_lid = sharedPreferences.getInt("mid", 0);

//          mid = sharedPreferences.getInt("mid",0);

            mltext.setText(String.valueOf(m_lid));
            mlid = mltext.getText().toString();

            fetchleavemanager();

        }



    }




    private void fetchleavemanager() {
        class bgWorker1 extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {




                String type = strings[0];


                if (type.equals("leave_manager")) {



                    try {



                        String fetch_url = "http://lms-php.000webhostapp.com/LMS_PHP/leave_manager.php";
                        URL url = new URL(fetch_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("mlid", "UTF-8") + "=" + URLEncoder.encode(mlid, "UTF-8");
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


//                    if (s.equals("Failed")) {
//                        alertDialog.setMessage("Details not found");
//                        alertDialog.show();
//                    }
//                    else {
                    try
                    {
                        JSONArray jsonArray = new JSONArray(s);
                        JSONObject jsonObject = null;
                        mldata.clear();

                        for (int i=0; i<jsonArray.length(); i++)
                        {

                            jsonObject = jsonArray.getJSONObject(i);
                            String row = //jsonObject.getInt("uid")+" "+
                                    "Employee ID" + " \t \t\t \t" +
                                    jsonObject.getString("L_e_id")+ "\n \n"+
                                            "Manager ID" + "\t \t\t \t " +
                                            jsonObject.getString("L_m_id")+"\n"+
                                            "Note :" + "\n \n" +
                                            //jsonObject.getString("upassword")+" "+
                                            jsonObject.getString("note")+ "\n \n "+
                                            "Leave Start Date" + " " + "\t \t \t \t"+
                                            jsonObject.getString("L_s_date")+ "\n \n"+
                                            "Leave End Date" + " "  +"\t \t \t \t"+
                                            jsonObject.getString("L_e_date")+ "\n \n" +
                                            "Leave Type" + " "   + "\t \t \t \t"+
                                            jsonObject.getString("typee") +"\n--------------------------------------------------------------- \n";

                            mldata.add(row);
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mldata);
                        ml.setAdapter(adapter);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

//                    }


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
        bgWorker1 bg = new bgWorker1();
        bg.execute("leave_manager");

    }




//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.manager_menu, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.myteam)
//        {
//            Intent intent = new Intent(this,team.class);
//            startActivity(intent);
//        }
//        if (id == R.id.leave_id_click)
//        {
//            Intent intent = new Intent(this,leaves.class);
//            startActivity(intent);
//        }
////        if (id == R.id.summary)
////        {
////            Intent intent = new Intent(first.this,manager_register.class);
////            startActivity(intent);
////        }
////        if (id == R.id.calendar)
////        {
////            Intent intent = new Intent(first.this,manager_register.class);
////            startActivity(intent);
////        }
////        if (id == R.id.logout)
////        {
////            Intent intent = new Intent(first.this,manager_register.class);
////            startActivity(intent);
////        }
//
//
//
//        return super.onOptionsItemSelected(item);
//
//    }


    public void onBackPressed()
    {
        if(!(sharedPreferences.contains("mid") && sharedPreferences.contains("mname")))
        {
            Intent intent = new Intent(leaves.this, leaves.class);
            startActivity(intent);
        }
    }
}

