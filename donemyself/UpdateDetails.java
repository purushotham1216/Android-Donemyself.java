package com.mine.donemyself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class UpdateDetails extends AppCompatActivity {

    private ArrayList<String> dist_list,kurnool_mandal,pkd_vill,adoni_vill,mntl_vill
            ,anantapur_mandal,guthi_vill,guntakal_vill,dharmavaram_vill;

    DatabaseSection ds;
    TextView back_btn_usdp,uniq_sl;
    Button update_data,view_data,delete_details;
    Spinner usdist,usmandal,usvillage;
    EditText usname,usfname;
    SharedPreferences spref;


    String us_str_dist,us_str_mand,us_str_vill,us_str_name,us_str_fname;
    String uniq = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        back_btn_usdp = findViewById(R.id.back_img);
        update_data = findViewById(R.id.save_usd);
        view_data = findViewById(R.id.usd_details);
        delete_details = findViewById(R.id.usd_details);
        usdist = findViewById(R.id.sp_dist_usd);
        usmandal = findViewById(R.id.sp_mand_usd);
        usvillage = findViewById(R.id.sp_vill_usd);
        usname = findViewById(R.id.et_name_usd);
        usfname = findViewById(R.id.et_fname_usd);
        uniq_sl = findViewById(R.id.uni);

        ds = new DatabaseSection(UpdateDetails.this);
        spref = getSharedPreferences("key",MODE_PRIVATE);

        kurnool_mandal = new ArrayList<>();
        kurnool_mandal.add("<--- Select your Mandal --->");
        kurnool_mandal.add("Pattikonda");
        kurnool_mandal.add("Adoni");
        kurnool_mandal.add("Mantralayam");


        pkd_vill = new ArrayList<>();
        pkd_vill.add("<--- Select your village");
        pkd_vill.add("Rathana");
        pkd_vill.add("D.banda");
        pkd_vill.add("Tuggali");
        pkd_vill.add("Maddikera");
        pkd_vill.add("Peravali");


        adoni_vill = new ArrayList<>();
        adoni_vill.add("<--- Select your village");
        adoni_vill.add("Madire");
        adoni_vill.add("Pedda Thumbalam");
        adoni_vill.add("Pedda Harivanam");
        adoni_vill.add("Santhekudlur");


        mntl_vill = new ArrayList<>();
        mntl_vill.add("<--- Select your village");
        mntl_vill.add("Manchala");
        mntl_vill.add("Madhavaram");
        mntl_vill.add("Hagaruru Thimmapuram");
        mntl_vill.add("Rachumarri");


        anantapur_mandal = new ArrayList<>();
        anantapur_mandal.add("<--- Select your mandal --->");
        anantapur_mandal.add("Guthi");
        anantapur_mandal.add("Guntakal");
        anantapur_mandal.add("Dharmavaram");


        guthi_vill = new ArrayList<>();
        guthi_vill.add("<--- Select your village");
        guthi_vill.add("Jakkalacheruvu");
        guthi_vill.add("Kothapeta");
        guthi_vill.add("Ubicherla");
        guthi_vill.add("Karadikonda");


        guntakal_vill = new ArrayList<>();
        guntakal_vill.add("<--- Select your village");
        guntakal_vill.add("Nagasamudram");
        guntakal_vill.add("Konganapalle");
        guntakal_vill.add("Kasapuram");
        guntakal_vill.add("Yerrathimmarayachervu");


        dharmavaram_vill = new ArrayList<>();
        dharmavaram_vill.add("<--- Select your village");
        dharmavaram_vill.add("Kunuthuru");
        dharmavaram_vill.add("Gotlur");
        dharmavaram_vill.add("Ravulacheruvu");
        dharmavaram_vill.add("Chigicherla");

        districtDetails();



        back_btn_usdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_activity = new Intent(UpdateDetails.this,MainActivity.class);
                startActivity(main_activity);
            }
        });

        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us_str_name =  usname.getText().toString();
                us_str_fname = usfname.getText().toString();
                us_str_dist = usdist.getSelectedItem().toString();
                us_str_mand = usmandal.getSelectedItem().toString();
                us_str_vill = usvillage.getSelectedItem().toString();

                 if (us_str_name.isEmpty() || us_str_fname.isEmpty() || us_str_dist.isEmpty()
                                    || us_str_mand.isEmpty()  || us_str_vill.isEmpty()){
                 }else {

                if (us_str_name.isEmpty()){
                    usname.setError("Name should not be empty");
                }else if (us_str_name.length() < 4 ){
                    usname.setError("Name should not be lessthan 4 charecters.");
                }else if (us_str_fname.isEmpty()){
                    usfname.setError("Father name should not be empty");
                }else if (us_str_fname.length() < 4) {
                    usfname.setError("Father name should not be lessthan 4 cherecters");
                }

                long usupdate = ds.updateData(us_str_name,us_str_fname,us_str_dist,us_str_mand,us_str_vill,uniq);

//                Log.d("update_data",""+usupdate);
                     if (usupdate <= 0) {
                         Log.d("update_data",""+usupdate);
                         Toasting.message(getApplicationContext(), "Update not successful");
                     } else {
                         usname.setText("");
                         usfname.setText("");
                         Toasting.message(getApplicationContext(), "Update successful");
                     }
                 }

