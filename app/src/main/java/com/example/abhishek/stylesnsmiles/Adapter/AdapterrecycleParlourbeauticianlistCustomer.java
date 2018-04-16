package com.example.abhishek.stylesnsmiles.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.BookingAppointment;
import com.example.abhishek.stylesnsmiles.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.R;

import java.util.List;


public class AdapterrecycleParlourbeauticianlistCustomer extends RecyclerView.Adapter<AdapterrecycleParlourbeauticianlistCustomer.MyViewHolder> {

    private Context mContext;
    List<PojoParlourBeauticaian> albumList;
String name,url;
    String mob,email,status,title;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, emailid,mobile;
        private Button btnbook;
//        public ImageView thumbnail, overflow;
//LinearLayout llalbum;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            emailid = (TextView) view.findViewById(R.id.email);
            mobile = (TextView) view.findViewById(R.id.mob);
            btnbook=view.findViewById(R.id.btnbook);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AdapterrecycleParlourbeauticianlistCustomer(Context mContext, List<PojoParlourBeauticaian> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beautician_adapter_cust, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        PojoParlourBeauticaian album = albumList.get(position);
        holder.title.setText(album.getUsername());
        holder.mobile.setText(album.getMobilenumber());
        holder.emailid.setText(album.getEmailId());
        holder.btnbook.setText(album.getStatus());
//        name = albumList.get(position).getName();
        // loading album cover using Glide library
//
        holder.btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = albumList.get(position).getTitle();
                mob = albumList.get(position).getMobilenumber();
                email = albumList.get(position).getEmailId();
                status = albumList.get(position).getStatus();
                title = albumList.get(position).getUsername();
                Bundle bundle = new Bundle();
                String url = "http://www.google.com";
                bundle.putString("name", name);
                bundle.putString("mobile", mob);
                bundle.putString("email", email);
                bundle.putString("status", status);
                bundle.putString("title", title);
                holder.btnbook.setText("Booked");

                holder.btnbook.setEnabled(false);
//                Intent intt=new Intent(mContext, BookingAppointment.class);
//                        startActivity(new Intent(mContext, BookingAppointment.class));
                Intent intent= new Intent(mContext, BookingAppointment.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}