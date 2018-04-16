package com.example.abhishek.stylesnsmiles;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.Adapter.ChatForum;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import android.text.format.DateFormat;
public class ChatScreen extends AppCompatActivity {
private static int SIGN_IN_REQUEST_CODE=1;
private FirebaseListAdapter<ChatForum> adapter;
RelativeLayout activity_chat;
    FloatingActionButton fab;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== SIGN_IN_REQUEST_CODE){
            if(requestCode==RESULT_OK){
                Snackbar.make(activity_chat,"Successfully signed in ",Snackbar.LENGTH_SHORT).show();
                displaychatmessage();
            }
            else {
                Snackbar.make(activity_chat,"we couldnt sign in ",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        activity_chat=findViewById(R.id.rel);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input=(EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatForum(input.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            }
        });
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }else{
            Snackbar.make(activity_chat,"Welcome"+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
        }
        displaychatmessage();
    }
    private void displaychatmessage(){
        ListView listOfmessage=findViewById(R.id.list);
        adapter=new FirebaseListAdapter<ChatForum>(this,ChatForum.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, ChatForum model, int position) {
                TextView messagetext,messageuser,messagetime;
                messagetext=findViewById(R.id.message_text);
                messageuser=findViewById(R.id.message_user);
                messagetime=findViewById(R.id.message_time);
                messagetext.setText(model.getMessageText());
                messageuser.setText(model.getMessageUser());
                messagetime.setText(DateFormat.format("dd-MM-yyyy(HH:mm:ss)",model.getMessageTime()));
            }
        };


    }
}
