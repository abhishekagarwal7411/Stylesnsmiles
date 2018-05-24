package com.example.abhishek.stylesnsmiles.Activity;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;

public class MessageAdapter extends FirebaseListAdapter<ChatMessage> {

    private GroupChat activity;

    public MessageAdapter(GroupChat activity, Class<ChatMessage> modelClass, int modelLayout, DatabaseReference ref) {
        super(activity, modelClass, modelLayout, ref);
        this.activity = activity;
    }

    @Override
    protected void populateView(View v, ChatMessage model, int position) {
        TextView messageText = (TextView) v.findViewById(R.id.message_text);
        TextView messageUser = (TextView) v.findViewById(R.id.message_user);
        TextView messageTime = (TextView) v.findViewById(R.id.message_time);

//        Log.e("messageText",model.getMessageText());
//        Log.e("messageuser",model.getMessageUser());


        messageText.setText(model.getMessageText());
        messageUser.setText(model.getMessageUser());

        // Format the date before showing it
        messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ChatMessage chatMessage = getItem(position);

//        Log.e("chatMessage",chatMessage.getMessageUserId().toString());


//                    if (chatMessage.getMessageUserId().equals(activity.getLoggedInUserName()))
//                        view = activity.getLayoutInflater().inflate(R.layout.item_out_message, viewGroup, false);
//
//                    else
//                        view = activity.getLayoutInflater().inflate(R.layout.item_in_message, viewGroup, false);
//
        view = activity.getLayoutInflater().inflate(R.layout.item_out_message, viewGroup, false);

//        if(chatMessage.getMessageUserId()==null || chatMessage.getMessageText()==null || chatMessage.getMessageUser()==null)
//                    {
//
//                    }else {
                        populateView(view, chatMessage, position);
//                    }
//



        return view;

        //generating view

    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }
}
