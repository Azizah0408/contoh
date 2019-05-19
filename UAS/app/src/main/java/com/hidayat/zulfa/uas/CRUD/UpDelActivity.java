package com.hidayat.zulfa.uas.CRUD;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hidayat.zulfa.uas.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UpDelActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextNama;
    private EditText editTextNim;
    private EditText editTextAlamat;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_del);

        Intent intent = getIntent();
        id = intent.getStringExtra(konfigurasi.EMP_ID);

        editTextId = (EditText)findViewById(R.id.etId);
        editTextNama = (EditText)findViewById(R.id.etNameUpdt);
        editTextNim = (EditText)findViewById(R.id.etNimupdt);
        editTextAlamat = (EditText)findViewById(R.id.etAlamatupdt);

        buttonUpdate = (Button)findViewById(R.id.btnUpdatemahasiswa);
        buttonDelete = (Button)findViewById(R.id.btnHapusmahasiswa);

//        buttonUpdate.setOnClickListener(this);
//        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getEmployee();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(b);
            }
        });
    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(UpDelActivity.this,"Feching...","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP, id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(konfigurasi.TAG_NAMA);
            String nim = c.getString(konfigurasi.TAG_NIM);
            String alamat = c.getString(konfigurasi.TAG_ALAMAT);

            editTextNama.setText(name);
            editTextNim.setText(nim);
            editTextAlamat.setText(alamat);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void updateEmployee(){
        final String name = editTextNama.getText().toString().trim();
        final String nim = editTextNim.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(UpDelActivity.this,"Updating...","Wait...",false,false);
                Intent a = new Intent(getApplicationContext(), CRUDActivity.class);
                startActivity(a);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UpDelActivity.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID, id);
                hashMap.put(konfigurasi.KEY_EMP_NAMA, name);
                hashMap.put(konfigurasi.KEY_EMP_NIM, nim);
                hashMap.put(konfigurasi.KEY_EMP_ALAMAT, alamat);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);
                return s;
            }
        }
        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(UpDelActivity.this,"Udating...","Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UpDelActivity.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
                return s;
            }
        }
        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghaus Data Ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEmployee();
                        startActivity(new Intent(UpDelActivity.this,TampilMhs.class));
                    }
                });
        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { ;
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

//    @Override
//    public void onClick(View view) {
//        if ( view == buttonUpdate){
//            updateEmployee();
//        }
//        if ( view == buttonDelete){
//            confirmDeleteEmployee();
//        }
//    }
}
