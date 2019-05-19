package com.hidayat.zulfa.uas.CRUD;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.hidayat.zulfa.uas.R;

import java.util.HashMap;

public class CRUDActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextNim;
    private EditText editTextAlamat;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        editTextName = (EditText)findViewById(R.id.etName);
        editTextNim = (EditText)findViewById(R.id.etnim);
        editTextAlamat = (EditText)findViewById(R.id.etalamat);

        buttonAdd = (Button)findViewById(R.id.btnAdd);
        buttonView = (Button)findViewById(R.id.btnView);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);

    }

    private void addEmployee(){
        final String name = editTextName.getText().toString().trim();
        final String nim = editTextNim.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(CRUDActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(CRUDActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA, name);
                params.put(konfigurasi.KEY_EMP_NIM, nim);
                params.put(konfigurasi.KEY_EMP_ALAMAT, alamat);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }
        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonAdd){
            addEmployee();
        }
        if (view == buttonView){
            Intent a = new Intent(CRUDActivity.this, TampilMhs.class);
            startActivity(a);
        }
    }
}