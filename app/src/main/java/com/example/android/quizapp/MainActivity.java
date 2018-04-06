package com.example.android.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Keeps a tally of all correctly answered questions.
    int quizScore = 0;
    // If correct value will be set to 1.
    int question1 = 0;
    int question2 = 0;
    int question3 = 0;
    int question4 = 0;
    int question5 = 0;
    int question6 = 0;
    int question7 = 0;
    int question8 = 0;
    // Holds the number from number picker.
    int q6Correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize Number Picker
        NumberPicker npQ6Answer = (NumberPicker) findViewById(R.id.q6_number_picker);
        npQ6Answer.setMinValue(49);
        npQ6Answer.setMaxValue(69);
        npQ6Answer.setWrapSelectorWheel(true);
        npQ6Answer.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Save the number selected from the Number Picker
                q6Correct = newVal;
            }
        });
    }

    public void submitQuiz(View view) {
        // Checks status of Question 01 checkboxes.
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
        // Checks if Question 01 answer is correct and updates the Q1 variable if correct.
        question1 = q1Score(isPresident, isCourt, isSenate, isHouse, isCabinet);
        // Checks if Question 02 is correct and updates Q2 variable if correct.
        RadioButton q2Answer = (RadioButton) findViewById(R.id.radio_q2_100);
        boolean q2Correct = q2Answer.isChecked();
        if (q2Correct)
            question2 = 1;
        // Checks if Question 03 is correct and updates Q3 variable if correct.
        RadioButton q3Answer = (RadioButton) findViewById(R.id.radio_q3_435);
        boolean q3Correct = q3Answer.isChecked();
        if (q3Correct)
            question3 = 1;
        // Checks if Question 04 is correct and updates Q4 variable if correct.
        EditText q4Answer = (EditText) findViewById(R.id.q4_text_field);
        String q4Correct = q4Answer.getText().toString().toLowerCase();
        if (q4Correct.equals("speaker of the house"))
            question4 = 1;
        else if (q4Correct.equals("the speaker of the house"))
            question4 = 1;
        // Checks if Question 05 is correct and updates Q5 variable if correct.
        RadioButton q5Answer = (RadioButton) findViewById(R.id.radio_q5_long);
        boolean q5Correct = q5Answer.isChecked();
        if (q5Correct)
            question5 = 1;
        // Checks if Question 06 is correct and updates Q6 variable if correct.
        if (q6Correct == 60)
            question6 = 1;
        // Checks if Question 07 is correct and updates Q7 variable if correct.
        RadioButton q7Answer = (RadioButton) findViewById(R.id.radio_q7_538);
        boolean q7Correct = q7Answer.isChecked();
        if (q7Correct)
            question7 = 1;
        // Checks if Question 08 is correct and updates Q8 variable if correct.
        RadioButton q8Answer = (RadioButton) findViewById(R.id.radio_q8_27);
        boolean q8Correct = q8Answer.isChecked();
        if (q8Correct)
            question8 = 1;
        // Checks overall quiz score.
        quizScore = question1 + question2 + question3 + question4 + question5 + question6 + question7 + question8;

        // Posts results for quiz with custom toast message.
        String toastString = "You received a " + quizScore * 12.5;
        toastString += "\n\nYou answered " + quizScore + " correctly out of 8.";
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.congress_drawing);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(toastString);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();


        // Removes focus from edit text to prevent scrolling.
        q4Answer.clearFocus();
        // Makes ANSWERS button and RESET button visible.
        Button answersButton = (Button) findViewById(R.id.answers_button);
        answersButton.setVisibility(View.VISIBLE); //To set visible
        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setVisibility(View.VISIBLE); //To set visible
    }

    public void answers(View view) {
        // Adds green background for correct answers.
        CheckBox q1SenateCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_senate);
        q1SenateCheckbox.setBackgroundColor(Color.parseColor("#8BC34A"));
        CheckBox q1HouseCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_house);
        q1HouseCheckbox.setBackgroundColor(Color.parseColor("#8BC34A"));
        RadioButton q2Answer = (RadioButton) findViewById(R.id.radio_q2_100);
        q2Answer.setBackgroundColor(Color.parseColor("#8BC34A"));
        RadioButton q3Answer = (RadioButton) findViewById(R.id.radio_q3_435);
        q3Answer.setBackgroundColor(Color.parseColor("#8BC34A"));
        EditText q4Answer = (EditText) findViewById(R.id.q4_text_field);
        q4Answer.setText("speaker of the house");
        q4Answer.setBackgroundColor(Color.parseColor("#8BC34A"));
        RadioButton q5Answer = (RadioButton) findViewById(R.id.radio_q5_long);
        q5Answer.setBackgroundColor(Color.parseColor("#8BC34A"));
        NumberPicker npQ6Answer = (NumberPicker) findViewById(R.id.q6_number_picker);
        npQ6Answer.setValue(60);
        npQ6Answer.setBackgroundColor(Color.parseColor("#8BC34A"));
        RadioButton q7Answer = (RadioButton) findViewById(R.id.radio_q7_538);
        q7Answer.setBackgroundColor(Color.parseColor("#8BC34A"));
        RadioButton q8Answer = (RadioButton) findViewById(R.id.radio_q8_27);
        q8Answer.setBackgroundColor(Color.parseColor("#8BC34A"));
    }

    public void reset(View view) {
        // Scroll to top.
        ScrollView mainScrollView = (ScrollView)findViewById(R.id.scroll_view);
        mainScrollView.fullScroll(ScrollView.FOCUS_UP);
        // Removes background for all correct answers.
        CheckBox q1SenateCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_senate);
        q1SenateCheckbox.setBackgroundColor(0);
        CheckBox q1HouseCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_house);
        q1HouseCheckbox.setBackgroundColor(0);
        RadioButton q2Answer = (RadioButton) findViewById(R.id.radio_q2_100);
        q2Answer.setBackgroundColor(0);
        RadioButton q3Answer = (RadioButton) findViewById(R.id.radio_q3_435);
        q3Answer.setBackgroundColor(0);
        EditText q4Answer = (EditText) findViewById(R.id.q4_text_field);
        q4Answer.setBackgroundColor(0);
        RadioButton q5Answer = (RadioButton) findViewById(R.id.radio_q5_long);
        q5Answer.setBackgroundColor(0);
        NumberPicker npQ6Answer = (NumberPicker) findViewById(R.id.q6_number_picker);
        npQ6Answer.setBackgroundColor(0);
        RadioButton q7Answer = (RadioButton) findViewById(R.id.radio_q7_538);
        q7Answer.setBackgroundColor(0);
        RadioButton q8Answer = (RadioButton) findViewById(R.id.radio_q8_27);
        q8Answer.setBackgroundColor(0);
        // Deselect checkboxes.
        CheckBox q1PresidentCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_president);
        q1PresidentCheckbox.setChecked(false);
        CheckBox q1CourtCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_court);
        q1CourtCheckbox.setChecked(false);
        CheckBox q1CabinetCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_cabinet);
        q1CabinetCheckbox.setChecked(false);
        q1SenateCheckbox.setChecked(false);
        q1HouseCheckbox.setChecked(false);
        // Deselect radio buttons.
        RadioGroup q2RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q2);
        q2RadioGroup.clearCheck();
        RadioGroup q3RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q3);
        q3RadioGroup.clearCheck();
        RadioGroup q5RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q5);
        q5RadioGroup.clearCheck();
        RadioGroup q7RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q7);
        q7RadioGroup.clearCheck();
        RadioGroup q8RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q8);
        q8RadioGroup.clearCheck();
        // Clear Q4 EditText
        q4Answer.setText("");
        // Reset Q6 NumberPicker
        npQ6Answer.setValue(49);
        // Resets the correctness of each question.
        question1 = 0;
        question2 = 0;
        question3 = 0;
        question4 = 0;
        question5 = 0;
        question6 = 0;
        question7 = 0;
        question8 = 0;
        // Clears tally of all correct answers.
        quizScore = 0;

    }


    /**
     * Checks answer for question 1.
     * If only Senate and House checkboxes are checked then answer is correct.
     *
     * @param president checkbox
     * @param court     checkbox
     * @param senate    checkbox
     * @param house     checkbox
     * @param cabinet   checkbox
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

