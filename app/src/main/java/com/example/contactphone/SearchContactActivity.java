package com.example.contactphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class SearchContactActivity extends AppCompatActivity {
    private EditText edtSearch;
    private Button btnSearch;
    private ListView lvContact;
    private ContactDBHelper helper;
    private List<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        initComponent();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtSearch.getText().toString();
                contacts = helper.selectByName(name);

                ContactAdapter adapter = new ContactAdapter(SearchContactActivity.this, contacts);
                lvContact.setAdapter(adapter);
            }
        });
    }

    private void initComponent() {
        edtSearch = findViewById(R.id.edt_search);
        btnSearch = findViewById(R.id.btn_search);
        lvContact = findViewById(R.id.lv_contact);

        helper = new ContactDBHelper(SearchContactActivity.this);
    }
}
