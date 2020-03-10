package com.example.plane.MODELE;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * @author Abdelkader
 * @version 1
 * cette classe permet de crée le personne principal de notre jeu
 */

public class Sangoku {


    private Bitmap image;
    public int x, y;
    private int xVelocity = 10;
    public int yVelocity = 10;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int count = 0;

    /**
     * Cette methode permet de placer sur la surfaceview.
     * @param bmp c'est l'image qu'on insert dans la SurfaceView
     */


    public Sangoku (Bitmap bmp) {
        image = bmp;
        x = 500;
        y = 500;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);



    }
    public void update() {

        y += yVelocity;



    }


}