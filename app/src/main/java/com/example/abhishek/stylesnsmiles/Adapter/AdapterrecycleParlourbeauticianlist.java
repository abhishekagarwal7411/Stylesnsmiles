package com.example.abhishek.stylesnsmiles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.PojoClass.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.R;

import java.util.List;


public class AdapterrecycleParlourbeauticianlist extends RecyclerView.Adapter<AdapterrecycleParlourbeauticianlist.MyViewHolder> {

    List<PojoParlourBeauticaian> albumList;
    String name, url;
    private Context mContext;

    public AdapterrecycleParlourbeauticianlist(Context mContext, List<PojoParlourBeauticaian> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beautician_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        PojoParlourBeauticaian album = albumList.get(position);
        holder.title.setText(album.getUsername());
        holder.mobile.setText(album.getMobilenumber());
        holder.emailid.setText(album.getEmailId());
//        name = albumList.get(position).getName();
        // loading album cover using Glide library
//
//        holder.llalbum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                name = albumList.get(position).getName();
//
//                Intent centerLocationDetails = new Intent(mContext, ParlourDetails.class);
//
//                centerLocationDetails.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                centerLocationDetails.putExtra("parlournameCustomer",name);
//                mContext.startActivity(centerLocationDetails);
//            }
//        });
    }

    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, emailid, mobile;

        //        public ImageView thumbnail, overflow;
//LinearLayout llalbum;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            emailid = (TextView) view.findViewById(R.id.email);
            mobile = (TextView) view.findViewById(R.id.mob);

//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }
}