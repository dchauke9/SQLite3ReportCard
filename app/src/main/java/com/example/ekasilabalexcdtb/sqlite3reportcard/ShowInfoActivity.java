package com.example.ekasilabalexcdtb.sqlite3reportcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ekasilabalexcdtb.sqlite3reportcard.DatabaseHelper.DBHelper;
import com.example.ekasilabalexcdtb.sqlite3reportcard.ReportClasses.AppClass;
import com.example.ekasilabalexcdtb.sqlite3reportcard.ReportClasses.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowInfoActivity extends AppCompatActivity {
    MyAdapter myAdapter;
    DBHelper dbHelper;
    AppClass appClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        dbHelper = new DBHelper(ShowInfoActivity.this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        Log.v("Test", String.valueOf(dbHelper.numberOfRows()));

        List<AppClass> mPersonList = (ArrayList<AppClass>) dbHelper.getAllApps();

        myAdapter = new MyAdapter(ShowInfoActivity.this, mPersonList);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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


            AlertDialog alertDialog = new AlertDialog.Builder(ShowInfoActivity.this).create();
            alertDialog.setTitle("Update database");
            alertDialog.setMessage("Update your database here");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Udate",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.updateApp(appClass.getId());
                            Log.v("Update", "Updated" + db.updateApp(appClass.getId()));
                            Intent intent = new Intent(ShowInfoActivity.this, UpdateActivity.class);
                            startActivity(intent);
                        }
                    });
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
