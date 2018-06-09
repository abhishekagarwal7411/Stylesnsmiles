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

import com.example.abhishek.stylesnsmiles.Activity.BookingAppointment;
import com.example.abhishek.stylesnsmiles.PojoClass.BookingDetails;
import com.example.abhishek.stylesnsmiles.PojoClass.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class AdapterrecycleParlourbeauticianlistCustomerBooking extends RecyclerView.Adapter<AdapterrecycleParlourbeauticianlistCustomerBooking.MyViewHolder> {

    List<BookingDetails> albumList;
    String name, url;
    String mob, email, status, title;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String titleb,page;
    private Context mContext;


    public AdapterrecycleParlourbeauticianlistCustomerBooking(Context mContext, List<BookingDetails> albumList,String titleb,String page) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.titleb=titleb;
        this.page=page;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beautician_adapter_booking, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(titleb + "Booking");

        final BookingDetails album = albumList.get(position);
        holder.title.setText(album.getParlourEmployeename());
        holder.username.setText(album.getUsername());
        holder.usermob.setText(album.getCustomermobile());
        holder.date.setText(album.getDate());
        holder.time_book.setText(album.getTime());
        if(!albumList.isEmpty()){
            for(int i=0;i<albumList.size();i++){

                if(albumList.get(i).getStatus().equalsIgnoreCase("0")){
                    holder.ordersuccess.setVisibility(View.GONE);
                    holder.orderStat.setText("Cancelled");
            holder.orderStat.setEnabled(false);
                }
            }
        }
        if(page.equalsIgnoreCase("parlour")){
            holder.ordersuccess.setVisibility(View.GONE);
            holder.orderStat.setVisibility(View.GONE);
        }
//        if(album.)
//        if(album.getStatus().equalsIgnoreCase("1")){
//            holder.ordersuccess.setVisibility(View.VISIBLE);
////            holder.orderStat.setVisibility(View.GONE);
//        }
//        else {
//            holder.ordersuccess.setVisibility(View.GONE);
//            holder.orderStat.setVisibility(View.VISIBLE);
//            holder.orderStat.setText("cancelled");
//            holder.orderStat.setEnabled(false);
//
//        }
//        name = albumList.get(position).getName();
//         loading album cover using Glide library
holder.orderStat.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String key=albumList.get(position).getUsername().concat(albumList.get(position).getParlourEmployeename());

        databaseReference
                .child(key)
                .child("status").setValue("0");
        holder.ordersuccess.setVisibility(View.GONE);
        holder.orderStat.setText("Cancelled");
        notifyDataSetChanged();


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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, username, usermob,date,time_book,orderStat,ordersuccess;
        private Button btnbook;

        //        public ImageView thumbnail, overflow;
//LinearLayout llalbum;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_name);
            username = (TextView) view.findViewById(R.id.username);
            usermob = (TextView) view.findViewById(R.id.usermob);
            date = view.findViewById(R.id.date);
            time_book = view.findViewById(R.id.time_book);
            orderStat=view.findViewById(R.id.ordstat);
            ordersuccess=view.findViewById(R.id.orderStatus);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }
}