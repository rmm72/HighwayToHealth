package com.example.highwaytohealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class CalorieCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get manipulated TextViews and Switch
        heightTextView = findViewById(R.id.heightTextView);
        weightTextView = findViewById(R.id.weightTextView);
        genderSwitch = (Switch) findViewById(R.id.genderSwitch);

        //set TextWatcher for ageEditText
        ageEditText = findViewById(R.id.ageEditText);
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        //set Listener for heightSeekBar
        heightSeekBar = (SeekBar) findViewById(R.id.heightSeekBar);
        heightSeekBar.setOnSeekBarChangeListener(heightSeekBarListener);

        //set Listener for weightSeekBar
        weightSeekBar = findViewById(R.id.weightSeekBar);
        weightSeekBar.setOnSeekBarChangeListener(weightSeekBarListener);

        //get radio buttons
        zero = (RadioButton) findViewById(R.id.zeroButton);
        oneToTwo = (RadioButton) findViewById(R.id.oneToTwoButton);
        threeToFive = (RadioButton) findViewById(R.id.threeToFiveButton);
        sixToSeven = (RadioButton) findViewById(R.id.sixToSevenButton);

        //get Buttons
        calculateBmrButton = (Button) findViewById(R.id.calculateBmrButton);
        calculateBmrButton.setOnClickListener(calculateBmrButtonListener);

        caloricTextView=(TextView)findViewById(R.id.caloricTextView);
    }

    private EditText ageEditText;
    private SeekBar weightSeekBar;
    private SeekBar heightSeekBar;

    public static int height = 48;
    public static int weight = 200;
    public static int age = 0;

    private TextView heightTextView;//show User's height
    private TextView weightTextView;//show User's weight
    public  Switch genderSwitch;//gets User's gender
    private TextView caloricTextView;

    //Radio buttons
    public static RadioButton zero;
    public static RadioButton oneToTwo;
    public static RadioButton threeToFive;
    public static RadioButton sixToSeven;

    private Button calculateBmrButton;

    private View.OnClickListener calculateBmrButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calculate();
        }
    };

    private final SeekBar.OnSeekBarChangeListener heightSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            height = progress;//set the height to progress of the seekbar
            heightTextView.setText((height/12) + "\'" + (height%12) + "\"");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final SeekBar.OnSeekBarChangeListener weightSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            weight = progress;
            weightTextView.setText(weight + "lbs");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {//get age and display it
                age = Integer.parseInt(s.toString());
            } catch (NumberFormatException e){//if its not a number or empty
                age = 0;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void calculate() {

        double heightIn = height;
        double weightLb = weight;
        double bmr = 0.0;
        double dailyCaloricIntake = 0.0;

        if (!genderSwitch.isChecked()) {
            //equation for females
            bmr = 655 + (heightIn * 4.7) + (weightLb * 4.3) - (age * 4.7);
        } else {
            //equation for males
            bmr = 66 + (heightIn * 12.9) + (weightLb * 6.3) - (age * 6.8);
        }

        if (zero.isChecked()) {
            dailyCaloricIntake = bmr * 1.2;
        } else if (oneToTwo.isChecked()) {
            dailyCaloricIntake = bmr * 1.375;
        } else if (threeToFive.isChecked()) {
            dailyCaloricIntake = bmr * 1.55;
        } else if (sixToSeven.isChecked()) {
            dailyCaloricIntake = bmr * 1.725;
        }

        caloricTextView.setText((int)dailyCaloricIntake + " Calories");
    }
}
