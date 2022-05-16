package com.example.truepower;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HealthAdapter extends FirebaseRecyclerAdapter<Health, HealthAdapter.healthViewholder>{

    private Context context;
    private DatabaseReference health;
    private FirebaseAuth auth;
    String id;


    public HealthAdapter(@NonNull FirebaseRecyclerOptions<Health> options,Context context) {
        super(options);
        this.context = context;
    }
    public HealthAdapter(@NonNull FirebaseRecyclerOptions<Health> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull healthViewholder holder, int position, @NonNull Health model) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String temp = formatter.format(date);
        int getDate = nDays_Between_Dates(temp, model.getNextDate());

        holder.name.setText(model.getName());
        holder.nextDate.setText(getDate+"");
        holder.description.setText(model.getDescription());
        id = model.getId();

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditHealth.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", model.getId());
                bundle.putString("name", model.getName());
                bundle.putString("nextDate", model.getNextDate());
                bundle.putString("lastDate", model.getLastDate());
                bundle.putString("description", model.getDescription());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setCancelable(false);
                alertDialog.setTitle("Delete Health");
                alertDialog.setMessage("Do you want to delete this Health ?");
                alertDialog.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        routineDelete(view);
                    }
                });
                alertDialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }

            private void routineDelete(View view) {
                health.child(model.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(view.getContext(), "Health Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "Health Delete Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public static int nDays_Between_Dates(String date1, String date2) {
        int diffDays = 0;
        try {
            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate = dates.parse(date1);
            Date endDate = dates.parse(date2);
            long diff = endDate.getTime() - startDate.getTime();
            diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Math.abs(diffDays);
    }


    public healthViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_health_card, parent, false);
        return new HealthAdapter.healthViewholder(view);
    }


    class healthViewholder extends RecyclerView.ViewHolder {

        TextView name, description,nextDate;
        Button edit,delete;


        public healthViewholder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.et_disease_name);
            nextDate = itemView.findViewById(R.id.et_remain_date);
            description = itemView.findViewById(R.id.et_description);

            edit=itemView.findViewById(R.id.btnEdit);
            delete=itemView.findViewById(R.id.btnDelete);

            context=itemView.getContext();
            auth = FirebaseAuth.getInstance();
            health = FirebaseDatabase.getInstance().getReference().child("health").child(auth.getCurrentUser().getUid());

        }

    }
}