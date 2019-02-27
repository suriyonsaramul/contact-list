package com.example.contactphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private List<Contact> contacts;
    LayoutInflater inflater;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.contacts = contacts;
        inflater =LayoutInflater.from(context);
    }

    class ViewHolder {
        TextView tvName;
        TextView tvPhone;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null) {
            view = inflater.inflate(R.layout.contact_row, parent, false);

            holder = new ViewHolder();
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvPhone = view.findViewById(R.id.tv_phone);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvName.setText(contacts.get(position).getName());
        holder.tvPhone.setText(contacts.get(position).getPhone());

        return view;
    }
}
