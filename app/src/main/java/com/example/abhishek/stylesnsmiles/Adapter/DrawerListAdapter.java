package com.example.abhishek.stylesnsmiles.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.Activity.About;
import com.example.abhishek.stylesnsmiles.Activity.ChatScreen;
import com.example.abhishek.stylesnsmiles.Activity.LoginUser;
import com.example.abhishek.stylesnsmiles.ClientConstant;
import com.example.abhishek.stylesnsmiles.R;


public class DrawerListAdapter extends BaseAdapter {
    Context context;

    String[] navMenuContent;
    TextView drawerText;
    LinearLayout drawerlistparent,drawer_list_parent;
    private String[] listIds;

    public DrawerListAdapter(Context context, String[] navMenuContent) {
        this.context = context;
        this.navMenuContent = navMenuContent;
    }

    @Override
    public int getCount() {
        return navMenuContent.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.activity_drawer_list_adapter, parent, false);
        drawer_list_parent=convertView.findViewById(R.id.drawer_list_parent);
        drawerText = convertView.findViewById(R.id.text_drawer);
        ImageView imageView = convertView.findViewById(R.id.drawer_img);

        drawerlistparent = convertView.findViewById(R.id.drawer_list_parent);
        drawerText.setText(navMenuContent[position]);
        if(navMenuContent[position].equals("Share")){
            imageView.setImageResource(R.drawable.ic_share);
        }
        if(navMenuContent[position].equals("About")){
            imageView.setImageResource(R.drawable.ic_about);
        }
        if(navMenuContent[position].equals("LogOut")){
            imageView.setImageResource(R.drawable.ic_logout);
        }
        if(navMenuContent[position].equals("Chat")){
            imageView.setImageResource(R.drawable.ic_chat);
        }

//            android.util.Log.e("jhdejdwek","bfedk");
//        drawerText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ChatScreen.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
////        intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });

        return convertView;

    }
}
