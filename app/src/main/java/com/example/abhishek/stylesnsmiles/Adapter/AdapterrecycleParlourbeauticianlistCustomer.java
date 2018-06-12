package com.example.abhishek.stylesnsmiles.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.Calendar;
import java.util.List;

import static com.example.abhishek.stylesnsmiles.ClientConstant.DEFAULT_PREFERENCE;
import static com.example.abhishek.stylesnsmiles.ClientConstant.KEY_USERNAME;


public class AdapterrecycleParlourbeauticianlistCustomer extends RecyclerView.Adapter<AdapterrecycleParlourbeauticianlistCustomer.MyViewHolder> {

    List<PojoParlourBeauticaian> albumList;
    List<BookingDetails> bookingvalid;
    String name, url;
    String mob, email, status, title;
    String pos,username,titleparlour;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    SharedPreferences defaultPreferences;
    SharedPreferences.Editor editPreferences;
    private Context mContext;

    public AdapterrecycleParlourbeauticianlistCustomer(Context mContext, List<PojoParlourBeauticaian> albumList ,List<BookingDetails> bookingvalid,String titleparlour) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.bookingvalid=bookingvalid;
        this.titleparlour=titleparlour;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beautician_adapter_cust, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        defaultPreferences =mContext.getSharedPreferences(DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        editPreferences = defaultPreferences.edit();
        username = defaultPreferences.getString(KEY_USERNAME, KEY_USERNAME);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(titleparlour+"Booking");
        PojoParlourBeauticaian album = albumList.get(position);
        holder.title.setText(album.getUsername());
        holder.mobile.setText(album.getMobilenumber());
        holder.emailid.setText(album.getEmailId());

//        }
//        Calendar currentDate=Calendar.getInstance();
//              int year=  currentDate.get(Calendar.YEAR);
//        int month =currentDate.get(Calendar.MONTH);
//        int day= currentDate.get(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < bookingvalid.size(); i++) {
            if (album.getUsername().equalsIgnoreCase(bookingvalid.get(i).getParlourEmployeename())) {
                if(bookingvalid.get(i).getStatus().equalsIgnoreCase("0")) {
                    holder.btnbook.setText("Book again");
                }else if(bookingvalid.get(i).getStatus().equalsIgnoreCase("1")){
                    holder.btnbook.setText("BOOKED");
                }
            }
//            if(bookingvalid.get(i).getDate().equalsIgnoreCase(day + "-" + (month + 1) + "-" + year));{
//                String key=bookingvalid.get(i).getUsername().concat(bookingvalid.get(i).getParlourEmployeename());
//                databaseReference.child(key).setValue(null);
//                notifyDataSetChanged();
//            }
        }

//        if(!booking.getStatus().isEmpty() && booking.getStatus().equalsIgnoreCase("1")){
//            holder.btnbook.setText("Booked");
//
//        }else{
//            holder.btnbook.setText("BOOK");
//
//        }
//        name = albumList.get(position).getName();
        // loading album cover using Glide library
//
        if (!holder.btnbook.getText().equals("BOOKED")) {


            holder.btnbook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    name = albumList.get(position).getTitle();
                    mob = albumList.get(position).getMobilenumber();
                    email = albumList.get(position).getEmailId();
                    status = albumList.get(position).getStatus();
                    title = albumList.get(position).getUsername();
                    pos = albumList.get(position).toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("mobile", mob);
                    bundle.putString("email", email);
                    bundle.putString("status", status);
                    bundle.putString("title", title);
                    bundle.putInt("postion", position);

//                Intent intt=new Intent(mContext, BookingAppointment.class);
//                        startActivity(new Intent(mContext, BookingAppointment.class));
                    Intent intent = new Intent(mContext, BookingAppointment.class);
                    intent.putExtras(bundle);

                    mContext.startActivity(intent);
                }
            });
        }else
        {
            holder.btnbook.setEnabled(false);
            holder.cancel.setVisibility(View.VISIBLE);

        }

    holder.cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder((Activity)mContext);

        alert.setMessage("Are you sure you want to cancel Booking?");
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
//                        holder.btnbook.setText("Book");
//                        holder.cancel.setVisibility(View.GONE);
//                        holder.btnbook.setEnabled(true);
//                        Log.e("gggg",username);

                        String emp=albumList.get(position).getUsername();
                        String key=username.concat(emp);

                        databaseReference.child(key).setValue(null);
                        holder.btnbook.setEnabled(true);
                        holder.btnbook.setText("Book");
                        holder.cancel.setVisibility(View.GONE);
                        

                    }
                });
        alert.create().show();
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
        private Button btnbook,cancel;

        //        public ImageView thumbnail, overflow;
//LinearLayout llalbum;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titlecustbook);
            emailid = (TextView) view.findViewById(R.id.emailcustbook);
            mobile = (TextView) view.findViewById(R.id.mobcustbook);
            btnbook = view.findViewById(R.id.btnbookcustbook);
            cancel=view.findViewById(R.id.viscancel);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }
}