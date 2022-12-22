package com.example.code;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter{
    ArrayList<Bank> bank;
    public AdapterClass(ArrayList<Bank> bank){
        this.bank = bank;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_holder,parent,false);
       MyViewHolder viewHolderClass =new MyViewHolder(view);
       return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holderClass =(MyViewHolder)holder;
        Bank fetchBankData =bank.get(position);
        holderClass.bankID.setText(fetchBankData.getBankID());
        holderClass.bankName.setText(fetchBankData.getBankName());
        holderClass.compBankNum.setText(String.valueOf(fetchBankData.getCompBankNum()));
        holderClass.compNum.setText(String.valueOf(fetchBankData.getCompNum()));
    }

    /*@Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(bank.get(position).getBankID());
        holder.name.setText(bank.get(position).getBankName());
    }*/

    @Override
    public int getItemCount() {
        return bank.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bankID,bankName,compBankNum,compNum;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bankID = itemView.findViewById(R.id.bankIDView);
            bankName = itemView.findViewById(R.id.bankNameView);
            compBankNum = itemView.findViewById(R.id.compBankNumView);
            compNum = itemView.findViewById(R.id.compNumView);
        }
    }

}
