package com.example.contactphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Contact> contacts = new ArrayList<Contact>();
    private ListView lvContact;
    private ContactDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        getContact();

        ContactAdapter adapter = new ContactAdapter(MainActivity.this, contacts);
        lvContact.setAdapter(adapter);
    }

    private void getContact() {
        contacts = helper.selectAll();
    }

    private void initComponent() {
        lvContact = findViewById(R.id.lv_contact);
        helper = new ContactDBHelper(MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu:
                Toast.makeText(MainActivity.this, "Add Contact", Toast.LENGTH_LONG).show();
                break;
            case R.id.search_menu:
                Toast.makeText(MainActivity.this, "Search Contact", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
