package com.example.connect_odoo_mobile.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.connect_odoo_mobile.R;
import com.example.connect_odoo_mobile.adapter.ContactAdapter;
import com.example.connect_odoo_mobile.data.GetDataFromOdoo;
import com.example.connect_odoo_mobile.data_models.Contact;
import com.example.connect_odoo_mobile.read_json.ReadJSON;
import com.google.gson.Gson;

import org.apache.xmlrpc.XmlRpcException;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {
    private View view;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList;
    private RecyclerView layoutView;
    private GetDataFromOdoo getDataFromOdoo = new GetDataFromOdoo();
    private Gson gson = new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_customer, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //mapping view
        mapping();
        //set contact recycler view
        setContactRecyclerView();
        return view;
    }

    private void setContactRecyclerView() {
        try {
            contactList = getDataFromOdoo.getContact();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        contactAdapter = new ContactAdapter(getContext(), contactList);
        LinearLayoutManager contactManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutView.setLayoutManager(contactManager);
        layoutView.setAdapter(contactAdapter);
    }

    private void mapping() {
        layoutView = view.findViewById(R.id.layoutView);
    }
}