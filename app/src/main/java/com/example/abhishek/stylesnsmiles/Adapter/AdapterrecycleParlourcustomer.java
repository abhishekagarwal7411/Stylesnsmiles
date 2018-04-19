package com.example.abhishek.stylesnsmiles.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abhishek.stylesnsmiles.Activity.ParlourDetails;
import com.example.abhishek.stylesnsmiles.PojoClass.Album;
import com.example.abhishek.stylesnsmiles.R;

import java.util.List;

/**
 * Created by Abhishek on 2/04/18
 */
public class AdapterrecycleParlourcustomer extends RecyclerView.Adapter<AdapterrecycleParlourcustomer.MyViewHolder> {

    String name, url;
    int thumbnails;
    private Context mContext;
    private List<Album> albumList;

    public AdapterrecycleParlourcustomer(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getCity());
//        name = albumList.get(position).getName();
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.llalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = albumList.get(position).getName();
//            thumbnails=albumList.get(position).getThumbnail();
                Intent centerLocationDetails = new Intent(mContext, ParlourDetails.class);

                centerLocationDetails.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                centerLocationDetails.putExtra("parlournameCustomer", name);
//                centerLocationDetails.putExtra("image",thumbnails);

                mContext.startActivity(centerLocationDetails);
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
        public TextView title, count;
        public ImageView thumbnail, overflow;
        LinearLayout llalbum;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            llalbum = (LinearLayout) view.findViewById(R.id.parlouralbum);

//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }
}