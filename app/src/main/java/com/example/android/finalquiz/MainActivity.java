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
import android.widget.Toast;


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
    String selectedText;
    String msgToDisplay;
    int nextCount = 1;
    int correct;
    int wrong;
    RadioGroup radioGroup;
    RadioButton radioButton;
    CheckBox checkBox;
    CheckBox chkBoxOne;
    CheckBox chkBoxTwo;
    CheckBox chkBoxThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        content_group_one = (ViewGroup) findViewById(R.id.content_group_one);
        content_group_two = (ViewGroup) findViewById(R.id.content_group_two);
        content_group_three = (ViewGroup) findViewById(R.id.content_group_three);
        content_group_four = (ViewGroup) findViewById(R.id.content_group_four);
        content_group_five = (ViewGroup) findViewById(R.id.content_group_five);
        content_group_six = (ViewGroup) findViewById(R.id.content_group_six);
        initialUISetup();

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

    public void initialUISetup() {
        chkBoxOne = (CheckBox) findViewById(R.id.check_one);
        chkBoxTwo = (CheckBox) findViewById(R.id.check_two);
        chkBoxThree = (CheckBox) findViewById(R.id.check_three);
    }

    /**
     * This method increments a counter to validate the users progress in the quiz
     * based on incrementation the method determines how the UI is presented to the user
     */

    public void nextButton(View view) {
        if (nextCount == 1) {
            nextCount = nextCount + 1;
            int checkId = radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(checkId);
            int radioIndex = radioGroup.indexOfChild(radioButton);
            RadioButton r = (RadioButton) radioGroup.getChildAt(radioIndex);
            selectedText = r.getText().toString();
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
            //code to check if this checkbox is checked!
            String strMessage = "";
            if (chkBoxOne.isChecked()) {
                strMessage += "Oregon";
            }

            if (chkBoxTwo.isChecked()) {
                strMessage += "Wyoming";
            }

            if (chkBoxThree.isChecked()) {
                strMessage += "Arizona";
            }

            showTextNotification(strMessage);
            findViewById(R.id.content_group_five).setVisibility(ViewGroup.GONE);
            findViewById(R.id.content_group_six).setVisibility(ViewGroup.VISIBLE);
        }

    }

    public void showTextNotification(String msgToDisplay) {
        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }

    private void displayMessage(String message) {
        TextView quizSummary = (TextView) findViewById(R.id.displayMessage);
        quizSummary.setText(message);
    }

    public void checkButton(View v) {
        // Display the order summary on the screen
        String message = createOrderSummary(selectedText, subwayCity, skyscraperCity, msgToDisplay);
        findViewById(R.id.displayMessage).setVisibility(TextView.GONE);
        displayMessage(message);
    }

    private String createOrderSummary(String text, String subwayCity, String skyscraperCity,String msgToDisplay) {
        String message = "Hello, " + name;
        message += "\nThe answer to question 1 was " + getText(R.string.radio_three) + ". \nYou selected " + text + " for your answer.";
        message += "\nThe answer to question 2 was Boston. \nYou answered " + getText(R.string.subwayCityInput);
        message += "\nThe answer to question 3 was Chicago. \nYou answered " + getText(R.string.skyscraperCityInput);
        message += "\nThe answer to question 4 was Oregon and Wyoming. \nYou answered" + msgToDisplay;
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






