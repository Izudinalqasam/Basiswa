package com.example.asus.beasiswa;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.VHolderStatus> {

    ArrayList<String> uBiasa = new ArrayList<>();
    ArrayList<String> uRegister = new ArrayList<>();
    boolean st =false;

    AdapterStatus(ArrayList<String> b,ArrayList<String> r){
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

        for (int j=0; j<uRegister.size(); j++){
            if (uBiasa.get(i).equalsIgnoreCase(uRegister.get(j))){
                vHolderStatus.namaU.setText(uBiasa.get(i));
                vHolderStatus.status.setText("Registered");
                st = true;
            }
        }

        if (!st){
            vHolderStatus.namaU.setText(uBiasa.get(i));
            vHolderStatus.status.setText("Un-Registered");
        }

    }

    @Override
    public int getItemCount() {
        return uBiasa.size();
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
