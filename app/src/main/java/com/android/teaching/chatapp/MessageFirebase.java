package com.android.teaching.chatapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageFirebase {

    private ArrayList<Message> messages = new ArrayList<>();

    public void getMessage(final MessagesCallback callback){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myReference = firebaseDatabase.getReference("messages");
        myReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot nodomensaje:dataSnapshot.getChildren()){
                    Message model =nodomensaje.getValue(Message.class);
                    messages.add(model);
                    Log.d("Firebase Interactor", "Message: " + model.getUsername());
                    Log.d("Firebase Interactor", "Message: " + model.getText());

                }
                callback.onMessagesAvailable();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Message> getMessage() {
        return messages;
    }
}
