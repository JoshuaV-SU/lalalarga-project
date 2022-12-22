package com.example.code;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTransComp {
    private DatabaseReference dbref;

    public AddTransComp (){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbref = db.getReference(TRANSPORT_COMPANY.class.getSimpleName());
    }
    public Task<Void> add(TRANSPORT_COMPANY tcomp)
    {
        return dbref.child(tcomp.getCompID()).setValue(tcomp);
    }

}
