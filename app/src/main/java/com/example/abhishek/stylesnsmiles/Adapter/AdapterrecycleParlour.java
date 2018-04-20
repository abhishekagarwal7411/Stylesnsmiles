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
                name = albumList.get(position).getName();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

                //AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

                // Setting Dialog Title
//                alertDialog.setTitle("PASSWORD");

                // Setting Dialog Message
                alertDialog.setTitle("PASSWORD");

//                alertDialog.setMessage("Enter Password");

              final EditText  input = new EditText(mContext);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                lp.setMargins(20,0,20,0);
                input.setLayoutParams(lp);
                input.setHint("Enter Password");
                alertDialog.setView(input);

//                alertDialog.setIcon(R.drawable.key);
                //alertDialog.setView(input);

                // Setting Icon to Dialog


                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                // Write your code here to execute after dialog
                               String edittext = input.getText().toString();
//                               String positions= albumList.get(position).toString();
//                                Log.e("",positions);
//                                Log.e("",edittext);
                                if(edittext.equalsIgnoreCase("password")) {
                                    Toast.makeText(mContext, "Password Matched", Toast.LENGTH_SHORT).show();
                                    Intent centerLocationDetails = new Intent(mContext, ParlourRegister.class);

                                    centerLocationDetails.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    centerLocationDetails.putExtra("parlourname", name);
                                    mContext.startActivity(centerLocationDetails);
                                }else{
                                    Toast.makeText(mContext, "Password  doesn't Matched", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });

                // closed

                // Showing Alert Message
                alertDialog.show();


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