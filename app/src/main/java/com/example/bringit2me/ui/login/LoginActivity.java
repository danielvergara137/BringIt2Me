package com.example.bringit2me.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bringit2me.Activities.MainActivity;
import com.example.bringit2me.DB.DBQueries;
import com.example.bringit2me.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextInputLayout inputusername;
    private TextInputLayout inputpassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);
        inputusername = (TextInputLayout)findViewById(R.id.MainActivity_inputusername);
        inputpassword = (TextInputLayout)findViewById(R.id.MainActivity_inputpassword);
    }

    @Override
    public void onBackPressed() {
    }

    public void Login(View view){
        String str_username = usernameEditText.getText().toString();
        String str_password = passwordEditText.getText().toString();

        if (!str_username.isEmpty()){
            inputusername.setError(null);
            if(!str_password.isEmpty()) {
                inputpassword.setError(null);
                if (DBQueries.isUsuarioRegistrado(str_username, this)) {
                    inputusername.setError(null);
                    if (DBQueries.LoginUsuario(str_username, str_password, this)) {
                        inputpassword.setError(null);
                        Usuario usuario = DBQueries.getUsuario(str_username, this);
                        Intent i = new Intent(this, MainActivity.class);
                        i.putExtra("usuario_entidad", usuario);
                        startActivity(i);
                        this.finish();
                    } else inputpassword.setError("Contraseña incorrecta");
                } else inputusername.setError("Usuario no registrado");
            } else inputpassword.setError("Ingrese contraseña");
        } else inputusername.setError("Ingrese un usuario");
    }
}
