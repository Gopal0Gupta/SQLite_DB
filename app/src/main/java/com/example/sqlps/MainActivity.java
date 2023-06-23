package com.example.sqlps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBhelper dBhelper = new DBhelper(this);
//        For Contact addition

        dBhelper.addcontact("ram","0123456789");
        dBhelper.addcontact("raman","0123756789");
        dBhelper.addcontact("ramanujan","0173456789");
        dBhelper.addcontact("rama","0123456589");

//        For updation
//        structure structure = new structure();
//        structure.id = 1;
//        structure.number = "0101010101";
//
//        dBhelper.updatecontacts(structure);

//        For Deletion
//        dBhelper.DeleteContact(1);

        ArrayList<structure> arrcontacts = dBhelper.fetchcont();
        for(int i=0;i<arrcontacts.size();i++){
            Log.d("Contact_info","Name : "+arrcontacts.get(i).name+" Number : "+arrcontacts.get(i).number);
        }
    }
}