package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {


    private EditText emailname, passwordname;
    private Button update;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        update=findViewById(R.id.btn_Submit);


        if (user != null) {
            String email = user.getEmail();
            boolean emailVerified = user.isEmailVerified();
            emailname = findViewById(R.id.et_email);
            emailname.setText(email);
            passwordname=findViewById(R.id.et_password);

            String uid = user.getUid();
        }
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateProfile();
//            }
//        });



    }

    //   private void updateProfile() {
//        EditText e_Mail = findViewById(R.id.et_email);
//        String e_M=e_Mail.getText().toString();
//
//        String p_name=passwordname.getText().toString();
//
//        user.updateEmail("CTD123@gmail.com")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//
//                   Toast.makeText(UserProfileActivity.this,"Email Updated",Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//        user.updatePassword(p_name)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(UserProfileActivity.this,"Password Updated",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

    //   }

    public void backToMyHealth(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void toBMI(View view) {
        Intent intent = new Intent(this, BMICalculator.class);
        startActivity(intent);
        finish();
    }

    public void toBMR(View view) {
        Intent intent = new Intent(this, BMRCalculatorActivity.class);
        startActivity(intent);
        finish();
    }
}
