package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {
    Button login;
    TextView registrar;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        EditText user = findViewById(R.id.et_email);
        EditText pass  = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        registrar = findViewById(R.id.btn_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registro.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario,contra;
                usuario=String.valueOf(user.getText());
                contra=String.valueOf(pass.getText());
                if (TextUtils.isEmpty(usuario)){
                    Toast.makeText(Registro.this,"ingrese usuario",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(contra)){
                    Toast.makeText(Registro.this,"ingrese Contraseña",Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(usuario,contra)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Registro.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Registro.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Registro.this,"Registro fallido",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}