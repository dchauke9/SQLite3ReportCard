package com.example.ekasilabalexcdtb.sqlite3reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ekasilabalexcdtb.sqlite3reportcard.DatabaseHelper.DBHelper;
import com.example.ekasilabalexcdtb.sqlite3reportcard.ReportClasses.AppClass;

import java.util.List;

public class ViewActivity extends AppCompatActivity {
    ListView lvmain;
    ArrayAdapter<AppClass> adapter;
    List<AppClass> data;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        lvmain=(ListView)findViewById(R.id.lvmain);


        dbHelper= new DBHelper(this);


        data=dbHelper.getAllApps();




        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);

        lvmain.setAdapter(adapter);
    }
}
