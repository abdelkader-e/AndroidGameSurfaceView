package com.example.plane.DAO;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Abdelkader
 * @version 1
 * cette classe est le modèle pour persister les scores dans la base de donnée
 * il a etais crée avec un ORM.
 *
 */

@DatabaseTable(tableName = "dbScore")
public class dbScore {

    @DatabaseField(columnName = "id", generatedId=true)
    private int idScore;
    @DatabaseField
    private int score;

    public dbScore() {
    }

    public dbScore(int score) {
        this.score = score;
    }

    public dbScore(int idScore, int score) {
        this.idScore = idScore;
        this.score = score;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "dbScore{" +
                "idScore=" + idScore +
                ", score=" + score +
                '}';
    }
}
