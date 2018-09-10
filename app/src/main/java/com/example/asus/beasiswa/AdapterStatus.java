package com.example.asus.beasiswa;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.VHolderStatus> {

    String [] uBiasa;
    String [] uRegister;
    boolean st =false;

    AdapterStatus(String [] b, String [] r){
        uBiasa = b;
        uRegister = r;
    }

    @NonNull
    @Override
    public VHolderStatus onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_beasiswa_registered,viewGroup,false);
        return new VHolderStatus(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolderStatus vHolderStatus, int i) {

        for (int j=0; j<uRegister.length; j++){
            if (uBiasa[i].equalsIgnoreCase(uRegister[j])){
                vHolderStatus.namaU.setText(uBiasa[i]);
                vHolderStatus.status.setText("Registered");
                st = true;
            }
        }

        if (!st){
            vHolderStatus.namaU.setText(uBiasa[i]);
            vHolderStatus.status.setText("Un-Registered");
        }

    }

    @Override
    public int getItemCount() {
        return uBiasa.length;
    }

    class VHolderStatus extends RecyclerView.ViewHolder{

        TextView namaU,status;

        public VHolderStatus(@NonNull View itemView) {
            super(itemView);

            namaU = (TextView) itemView.findViewById(R.id.nameUser);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }
}
