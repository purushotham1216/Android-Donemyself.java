package com.mine.donemyself;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecycleField extends AppCompatActivity {
    TextView fieldname,fieldfname,fielddist,fieldmandal,fieldvillage;
    String nnnnnn,fnnnnnn,ddddd,mmmmm,vvvvvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_field);

        fieldname = findViewById(R.id.recycle_nametext);
        fieldfname = findViewById(R.id.recycle_fnametext);
        fielddist = findViewById(R.id.recycle_districttext);
        fieldmandal = findViewById(R.id.recycle_mandaltext);
        fieldvillage = findViewById(R.id.recycle_villagetext);

        Bundle click = getIntent().getExtras();

        nnnnnn = click.getString("_name","");
        fnnnnnn = click.getString("_fname","");
        ddddd = click.getString("_dist_name","");
        mmmmm = click.getString("_mandal_name","");
        vvvvvv = click.getString("_village_name","");

        fieldname.setText(nnnnnn);
        fieldfname.setText(fnnnnnn);
        fielddist.setText(ddddd);
        fieldmandal.setText(mmmmm);
        fieldvillage.setText(vvvvvv);

    }
}
