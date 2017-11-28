package com.example.ekasilabalexcdtb.sqlite3reportcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekasilabalexcdtb.sqlite3reportcard.DatabaseHelper.DBHelper;
import com.example.ekasilabalexcdtb.sqlite3reportcard.ReportClasses.AppClass;
import com.example.ekasilabalexcdtb.sqlite3reportcard.ReportClasses.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class AppRegActivity extends AppCompatActivity {
    DBHelper dbHelper;
    AppClass appClass;
EditText id;
    EditText miwok;
    EditText CourtCounter;;
    EditText AlexTourGuide;;
    EditText QuizAppp;;
    EditText FriendlyChat;
    EditText JustJava;

    private Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_reg);

        id = (EditText) findViewById(R.id.id);
        miwok = (EditText) findViewById(R.id.miwok);
        CourtCounter = (EditText) findViewById(R.id.courtcount);
        AlexTourGuide = (EditText) findViewById(R.id.tourguide);
        QuizAppp = (EditText) findViewById(R.id.quizapp);
        FriendlyChat = (EditText) findViewById(R.id.friendlyChat);
        JustJava = (EditText) findViewById(R.id.justjava);
        sub = (Button) findViewById(R.id.send);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppClass appClass = new AppClass();


                String name, email, password;
                // String sub2, String sub3, String sub4, String sub5, String sub6

                appClass.setId(id.getText().toString());

                appClass.setSub1(miwok.getText().toString());
                appClass.setSub2(CourtCounter.getText().toString());
                appClass.setSub3(AlexTourGuide.getText().toString());
                appClass.setSub4(QuizAppp.getText().toString());
                appClass.setSub5(FriendlyChat.getText().toString());
                appClass.setSub6(JustJava.getText().toString());

                dbHelper.insertInformation(appClass.getId(), appClass.getName(), appClass.getEmail(), appClass.getPassword(),
                        appClass.getSub1(), appClass.getSub2(), appClass.getSub3(), appClass.getSub4(), appClass.getSub5(), appClass.getSub6());

               Intent intent = new Intent(AppRegActivity.this, ShowInfoActivity.class);
               startActivity(intent);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        appClass = new AppClass();

        final DBHelper db = new DBHelper(this);

        if (id == R.id.action_update) {

            AlertDialog alertDialog = new AlertDialog.Builder(AppRegActivity.this).create();
            alertDialog.setTitle("Update database");
            alertDialog.setMessage("Update your database here");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Udate",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.updateApp(appClass.getId());
                            Log.v("Update", "Updated" + db.updateApp(appClass.getId()));
                            Intent intent = new Intent(AppRegActivity.this, UpdateActivity.class);
                            startActivity(intent);
                        }
                    });
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
