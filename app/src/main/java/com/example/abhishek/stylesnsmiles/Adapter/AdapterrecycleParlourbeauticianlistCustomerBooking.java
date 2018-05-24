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

import java.util.List;


public class AdapterrecycleParlourbeauticianlistCustomerBooking extends RecyclerView.Adapter<AdapterrecycleParlourbeauticianlistCustomerBooking.MyViewHolder> {

    List<BookingDetails> albumList;
    String name, url;
    String mob, email, status, title;
    private Context mContext;

    public AdapterrecycleParlourbeauticianlistCustomerBooking(Context mContext, List<BookingDetails> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beautician_adapter_booking, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        BookingDetails album = albumList.get(position);
        holder.title.setText(album.getParlourEmployeename());
        holder.username.setText(album.getUsername());
        holder.usermob.setText(album.getCustomermobile());
        holder.date.setText(album.getDate());
        holder.time_book.setText(album.getTime());
//        name = albumList.get(position).getName();
        // loading album cover using Glide library
//

    }

    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, username, usermob,date,time_book;
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
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }
}