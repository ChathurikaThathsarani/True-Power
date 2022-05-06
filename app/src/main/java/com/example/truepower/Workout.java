package com.example.truepower;

public class Workout {
    String id,workoutName,workoutRepetitionVar,workoutTimeVar;
    public Workout(){}
    public Workout(String id, String workoutName, String workoutRepetitionVar, String workoutTimeVar)
    {
        this.id = id;
        this.workoutName = workoutName;
        this.workoutRepetitionVar = workoutRepetitionVar;
        this.workoutTimeVar = workoutTimeVar;

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }
    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutRepetitionVar() {
        return workoutRepetitionVar;
    }
    public void setWorkoutRepetitionVar(String workoutRepetitionVar) {
        this.workoutRepetitionVar = workoutRepetitionVar;
    }

    public String getWorkoutTimeVar() {
        return workoutTimeVar;
    }
    public void setWorkoutTimeVar(String workoutTimeVar) {
        this.workoutTimeVar = workoutTimeVar;
    }
}

