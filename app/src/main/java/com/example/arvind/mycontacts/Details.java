package com.example.arvind.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button btn = (Button) this.findViewById(R.id.submitButton);

        final TextView n = (TextView) findViewById(R.id.editText);
        final TextView n2 = (TextView) findViewById(R.id.editText2);
        final TextView n3= (TextView) findViewById(R.id.editText3);
        final TextView n4= (TextView) findViewById(R.id.editText4);

        btn.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        DBHandler db = new DBHandler(Details.this);
                        db.addContact(n.getText().toString(),n2.getText().toString(),n3.getText().toString(), n4.getText().toString());

                        Intent i = new Intent(Details.this, Contacts.class);

                        startActivity(i);
                        finish();
                    }
                }
        );
    }

}
