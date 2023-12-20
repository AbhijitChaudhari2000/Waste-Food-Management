package com.example.wastefoodmanagment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter2  extends RecyclerView.Adapter<Item_ModelViewHolder> {

    Context context;
    ArrayList<Item_Model> itemModelArrayList;
    public RecyclerAdapter2(  Context context, ArrayList<Item_Model> itemModelArrayList) {

        this.context =context;
        this.itemModelArrayList = itemModelArrayList;

    }

    @NonNull
    @Override
    public Item_ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_item2,parent,false);
        return new Item_ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_ModelViewHolder holder, int position) {
        String str =itemModelArrayList.get(position).getName();
        holder.name2.setText(itemModelArrayList.get(position).getName());
        holder.address2.setText(itemModelArrayList.get(position).getAddress());
        holder.contact.setText(itemModelArrayList.get(position).getContact());
        String et1 = itemModelArrayList.get(position).getContact();
        holder.id.setText(itemModelArrayList.get(position).getId());
        holder.idngo.setText(itemModelArrayList.get(position).getIdngo());
        holder.donate.setText(itemModelArrayList.get(position).getDonate());
        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent();
                i1.setAction(Intent.ACTION_SEND);
                i1.setType("text/plain");
                i1.putExtra(Intent.EXTRA_TEXT, "");
                context.startActivity(i1);
            }
        });
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str1 = et1.toString();
                    Intent i1 = new Intent(Intent.ACTION_DIAL);
                    i1.setData(Uri.parse("tel:" +str1));

                   // Intent callIntent = new Intent(Intent.ACTION_CALL);
                   // callIntent.setData(Uri.parse("tel:"+8802177690l));//change the number.
                    context.startActivity(i1);


                } catch (Exception e) {
                  e.printStackTrace();
                }
            }
        });


}


    @Override
    public int getItemCount() {
        return itemModelArrayList.size();
    }
}
class Item_ModelViewHolder extends RecyclerView.ViewHolder{


    TextView name2,address2,id,donate,contact,idngo;
    Button chat,call;
    public Item_ModelViewHolder(@NonNull View itemView) {
        super(itemView);
        idngo = itemView.findViewById(R.id.idngo2);
        id = itemView.findViewById(R.id.id2);
        name2= itemView.findViewById(R.id.name2);
        address2 =itemView. findViewById(R.id.address2);
        donate =itemView. findViewById(R.id.donate2);
        contact =itemView. findViewById(R.id.contact2);
        chat= itemView.findViewById(R.id.chat);
        call= itemView.findViewById(R.id.call);
    }
}