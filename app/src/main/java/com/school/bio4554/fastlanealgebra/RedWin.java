package com.school.bio4554.fastlanealgebra;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RedWin extends AppCompatActivity {

    Intent intentExtras = getIntent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_win);
        ImageView carImage = (ImageView)findViewById(R.id.imageView5);
        TextView textThing = (TextView) findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        int carwin = extras.getInt("car", 1);
        switch (carwin) {
            case 1: //Red
                carImage.setImageResource(R.drawable.redcar);
                textThing.setText("Red Car wins!");
                break;
            case 2: //Blue
                carImage.setImageResource(R.drawable.bluecar);
                textThing.setText("Blue Car wins!");
                break;
            case 3: //Green
                carImage.setImageResource(R.drawable.greencar);
                textThing.setText("Green Car wins!");
                break;
        }
    }

    public void restartGame(View view) {
        Intent intent = new Intent(this, MainGame.class);
        startActivity(intent);
    }

}
