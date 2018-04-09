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
    // Declare variables for views.
    CheckBox q1PresidentCheckbox;
    CheckBox q1CourtCheckbox;
    CheckBox q1SenateCheckbox;
    CheckBox q1HouseCheckbox;
    CheckBox q1CabinetCheckbox;
    RadioButton q2Answer;
    RadioButton q3Answer;
    EditText q4Answer;
    RadioButton q5Answer;
    NumberPicker npQ6Answer;
    RadioButton q7Answer;
    RadioButton q8Answer;
    RadioGroup q2RadioGroup;
    RadioGroup q3RadioGroup;
    RadioGroup q5RadioGroup;
    RadioGroup q7RadioGroup;
    RadioGroup q8RadioGroup;
    Button resetButton;
    Button answersButton;
    ScrollView mainScrollView;
    View customToastLayout;
    ImageView toastImage;
    TextView toastText;
    // Holds the number from number picker.
    int q6Correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize Views
        initializeViews();
    }

    private void initializeViews() {
        // Initialize CheckBoxes
        q1PresidentCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_president);
        q1CourtCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_court);
        q1SenateCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_senate);
        q1HouseCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_house);
        q1CabinetCheckbox = (CheckBox) findViewById(R.id.checkbox_q1_cabinet);
        // Initialize correct Radio Buttons
        q2Answer = (RadioButton) findViewById(R.id.radio_q2_100);
        q3Answer = (RadioButton) findViewById(R.id.radio_q3_435);
        q5Answer = (RadioButton) findViewById(R.id.radio_q5_long);
        q7Answer = (RadioButton) findViewById(R.id.radio_q7_538);
        q8Answer = (RadioButton) findViewById(R.id.radio_q8_27);
        // Initialize RadioGroups
        q2RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q2);
        q3RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q3);
        q5RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q5);
        q7RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q7);
        q8RadioGroup = (RadioGroup) findViewById(R.id.radio_group_q8);
        // Initialize EditText
        q4Answer = (EditText) findViewById(R.id.q4_text_field);
        //Initialize Number Picker
        npQ6Answer = (NumberPicker) findViewById(R.id.q6_number_picker);
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
        //Initialize Buttons
        answersButton = (Button) findViewById(R.id.answers_button);
        resetButton = (Button) findViewById(R.id.reset_button);
        // Initialize ScrollView
        mainScrollView = (ScrollView) findViewById(R.id.scroll_view);
        // Initialize toast views.
        LayoutInflater inflater = getLayoutInflater();
        customToastLayout = customToastLayout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));
        toastImage = (ImageView) customToastLayout.findViewById(R.id.toast_image);
        toastText = (TextView) customToastLayout.findViewById(R.id.toast_text);
    }

    public void submitQuiz(View view) {
        // Checks if Question 01 is correct and updates quiz score if correct.
        boolean isPresident = q1PresidentCheckbox.isChecked();
        boolean isCourt = q1CourtCheckbox.isChecked();
        boolean isSenate = q1SenateCheckbox.isChecked();
        boolean isHouse = q1HouseCheckbox.isChecked();
        boolean isCabinet = q1CabinetCheckbox.isChecked();
        if (isSenate && isHouse && !isPresident && !isCourt && !isCabinet) {
            quizScore += 1;
        }
        // Checks if Questions 02 - 08 are correct and updates the quiz score if correct.
        if (q2Answer.isChecked()) {
            quizScore += 1;
        }
        if (q3Answer.isChecked()) {
            quizScore += 1;
        }
        if (q4Answer.getText().toString().toLowerCase().equals(getString(R.string.speaker_of_the_house))) {
            quizScore += 1;
        } else if (q4Answer.getText().toString().toLowerCase().equals(getString(R.string.the_speaker_of_the_house))) {
            quizScore += 1;
        }
        if (q5Answer.isChecked()) {
            quizScore += 1;
        }
        if (q6Correct == 60) {
            quizScore += 1;
        }
        if (q7Answer.isChecked()) {
            quizScore += 1;
        }
        if (q8Answer.isChecked()) {
            quizScore += 1;
        }
        // Posts results for quiz with custom toast message.
        String toastString = getString(R.string.you_received_a_) + " " + quizScore * 12.5;
        toastString += "\n\n" + getString(R.string.you_answered) + " " + quizScore + " " + getString(R.string.correctly_out_of_8);
        toastImage.setImageResource(R.drawable.congress_drawing);
        toastText.setText(toastString);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(customToastLayout);
        toast.show();
        // Reset quizScore
        quizScore = 0;
        // Removes focus from edit text to prevent scrolling.
        q4Answer.clearFocus();
        // Makes ANSWERS button and RESET button visible.
        answersButton.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.VISIBLE);
    }

    public void answers(View view) {
        // Adds green background for correct answers.
        q1SenateCheckbox.setBackgroundColor(getResources().getColor(R.color.green));
        q1HouseCheckbox.setBackgroundColor(getResources().getColor(R.color.green));
        q2Answer.setBackgroundColor(getResources().getColor(R.color.green));
        q3Answer.setBackgroundColor(getResources().getColor(R.color.green));
        q4Answer.setText(getString(R.string.speaker_of_the_house));
        q4Answer.setBackgroundColor(getResources().getColor(R.color.green));
        q5Answer.setBackgroundColor(getResources().getColor(R.color.green));
        npQ6Answer.setValue(60);
        npQ6Answer.setBackgroundColor(getResources().getColor(R.color.green));
        q7Answer.setBackgroundColor(getResources().getColor(R.color.green));
        q8Answer.setBackgroundColor(getResources().getColor(R.color.green));
    }

    public void reset(View view) {
        // Scroll to top.
        mainScrollView.fullScroll(ScrollView.FOCUS_UP);
        // Removes background for all correct answers.
        q1SenateCheckbox.setBackgroundColor(0);
        q1HouseCheckbox.setBackgroundColor(0);
        q2Answer.setBackgroundColor(0);
        q3Answer.setBackgroundColor(0);
        q4Answer.setBackgroundColor(0);
        q5Answer.setBackgroundColor(0);
        npQ6Answer.setBackgroundColor(0);
        q7Answer.setBackgroundColor(0);
        q8Answer.setBackgroundColor(0);
        // Deselect checkboxes.
        q1PresidentCheckbox.setChecked(false);
        q1CourtCheckbox.setChecked(false);
        q1CabinetCheckbox.setChecked(false);
        q1SenateCheckbox.setChecked(false);
        q1HouseCheckbox.setChecked(false);
        // Deselect radio buttons.
        q2RadioGroup.clearCheck();
        q3RadioGroup.clearCheck();
        q5RadioGroup.clearCheck();
        q7RadioGroup.clearCheck();
        q8RadioGroup.clearCheck();
        // Clear Q4 EditText
        q4Answer.setText("");
        // Reset Q6 NumberPicker
        npQ6Answer.setValue(49);
        q6Correct = 0;
        // Clears tally of all correct answers.
        quizScore = 0;
        // Resets ANSWERS button and RESET button to invisible.
        answersButton.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
    }

}

