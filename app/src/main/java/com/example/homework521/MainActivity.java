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

    String PASSWORD_FILE_NAME = "password";
    String LOGIN_FILE_NAME = "login";
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
                String loginText = readFile(LOGIN_FILE_NAME);
                String passwordText = readFile(PASSWORD_FILE_NAME);

                if (loginText.equals(login.getText().toString()) & passwordText.equals(password.getText().toString())){
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage(R.string.entry_true)
                            .setCancelable(false)
                            .setNegativeButton(R.string.button_OK,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                            .create()
                            .show();
                }
            }
        });
    }

    public void regClick(View view){
        if (password.getText().toString().equals("") | login.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, R.string.alert_message, Toast.LENGTH_LONG);
            toast.show();

        }else{
            saveData(LOGIN_FILE_NAME, login.getText().toString());
            saveData(PASSWORD_FILE_NAME, password.getText().toString());
            Toast toast = Toast.makeText(this, R.string.registration_true, Toast.LENGTH_LONG);
            toast.show();
        }
    }


    private String readFile(String fileName){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveData(String fileName, String data) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE)));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
