package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class transport_Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport_Add);

        EditText edt_compname = (EditText) findViewById(R.id.edit_compName);
        EditText edt_vehtype = (EditText) findViewById(R.id.edit_TransType);
        EditText edt_compid = (EditText) findViewById(R.id.edit_TransCompID);
        EditText edt_CompNum = (EditText) findViewById(R.id.edit_CompNum);
        Button btn_add = (Button) findViewById(R.id.btn_add);

        Button next = (Button) findViewById(R.id.btn_next);
        Button back = (Button) findViewById(R.id.btn_trans);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrans();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAct2();
            }
        });

        com.example.code.AddTransComp add = new com.example.code.AddTransComp();

        btn_add.setOnClickListener((View v) ->{
            boolean fields = empty();

            if (fields == true){
                Toast.makeText(this,"Fields must not be empty", Toast.LENGTH_LONG).show();
            }
            else{
            int cnum = Integer.parseInt(edt_CompNum.getText().toString());
            com.example.code.TRANSPORT_COMPANY transC = new com.example.code.TRANSPORT_COMPANY(edt_compname.getText().toString(),edt_vehtype.getText().toString(),edt_compid.getText().toString(), cnum);
            add.add(transC).addOnSuccessListener((Void suc) ->{
                Toast.makeText(this, "Record is inserted", Toast.LENGTH_LONG ).show();
                cleartxt();
            }).addOnFailureListener((Exception er) ->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_LONG).show();
            });
            }

        });
    }

    private Boolean empty() {
        boolean empty = false;
        EditText edt_compname = (EditText) findViewById(R.id.edit_compName);
        EditText edt_vehtype = (EditText) findViewById(R.id.edit_TransType);
        EditText edt_compid = (EditText) findViewById(R.id.edit_TransCompID);
        EditText edt_CompNum = (EditText) findViewById(R.id.edit_CompNum);

        if (TextUtils.isEmpty(edt_compid.getText().toString())){ empty = true;}

        else if (TextUtils.isEmpty(edt_CompNum.getText().toString())) { empty = true;}

        else if (TextUtils.isEmpty(edt_compname.getText().toString())){ empty = true;}

        else if (TextUtils.isEmpty(edt_vehtype.getText().toString())){ empty = true;}

        return empty;

    }

    private void cleartxt() {
        EditText edt_compname = (EditText) findViewById(R.id.edit_compName);
        EditText edt_vehtype = (EditText) findViewById(R.id.edit_TransType);
        EditText edt_compid = (EditText) findViewById(R.id.edit_TransCompID);
        EditText edt_CompNum = (EditText) findViewById(R.id.edit_CompNum);

        edt_compname.setText("");
        edt_vehtype.setText("");
        edt_compid.setText("");
        edt_CompNum.setText("");
    }

    private void openTrans() {
        Intent intent = new Intent(this, TransList.class);
        startActivity(intent);
    }

    private void openAct2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}