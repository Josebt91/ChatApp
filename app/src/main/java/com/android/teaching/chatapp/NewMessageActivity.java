package com.android.teaching.chatapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewMessageActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        Toolbar myToolbar=findViewById(R.id.toolbar_new);
        setSupportActionBar(myToolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        usernameEditText=findViewById(R.id.username);
        messageEditText=findViewById(R.id.description);

    }
    public boolean camposEmpty(View view){

        if(usernameEditText.getText().toString().isEmpty()||
                messageEditText.getText().toString().isEmpty()){
            Toast.makeText(this,"ERROR: Se han detectado campos vacios",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }
    }

    public void enviarMensaje(View view){

        int numero = (int)Math.random();
        String cadena=" ";
        cadena=String.valueOf(numero);

        if(camposEmpty(view)){
        Message nmessage=new Message();
        nmessage.setUsername(usernameEditText.getText().toString());
        nmessage.setText(messageEditText.getText().toString());

        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("messages");

        DatabaseReference ref =myRef.child(cadena);
        Log.d("TAG"," este es el valor "+cadena);

        ref.setValue(nmessage);



        Log.d("Message",nmessage.getText());}

    }
}
