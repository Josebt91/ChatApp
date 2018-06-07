package com.android.teaching.chatapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ListView listView;
    private MessageFirebase messageFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageFirebase=new MessageFirebase();
        messageFirebase.getMessage();

        listView=findViewById(R.id.chat_view);
        myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);
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

            EditText username= rowView.findViewById(R.id.username_view);
            username.setText(messageFirebase.getMessage().get(i).getUsername());

            EditText description= rowView.findViewById(R.id.description_view);
            username.setText(messageFirebase.getMessage().get(i).getMessage());

            return rowView;
        }
    }

}
