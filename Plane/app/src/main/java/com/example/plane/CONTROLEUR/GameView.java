package com.example.plane.CONTROLEUR;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.plane.DAO.dataBaseManager;
import com.example.plane.DAO.dbScore;
import com.example.plane.MODELE.Mechant;
import com.example.plane.MODELE.Sangoku;
import com.example.plane.MODELE.TxtScore;
import com.example.plane.R;
import com.example.plane.VUE.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Abdelkader
 * @version 1
 * Cette classe crée la surfaceView sur laquelle on vas joeur
 * il y a toute les methodes necessaires on bon fonctionnement du jeu
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Sangoku ssj;
    public Mechant mechant1, mechant2, mechant3;
    public static int gapHeight = 500;
    public static int velocity = 20;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.namek);
    Bitmap scaled;
    Context mContext;
    public static Boolean Boul=false;

    dbScore dbs;


    int countL=-2;

    Bitmap beerus;
    Bitmap ss;
    dataBaseManager dbm;
    private String txtS="0";
    TxtScore sc;



    private MainActivity mainActivity;


    public GameView(Context context, MainActivity pMainActivity) {
        this(context);
        this.mainActivity =  pMainActivity;
    }






    public GameView(Context context) {
        super(context);
       getHolder().addCallback(this);
        mContext =context;
        thread = new MainThread(getHolder(), this);
        dbm= new dataBaseManager(context);
        Log.i("creation","test");
//


    }

    /**
     * cette classe permet de mettre à jour le thread avec les elements en fonction de ce qui se passe dans le jeu
     */
    public void update() {
        ssj.update();
        logic();

        mechant1.update();
        mechant2.update();
       // mechant3.update();


        }

    private void passToMainActivity(String valToPass) {

        Log.i("test","mains");
        mainActivity.callMe(valToPass);

    }


    /**
     * cette classe clrée la surfaceView, demare le thread et crée le level
     * @param holder
     */

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        float scale = (float)background.getHeight()/(float)getHeight();
        int newWidth = Math.round(background.getWidth()/scale);
        int newHeight = Math.round(background.getHeight()/scale);
        this.scaled = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);




        thread.setRunning(true);
        thread.start();
        makeLevel();


    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * Cette classe permet une fois que le joueur est mort d'appeler l'Activité YourDead en envoyé les données sur la mainActivity
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {



        mainActivity.callMe("ME - 2");
        thread.setRunning(false);
    }

    /**
     * cette classe dessine sur la surface view les elements en recuperant dans les modeles les données de positions..
     * @param canvas
     */

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {


            canvas.drawBitmap(scaled, 0, 0, null);
            ssj.draw(canvas);

            sc.draw(canvas);

            mechant1.draw(canvas);
            mechant2.draw(canvas);
            mechant3.draw(canvas);

        }
    }

    /**
     * Cette classe permet de redimenssionner une image
     * @param bm
     * @param newWidth
     * @param newHeight
     * @return
     */
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();

        matrix.postScale(scaleWidth, scaleHeight);


        Bitmap resizedBitmap =
                Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


    /**
     * cette classe permet de faire remonter le joeur à chaque click
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        ssj.y = ssj.y - (ssj.yVelocity * 20);

        return super.onTouchEvent(event);
    }

    /**
     * cette classe permet de crée le lever
     * on y insere les resources des images, on les redimenssionnes.
     *
     */
    private void makeLevel() {
        ssj = new Sangoku(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sangokunuage), 250, 200));

        beerus = getResizedBitmap(BitmapFactory.decodeResource
                        (getResources(), R.drawable.sangp_kame), 180,
                Resources.getSystem().getDisplayMetrics().heightPixels / 2);
        ss = getResizedBitmap(BitmapFactory.decodeResource
                        (getResources(), R.drawable.vege_kame), 180,
                Resources.getSystem().getDisplayMetrics().heightPixels / 2);

        mechant1 = new Mechant(beerus, ss, 0, 3300 );
        mechant2 = new Mechant(beerus, ss, -250 , 2200 );
        mechant3 = new Mechant(beerus, ss, 250 , 4500 );

        sc= new TxtScore(txtS);
        Log.i("TxtMakeLevel", txtS);



    }

    /**
     * cette classe donne la logic du jeu
     */
    public void logic() {


        List<Mechant> mechants = new ArrayList<>();
        mechants.add(mechant1);
        mechants.add(mechant2);


        for (int i = 0; i < mechants.size(); i++) {
            Log.i("choc","avan");
            //Cette methode nous dis si le personnage à toucher un des kamehameha
            //Ne pas hesiter a jouer plusieurs fois pour faire la demo car elle reste a etre améliorer
            if (
                    ssj.y < mechants.get(i).yY + (screenHeight / 2)- (gapHeight / 2)
                    && ssj.x +100> mechants.get(i).xX
                    && ssj.x < mechants.get(i).xX+200)
                     {
                Log.i("choc","pendant");
                resetLevel();
            } else if (ssj.y  > (screenHeight / 2) +
                    (gapHeight / 2) + mechants.get(i).yY
                    && ssj.x  > mechants.get(i).xX
                    && ssj.x < mechants.get(i).xX + 200) {
                Log.i("choc","apres");
                resetLevel();
            }

            //celui ci regenere les kamehameha une fois qu'il ont quitter l'ecran
            if (mechants.get(i).xX + 500 < 0) {
                countL = countL + 1;
                txtS = Integer.toString(countL);
                sc = new TxtScore(txtS);
                Log.i("TxtLogic", txtS);
                Random r = new Random();
                int value1 = r.nextInt(500);
                int value2 = r.nextInt(500);
                mechants.get(i).xX = screenWidth + value1 + 1000;
                mechants.get(i).yY = value2 - 500;
            }
        }

        //celle ci dettecte si le personnage est sortie de l'ecran
        if (ssj.y + 240 < 0) {
            Log.i("choc","aprestop");


            resetLevel(); }
        if (ssj.y > screenHeight) {
            Log.i("choc","apresbas");


            resetLevel(); }
    }

    /**
     * cette classe permet d'inserer dans la base de donnée le score en cours
     * elle nous envoie aussi sur l'activité YourDead grace a surfaceDestroyed
     */

    public void resetLevel() {


        dbs=new dbScore(countL);
        dbm.insertScore(dbs);
        Boul=true;
        surfaceDestroyed(getHolder());




    }




}

