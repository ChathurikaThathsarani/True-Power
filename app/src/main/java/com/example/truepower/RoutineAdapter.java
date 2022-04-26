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

public class RoutineAdapter extends FirebaseRecyclerAdapter<Routine, RoutineAdapter.routineViewholder>{

    private Context context;
    private DatabaseReference routine;
    private FirebaseAuth auth;
    String id;



    public RoutineAdapter(
            @NonNull FirebaseRecyclerOptions<Routine> options,Context context)
    {
        super(options);
        this.context = context;
    }
    public RoutineAdapter(
            @NonNull FirebaseRecyclerOptions<Routine> options)
    {
        super(options);
    }

    protected void onBindViewHolder(@NonNull routineViewholder holder,
                     int position, @NonNull Routine model)
    {
        holder.name.setText(model.getName());
        holder.date.setText(model.getDate());
        holder.description.setText(model.getDescription());
        holder.status.setText(model.getStatus());
        id=model.getId();

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,
                        EditRoutineActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", model.getId());
                bundle.putString("name", model.getName());
                bundle.putString("date", model.getDate());
                bundle.putString("description", model.getDescription());
                bundle.putString("status", model.getStatus());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setCancelable(false);
                alertDialog.setTitle("Delete Routine");
                alertDialog.setMessage("Do you want to delete this routine ?");
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
                routine.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(view.getContext(), "Routine Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "Routine Delete Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public routineViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routine, parent, false);
        return new RoutineAdapter.routineViewholder(view);
    }


    class routineViewholder extends RecyclerView.ViewHolder {

        TextView name, date, description,status;
        Button edit,delete;


        public routineViewholder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.routine_name);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            context=itemView.getContext();
            auth = FirebaseAuth.getInstance();
            routine= FirebaseDatabase.getInstance().getReference().child("routine").child(auth.getCurrentUser().getUid());


        }


    }
}
