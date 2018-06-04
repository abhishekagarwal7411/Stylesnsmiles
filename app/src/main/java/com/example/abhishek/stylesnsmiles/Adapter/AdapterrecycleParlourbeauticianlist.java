package com.example.abhishek.stylesnsmiles.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.PojoClass.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class AdapterrecycleParlourbeauticianlist extends RecyclerView.Adapter<AdapterrecycleParlourbeauticianlist.MyViewHolder> {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    List<PojoParlourBeauticaian> albumList;
    String name, url;
    private Context mContext;
String title;
    public AdapterrecycleParlourbeauticianlist(Context mContext, List<PojoParlourBeauticaian> albumList ,String title) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.title= title;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beautician_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(title);
        PojoParlourBeauticaian album = albumList.get(position);
        holder.title.setText(album.getUsername());
        holder.mobile.setText(album.getMobilenumber());
        holder.emailid.setText(album.getEmailId());
//        name = albumList.get(position).getName();
        // loading album cover using Glide library
//
        holder.delbeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder((Activity)mContext);

                alert.setMessage("Do you want to delete?");
                alert.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int whichButton) {
                                dialog.cancel();
                            }
                        });
                alert.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int whichButton) {
                                name = albumList.get(position).getUsername();
                                databaseReference.child(name).setValue(null);
//                        notifyDataSetChanged();
                                albumList.remove(position);
                                notifyDataSetChanged();

                            }
                        });
                alert.create().show(); // btw show() create
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
        public TextView title, emailid, mobile;
        ImageView delbeauty;

        //        public ImageView thumbnail, overflow;
//LinearLayout llalbum;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            emailid = (TextView) view.findViewById(R.id.email);
            mobile = (TextView) view.findViewById(R.id.mob);
delbeauty=view.findViewById(R.id.delete_beautician);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }
}