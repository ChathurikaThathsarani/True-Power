package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditRoutineActivity extends AppCompatActivity {
    private EditText name;
    private EditText date;
    private EditText description;
    private EditText status;
    private Button submit;
    private Bundle bundle;

    private FirebaseAuth auth;
    private DatabaseReference  routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_routine);

        name = findViewById(R.id.routine_name);
        date = findViewById(R.id.day_to_complete);
        description = findViewById(R.id.description);
        status = findViewById(R.id.spinner_status);
        submit = findViewById(R.id.edit_routine);
        bundle = getIntent().getExtras();
        auth = FirebaseAuth.getInstance();
        routine=FirebaseDatabase.getInstance().getReference().child("routine").child(auth.getCurrentUser().getUid());
        displayRoutineDetails();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRoutineDetails();
            }
        });
    }
    private void displayRoutineDetails() {
        name.setText(bundle.getString("name"));
        date.setText(bundle.getString("date"));
        description.setText(bundle.getString("description"));
        status.setText(bundle.getString("status"));
    }

    private void updateRoutineDetails() {
        final EditText name = findViewById(R.id.routine_name);
        final EditText date = findViewById(R.id.day_to_complete);
        final EditText description = findViewById(R.id.description);
        final EditText spinner = findViewById(R.id.spinner_status);

        String r_name =name.getText().toString();
        String r_date=date.getText().toString();
        String r_description=description.getText().toString();
        String r_status=spinner.getText().toString();
        String id = bundle.getString("id");


        // validations
        if (TextUtils.isEmpty(r_name)) {
            name.setError("Name is required");
            return;
        }
        if (TextUtils.isEmpty(r_date)) {
            date.setError("Date is required");
            return;
        }
        if (TextUtils.isEmpty(r_description)) {
            description.setError("Description is required");
            return;
        }
        if (TextUtils.isEmpty(r_status)) {
            status.setError("Status is required");
            return;
        }


        Routine updateRoutine = new Routine(id, r_name, r_description, r_status,r_date);
        routine.child(id).setValue(updateRoutine).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditRoutineActivity.this, "Routine Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditRoutineActivity.this, RoutineActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(EditRoutineActivity.this, "Routine Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void backToMyRoutine(View view) {
        Intent intent = new Intent(this, RoutineActivity.class);
        startActivity(intent);
    }

    public EditRoutineActivity() {
    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
    }
}