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

import java.util.Random;
import java.util.Vector;

/**
 * Created by bio4554 on 5/16/2016.
 */
public class MainGame extends AppCompatActivity{

    Random rand = new Random();
    private final IntentFilter intentFilter = new IntentFilter();
    WifiP2pManager.Channel mChannel;
    WifiP2pManager mManager;

    public int getRand(int min, int max) {
        int val = rand.nextInt(max) + min;
        return val;
    }

    Vector<String> questions = new Vector<String>(4);
    Vector<String> answers = new Vector<String>(4);
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

    private void addQuestion(String ques, String ans) {
        questions.addElement(ques);
        answers.addElement(ans);
    }

    private void initQuestions() {
        addQuestion("X+2=4", "X=2");
        addQuestion("X-5=7", "X=12");
        addQuestion("X+9=8", "X=-1");
        addQuestion("X-2=9", "X=11");
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
        answer1 = (RadioButton)findViewById(R.id.answerChoice1);
        answer2 = (RadioButton) findViewById(R.id.answerChoice2);
        answer3 = (RadioButton) findViewById(R.id.answerChoice3);
        answer4 = (RadioButton) findViewById(R.id.answerChoice4);
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);
        updateQuestion();
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        redCar = (ImageView) findViewById(R.id.redCar);
    }

    public void advanceRed(View view) {
        ImageView redCar = (ImageView)findViewById(R.id.redCar);
        redCar.setPadding(redCar.getPaddingLeft()+10, 0, 0, 0);
    }

    public void updateQuestion() {
        currentanswer = getRand(1,4);
        do {
            currentquestion = getRand(0, questions.capacity());
        } while(currentquestion == prechoice);
        prechoice = currentquestion;
        for(int i = 1; i < 5; i++) {
            switch (i) {
                case 1:
                    answer1.setText(answers.elementAt(getRand(0, questions.capacity())));
                    break;
                case 2:
                    do {
                        answer2.setText(answers.elementAt(getRand(0, questions.capacity())));
                    } while(answer2.getText().equals(answer1.getText()));
                    break;
                case 3:
                    do {
                        answer3.setText(answers.elementAt(getRand(0, questions.capacity())));
                    } while((answer3.getText().equals(answer1.getText())) || (answer3.getText().equals(answer2.getText())));
                    break;
                case 4:
                    do {
                        answer4.setText(answers.elementAt(getRand(0, questions.capacity())));
                    } while((answer4.getText().equals(answer1.getText())) || (answer4.getText().equals(answer2.getText())) || (answer4.getText().equals(answer3.getText())));
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

        for(int i = 1; i < 5; i++) {
            switch (i) {
                case 1:
                    if(i != currentanswer) {
                        if(answer1.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer1.setText(answers.elementAt(getRand(0, questions.capacity())));
                            } while((answer1.getText().equals(answer2.getText())) || (answer1.getText().equals(answer3.getText())) || (answer1.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 2:
                    if(i != currentanswer) {
                        if(answer2.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer2.setText(answers.elementAt(getRand(0, questions.capacity())));
                            } while((answer2.getText().equals(answer1.getText())) || (answer2.getText().equals(answer3.getText())) || (answer2.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 3:
                    if(i != currentanswer) {
                        if(answer3.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer3.setText(answers.elementAt(getRand(0, questions.capacity())));
                            } while((answer3.getText().equals(answer2.getText())) || (answer3.getText().equals(answer1.getText())) || (answer3.getText().equals(answer4.getText())));
                        }
                    }
                    break;
                case 4:
                    if(i != currentanswer) {
                        if(answer4.getText().equals(answers.elementAt(currentquestion))) {
                            do {
                                answer4.setText(answers.elementAt(getRand(0, questions.capacity())));
                            } while((answer4.getText().equals(answer2.getText())) || (answer4.getText().equals(answer3.getText())) || (answer4.getText().equals(answer1.getText())));
                        }
                    }
                    break;
            }
        }
        questiontext.setText(questions.elementAt(currentquestion));
    }

    public void checkQuestion(View view) {
        if(radiogroup.getCheckedRadioButtonId()!=-1){
            int id= radiogroup.getCheckedRadioButtonId();
            View radioButton = radiogroup.findViewById(id);
            int radioId = radiogroup.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) radiogroup.getChildAt(radioId);
            String selection = (String) btn.getText();
            System.out.println("Found selected button!");
            if(selection.equals(answers.elementAt(currentquestion))) {
                System.out.println("Correct!");
                redCar.setPadding(redCar.getPaddingLeft()+10, 0 ,0 ,0);
                updateQuestion();
            } else {
                System.out.println("Wrong!");
            }
        } else {
            System.out.println("No button was selected");
        }
    }
}
