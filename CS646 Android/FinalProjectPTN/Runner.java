package edu.sdsu.cs.cs646.finalprojectptn;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Paul on 3/4/2016.
 */
public class Runner {

    private Bitmap bitmap;
    private Rect sourceRect;
    private Rect collisionRect;
    private int frameNr;
    private int currentFrame;
    private long frameCounter;
    private int frames;
    private int birdWidth;
    private int birdHeight;
    private int x;
    private int y;

    public Runner(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        currentFrame = 0;
        frameNr = frameCount;
        birdWidth = bitmap.getWidth() / frameCount;
        birdHeight = bitmap.getHeight();
        sourceRect = new Rect(0, 0, birdWidth, birdHeight);
        frames = 1000 / fps;
        frameCounter = 0l;
        collisionRect = new Rect();
    }

    public void update(long gameTime) {
        if (gameTime > frameCounter + frames) {
            frameCounter = gameTime;
            currentFrame++;
            if (currentFrame >= frameNr) {
                currentFrame = 0;
            }
        }
        this.sourceRect.left = currentFrame * birdWidth;
        this.sourceRect.right = this.sourceRect.left + birdWidth;
    }

    public void draw(Canvas canvas) {
        Rect destRect = new Rect(getX(), getY(), getX() + birdWidth, getY() + birdHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
        collisionRect.set(destRect);
    }
    public int flys (int y){
        this.y -= y;
        return this.y;
    }

    public Rect runnerRectangle() {
        return collisionRect;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }



}