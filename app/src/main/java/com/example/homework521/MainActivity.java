package com.example.homework521;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText password;
    EditText login;
    Button entry;
    Button registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        entry = findViewById(R.id.entry);
        registration = findViewById(R.id.registration);

        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = openFileInput("login_and_password");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String loginAndPassword = "";
                try {
                    loginAndPassword = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (loginAndPassword.equals(login.getText().toString() + " " + password.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Важное сообщение!")
                            .setMessage("вы вошли!")
                            .setCancelable(false)
                            .setNegativeButton("ОК",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }


            }
        });
    }

    public void regClick(View view){
        if (password.getText().toString().equals("") | login.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, R.string.alert_message, Toast.LENGTH_LONG);
            toast.show();

        }else{
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput("login_and_password", MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            try {
                bw.write(login.getText().toString() + " " + password.getText().toString());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast toast = Toast.makeText(this, "Регистрация прошла успешно", Toast.LENGTH_LONG);
            toast.show();
        }
    }


}
