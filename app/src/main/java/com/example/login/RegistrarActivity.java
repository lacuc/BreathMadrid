package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarActivity extends AppCompatActivity {

    static final String CLAVE_REGISTRO = "CLAVE_REGISTRO";

    EditText etEmail;
    EditText etPassword;
    EditText etPassword2;

    private FirebaseAuth fa;
    private FirebaseUser fu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        getSupportActionBar().hide();

        fa = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmailReg);
        etPassword = findViewById(R.id.etPasswordReg);
        etPassword2 = findViewById(R.id.etPassword2Reg);


    }

    public void registrar(View view) {
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        String password2 = etPassword2.getText().toString().trim();


        if (email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            Toast.makeText(this, getString(R.string.campos_vacios), Toast.LENGTH_LONG).show();
        } else if (password.length() < 6){
            Toast.makeText(this, getString(R.string.password_corta), Toast.LENGTH_LONG).show();
        } else if (!password.equals(password2)){
            Toast.makeText(this, getString(R.string.password_no_igual), Toast.LENGTH_LONG).show();
        } else {
            fa.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                fu = fa.getCurrentUser();
                                Intent i = new Intent(RegistrarActivity.this, InicioActivity.class);
                                startActivity(i);
                                //Toast.makeText(RegistrarActivity.this, getString(R.string.msj_registrado), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistrarActivity.this, getString(R.string.error_registro), Toast.LENGTH_SHORT).show();
                            } } });

        }
    }

    public void cancelar(View view) {
        Intent i = new Intent(RegistrarActivity.this, InicioActivity.class);
        startActivity(i);
    }
}
