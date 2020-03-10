package com.example.plane.VUE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plane.CONTROLEUR.GameView;
import com.example.plane.R;
import com.example.plane.DAO.dataBaseManager;
import com.example.plane.DAO.dbScore;

import java.util.List;

public class MainActivity extends Activity {
    Button jouer;
    ImageView iv;
    ImageView log;
    dataBaseManager dbm;
    TextView tvHigh;
    List<dbScore> Liste;
    dbScore dbs;
    List<Integer> Lint;
    int z;
    int e=0;
    GameView gv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debut);
        iv =findViewById(R.id.imv);
        log =findViewById(R.id.logo);
        tvHigh =findViewById(R.id.txtHighScore);
        dbm= new dataBaseManager(this);
        Liste =dbm.getAllScore();

        // le for recherche dans la base de donnée le meilleur score et l'affiche dans le layout activité debut
        for(dbScore dbs:Liste){

            z=dbs.getScore();

            if (z>e){
                e=z;
            }

           Log.i("dbs", Integer.toString(e));


        }
        Log.i("tv", tvHigh.getText().toString());
        tvHigh.setText(Integer.toString(e));
        Log.i("tv", tvHigh.getText().toString());

        this.iv.setImageResource(R.drawable.namek);
        this.log.setImageResource(R.drawable.logo);

    // on crée un obket GameView qui créee et instancie la SurfaceView et on recupere le contexte de la mainActivity
        //necessaire pour pouvoir arreter la surfaceView depuis la Mainactivity

        this.jouer = findViewById(R.id.btnJouer);
        gv =new GameView(getApplicationContext(), this);
        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setContentView(new GameView(getApplicationContext()));
                setContentView(gv);
            }
        });





    }
    @Override
    protected void onPause() {
        super.onPause();
        gv.surfaceDestroyed(gv.getHolder());
        //cette classe met le jeu en pause quand on quitte le jeu
        System.out.println("ME - onPause");

    }

    public void callMe(String valuePassed) {
        //cette mette permet dans la surfaceView de lancée YourDead.
        // depuis la surfaceView il n'est pas possible de faire un intent car ce n'est pas une activity
        //a part entiére.
        System.out.println(valuePassed);
        gv.setVisibility(View.INVISIBLE); // on la rend invisible


          Intent intent = new Intent(MainActivity.this, YourDead.class);
        startActivity(intent);

        Log.i("test","MainActivity was passed ["+ valuePassed +"]");
    }

    public void relancerSurfaceView() {
        gv.setVisibility(View.VISIBLE);
    }
}




