package com.example.code;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompanyAdapterHotel extends RecyclerView.Adapter {

    List<ModelClassHotel> fetchCompany;

    public CompanyAdapterHotel(List<ModelClassHotel> fetchCompany) {
        this.fetchCompany = fetchCompany;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        myViewHolder viewHolderClass = new myViewHolder(view);
        return viewHolderClass;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        myViewHolder viewHolderClass = (myViewHolder)holder;
        ModelClassHotel fetchCompanyData = fetchCompany.get(position);
        viewHolderClass.AcmID.setText(fetchCompanyData.getAcmID());
        viewHolderClass.AcmLoc.setText(fetchCompanyData.getAcmLoc());
        viewHolderClass.AcmName.setText(fetchCompanyData.getAcmName());
        viewHolderClass.AcmRmPrc.setText(fetchCompanyData.getAcmRmPrc());
        viewHolderClass.AcmRooms.setText(fetchCompanyData.getAcmRooms());
        viewHolderClass.AcmType.setText(fetchCompanyData.getAcmType());
    }

    @Override
    public int getItemCount() {
        return fetchCompany.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView AcmID, AcmLoc, AcmName, AcmRmPrc, AcmRooms, AcmType;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            AcmID = itemView.findViewById(R.id.AcmIDTxt);
            AcmLoc = itemView.findViewById(R.id.AcmLocTxt);
            AcmName = itemView.findViewById(R.id.AcmNameTxt);
            AcmRmPrc = itemView.findViewById(R.id.AcmRmPrcTxt);
            AcmRooms = itemView.findViewById(R.id.AcmRoomsTxt);
            AcmType = itemView.findViewById(R.id.AcmTypeTxt);

        }
    }
}
