package com.example.contactphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Contact> contacts = new ArrayList<Contact>();
    private ListView lvContact;
    private ContactDBHelper helper;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        getContact();

        adapter = new ContactAdapter(MainActivity.this, contacts);
        lvContact.setAdapter(adapter);


        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        registerForContextMenu(lvContact);
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
                //Toast.makeText(MainActivity.this, "Add Contact", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
                break;
            case R.id.search_menu:
                Toast.makeText(MainActivity.this, "Search Contact", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = contacts.get(info.position).getId();

        switch (item.getItemId()) {
            case R.id.edit_menu:
                Toast.makeText(MainActivity.this, "Contact Id: " + contacts.get(info.position).getId(),
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

                break;
            case R.id.delete_menu:
                Toast.makeText(MainActivity.this, "Delete Contact",
                        Toast.LENGTH_LONG).show();
                boolean result = helper.delete(id);
                if(result) {
                    adapter.notifyDataSetChanged();
                    getContact();
                    adapter = new ContactAdapter(MainActivity.this, contacts);
                    lvContact.setAdapter(adapter);
                }
                return true;
        }
        return super.onContextItemSelected(item);
    }


}
