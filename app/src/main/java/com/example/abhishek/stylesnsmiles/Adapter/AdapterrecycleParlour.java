package com.example.abhishek.stylesnsmiles.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.abhishek.stylesnsmiles.Activity.ParlourRegister;
import com.example.abhishek.stylesnsmiles.PojoClass.Album;
import com.example.abhishek.stylesnsmiles.R;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Abhishek on 5/04/18
 */
public class AdapterrecycleParlour extends RecyclerView.Adapter<AdapterrecycleParlour.MyViewHolder> {

    String name;
    private Context mContext;
    private List<Album> albumList;
//    EditText input;
//    String edittext;
    public AdapterrecycleParlour(Context mContext, List<Album> albumList) {
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
        final Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getCity());
//        name = albumList.get(position).getName();
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.llalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater =  LayoutInflater.from(mContext);
                View alertLayout = inflater.inflate(R.layout.activity_aler, null);
                final EditText etUsername = alertLayout.findViewById(R.id.editTextDialogUserInput);
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
//                alert.setTitle("Password");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = etUsername.getText().toString();
                        Log.e("userdd",user);
                     if(user.equalsIgnoreCase("password")){
                         name = albumList.get(position).getName();


                         Intent centerLocationDetails = new Intent(mContext, ParlourRegister.class);

                         centerLocationDetails.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         centerLocationDetails.putExtra("parlourname", name);
                         mContext.startActivity(centerLocationDetails);
                     }else{
                         Toast.makeText(mContext, "Please enter correct password", Toast.LENGTH_SHORT).show();

                     }
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
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