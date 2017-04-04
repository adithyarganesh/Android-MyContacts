package com.example.arvind.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {
    ArrayList<String> a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView lv = (ListView) findViewById(R.id.contactList);
        DBHandler dbHandler = new DBHandler(this);
        ArrayAdapter<String> ar = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        a = dbHandler.getNames();
        if(!a.isEmpty()) {
            ar.addAll(a);

            lv.setAdapter(ar);
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Contacts.this, Details.class);
                startActivity(i);
                finish();
            }
        });
/*@Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = lv.getAdapter().toString();

                    }*/
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = String.valueOf(parent.getItemAtPosition(position));
                        DBHandler dbh = new DBHandler(Contacts.this);
                        //TextView t = (TextView)findViewById(R.id.textView);
                        //t.append(/**/s);
                        //Toast.makeText(Contacts.this, dbh.getDetail(s), Toast.LENGTH_LONG).show();
                        TextView q = (TextView) findViewById(R.id.textView2);
                        q.setText(dbh.getDetail(s).toString());
                        //Snackbar.make(view , dbh.getDetail(s), Snackbar.LENGTH_SHORT).show();
                    }
                }
        );

        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = String.valueOf(parent.getItemAtPosition(position));
                        DBHandler dbh = new DBHandler(Contacts.this);
                        dbh.delete(s);
                        final ListView lv = (ListView) findViewById(R.id.contactList);
                        DBHandler dbHandler = new DBHandler(Contacts.this);
                        ArrayAdapter<String> ar = new ArrayAdapter<String>(Contacts.this,android.R.layout.simple_list_item_1);
                        a = dbHandler.getNames();
                        if(!a.isEmpty()) {
                            ar.addAll(a);

                            lv.setAdapter(ar);
                        }
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.Delete_contact ) {

                Toast.makeText(this, "click item to see details and long click to delete", Toast.LENGTH_LONG).show();

            return true;

        }
        if(id == 2){

        }

        return super.onOptionsItemSelected(item);
    }
}
