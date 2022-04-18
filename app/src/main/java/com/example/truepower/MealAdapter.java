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

public class MealAdapter extends FirebaseRecyclerAdapter<Meal, MealAdapter.mealViewHolder> {
    private Context context;
    private DatabaseReference meal;
    private FirebaseAuth auth;
    String id;

    public MealAdapter(
            @NonNull FirebaseRecyclerOptions<Meal> options,Context context)
    {
        super(options);
        this.context = context;
    }

    public MealAdapter(@NonNull FirebaseRecyclerOptions<Meal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mealViewHolder holder, int position, @NonNull Meal model) {
        holder.mealName.setText(model.getMealName());
        holder.mealCategory.setText(model.getMealCategory());
        holder.mealType.setText(model.getMealType());
        holder.calories.setText(model.getCalories());
        id=model.getId();

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,
                        EditMealActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", model.getId());
                bundle.putString("mealName", model.getMealName());
                bundle.putString("mealCategory", model.getMealCategory());
                bundle.putString("mealType", model.getMealType());
                bundle.putString("calories", model.getCalories());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setCancelable(false);
                alertDialog.setTitle("Delete Meal");
                alertDialog.setMessage("Do you want to delete this meal ?");
                alertDialog.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mealDelete(view);
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

            private void mealDelete(View view) {
                meal.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(view.getContext(), "Meal Deleted !", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "Meal Delete Failed !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    class mealViewHolder extends RecyclerView.ViewHolder{
        TextView mealName,mealCategory,mealType,calories;
        Button edit,delete;

        public mealViewHolder(@NonNull View itemView)
        {
            super(itemView);

            mealName = itemView.findViewById(R.id.tv_meal_item_name_ans_txt);
            mealCategory = itemView.findViewById(R.id.tv_meal_item_category_ans_txt);
            mealType = itemView.findViewById(R.id.tv_meal_item_type_ans_txt);
            calories = itemView.findViewById(R.id.tv_meal_item_calories_ans_txt);
            edit=itemView.findViewById(R.id.btn_meal_item_edit);
            delete=itemView.findViewById(R.id.btn_meal_item_delete);
            context=itemView.getContext();
            auth = FirebaseAuth.getInstance();
            meal= FirebaseDatabase.getInstance().getReference().child("meal").child(auth.getCurrentUser().getUid());


        }


    }
    public mealViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meal_item, parent, false);
        return new MealAdapter.mealViewHolder(view);
    }

}
