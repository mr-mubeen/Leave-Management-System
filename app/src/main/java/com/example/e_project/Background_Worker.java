package com.example.e_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Background_Worker extends AsyncTask<String,Void,String> {

    String type;
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;

    Background_Worker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
         type = params[0];

        if (type.equals("login")) {
            try {
                String login_url = "http://lms-php.000webhostapp.com/LMS_PHP/login.php";
                String un = params[1];
                String ps = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("un", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8") + "&"
                        + URLEncoder.encode("ps", "UTF-8") + "=" + URLEncoder.encode(ps, "UTF-8");
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


        if (type.equals("mlogin")) {
            try {
                String login_url = "http://lms-php.000webhostapp.com/LMS_PHP/mlogin.php";
                String mun = params[1];
                String mps = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("mun", "UTF-8") + "=" + URLEncoder.encode(mun, "UTF-8") + "&"
                        + URLEncoder.encode("mps", "UTF-8") + "=" + URLEncoder.encode(mps, "UTF-8");
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


        if (type.equals("alogin")) {
            try {
                String login_url = "http://lms-php.000webhostapp.com/LMS_PHP/alogin.php";
                String aun = params[1];
                String aps = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("aun", "UTF-8") + "=" + URLEncoder.encode(aun, "UTF-8") + "&"
                        + URLEncoder.encode("aps", "UTF-8") + "=" + URLEncoder.encode(aps, "UTF-8");
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

        if (type.equals("applyleave")) {
            String reg_url = "http://lms-php.000webhostapp.com/LMS_PHP/applyleave.php";
            String nt = params[1];
            String sd = params[2];
            String ed = params[3];
            String lt = params[4];
            String eid = params[5];
            String mid = params[6];




            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                String post_data =
                        URLEncoder.encode("nt", "UTF-8") + "=" + URLEncoder.encode(nt, "UTF-8") +
                                "&" + URLEncoder.encode("sd", "UTF-8") + "=" + URLEncoder.encode(sd, "UTF-8") +
                                "&" + URLEncoder.encode("ed", "UTF-8") + "=" + URLEncoder.encode(ed, "UTF-8") +
                                "&" + URLEncoder.encode("lt", "UTF-8") + "=" + URLEncoder.encode(lt, "UTF-8") +
                                "&" + URLEncoder.encode("eid", "UTF-8") + "=" + URLEncoder.encode(eid, "UTF-8") +
                                "&" + URLEncoder.encode("mid", "UTF-8") + "=" + URLEncoder.encode(mid, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                String result = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();

                httpURLConnection.disconnect();


                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


            //REGISTER CODE
//            if (type.equals("register")) {
//                String reg_url = "http://192.168.56.1/LMS_PHP/employee_reg.php";
//                String un = params[1];
//                String ps = params[2];
//
//                String jd = params[3];
//
//
//                try {
//                    URL url = new URL(reg_url);
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    httpURLConnection.setRequestMethod("POST");
//                    httpURLConnection.setDoInput(true);
//                    httpURLConnection.setDoOutput(true);
//
//                    OutputStream outputStream = httpURLConnection.getOutputStream();
//                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//
//                    String post_data =
//                            URLEncoder.encode("un", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8") +
//                                    "&" + URLEncoder.encode("ps", "UTF-8") + "=" + URLEncoder.encode(ps, "UTF-8") +
//                                    "&" + URLEncoder.encode("un", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8") +
//
//                                    "&" + URLEncoder.encode("jd", "UTF-8") + "=" + URLEncoder.encode(jd, "UTF-8");
//
//                    bufferedWriter.write(post_data);
//                    bufferedWriter.flush();
//                    bufferedWriter.close();
//                    outputStreamWriter.close();
//                    outputStream.close();
//
//
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                    String line = "";
//                    String result = "";
//
//                    while ((line = bufferedReader.readLine()) != null) {
//                        result += line;
//                    }
//
//                    bufferedReader.close();
//                    inputStreamReader.close();
//                    inputStream.close();
//
//                    httpURLConnection.disconnect();
//
//                    alertDialog.setTitle("Registration Status");
//                    return result;
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }


//        REGISTER CODE
//            if (type.equals("date")) {
//                String reg_url = "http://lms-php.000webhostapp.com/LMS_PHP/date.php";
//               String Dates = params[1];
//
//
//
//                try
//                { URL url = new URL(reg_url);
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    httpURLConnection.setRequestMethod("POST");
//                    httpURLConnection.setDoInput(true);
//                    httpURLConnection.setDoOutput(true);
//
//                    OutputStream outputStream = httpURLConnection.getOutputStream();
//                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//
//                    String post_data =
//                            URLEncoder.encode("Dates", "UTF-8") + "=" + URLEncoder.encode(Dates, "UTF-8") ;
//
//
//                    bufferedWriter.write(post_data);
//                    bufferedWriter.flush();
//                    bufferedWriter.close();
//                    outputStreamWriter.close();
//                    outputStream.close();
//
//
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                    String line = "";
//                    String result = "";
//
//                    while ((line = bufferedReader.readLine()) != null) {
//                        result += line;
//                    }
//
//                    bufferedReader.close();
//                    inputStreamReader.close();
//                    inputStream.close();
//
//                    httpURLConnection.disconnect();
//
//
//                    return result;
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }

            return null;

        }

        @Override
        protected void onPreExecute () {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Alert Message");

        }


        @Override
        protected void onPostExecute (String s)
        {
            if (s != null) {

                String tt = type;


//                    if (s.equals("Registered"))
//                    {
//                        alertDialog.setMessage("Employee Registered Succesfully");
//                        alertDialog.show();
//                    }
//                    else
//                    {
//                        alertDialog.setMessage("Register Failed");
//                        alertDialog.show();
//                    }

//        EMPLOYEE-LOGIN AND SHARED PREFERENCES FOR EMPLOYEE OUTCOME

                if (type.equals("login")) {

                    if (s.equals("Employee-Login-Failed")) {
                        alertDialog.setMessage("Invalid username or password");
                        alertDialog.show();
                    } else {

                        try {
                            sharedPreferences = context.getSharedPreferences("employee", Context.MODE_PRIVATE);

                            JSONObject jobj = new JSONObject(s);
                            int i = jobj.getInt("e_id");
                            String nm = jobj.getString("emp_name");
                            int mid = jobj.getInt("m_id");

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("e_id", i);
                            editor.putString("username", nm);
                            editor.putInt("mid", mid);
                            editor.commit();

                            Intent intent = new Intent(context, Employee_profile.class);
                            context.startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }


                else if (type.equals("mlogin")) {
                    if (s.equals("mFailed")) {
                        alertDialog.setMessage("Invalid username or password");
                        alertDialog.show();
                    } else {

                        try {
                            sharedPreferences = context.getSharedPreferences("manager", Context.MODE_PRIVATE);
                            JSONObject jobj = new JSONObject(s);
                            int mid = jobj.getInt("m_id");
                            String mname = jobj.getString("m_name");
                            int mp = jobj.getInt("m_id");

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("mid", mid);
                            editor.putString("mname", mname);
                            editor.putInt("mid", mid);
                            editor.commit();

                            Intent intent = new Intent(context, leaves.class);
                            context.startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }




                }



                else if (type.equals("alogin")) {
                    if (s.equals("aFailed")) {
                        alertDialog.setMessage("Invalid username or password");
                        alertDialog.show();
                    } else {

                        try {
                            sharedPreferences = context.getSharedPreferences("admin", Context.MODE_PRIVATE);
                            JSONObject jobj = new JSONObject(s);
                            int aid = jobj.getInt("a_id");
                            String aname = jobj.getString("a_user");
//                        int mp = jobj.getInt("m_id");

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("aid", aid);
                            editor.putString("aname", aname);
//                        editor.putInt("mid", mid);
                            editor.commit();

                            Intent intent = new Intent(context, admin_dashboard.class);
                            context.startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                else if (type.equals("applyleave"))
                {
                    if (s.equals("Leave-Registered")) {
                        alertDialog.setMessage("Leave Applied Successfully");
                        alertDialog.show();
                    } else if (s.equals("Leave-Failed")) {
                        alertDialog.setMessage("You don't have enough leaves");
                        alertDialog.show();
                    }
                }
            }






            }

}





