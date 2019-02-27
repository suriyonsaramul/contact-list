package com.example.contactphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditContactActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPhone;
    private Button btnUpdate;
    private ContactDBHelper helper;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        initComponent();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        addDataToEditText(id);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact = new Contact(contact.getId(), edtName.getText().toString(), edtPhone.getText().toString());
                boolean result = helper.update(contact);
                if(result){
                    Intent intent = new Intent(EditContactActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{

                }
            }
        });
    }

    private void addDataToEditText(int id) {
        contact = helper.selectById(id);
        edtName.setText(contact.getName());
        edtPhone.setText(contact.getPhone());
    }

    private void initComponent() {
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        btnUpdate = findViewById(R.id.btn_update);
        helper = new ContactDBHelper(EditContactActivity.this);
    }
}
