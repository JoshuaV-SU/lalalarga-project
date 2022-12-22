package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainTransport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transport);

        Button transc = (Button) findViewById(R.id.btn_transC);
        Button plane = (Button) findViewById(R.id.btn_plane);
        Button ferry = (Button) findViewById(R.id.btn_ferry);

        transc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrans();
            }
        });

        plane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlane();
            }
        });
        ferry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFerry();
            }
        });
    }

    private void openFerry() {
        Intent intent = new Intent(this, com.example.code.FerryList.class);
        startActivity(intent);
    }

    private void openTrans() {
        Intent intent = new Intent(this, TransList.class);
        startActivity(intent);
    }

    private void openPlane() {
        Intent intent = new Intent(this, com.example.code.PlaneList.class);
        startActivity(intent);
    }
}