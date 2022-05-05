package com.example.truepower;

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
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;

public class WorkoutAdapter  extends FirebaseRecyclerAdapter<Workout, WorkoutAdapter.workoutViewholder> {

    private Context context;
    private DatabaseReference workout;
    private FirebaseAuth auth;
    String id;


    public WorkoutAdapter(
            @NonNull FirebaseRecyclerOptions<Workout> options,Context context)
    {
        super(options);
        this.context = context;
    }
    public WorkoutAdapter(
            @NonNull FirebaseRecyclerOptions<Workout> options) {
            super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull workoutViewholder holder, int position, @NonNull Workout model)
    {
        holder.workoutName.setText(model.getWorkoutName());
        holder.workoutRepetitionVar.setText(model.getWorkoutRepetitionVar());
        holder.workoutTimeVar.setText(model.getWorkoutTimeVar());
        id=model.getId();

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,
                            EditWorkoutActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", model.getId());
                    bundle.putString("WorkoutName", model.getWorkoutName());
                    bundle.putString("WorkoutRepetitionVar", model.getWorkoutRepetitionVar());
                    bundle.putString("WorkoutTimeVar", model.getWorkoutTimeVar());
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle("Delete Workout");
                    alertDialog.setMessage("Do you want to delete this Workout ?");
                    alertDialog.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            workoutDelete(view);
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

                private void workoutDelete(View view) {
                    workout.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(view.getContext(), "Workout Deleted !", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(view.getContext(), "Workout Delete Failed !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }



    public class workoutViewholder extends RecyclerView.ViewHolder {


        TextView workoutName, workoutRepetitionVar, workoutTimeVar;
        Button edit,delete;


        public workoutViewholder(@NonNull View itemView)
        {
            super(itemView);

            workoutName = itemView.findViewById(R.id.add_workout_name);
            workoutRepetitionVar = itemView.findViewById(R.id.select_repetition);
            workoutTimeVar = itemView.findViewById(R.id.select_time);
            edit=itemView.findViewById(R.id.btn_workout_edit);
            delete=itemView.findViewById(R.id.btn_workout_delete);
            context=itemView.getContext();
            auth = FirebaseAuth.getInstance();
            workout= FirebaseDatabase.getInstance().getReference().child("workout").child(auth.getCurrentUser().getUid());
        }
    }

    public workoutViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)

    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_session, parent, false);
        return new WorkoutAdapter.workoutViewholder(view);
    }





}
