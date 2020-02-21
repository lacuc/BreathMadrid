package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InicioActivity extends AppCompatActivity {

    ImageView fondo;
    TextView tvSignUp;
    EditText etEmail;
    EditText etPassword;

    String email;
    String password;

    private FirebaseAuth fa;
    private FirebaseUser fu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        getSupportActionBar().hide();
        //animacionFondo();


        tvSignUp = findViewById(R.id.tvSignUpTextini);
        fondo = findViewById(R.id.BackgroundImageIni);
        etEmail = findViewById(R.id.etEmailIni);
        etPassword = findViewById(R.id.etPasswordIni);

        fa = FirebaseAuth.getInstance();
        onClickListenerRegistro();

    }

    private void animacionFondo() {
        Animation myAnimLogo = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fondo.startAnimation(myAnimLogo);
    }

    private void onClickListenerRegistro() {
        tvSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(InicioActivity.this, RegistrarActivity.class);
                startActivity(intent);


            }
        });
    }

    public void acceder(View view) {
        //Intent i = new Intent(InicioActivity.this, ScrollingActivity.class);
        //startActivity(i);
        String msj = validarDatos();

        if(msj != null){
            Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
        } else {
            fa.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                fu = fa.getCurrentUser();
                                //Intent i = new Intent(InicioActivity.this, SegundoActivity.class);
                                //i.putExtra(CLAVE_EMAIL, fu.getEmail());
                                Intent i = new Intent(InicioActivity.this, ScrollingActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(InicioActivity.this, getString(R.string.msj_no_accede), Toast.LENGTH_SHORT).show();
                            } } });


        }
    }

    public void prueba(View view) {
        Intent i = new Intent(this, RefreshActivity.class);
        startActivity(i);
    }

    private String validarDatos(){
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        String msj = null;
        if (email.isEmpty() || password.isEmpty()){
            msj = getString(R.string.campos_vacios);
        }
        return msj;
    }
}
