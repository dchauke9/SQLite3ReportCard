package com.example.ekasilabalexcdtb.sqlite3reportcard.ReportClasses;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekasilabalexcdtb.sqlite3reportcard.DatabaseHelper.DBHelper;
import com.example.ekasilabalexcdtb.sqlite3reportcard.R;

import java.util.List;

/**
 * Created by eKasiLab Alex CDTB on 23 Nov 2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<AppClass> personList;

    public MyAdapter(Context context, List<AppClass> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.results, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AppClass appClass = personList.get(position);

        holder.name.setText(appClass.getName());
        holder.username.setText(appClass.getUsername());
        holder.email.setText(appClass.getEmail());
        holder.appID.setText(appClass.getId());
        holder.app1.setText(appClass.getSub1());
        holder.app2.setText(appClass.getSub2());
        holder.app3.setText(appClass.getSub3());
        holder.app4.setText(appClass.getSub4());
        holder.app5.setText(appClass.getSub5());
        holder.app6.setText(appClass.getSub6());

        //Delete the database
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(context);

                dbHelper.deleteApp(holder.appID.getId());
                personList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Table deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, username, app1, app2, app3, app4, app5, app6, appID;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            appID = (TextView) view.findViewById(R.id.appId);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.mail);
            username = (TextView) view.findViewById(R.id.surname);
            app1 = (TextView) view.findViewById(R.id.app1);
            app2 = (TextView) view.findViewById(R.id.app2);
            app3 = (TextView) view.findViewById(R.id.app3);
            app4 = (TextView) view.findViewById(R.id.app4);
            app5 = (TextView) view.findViewById(R.id.app5);
            app6 = (TextView) view.findViewById(R.id.app6);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }

}
