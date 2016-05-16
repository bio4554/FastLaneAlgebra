package com.school.bio4554.fastlanealgebra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by bio4554 on 5/16/2016.
 */
public class MainGame extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void advanceRed(View view) {
        ImageView redCar = (ImageView)findViewById(R.id.redCar);
        redCar.setPadding(redCar.getPaddingLeft()+10, 0, 0, 0);
    }
}
