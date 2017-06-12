package com.example.user.happykid001;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    db_connect db_connect;
    ImageButton mood;
    ImageButton statistic;
    double emotion;
    Button btnEmotion;
ProgressBar pbbar;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db_connect = new db_connect();
        btnEmotion = (Button) findViewById(R.id.button);
        pbbar = (ProgressBar) findViewById(R.id.progressBar);
        pbbar.setVisibility(View.GONE);

        btnEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoCheck doCheck = new DoCheck();
                doCheck.execute("");

            }
        });

        mood = (ImageButton) findViewById(R.id.imageButton);
        statistic = (ImageButton) findViewById(R.id.imageButton2);

    }

    public class DoCheck extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;


//        String userid = edtuserid.getText().toString();
//        String password = edtpass.getText().toString();


        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();

            if (isSuccess) {
                Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
            }

        }

        // Connection With SQL Server

        @Override
        protected String doInBackground(String... params) {


            try {
                Connection con = db_connect.CONN();
                if (con == null) {
                    z = "Error:505 in connecting with the Database";

                } else {

                    //SQL Select Query
                    String query = "SELECT * FROM emotiontable WHERE emotions=' " +emotion;
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);


                    //Emotion Calculation

                    if (emotion <= 0.98755) {

                        z = "The child is Happy";
                        isSuccess = true;

                    } else if (emotion <= 0.00300731952) {
                        z = "The Child is Anger";
                        isSuccess = true;


                    } else if (emotion <= 5.14648448) {
                        z = "The Child is Contempt";
                        isSuccess = true;
                    }
                    else if (emotion <= 9.180124) {
                        z = "The Child is Disgusted";
                        isSuccess = true;
                    }
                    else if (emotion <= 0.0001912825) {
                        z = "The Child is fear";
                        isSuccess = true;
                    }
                    else if (emotion <= 0.0009861537) {
                        z = "The Child is Nutral";
                        isSuccess = true;
                    }
                    else if (emotion <= 1.889955) {
                        z = "The Child is sad";
                        isSuccess = true;
                    }
                    else if (emotion <= 0.008229999) {
                        z = "The Child is Surprised";
                        isSuccess = true;
                    }

                }
            } catch (Exception ex) {
                isSuccess = false;
                z = "No Emotion Data Found";
            }

            return z;
        }
    }
}


      /*  public void emotionView(View view) {
            Intent i = new Intent(this, Emotion.class);
            startActivity(i);
        }

        public void stats(View view) {
            Intent i = new Intent(this, Statistics.class);
            startActivity(i);
        }

    }
}*/


















