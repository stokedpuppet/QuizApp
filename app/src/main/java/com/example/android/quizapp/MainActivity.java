package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quizScore = 0;
    int question1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitQuiz(View view) {
        CheckBox q1PresidentCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_president);
        boolean isPresident = q1PresidentCheckbox.isChecked();
        CheckBox q1CourtCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_court);
        boolean isCourt = q1CourtCheckbox.isChecked();
        CheckBox q1SenateCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_senate);
        boolean isSenate = q1SenateCheckbox.isChecked();
        CheckBox q1HouseCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_house);
        boolean isHouse = q1HouseCheckbox.isChecked();
        CheckBox q1CabinetCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_cabinet);
        boolean isCabinet = q1CabinetCheckbox.isChecked();
        question1 = q1Score(isPresident, isCourt, isSenate, isHouse, isCabinet);
        if (question1 == 1) {
            Toast toast = Toast.makeText(getApplicationContext(), "yesssss!!!!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Checks answer for question 1.
     * If only Senate and House checkboxes are checked then answer is correct.
     *
     * @param president incorrect checkbox
     * @param court     incorrect checkbox
     * @param senate    correct checkbox
     * @param house     correct checkbox
     * @param cabinet   incorrect checkbox
     * @return question1 has a value of 1 if answered correctly.
     */
    private int q1Score(boolean president, boolean court, boolean senate, boolean house, boolean cabinet) {
        int checkboxes = 0;
        if (president)
            checkboxes += 1;
        if (court)
            checkboxes += 1;
        if (senate)
            checkboxes += 1;
        if (house)
            checkboxes += 1;
        if (cabinet)
            checkboxes += 1;
        if (checkboxes == 2 && senate && house) {
            question1 = 1;
        }
        return question1;


    }
}

