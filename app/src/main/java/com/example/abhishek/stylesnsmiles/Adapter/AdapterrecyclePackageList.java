package com.example.abhishek.stylesnsmiles.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.Activity.BookingAppointment;
import com.example.abhishek.stylesnsmiles.Activity.PackageDetails;
import com.example.abhishek.stylesnsmiles.PojoClass.Packages;
import com.example.abhishek.stylesnsmiles.PojoClass.PackagesDetail;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Abhishek on 5/04/18
 */
public class AdapterrecyclePackageList extends RecyclerView.Adapter<AdapterrecyclePackageList.MyViewHolder> {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String name;
    private Context mContext;
    private List<PackagesDetail> albumList;
    String title;
//    EditText input;
//    String edittext;
    public AdapterrecyclePackageList(Context mContext, List<PackagesDetail> albumList, String title) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.title=title;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.packages_adapter_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(title+"package");
        final PackagesDetail album = albumList.get(position);
        holder.heading.setText(album.getPcktitle());
        holder.txt1.setText(album.getPckone());
        holder.txt2.setText(album.getPcktwo());
        holder.txt3.setText(album.getPckthree());
        holder.txt4.setText(album.getPckfour());
        holder.price.setText("Price: "+album.getPckprice());
        holder.updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
String pctitle,pcone,pctwo,pcthree,pcfour,pcprice;
                pctitle = albumList.get(position).getPcktitle();
                pcone = albumList.get(position).getPckone();
                pctwo = albumList.get(position).getPcktwo();
                pcthree = albumList.get(position).getPckthree();
                pcfour = albumList.get(position).getPckfour();
                pcprice = albumList.get(position).getPckprice();

                Bundle bundle = new Bundle();
                bundle.putString("pctitle", pctitle);
//                bundle.putString("title", title);
                bundle.putString("pcone", pcone);
                bundle.putString("pctwo", pctwo);
                bundle.putString("pcthree", pcthree);
                bundle.putString("pcfour", pcfour);
                bundle.putString("pcprice", pcprice);

//                Intent intt=new Intent(mContext, BookingAppointment.class);
//                        startActivity(new Intent(mContext, BookingAppointment.class));
                Intent intent = new Intent(mContext, PackageDetails.class);
                intent.putExtras(bundle);
                intent.putExtra("parlourtitle", title);
                mContext.startActivity(intent);
albumList.clear();
                notifyDataSetChanged();

            }
        });
holder.del.setOnClickListener(new View.OnClickListener() {
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
                        name = albumList.get(position).getPcktitle();
                        databaseReference.child(name).setValue(null);
//                        notifyDataSetChanged();
                        albumList.remove(position);
                        notifyDataSetChanged();

                    }
                });
        alert.create().show(); // btw show() creates and shows it..
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
        public TextView heading, txt1,txt2,txt3,txt4,price;
       ImageView del,updatedata;
        LinearLayout llalbum;

        public MyViewHolder(View view) {
            super(view);
            heading = (TextView) view.findViewById(R.id.pcktitlelist);
            txt1 = (TextView) view.findViewById(R.id.itmfirst);
            txt2 = (TextView) view.findViewById(R.id.itmsecond);
            txt3 = (TextView) view.findViewById(R.id.itmthird);

            txt4 = (TextView) view.findViewById(R.id.itmfourth);
            price = (TextView) view.findViewById(R.id.itmprice);
del=view.findViewById(R.id.deletepck);
updatedata=view.findViewById(R.id.update);
        }
    }
}