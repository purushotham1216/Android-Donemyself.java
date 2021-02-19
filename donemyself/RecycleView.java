package com.mine.donemyself;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleView extends AppCompatActivity {
    RecyclerView rv;
    RecyclerAdapter recyclerAdapter;
    GridLayoutManager gridLayoutManager;
    ArrayList<SetMethods> rlist = new ArrayList<>();
    DatabaseSection dbss;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_field3);
//        setContentView(R.layout.activity_recycle_view);

//        rv = findViewById(R.id.recycle);
        rv = findViewById(R.id.rv3);

        SetMethods setMethods = new SetMethods();
        dbss = new DatabaseSection(this);
        rlist = dbss.getData();


        /*setting the recyclerAdapter to the recyclerview (as like keeping the list in a spinner in a list adapter)
        setting the layout in recyclerView
        * */


        recyclerAdapter = new RecyclerAdapter(RecycleView.this,rlist);
        gridLayoutManager = new GridLayoutManager(this,1,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(gridLayoutManager); // adding the gridView here instead of LinearLayout
        rv.setLayoutManager(new LinearLayoutManager(RecycleView.this));
        rv.setAdapter(recyclerAdapter);
        rv.setHasFixedSize(false);

    }

    /* First it will ask to create "MyViewHolder class" and will ask for implement the methods as follows
    ==> onCreateViewHolder
    ==> onBindViewHolder
    ==> getItemCount

    As MyViewHolder created with extension RecyclerView.ViewHolder, it will ask for create constructor for it.
    Create it as usually(override)
    * */
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
        Context context;
        LayoutInflater inflater;
        ArrayList<SetMethods> rlist = new ArrayList();

        public RecyclerAdapter(Context context,  ArrayList<SetMethods> rlist) {
            this.context = context;
            inflater =LayoutInflater.from(context);
            this.rlist = rlist;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
/*            getting the layout which we need to list in the manner(how we designed in the layout)
            and initiating the layout to view the content in it.
            ==> Here the parent is to view the how many record that occur in database(if we use database,
             otherwise the list that we created statically)
 */
            inflater = LayoutInflater.from(context);
            View view =inflater.inflate(R.layout.recycle_field2,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

            holder.name_view.setText(rlist.get(position).getMname());
            holder.fname_view.setText(rlist.get(position).getMfname());
            holder.dist_view.setText(rlist.get(position).getMdist());
            holder.mandal_view.setText(rlist.get(position).getMmand());
            holder.village_view.setText(rlist.get(position).getMvill());

            holder.itemView.setTag(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int place = (int) holder.itemView.getTag();

                    preferences = getSharedPreferences("key",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("name",rlist.get(place).getMname());
                    editor.putString("fname",rlist.get(place).getMfname());
                    editor.putString("districts",rlist.get(place).getMdist());
                    editor.putString("mandals",rlist.get(place).getMmand());
                    editor.putString("villages",rlist.get(place).getMvill());
                    editor.putString("uni_id",rlist.get(place).getMid());
                    editor.apply();

                    Intent p = new Intent(context,UpdateDetails.class);
                    startActivity(p);
                    finish();
                }
            });


        }

        @Override
        public int getItemCount() {
            return rlist.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name_view,fname_view,dist_view,mandal_view,village_view;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                name_view = itemView.findViewById(R.id.recycle_nametext);
                fname_view = itemView.findViewById(R.id.recycle_fnametext);
                dist_view = itemView.findViewById(R.id.recycle_districttext);
                mandal_view = itemView.findViewById(R.id.recycle_mandaltext);
                village_view = itemView.findViewById(R.id.recycle_villagetext);

            }
        }
    }
}