//                view_data.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
            }
        });
  /*      view_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SetMethods> sm = ds.getData();
                Iterator itr = sm.iterator();

                Log.d("getvalues",""+sm.toString());


                Intent valuestolist = new Intent(UpdateDetails.this,RecycleView.class);
                SetMethods smethods = (SetMethods) itr.next();

                valuestolist.putExtra("_name",smethods.getMname());
                valuestolist.putExtra("_fname",smethods.getMfname());
                valuestolist.putExtra("_dist_name",smethods.getMdist());
                valuestolist.putExtra("_mandal_name",smethods.getMdist());
                valuestolist.putExtra("_village_name",smethods.getMvill());
                valuestolist.putExtra("uni_id",smethods.getMid());

                startActivity(valuestolist);
            }
        });
*/

//        Delete details with unique id on selected item

       delete_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long did = ds.deleteData(uniq);
                if (did <= 0){
                    Toasting.message(getApplicationContext(),"Record not Deleted");
                }else {
                    Toasting.message(getApplicationContext(),"Record Deleted");
                }
                Intent deldetails = new Intent(UpdateDetails.this,RecycleView.class);
                startActivity(deldetails);
                finish();
            }
        });


//  Delete details with unique id on long click(not worked)

  /*      delete_details.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                long did = ds.deleteData(uniq);
                if (did <= 0){
                    Toasting.message(getApplicationContext(),"Record not Deleted");
                }else {
                    Toasting.message(getApplicationContext(),"Record Deleted");
                }
                Intent deldetails = new Intent(UpdateDetails.this,RecycleView.class);
                startActivity(deldetails);
                return true;
            }
        });
   */
    }
    public ArrayList<SetMethods> recallList(){
        ArrayList<SetMethods> al = ds.getData();
        return al;
    }

    public void districtDetails(){
        dist_list = new ArrayList<>();
        dist_list.add("<--- Select your District --->");
        dist_list.add("Kurnool");
        dist_list.add("Anantapur");

        final ArrayAdapter<String> district_list = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, dist_list);
        district_list.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        usdist.setAdapter(district_list);

        usdist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    if (dist_list.get(position).toString().equalsIgnoreCase("Kurnool"))
                        mandalDetails(kurnool_mandal);
                    else {
                        mandalDetails(anantapur_mandal);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*To keep the values of list to the specific place values(here spinners and edittext)*/
        if (!spref.getString("districts","").isEmpty()){

            usdist.setSelection(dist_list.indexOf(spref.getString("districts","")));
    }
    }

    public void mandalDetails(final ArrayList<String> whole_mandal){

        final ArrayAdapter<String> mandal_list = new ArrayAdapter<>(UpdateDetails.this,
                android.R.layout.simple_dropdown_item_1line,whole_mandal);
        mandal_list.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        usmandal.setAdapter(mandal_list);

        usmandal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    usvillage.setVisibility(VISIBLE);
                    if (whole_mandal.get(position).toString().equalsIgnoreCase("pattikonda"))
                        villageDetails(pkd_vill);
                    else if (whole_mandal.get(position).toString().equalsIgnoreCase("adoni"))
                        villageDetails(adoni_vill);
                    else if(whole_mandal.get(position).toString().equalsIgnoreCase("mntl"))
                        villageDetails(mntl_vill);
                    else if (whole_mandal.get(position).toString().equalsIgnoreCase("guthi"))
                        villageDetails(guthi_vill);
                    else if (whole_mandal.get(position).toString().equalsIgnoreCase("guntakal"))
                        villageDetails(guntakal_vill);
                    else
                        villageDetails(dharmavaram_vill);

                }else {
                    usname.setVisibility(GONE);
                    usfname.setVisibility(GONE);
                    update_data.setVisibility(GONE);
                    view_data.setVisibility(GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (!spref.getString("mandals","").isEmpty()) {

            usmandal.setSelection(whole_mandal.indexOf(spref.getString("mandals", "")));
        }
    }

    public void villageDetails(ArrayList<String> whole_vill){

        final ArrayAdapter<String> village_list = new ArrayAdapter<>(UpdateDetails.this,
                android.R.layout.simple_dropdown_item_1line,whole_vill);
        village_list.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        usvillage.setAdapter(village_list);

        usvillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    usname.setVisibility(VISIBLE);
                    usfname.setVisibility(VISIBLE);
                    update_data.setVisibility(VISIBLE);
                    view_data.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*preference values were got from the "RECYCLER" class that we given to clicked elements means click on item*/
        if (!spref.getString("villages","").isEmpty()){

            usvillage.setSelection(whole_vill.indexOf(spref.getString("villages","")));
            usname.setText(spref.getString("name",""));
            usfname.setText(spref.getString("fname",""));
            uniq = spref.getString("uni_id","");
        }

    }
}
