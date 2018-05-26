package com.example.abhishek.stylesnsmiles.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abhishek.stylesnsmiles.Activity.ParlourRegister;
import com.example.abhishek.stylesnsmiles.PojoClass.Album;
import com.example.abhishek.stylesnsmiles.PojoClass.Packages;
import com.example.abhishek.stylesnsmiles.PojoClass.PackagesDetail;
import com.example.abhishek.stylesnsmiles.R;

import java.util.List;

/**
 * Created by Abhishek on 5/04/18
 */
public class AdapterrecyclePackage extends RecyclerView.Adapter<AdapterrecyclePackage.MyViewHolder> {

    String name;
    private Context mContext;
    private List<PackagesDetail> albumList;
//    EditText input;
//    String edittext;
    public AdapterrecyclePackage(Context mContext, List<PackagesDetail> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("size",Integer.toString(albumList.size()));

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.packages_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final PackagesDetail album = albumList.get(position);
        holder.heading.setText(album.getPcktitle());
        holder.txt1.setText( "1 "+album.getPckone());
        holder.txt2.setText("2 "+album.getPcktwo());
        holder.txt3.setText("3 "+album.getPckthree());
        holder.txt4.setText("4 "+album.getPckfour());


        holder.price.setText("Price: " +album.getPckprice());
//        holder.count.setText(album.getCity());
//        name = albumList.get(position).getName();
        // loading album cover using Glide library
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btn.setText("Added");
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
        public TextView heading, txt1,txt2,txt3,txt4,txt5,price;
       Button btn;
        LinearLayout llalbum;

        public MyViewHolder(View view) {
            super(view);
            heading = (TextView) view.findViewById(R.id.heading);
            txt1 = (TextView) view.findViewById(R.id.txtp1);
            txt2 = (TextView) view.findViewById(R.id.txtp2);
            txt3 = (TextView) view.findViewById(R.id.txtp3);

            txt4 = (TextView) view.findViewById(R.id.txtp4);
            price = (TextView) view.findViewById(R.id.txtp6);
            btn=(Button)view.findViewById(R.id.bookingpackage);

//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }
}