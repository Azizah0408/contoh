package com.hidayat.zulfa.uas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hidayat.zulfa.uas.CRUD.RequestHandler;
import com.hidayat.zulfa.uas.CRUD.TampilMhs;
import com.hidayat.zulfa.uas.CRUD.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListSuka extends Fragment {

    Button btn;

//    String[] arraylist = {"Zulfa","Saya","Hidayat","Maulana","Pramita","Itu","Kamu","Apaan"};

    private ListView listView;
    private String JSON_STRING;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listView = (ListView)view.findViewById(R.id.listsuka);
        getJSON();
//        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.adapternya, arraylist);
//        ListView listView = (ListView)view.findViewById(R.id.listsuka);
//        listView.setAdapter(adapter);

//        btn = (Button)view.findViewById(R.id.btnsuka);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(getContext(), SukaActivity.class);
//                startActivity(a);
//            }
//        });
    }
    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_Id);
                String name = jo.getString(konfigurasi.TAG_SUKA);
//                String nim = jo.getString(konfigurasi.TAG_NIM);
//                String alamat = jo.getString(konfigurasi.TAG_ALAMAT);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_Id, id);
                employees.put(konfigurasi.TAG_SUKA, name);
//                employees.put(konfigurasi.TAG_NIM, nim);
//                employees.put(konfigurasi.TAG_ALAMAT, alamat);
                list.add(employees);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getContext(), list , R.layout.adapternya,
                new String[]{konfigurasi.TAG_Id,konfigurasi.TAG_SUKA},
                new int[]{R.id.label, R.id.label2, });
        listView.setAdapter(adapter);
    }
    private void getJSON() {
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Mengambil Data","Mohon Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_SUKA);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
