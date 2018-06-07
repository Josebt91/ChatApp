package com.android.teaching.chatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ListView listView;
    private MessageFirebase messageFirebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listView=findViewById(R.id.chat_view);

        messageFirebase=new MessageFirebase();
        messageFirebase.getMessage(new MessagesCallback() {
            @Override
            public void onMessagesAvailable() {
                myAdapter=new MyAdapter();
                listView.setAdapter(myAdapter);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater myinflater=getMenuInflater();
        myinflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent nuevomensaje= new Intent(this,NewMessageActivity.class);
        startActivity(nuevomensaje);
        return super.onOptionsItemSelected(item);
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return messageFirebase.getMessage().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.chat_item,viewGroup,false);

            TextView username= rowView.findViewById(R.id.username_view);
            username.setText(messageFirebase.getMessage().get(i).getUsername());

            TextView text= rowView.findViewById(R.id.description_view);
            text.setText(messageFirebase.getMessage().get(i).getText());

            return rowView;
        }
    }

}
