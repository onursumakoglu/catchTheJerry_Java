package com.onursumakoglu.catchthejerry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    Runnable runnable, runnable2;
    Handler handler, handler2;
    int number = 10;
    int score = 0;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView9,imageView8};
        hideImages();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                number--;
                timeText.setText("Time : " + number);
                handler.postDelayed(runnable, 1000);
                if (number == 0){
                    handler.removeCallbacks(runnable);
                    timeText.setText("Time Off");

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Restart Game");
                    alert.setMessage("Are you sure to restart game?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //restart

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"Game Over!", Toast.LENGTH_SHORT).show();
                            timeText.setText("Game Over!");
                        }
                    });
                    alert.show();
                }
            }
        };

        handler.post(runnable);

    }

    public void increaseScore(View view){

        if (number != 0){
            score++;
            scoreText.setText("Score : " + score);
        }

    }

    public void hideImages(){

        handler2 = new Handler();


        runnable2 = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler2.postDelayed(this, 800);

                if (number == 0){
                    handler2.removeCallbacks(this);
                }

            }
        };

        handler2.post(runnable2);





    }

}
