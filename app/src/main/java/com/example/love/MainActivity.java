package com.example.love;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewPirate;

    private ImageView imageViewMermaid;

    private ImageView imageViewBoat;

    private float halfW;

    ObjectAnimator objectAnimator, objectAnimatorMaid;

    private ObjectAnimator lftToRgt, rgtToLft;

    private AnimatorSet animatorSet;//required to set the sequence

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewBoat = findViewById(R.id.boat);
        imageViewMermaid = findViewById(R.id.mermaid_2);

        Point point = new Point();
        imageViewPirate = findViewById(R.id.pirate);
        final int width = point.x; // screen width
        halfW = width / 2.0f; // half the width or to any value required,global to class


        move();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Game Over");
                builder.setMessage("You failed shouting the Mermaid. Game over.");
                builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent q = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(q);


                    }
                });
                builder.setNegativeButton("Stop Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                builder.show();

            }
        };

        Handler handler1 = new Handler();

        handler1.postDelayed(runnable1, 5000);


    }

    public void move() {
        objectAnimator = ObjectAnimator.ofFloat(imageViewPirate, "x", 480);

        animatorSet = new AnimatorSet();
        objectAnimator.setDuration(5000);
        objectAnimator.start();

        objectAnimatorMaid = ObjectAnimator.ofFloat(imageViewMermaid, "x", 400);

        objectAnimatorMaid.setDuration(5000);
        objectAnimatorMaid.start();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                objectAnimatorMaid = ObjectAnimator.ofFloat(imageViewMermaid, "y", 400);

                objectAnimatorMaid.setDuration(5000);
                objectAnimatorMaid.start();

            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, 9000);
    }


}
