package com.example.asus.beasiswa;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AddapterBeasiswa extends RecyclerView.Adapter<AddapterBeasiswa.ViewHolder> {

    ArrayList<ModelBeasiswa> listB;
    RecyclerClick click;

    AddapterBeasiswa(ArrayList<ModelBeasiswa> l, RecyclerClick c){
        listB = l;
        click = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_beasiswa,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.judul.setText(listB.get(i).getNama());
        viewHolder.deksipsi.setText(listB.get(i).getDestination());

    }

    public void swap(ArrayList<ModelBeasiswa> b){
        if (b == null  || b.size() ==0)
            return;
        if (listB !=null || listB.size()>0)
            listB.clear();

        listB = b;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listB.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView judul,deksipsi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = (TextView) itemView.findViewById(R.id.namaBeasiswa);
            deksipsi = (TextView) itemView.findViewById(R.id.katNegara);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            click.onCLick(view,this.getLayoutPosition());
        }
    }
}
