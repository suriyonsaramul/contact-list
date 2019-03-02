package com.example.contactphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {
    private ContactDBHelper helper;
    private EditText edtName;
    private EditText edtPhone;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        initComponent();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().equals("") || edtPhone.getText().toString().equals("")) {
                    Toast.makeText(AddContactActivity.this, "Input Name and Phone number!",
                            Toast.LENGTH_LONG).show();
                }else{
                    String name = edtName.getText().toString().trim();
                    String phone = edtPhone.getText().toString().trim();

                    Contact contact = new Contact(name, phone);

                    boolean result = helper.insert(contact);
                    if(result) {
                        Toast.makeText(AddContactActivity.this, "Success",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AddContactActivity.this, "Error to add contact",
                                Toast.LENGTH_LONG).show();
                    }
                }
                clearEditText();
            }
        });
    }

    private void clearEditText() {
        edtName.setText("");
        edtPhone.setText("");
        edtName.requestFocus();

    }

    private void initComponent() {
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        btnAdd = findViewById(R.id.btn_add);
        edtName.requestFocus();

        helper = new ContactDBHelper(AddContactActivity.this);
    }
}
