package com.example.e_project;

import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class employee_register extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Spinner spinner;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter<String> manageradapter;
    RequestQueue requestQueu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_register);

        requestQueu = Volley.newRequestQueue(this);
        ed1 = findViewById(R.id.emp_name);
        ed2 = findViewById(R.id.emp_pass);
        ed2 = findViewById(R.id.emp_j_date);

        spinner = findViewById(R.id.spinnerManger);

        String fetch_url = "http://lms-php.000webhostapp.com/LMS_PHP/employee_register.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, fetch_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("manager");
                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String row = jsonObject.optString("m_id");

                        data.add(row);

                        manageradapter = new ArrayAdapter<String>( employee_register.this,  android.R.layout.simple_spinner_item,data);
                        manageradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(manageradapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueu.add(jsonObjectRequest);



    }

    public void reg_employee(View view)
    {
        String un = ed1.getText().toString();
        String ps= ed2.getText().toString();
        String manager= spinner.getSelectedItem().toString();
        String jd= ed3.getText().toString();



        Background_Worker bgworker = new Background_Worker(employee_register.this);
        bgworker.execute("register",un,ps,jd , manager);
    }

//    private void fetchusers()
//    {
//        class bgWorker extends AsyncTask<String,Void,String>
//        {
//
//            @Override
//            protected String doInBackground(String... strings)
//            {
//                String type = strings[0];
//
//                //FETCH CODE
//
//                if(type.equals("fetch"))
//                {
//                    String fetch_url = "http://192.168.56.1/LMS_PHP/employee_register.php";
//
//
//                    try
//                    {
//                        URL url = new URL(fetch_url);
//                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                        InputStream inputStream = httpURLConnection.getInputStream();
//                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                        String line = "";
//                        String result="";
//                        while ((line = bufferedReader.readLine()) != null)
//                        {
//                            result+=line;
//                        }
//
//                        bufferedReader.close();
//                        inputStreamReader.close();
//                        inputStream.close();
//
//                        httpURLConnection.disconnect();
//
//
//                        return result;
//
//                    }
//                    catch (MalformedURLException e)
//                    {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                return null;
//            }
//
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(String s)
//            {
//                try
//                {
//                    JSONArray jsonArray = new JSONArray(s);
//                    JSONObject jsonObject = null;
//                    data.clear();
//
//                    for (int i=0; i<jsonArray.length(); i++)
//                    {
//                        jsonObject = jsonArray.getJSONObject(i);
//                        String row = //jsonObject.getInt("uid")+" "+
//                                jsonObject.getString("m_id");
////                                        +" "+
////                                        jsonObject.getString("uemail")+" "+
////                                        //jsonObject.getString("upassword")+" "+
////                                        jsonObject.getString("ucontact")+" "+
////                                        jsonObject.getString("uaddress");
//
//                        data.add(row);
//                    }
//
//
////                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,data);
//
////                    lst.setAdapter(adapter);
//                }
//                catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        bgWorker bg = new bgWorker();
//        bg.execute("fetch");
//
//    }
}