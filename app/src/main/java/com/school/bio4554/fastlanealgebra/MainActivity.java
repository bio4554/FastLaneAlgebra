package com.school.bio4554.fastlanealgebra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);

    }

    public void startGame(View view) {
        Intent intent = new Intent(this, MainGame.class);
        startActivity(intent);
    }


}
