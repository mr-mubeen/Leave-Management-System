package com.example.e_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.renderscript.Sampler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class summary1 extends AppCompatActivity {

//    PieChart pie;
    ListView slist;
    int eid;

    TextView st1 , t25 , t27 ,t31 ,t33;
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    String employeeid;

    ImageView img;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    ArrayList<String> sdata = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary1);

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
                        Intent intent = new Intent( summary1.this , Employee_profile.class);
                        startActivity(intent);
                        return true;
                    case R.id.leave:
                        Intent intent2 = new Intent(summary1.this, apply_leave.class);
                        startActivity(intent2);
                        return true;
                    case R.id.summary:
                        Intent intent3 = new Intent(summary1.this, summary1.class);
                        startActivity(intent3);
                        return true;
                    case R.id.calendar:
                        Intent intent4 = new Intent(summary1.this, Calendar.class);
                        startActivity(intent4);
                        return true;
                    case R.id.logout:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("Id");
                        editor.remove("username");
                        editor.clear();

                        editor.commit();
                        Intent intent5 = new Intent(summary1.this,MainActivity.class);
                        startActivity(intent5);
                        return true;



                }
                return true;
            }



        });


        st1 = findViewById(R.id.textView35);

        sharedPreferences = getSharedPreferences("employee", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("e_id") && sharedPreferences.contains("username"))  {
            eid = sharedPreferences.getInt("e_id", 0);


            st1.setText(String.valueOf(eid));
            employeeid = st1.getText().toString();

            summary();

        }


//        textView = findViewById(R.id.textView5);



//        pie = (PieChart) findViewById(R.id.pie1);
//
//        ArrayList<PieEntry> recor = new ArrayList<>();
//        recor.add(new PieEntry( PL, "Sick Leaves"));
//        recor.add(new PieEntry(PL    , "Privilege Leaves"));
//        recor.add(new PieEntry(CL , "Casual Leaves"));
//        recor.add(new PieEntry(32 , "Extras"));
//
//        PieDataSet pieDataSet = new PieDataSet(recor , "");
//        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        pieDataSet.setValueTextColor(Color.BLACK);
//        pieDataSet.setValueTextSize(20f);
//
//
//        PieData pieData = new PieData(pieDataSet);
//
//        pie.setData(pieData);
//        pie.getDescription().setEnabled(true);
//        pie.setCenterText("Leaves");
//        pie.animate();
    }




    private void summary() {
        class bgWorker extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {




                String type = strings[0];


                if (type.equals("summary")) {



                    try {



                        String fetch_url = "http://lms-php.000webhostapp.com/LMS_PHP/summary.php";
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
                            sdata.clear();

                            for (int i=0; i<jsonArray.length(); i++)
                            {
                                jsonObject = jsonArray.getJSONObject(i);

                                String name = jsonObject.getString("emp_name");
                                String empid = jsonObject.getString("e_id");
                                String SL = jsonObject.getString("SL");
                                String PL = jsonObject.getString("PL");
                                String CL = jsonObject.getString("CL");

                                t25 = findViewById(R.id.textView25);
                                t27 = findViewById(R.id.textView27);
                                t31 = findViewById(R.id.textView31);
                                t33 = findViewById(R.id.textView33);


                                t25.setText(name);
                                t27.setText(PL);
                                t31.setText(SL);
                                t33.setText(CL);


                            }



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
        bg.execute("summary");

    }


}