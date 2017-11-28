package com.example.ekasilabalexcdtb.sqlite3reportcard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekasilabalexcdtb.sqlite3reportcard.DatabaseHelper.DBHelper;
import com.example.ekasilabalexcdtb.sqlite3reportcard.ReportClasses.AppClass;

public class SingUpActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etComfirmPassword;
    String username, password, comfirmPassword;
    Button register;
    Context context = this;

    DBHelper dbHelper;
    AppClass appClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        etUsername = (EditText) findViewById(R.id.etUsername1);
        etPassword = (EditText) findViewById(R.id.password);
        etComfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        register = (Button) findViewById(R.id.btnRegister1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                comfirmPassword = etComfirmPassword.getText().toString();

                if (!(password.equals(comfirmPassword))) {
                    Toast.makeText(getBaseContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    etPassword.setText("");
                    etComfirmPassword.setText("");
                } else {
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.putInformation(dbHelper, username, password);
                    Toast.makeText(getBaseContext(),"Registered successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
