package com.example.wastefoodmanagment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<ItemModelViewHolder> {

    Context context;
    ArrayList<ItemModel> itemModelArrayList;



    public RecyclerAdapter(Context context, ArrayList<ItemModel> itemModelArrayList) {
        this.context =context;
        this.itemModelArrayList = itemModelArrayList;

    }

    @NonNull
    @Override
    public ItemModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item,parent,false);
        return new ItemModelViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemModelViewHolder holder, int position) {
        String id11 =itemModelArrayList.get(position).getId();
        holder.id.setText(itemModelArrayList.get(position).getId());
        holder.contact.setText(itemModelArrayList.get(position).getContact());
        String et2 = itemModelArrayList.get(position).getContact();
        holder.name.setText(itemModelArrayList.get(position).getName());
        holder.address.setText(itemModelArrayList.get( position).getAddress());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NGODetail.class);
                intent.putExtra("id",id11);

                context.startActivity(intent);
            }
        });
       holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str1 = et2.toString();
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
class ItemModelViewHolder extends RecyclerView.ViewHolder{

    TextView name,address,id,contact;
    Button btn,call;
    public ItemModelViewHolder(@NonNull View itemView) {
        super(itemView);
        contact = itemView.findViewById(R.id.txt5);

        id = itemView.findViewById(R.id.id);
        name= itemView.findViewById(R.id.txt2);
        address =itemView. findViewById(R.id.txt6);
        btn = itemView.findViewById(R.id.btnshare);
       call = itemView.findViewById(R.id.call2);

    }
}
