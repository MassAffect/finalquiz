package com.example.android.finalquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ViewGroup content_group_one;
    ViewGroup content_group_two;
    ViewGroup content_group_three;
    ViewGroup content_group_four;
    ViewGroup content_group_five;
    ViewGroup content_group_six;
    String message;
    String name;
    String subwayCity;
    String skyscraperCity;
    int nextCount = 1;
    int correct = 0;
    int wrong = 0;
    RadioGroup radioGroup;
    RadioButton radioButton;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);

        content_group_one = (ViewGroup) findViewById(R.id.content_group_one);
        content_group_two = (ViewGroup) findViewById(R.id.content_group_two);
        content_group_three = (ViewGroup) findViewById(R.id.content_group_three);
        content_group_four = (ViewGroup) findViewById(R.id.content_group_four);
        content_group_five = (ViewGroup) findViewById(R.id.content_group_five);
        content_group_six = (ViewGroup) findViewById(R.id.content_group_six);

    }

    /**
     * This method is called when the start button is clicked.
     * content groups will be hidden and EditText string will be stored.
     */
    public void startButton(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.plainTextInput);
        Editable nameEditable = nameField.getText();
        name = nameEditable.toString();
        findViewById(R.id.content_group_one).setVisibility(ViewGroup.GONE);
        findViewById(R.id.content_group_three).setVisibility(ViewGroup.GONE);
        findViewById(R.id.content_group_four).setVisibility(ViewGroup.GONE);
        findViewById(R.id.content_group_five).setVisibility(ViewGroup.GONE);
        findViewById(R.id.content_group_six).setVisibility(ViewGroup.GONE);
        findViewById(R.id.displayMessage).setVisibility(TextView.GONE);
    }

    /**
     * This method increments a counter to validate the users progress in the quiz
     * based on incrementation the method determines how the UI is presented to the user
     */

    public void nextButton(View view) {
        if (nextCount == 1) {
            nextCount = nextCount + 1;
            radioGroup = findViewById(R.id.radioGroup);

            findViewById(R.id.content_group_two).setVisibility(ViewGroup.GONE);
            findViewById(R.id.content_group_three).setVisibility(ViewGroup.VISIBLE);
        } else if (nextCount == 2) {
            nextCount = nextCount + 1;
            EditText nameField = (EditText) findViewById(R.id.subwayCityInput);
            Editable nameEditable = nameField.getText();
            subwayCity = nameEditable.toString();
            findViewById(R.id.content_group_three).setVisibility(ViewGroup.GONE);
            findViewById(R.id.content_group_four).setVisibility(ViewGroup.VISIBLE);
        } else if (nextCount == 3) {
            nextCount = nextCount + 1;
            EditText nameField = (EditText) findViewById(R.id.skyscraperCityInput);
            Editable nameEditable = nameField.getText();
            skyscraperCity = nameEditable.toString();
            findViewById(R.id.content_group_four).setVisibility(ViewGroup.GONE);
            findViewById(R.id.content_group_five).setVisibility(ViewGroup.VISIBLE);
        } else if (nextCount == 4) {
            checkBox = findViewById(R.id.check_one);
            checkBox = (CheckBox) findViewById(R.id.check_one);
            findViewById(R.id.content_group_five).setVisibility(ViewGroup.GONE);
            findViewById(R.id.content_group_six).setVisibility(ViewGroup.VISIBLE);
        }
    }

    private void displayMessage(String message) {
        TextView quizSummary = (TextView) findViewById(R.id.displayMessage);
        quizSummary.setText(message);
    }

    public void checkButton(View v) {
        // Display the order summary on the screen
        String message = (String) createOrderSummary(radioGroup, subwayCity, skyscraperCity, checkBox);
        findViewById(R.id.displayMessage).setVisibility(TextView.VISIBLE);
        displayMessage(message);
        }

    private String createOrderSummary(RadioGroup radioGroup, String subwayCity, String skyscraperCity, CheckBox checkBox){
        String message = "Hello, " + name;
        message += "\nThe answer to question 1 was " + getText(R.string.radio_three) + ". \nYou selected " + getString(R.id.radioGroup, true) + " for your answer.";
        message += "\nThe answer to question 2 was Boston. \nYou answered " + getText(R.string.subwayCityInput);
        message += "\nThe answer to question 3 was Boston. \nYou answered" + getText(R.string.skyscraperCityInput);
        message += "\nThe answer to question 4 was Boston. \nYou answered" + checkBox;
        message += "\nI hope you enjoyed this quiz as \nmuch as I enjoyed creating it.";
        return message;
}


    public void checkbox(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.check_one:
                if (checked)
                    correct++;
                else
                    wrong++;
                break;
            case R.id.check_two:
                if (checked)
                    wrong++;
                break;
        }
    }

}






