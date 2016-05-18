package com.school.bio4554.fastlanealgebra;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

    private void addQuestion(String ques, String ans) {
        questions.setSize(questions.capacity()+1);
        answers.setSize(answers.capacity()+1);
        questions.addElement(ques);
        answers.addElement(ans);
    }

    private void initQuestions() {
        //Long division
        addQuestion("x^3 - 4x^2 + 2x + 5 / x-2", "x^2 - 2x - 2 + 1/x-2");

        //Quadratic
        addQuestion("Solve x^2 + 3x - 9 = 0 using the quadratic formula", "x = -9, x = 1");

        //Compound interest
        addQuestion("If you start a bank account with a deposit of $10,000 and the bank compounds the interest quarterly at a rate of 8%. how much money will you have after 5 years?", "$14,859.47");

        //Transformations
        addQuestion("What transformations graph the function of x^2 to the function -2(x+9)^2 - 57", "Reflection over X-axis, shift left 9, shift upwards 5");

        //Solving system of equations
        addQuestion("Solve the system using substitution: 3y - 2x = 11, y + 2x = 9", "x = 5, {5,5}");

        //Arithmetic sequence
        addQuestion("A jogger is training. The first week he runs 2 miles each session, increasing the distance he runs by 1.5 miles. Write a recursive definition for the sequence", "A(n-1)+1.5");

        //Exponential growth/decay
        addQuestion("Use the domain {1,2,3} to evaluate the function. y=40(1/2)^x", "20, 10, 5");

        //Exponents
        addQuestion("Simplify √(3x^2*y^3/4√(5xy^3))", "√(15x/20)");

        //Solve by completing
        addQuestion("n^2 + 19n + 66 = 6", "(-9 or -15)");
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
    }


    public void updateQuestion() {
        currentanswer = getRand(1, 4);
        do {
            currentquestion = getRand(0, questions.capacity());
        } while (currentquestion == prechoice);
        prechoice = currentquestion;
        for (int i = 1; i < 5; i++) {
            switch (i) {
                case 1:
                    answer1.setText(answers.elementAt(getRand(0, questions.capacity())));
                    break;
                case 2:
                    do {
                        answer2.setText(answers.elementAt(getRand(0, questions.capacity())));
                    } while (answer2.getText().equals(answer1.getText()));
                    break;
                case 3:
                    do {
                        answer3.setText(answers.elementAt(getRand(0, questions.capacity())));
                    }
                    while ((answer3.getText().equals(answer1.getText())) || (answer3.getText().equals(answer2.getText())));
                    break;
                case 4:
                    do {
                        answer4.setText(answers.elementAt(getRand(0, questions.capacity())));
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
                                answer1.setText(answers.elementAt(getRand(0, questions.capacity())));
                            }
                            while ((answer1.getText().equals(answer2.getText())) || (answer1.getText().equals(answer3.getText())) || (answer1.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 2:
                    if (i != currentanswer) {
                        if (answer2.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer2.setText(answers.elementAt(getRand(0, questions.capacity())));
                            }
                            while ((answer2.getText().equals(answer1.getText())) || (answer2.getText().equals(answer3.getText())) || (answer2.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 3:
                    if (i != currentanswer) {
                        if (answer3.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer3.setText(answers.elementAt(getRand(0, questions.capacity())));
                            }
                            while ((answer3.getText().equals(answer2.getText())) || (answer3.getText().equals(answer1.getText())) || (answer3.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 4:
                    if (i != currentanswer) {
                        if (answer4.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer4.setText(answers.elementAt(getRand(0, questions.capacity())));
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
                redCar.setPadding(redCar.getPaddingLeft() + 50, 0, 0, 0);
                updateQuestion();
                updatePlayerc();
            } else {
                System.out.println("Wrong!");
                disToast("Wrong!");
                updatePlayerc();
                updateQuestion();
            }
        } else {
            System.out.println("No button was selected");
            disToast("No button selected");
        }
    }

    public void updatePlayerc() {
        int correct;
        correct = getRand(0, 10);

        if (correct > 5) {
            blueCar.setPadding(blueCar.getPaddingLeft() + 50, 0, 0, 0);
        }

        correct = getRand(0, 10);

        if (correct > 5) {
            greenCar.setPadding(greenCar.getPaddingLeft() + 50, 0, 0, 0);
        }
    }

    public void disToast(String name) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, name, duration);
        toast.show();
    }
}
