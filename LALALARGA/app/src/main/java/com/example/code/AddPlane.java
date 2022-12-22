package com.example.code;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlane {
    private DatabaseReference dbrefplane;

    public AddPlane(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbrefplane = db.getReference(com.example.code.AIR.class.getSimpleName());
    }

    public Task<Void> addP(com.example.code.AIR airV)
    {
        return dbrefplane.child(airV.getPlaneID()).setValue(airV);
    }
}
