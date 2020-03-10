package com.example.plane.MODELE;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.plane.CONTROLEUR.GameView;

/**
 * @author Abdelkader
 * @version 1
 * Cette classe permet d'afficher les mechants en direct sur la surfaceView
 */

public class Mechant {

    private Bitmap image;
    private Bitmap image2;
    public int xX, yY;
    private int xVelocity = 10;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


    public int getxX() {
        return xX;
    }

    public void setxX(int xX) {
        this.xX = xX;
    }

    public int getyY() {
        return yY;
    }

    public void setyY(int yY) {
        this.yY = yY;
    }

    public Mechant(Bitmap bmp, Bitmap bmp2, int x, int y) {
        image = bmp;
        image2 = bmp2;
        yY = y;
        xX = x;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, xX, -(GameView.gapHeight / 2) + yY, null);
        canvas.drawBitmap(image2,xX, ((screenHeight / 2)+ (GameView.gapHeight / 2)) + yY, null);


    }


    public void update() {

        xX -= GameView.velocity;
    }

}