package com.example.wastefoodmanagment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter3 extends RecyclerView.Adapter<Item_Model3ViewHolder>{
    Context context;
    ArrayList<Item_Model3> itemModelArrayList;

    public RecyclerAdapter3(Context context, ArrayList<Item_Model3> itemModelArrayList) {
        this.context =context;
        this.itemModelArrayList = itemModelArrayList;

    }

    @NonNull
    @Override
    public Item_Model3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item3,parent,false);
        return new Item_Model3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_Model3ViewHolder holder, int position) {
        holder.id.setText(itemModelArrayList.get(position).getId());
        holder.name.setText(itemModelArrayList.get(position).getName());
        holder.date.setText(itemModelArrayList.get(position).getDate());
        String str =itemModelArrayList.get(position).getName();
    }

    @Override
    public int getItemCount() {
        return itemModelArrayList.size();
    }
}
class Item_Model3ViewHolder extends RecyclerView.ViewHolder {
    TextView name,date,id;
    public Item_Model3ViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.txt9);
        name= itemView.findViewById(R.id.txt2);
        date =itemView.findViewById(R.id.txt4);


    }
}
