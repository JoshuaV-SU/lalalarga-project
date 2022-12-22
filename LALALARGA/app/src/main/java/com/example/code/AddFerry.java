package com.example.code;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFerry {
    private DatabaseReference dbrefferry;

    public AddFerry(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbrefferry = db.getReference(SEA.class.getSimpleName());

    }

    public Task<Void> addF(SEA seaV)
    {
        return dbrefferry.child(seaV.getFerryID()).setValue(seaV);
    }


}
