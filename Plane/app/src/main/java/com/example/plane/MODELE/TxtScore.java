package com.example.plane.MODELE;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Abdelkader
 * @version 1
 * Cette classe permet d'afficher le score en direct sur la surfaceView
 */

public class TxtScore {

    private String txtS;
    int x,y;

    private int count = 0;


    /**
     * Cette methode permet de placer sur la surfaceview.
     * @param txt c'est le score qu'on modifie dans la SurfaceView
     */

    public TxtScore (String txt) {
        txtS = txt;
        x = 50;
        y = 100;
    }

    /**
     * Cette classe dessine sur la surfaceView le score, on y definie la couleur, la taille..
     * @param canvas
     */

    public void draw(Canvas canvas) {
        Paint paint= new Paint();
        paint.setTextSize(90);
        paint.setColor(Color.WHITE);
        canvas.drawText(txtS, x, y, paint);



    }
    public void update() {

        txtS=txtS;


    }

}
