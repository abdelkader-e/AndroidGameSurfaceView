package com.example.plane.VUE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plane.R;
import com.example.plane.DAO.dataBaseManager;
import com.example.plane.DAO.dbScore;

import java.util.List;

/**
 * @author Abdelkader
 * @version 1
 * cette classe crée la vue quand le personne meurt et lui rappele le meilleur score et son score
 *
 */

public class YourDead extends Activity {


        Button Retry;
        ImageView dead;
        TextView Score;
        TextView HichScore;
        ImageView back;
        List<dbScore> Liste;
        dataBaseManager dbm;
        int z;
        int e=0;
        ImageView HighScores;
        ImageView Scores;


    /**
     * Cette classe crée l'activité,  elle associe les score avec les imageviews
     *
     * @param savedInstanceState
     */
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.your_dead);
            dead =findViewById(R.id.imv4gameover);
            back =findViewById(R.id.imvbackdead);
            //log =findViewById(R.id.logo);
            Score =findViewById(R.id.txtscoredead);
            HichScore=findViewById(R.id.txtHighDead);

            Scores=findViewById(R.id.ivS);
            HighScores=findViewById(R.id.ivHS);

            //on injecte les resources dans les imagesview
            this.Scores.setImageResource(R.drawable.score);
            this.HighScores.setImageResource(R.drawable.high_score);
            this.back.setImageResource(R.drawable.namek);
            this.dead.setImageResource(R.drawable.gameover);
            dbm= new dataBaseManager(this);
            Liste =dbm.getAllScore();

            //Le for permet de rechercher le score le plus elevée dans la base de donnée.
            for(dbScore dbs:Liste){

                z=dbs.getScore();

                if (z>e){
                    e=z;
                }


            }

            HichScore.setText(Integer.toString(e));

            //le for permet de mettre le dernier score dans la base de donnée

            z=0;
            for (dbScore dbs:Liste){

                z=dbs.getScore();


            }
            Score.setText(Integer.toString(z));


            this.Retry = findViewById(R.id.btnRetry);

            Retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //setContentView(new GameView(getApplicationContext()));
                    Intent intent=new Intent(YourDead.this, MainActivity.class);
                    startActivity(intent);
                    //Le bouton retry permet de crée une nouvelle main activity pour rejouer

                }
            });


        }


}
