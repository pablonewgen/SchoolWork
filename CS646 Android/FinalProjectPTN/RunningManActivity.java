package edu.sdsu.cs.cs646.finalprojectptn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Paul on 3/4/2016.
 */
public class RunningManActivity extends AppCompatActivity {
    private int flappyPositionX = 200;
    private int flappyPositionY = 1225;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View flappyBirdView = new FlappyBirdView(this);
        setContentView(flappyBirdView);
        flappyBirdView.setBackgroundColor(Color.parseColor("#87CEFA"));

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
    }

    class FlappyBirdView extends View {
        private int pipeLeft = 900;
        private int pipeRight = 1050;
        private int rateOfFlying;
        private int jumpCounter;
        private Rect collisionBird;
        private Rect topPipe;
        private Rect bottomPipe;
        private Rect ground;
        private Rect groundCollision;
        private Paint paintPipe;
        private Paint paintGround;
        private Paint paintGroundCollision;
        private Runner runnerCharacter;
        private boolean startAnimation = false;
        private boolean inFlight = false;

        public FlappyBirdView(Context context) {
            super(context);
            paintPipe = new Paint();
            paintGround = new Paint();
            paintGroundCollision = new Paint();
            topPipe = new Rect();
            bottomPipe = new Rect();
            ground = new Rect();
            groundCollision = new Rect();

            runnerCharacter = new Runner(
                    BitmapFactory.decodeResource(getResources(), R.drawable.runner)
                    , flappyPositionX, flappyPositionY
                    , 108, 36
                    , 11, 3);
        }

        public FlappyBirdView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            int pipeHeight = canvas.getHeight();

            ground.set(0, pipeHeight, 1400, 1400);
            paintGround.setColor(Color.parseColor("#FFFACD"));
            canvas.drawRect(ground, paintGround);
            groundCollision.set(0, pipeHeight - 200, 1400, 1400);
            paintGroundCollision.setColor(Color.parseColor("#575A4B"));
            canvas.drawRect(groundCollision, paintGroundCollision);

            bottomPipe.set(pipeLeft, 1100, pipeRight, pipeHeight - 200);
            paintPipe.setColor(Color.GREEN);
            canvas.drawRect(bottomPipe, paintPipe);

            topPipe.set(pipeLeft, 0, pipeRight, pipeHeight / 3);
            paintPipe.setColor(Color.GREEN);
            canvas.drawRect(topPipe, paintPipe);

            runnerCharacter.draw(canvas);

            update();
            runnerCharacter.update(System.currentTimeMillis());

            invalidate();
        }

        private void update() {
            if(startAnimation) {
                runnerCharacter.setY(runnerCharacter.getY() + 12);
                //runnerCharacter.setX(runnerCharacter.getX() + 1);
                pipeLeft -= 6;
                pipeRight -= 6;
                if (inFlight && runnerCharacter.getY() > flappyPositionY){
                    runnerCharacter.flys(rateOfFlying);
                    rateOfFlying -= 3.5;
                }
                else {
                    if(startAnimation){
                        inFlight = false;
                    }
                }

                yOutofBounds(runnerCharacter);
                pipesOutOfBounds(runnerCharacter, topPipe, bottomPipe);
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            jumpCounter += 1;
            if (event.getAction() == MotionEvent.ACTION_DOWN && jumpCounter < 3) {
                flappyPositionY = runnerCharacter.getY() - 300;
                rateOfFlying = 60;
                inFlight = true;
                startAnimation = true;
            }
            return super.onTouchEvent(event);
        }

        private boolean yOutofBounds(Runner runner) {
            if (runner.getY() < 0 ) { //|| runner.getY() > 1225
                startAnimation = false;
                alerting();
                return true;
            }
            else if (runner.getY() > 1223) {
                runnerCharacter.setY(1225);
                startAnimation = true;
                jumpCounter = 0;
                return false;
            }
            return false;
        }

        private boolean pipesOutOfBounds (Runner runner, Rect tPipe, Rect bPipe) {
            collisionBird = runner.runnerRectangle();
            boolean collisionTop = collisionBird.intersect(tPipe);
            boolean collisionBottom = bPipe.intersect(collisionBird);

            if (collisionTop) {
                startAnimation = false;
                alerting();
                return true;
            } else if (collisionBottom){
                startAnimation = false;
                alerting();
                return true;
            }
            return false;
        }
        private void alerting () {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle("You failed the game! =(");
            alertDialog
                    .setMessage("Birdy DIED!")
                    .setCancelable(false)
                    .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Back to Menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog alerter = alertDialog.create();
            alerter.show();
        }
    }
}
