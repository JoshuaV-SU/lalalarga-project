package com.example.code;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText edt_vehtype = (EditText) findViewById(R.id.edit_vehType);
        EditText edt_vehid = (EditText) findViewById(R.id.edit_vehID);
        EditText edt_cid = (EditText) findViewById(R.id.edit_compid);
        EditText edt_vehnum = (EditText) findViewById(R.id.edit_vehNum);
        EditText edt_seats = (EditText) findViewById(R.id.edit_vehSeats);
        Button add = (Button) findViewById(R.id.btn_add);

        com.example.code.AddFerry addVeh = new com.example.code.AddFerry();
        AddPlane addp = new AddPlane();

        add.setOnClickListener((View v)->{
            String txt = edt_vehtype.getText().toString();
            String txt1 = "Plane";
            String res = String.valueOf(txt.contains(txt1));

            int vnum = Integer.parseInt(edt_vehnum.getText().toString());
            int seats = Integer.parseInt(edt_seats.getText().toString());

            boolean fields = empty();

            /*if (fields == true) {
                Toast.makeText(this,"Fields must not be empty", Toast.LENGTH_LONG).show();
            }*/

           /* else {*/

                if (res == "true") {
                    com.example.code.AIR air = new com.example.code.AIR(edt_vehid.getText().toString(), edt_cid.getText().toString(), vnum, seats);
                    addp.addP(air).addOnSuccessListener((Void suc) -> {
                        Toast.makeText(this, "Plane record added.", Toast.LENGTH_LONG).show();
                        clerartxt();
                    }).addOnFailureListener((Exception er) -> {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_LONG).show();
                    });
                } else {
                    com.example.code.SEA sea = new com.example.code.SEA(edt_vehid.getText().toString(), edt_cid.getText().toString(), vnum, seats);
                    addVeh.addF(sea).addOnSuccessListener((Void suc) -> {
                        Toast.makeText(this, "Ferry record added.", Toast.LENGTH_LONG).show();
                    }).addOnFailureListener((Exception er) -> {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_LONG).show();
                    });
                }
           // }
        });

    }

    private boolean empty() {
        boolean empty = false;

        EditText edt_vehtype = (EditText) findViewById(R.id.edit_vehType);
        EditText edt_vehid = (EditText) findViewById(R.id.edit_vehID);
        EditText edt_cid = (EditText) findViewById(R.id.edit_compid);
        EditText edt_vehnum = (EditText) findViewById(R.id.edit_vehNum);
        EditText edt_seats = (EditText) findViewById(R.id.edit_vehSeats);

        if (TextUtils.isEmpty(edt_vehtype.getText().toString())){ empty = true;}

        else if (TextUtils.isEmpty(edt_vehid.getText().toString())){ empty = true;}

        else if (TextUtils.isEmpty(edt_cid.getText().toString())){ empty = true;}

        else if(TextUtils.isEmpty(edt_vehnum.getText().toString())){ empty = true;}

        else if (TextUtils.isEmpty(edt_seats.getText().toString())){ empty = true;}

        return empty;
    }

    private void clerartxt() {
        EditText edt_vehtype = (EditText) findViewById(R.id.edit_vehType);
        EditText edt_vehid = (EditText) findViewById(R.id.edit_vehID);
        EditText edt_cid = (EditText) findViewById(R.id.edit_compid);
        EditText edt_vehnum = (EditText) findViewById(R.id.edit_vehNum);
        EditText edt_seats = (EditText) findViewById(R.id.edit_vehSeats);

        edt_vehtype.setText("");
        edt_vehid.setText("");
        edt_cid.setText("");
        edt_vehnum.setText("");
        edt_seats.setText("");

    }

}