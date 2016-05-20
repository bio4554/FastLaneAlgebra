package com.school.bio4554.fastlanealgebra;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Vector;


/**
 * Created by Austin Childress on 5/16/2016. Made for Algebra I
 */
public class MainGame extends AppCompatActivity {

    Random rand = new Random();
    private final IntentFilter intentFilter = new IntentFilter();
    WifiP2pManager.Channel mChannel;
    WifiP2pManager mManager;
    float scale;
    int increasepad;

    int redcorrect = 0;
    int bluecorrect = 0;
    int greencorrect = 0;

    public int getRand(int min, int max) {
        return rand.nextInt(max) + min;
    }

    Vector<String> questions = new Vector<>();
    Vector<String> answers = new Vector<>();
    int currentquestion; //Index of current question in vector
    int currentanswer; //Radio button with answer
    int prechoice = 0;

    TextView questiontext;
    RadioGroup radiogroup;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    RadioButton answer4;
    ImageView redCar;
    ImageView blueCar;
    ImageView greenCar;
    ProgressBar progress;

    private void addQuestion(String ques, String ans) {
        questions.addElement(ques);
        answers.addElement(ans);
    }

    private void initQuestions() {
        //Long division
        addQuestion("x^3 - 4x^2 + 2x + 5 / x-2", "x^2 - 2x - 2 + 1/x-2");
        addQuestion("(m^2 - 7m - 11) / (m-8)", "m + 1 - 3 / m - 8");
        addQuestion("(n^2 - n - 29) / (n - 6)", "n + 5 + 1 / n - 6");
        addQuestion("(a^2 - 28) / (a - 5)", "a + 5 - 3 / a - 5");
        addQuestion("(42x^2 - 33) / (7x + 7)", "6x - 6 + 9 / 7x + 7");

        //Quadratic
        addQuestion("Solve x^2 + 3x - 9 = 0 using the quadratic formula", "x = -9, x = 1");
        addQuestion("Solve m^2 - 5m - 14 = 0 using the quadratic formula", "{7,-2}");
        addQuestion("Solve b^2 - 4b + 4 = 0 using the quadratic formula", "{2}");
        addQuestion("Solve 2m^2 + 2m - 12 =0 using the quadratic formula", "{2,-3}");
        addQuestion("Solve 4b^2 + 8b + 7 = 4 using the quadratic formula", "{ -1/2 , -3/2 }");

        //Compound interest
        addQuestion("If you start a bank account with a deposit of $10,000 and the bank compounds the interest quarterly at a rate of 8%. how much money will you have after 5 years?", "$14,859.47");
        addQuestion("Find the balance on a deposit of $950, earning 4% interest compounded semiannually for 2 years.", "$1,028.31");
        addQuestion("If you deposit $400 in an account that earns 5.4% compounded annually, then what would be the balance in that account after 6 years?", "$548.41");
        addQuestion("An amount of $2,500.00 is deposited in a bank paying an annual interest rate of 4.3% compounded quarterly. What is the balance after 5 years?", "$3,096.10");
        addQuestion("An amount of $5500 is invested at 12% compounded weekly. What is the balance after 2 years? (52 weeks in a year)", "$6,989.94");

        //Transformations
        addQuestion("What transformations graph the function of x^2 to the function -2(x+9)^2 - 57", "Reflection over X-axis, shift left 9, shift upwards 5");

        //Solving system of equations
        addQuestion("Solve the system using substitution: 3y - 2x = 11, y + 2x = 9", "x = 5, {5,5}");
        addQuestion("Solve by elimination: -4x - 2y = 12, 4x + 8y = -24", "(6,-6)");
        addQuestion("Solve by elimination: 4x + 8y = 20, -4x + 2y = -30", "(7,-1)");
        addQuestion("Solve by substitution: y = 6x - 11, -2x - 3y = -7", "(2,1)");
        addQuestion("Solve by substitution: y = 5x - 7, -3x - 2y = 12", "(2,3)");

        //Arithmetic sequence
        addQuestion("A jogger is training. The first week he runs 2 miles each session, increasing the distance he runs by 1.5 miles. Write a recursive definition for the sequence", "A(n-1)+1.5");

        //Exponential growth/decay
        addQuestion("Use the domain {1,2,3} to evaluate the function. y=40(1/2)^x", "20, 10, 5");

        //Exponents
        addQuestion("Simplify 3 * 4^3", "192");
        addQuestion("Simplify 4x^3 * 2x^3", "8x^6");
        addQuestion("Simplify x^5 * x^3", "x^8");
        addQuestion("Simplify 2x^3 * 2x^2", "4x^5");
        addQuestion("Simplify ( 6^5 ) / ( 6^3 )", "36");

        //Solve by completing
        addQuestion("x^2 - 8x + 5 = 0", "x = 4 +- √(11)");
        addQuestion("x^2 + 12x + 4 = 0", "x = -6 +- 4√(2)");
        addQuestion("3x^2 - 12x - 7 = 0", "x = 2 +- √(57) / 3");
        addQuestion("-2x^2 - 12x - 9 = 0", "x = -3 +- 3√(2) / 2");
        addQuestion("4x^2 + 8x - 9 = 0", "x = -1 +- √(13) / 2");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initQuestions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        questiontext = (TextView) findViewById(R.id.questionText);
        answer1 = (RadioButton) findViewById(R.id.answerChoice1);
        answer2 = (RadioButton) findViewById(R.id.answerChoice2);
        answer3 = (RadioButton) findViewById(R.id.answerChoice3);
        answer4 = (RadioButton) findViewById(R.id.answerChoice4);
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);
        updateQuestion();
        redCar = (ImageView) findViewById(R.id.redCar);
        blueCar = (ImageView) findViewById(R.id.blueCar);
        greenCar = (ImageView) findViewById(R.id.greenCar);
        scale = getResources().getDisplayMetrics().density;
        progress = (ProgressBar)findViewById(R.id.progressBar3);
        progress.setProgress(0);
        increasepad = 75;
    }


    public void updateQuestion() {
        currentanswer = getRand(1, 4);
        do {
            currentquestion = getRand(0, questions.size());
        } while (currentquestion == prechoice);
        prechoice = currentquestion;
        for (int i = 1; i < 5; i++) {
            switch (i) {
                case 1:
                    answer1.setText(answers.elementAt(getRand(0, questions.size())));
                    break;
                case 2:
                    do {
                        answer2.setText(answers.elementAt(getRand(0, questions.size())));
                    } while (answer2.getText().equals(answer1.getText()));
                    break;
                case 3:
                    do {
                        answer3.setText(answers.elementAt(getRand(0, questions.size())));
                    }
                    while ((answer3.getText().equals(answer1.getText())) || (answer3.getText().equals(answer2.getText())));
                    break;
                case 4:
                    do {
                        answer4.setText(answers.elementAt(getRand(0, questions.size())));
                    }
                    while ((answer4.getText().equals(answer1.getText())) || (answer4.getText().equals(answer2.getText())) || (answer4.getText().equals(answer3.getText())));
                    break;
            }
        }
        switch (currentanswer) {
            case 1:
                answer1.setText(answers.elementAt(currentquestion));
                break;
            case 2:
                answer2.setText(answers.elementAt(currentquestion));
                break;
            case 3:
                answer3.setText(answers.elementAt(currentquestion));
                break;
            case 4:
                answer4.setText(answers.elementAt(currentquestion));
                break;
        }

        for (int i = 1; i < 5; i++) {
            switch (i) {
                case 1:
                    if (i != currentanswer) {
                        if (answer1.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer1.setText(answers.elementAt(getRand(0, questions.size())));
                            }
                            while ((answer1.getText().equals(answer2.getText())) || (answer1.getText().equals(answer3.getText())) || (answer1.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 2:
                    if (i != currentanswer) {
                        if (answer2.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer2.setText(answers.elementAt(getRand(0, questions.size())));
                            }
                            while ((answer2.getText().equals(answer1.getText())) || (answer2.getText().equals(answer3.getText())) || (answer2.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 3:
                    if (i != currentanswer) {
                        if (answer3.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer3.setText(answers.elementAt(getRand(0, questions.size())));
                            }
                            while ((answer3.getText().equals(answer2.getText())) || (answer3.getText().equals(answer1.getText())) || (answer3.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 4:
                    if (i != currentanswer) {
                        if (answer4.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer4.setText(answers.elementAt(getRand(0, questions.size())));
                            }
                            while ((answer4.getText().equals(answer2.getText())) || (answer4.getText().equals(answer3.getText())) || (answer4.getText().equals(answer1.getText())));
                        }
                    }
                    break;
            }
        }
        questiontext.setText(questions.elementAt(currentquestion));
        radiogroup.clearCheck();
    }

    public void checkQuestion(View view) {
        if (radiogroup.getCheckedRadioButtonId() != -1) {
            int id = radiogroup.getCheckedRadioButtonId();
            View radioButton = radiogroup.findViewById(id);
            int radioId = radiogroup.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) radiogroup.getChildAt(radioId);
            String selection = (String) btn.getText();
            System.out.println("Found selected button!");
            if (selection.equals(answers.elementAt(currentquestion))) {
                System.out.println("Correct!");
                disToast("Correct!");
                redcorrect++;
                progress.setProgress(progress.getProgress()+7);
                redCar.setPadding(redCar.getPaddingLeft() + increasepad, 0, 0, 0);
                updateQuestion();
                updatePlayerc();
                checkWin();
            } else {
                System.out.println("Wrong!");
                disToast("Wrong!");
                updatePlayerc();
                updateQuestion();
                checkWin();
            }
        } else {
            System.out.println("No button was selected");
            disToast("No button selected");
        }
    }

    public void updatePlayerc() {
        int correct;
        correct = getRand(3, 10);

        if (correct > 5) {
            bluecorrect++;
            blueCar.setPadding(blueCar.getPaddingLeft() + increasepad, 0, 0, 0);
        }

        correct = getRand(3, 10);

        if (correct > 5) {
            greencorrect++;
            greenCar.setPadding(greenCar.getPaddingLeft() + increasepad, 0, 0, 0);
        }
    }

    public void disToast(String name) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, name, duration);
        toast.show();
    }

    public void checkWin() {
        System.out.println(dpToPx(300, this));
        System.out.println("Red car: " + dpToPx(redCar.getPaddingLeft(), this));
        System.out.println("Blue car: " + dpToPx(blueCar.getPaddingLeft(), this));
        System.out.println("Green car: " + dpToPx(greenCar.getPaddingLeft(), this));
        if(redcorrect >= 15) {
            handleWin(1);
        } else if(bluecorrect >= 15) {
            handleWin(2);
        } else if(greencorrect >= 15) {
            handleWin(3);
        }
    }

    public void handleWin(int car) {
        Intent mainintent = new Intent(this, RedWin.class);
        mainintent.putExtra("car", car);
        startActivity(mainintent);
    }

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
