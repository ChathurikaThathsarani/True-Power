package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText email;
    private EditText password;
    private Button signUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_start);
        bottomNavigationView.setSelectedItemId(R.id.item_register);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_start:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.item_login:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.item_register:
                        return true;
                }

                return false;
            }
        });

        name = findViewById(R.id.et_reg_name);
        age = findViewById(R.id.et_reg_age);
        email = findViewById(R.id.email_reg_email);
        password = findViewById(R.id.pw_reg_password);
        signUp = findViewById(R.id.btn_sign_up);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_name = name.getText().toString().trim();
                String txt_age = age.getText().toString().trim();
                String txt_email = email.getText().toString().trim();
                String txt_password = password.getText().toString().trim();

                if(TextUtils.isEmpty(txt_name)||TextUtils.isEmpty(txt_age)||TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(RegisterActivity.this, "Please Fill All The Required Fields !!!", Toast.LENGTH_SHORT).show();
                }
                if(txt_password.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password Too Short !!!", Toast.LENGTH_SHORT).show();
                }
                    auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "User Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"User Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            }
        });
    }


}