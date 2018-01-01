package com.example.yassarchohan.virtual_shopping_app;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

/**
 * Created by Yassar chohan on 10/6/2017.
 */
public class Background_task_signup extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    String result = "";
    Background_task_signup(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];

        String register_url = "http://192.168.43.110/register.php";
        if(type.equals("register")) {
            try {
                String username = params[1];
                String password = params[2];
                String email = params[3];
                String contacts = params[4];


                if (username == null && password == null && email == null && contacts == null) {

                    Toast.makeText(context.getApplicationContext(), "please fill all fields", Toast.LENGTH_LONG).show();

                }


                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "="
                        + URLEncoder.encode(username, "UTF-8") + "&"    //username encoding
                        + URLEncoder.encode("email", "UTF-8") + "="
                        + URLEncoder.encode(email, "UTF-8") + "&"    //email encoding
                        + URLEncoder.encode("contacts", "UTF-8") + "="
                        + URLEncoder.encode(contacts, "UTF-8") + "&"    //contacts encoding
                        + URLEncoder.encode("password", "UTF-8") + "="
                        + URLEncoder.encode(password, "UTF-8");          //password encoding
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

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


            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");
        }


    @Override
    protected void onPostExecute(String s) {
        if (result.equalsIgnoreCase("insert successful")) {

            Toast.makeText(context.getApplicationContext(), "you are registered", Toast.LENGTH_SHORT).show();
            s = "posted successfully";
            alertDialog.setMessage(s);
            alertDialog.show();
        }
        else {
            Toast.makeText(context.getApplicationContext(), "please enter all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
