package com.example.rmaimone.hgraph;

/**
 * Created by rmaimone on 09/08/2016.
 */
public class HgraphData {


    // name of text label in the chart
    String label;
    // value to indicate if is healthy or unhealthy : if is zero is ok and the circle is in the green area,
    // - v is unhealthy inside circle, +v unhelathy outside circle
    int healthy;
    int score;



    public HgraphData(String label, int healthy) {
        this.label = label;
        this.healthy = healthy;
    }

    public HgraphData(String label, int healthy, int score) {
        this.label = label;
        this.healthy = healthy;
        this.score = score;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getHealthy() {
        return healthy;
    }

    public void setHealthy(int healthy) {
        this.healthy = healthy;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
