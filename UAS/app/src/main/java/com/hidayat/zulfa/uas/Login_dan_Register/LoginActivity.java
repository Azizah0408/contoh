package com.hidayat.zulfa.uas.Login_dan_Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hidayat.zulfa.uas.CreadFragment;
import com.hidayat.zulfa.uas.MainActivity;
import com.hidayat.zulfa.uas.R;

public class LoginActivity extends Activity {

    EditText editTextEmail;
    EditText editTextPassword;

    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    Button buttonLogin;

    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check user input is correct or not
                if (validate()) {
//Get values from EditText fields
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();//Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null,
                            Email, Password));
//Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
//User Logged in Successfully Launch You home screen activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
//User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try aga in", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

        private boolean validate() {

            boolean valid = false;
//Get values from EditText fields
            String Email = editTextEmail.getText().toString();
            String Password = editTextPassword.getText().toString();
//Handling validation for Email field
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                valid = false;
                textInputLayoutEmail.setError("Please enter valid email!");
            } else {
                valid = true;
                textInputLayoutEmail.setError(null);
            }
//Handling validation for Password field
            if (Password.isEmpty()) {
                valid = false;
                textInputLayoutPassword.setError("Please enter valid password!");
            } else {
                if (Password.length() > 5) {
                    valid = true;
                    textInputLayoutPassword.setError(null);
                } else {
                    valid = false;
                    textInputLayoutPassword.setError("Password is to short!");}
            }
            return valid;
        }

    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }return result;
    }

    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class
                );
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Ingin Keluar Dari Aplikasi?");
        builder.setCancelable(true);

        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "Keluar Aja Sana!!!", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
}

