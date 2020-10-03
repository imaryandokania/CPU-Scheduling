package com.adcreations.shorttermscheduling;

public class person {
    private int process;
    private int but;
    private int wt;
    private int at;
    private int tat;
    private int priority;
    private float awt;
    private float atat;
     private int n;
    public float getAwt() {
        return awt;
    }

    public void setAwt(float awt) {
        this.awt = awt;
    }

    public float getAtat() {
        return atat;
    }

    public void setAtat(float atat) {
        this.atat = atat;
    }

    public person(int process, int but, int  wt, int  tat, int at, int priority, float awt, float atat,int n)
    {
        this.process=process;
        this.but=but;
        this.wt =wt;
        this.tat=tat;
        this.at=at;
        this.priority=priority;
        this.awt=awt;
        this.atat=atat;
        this.n=n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getTat() {
        return tat;
    }

    public void setTat(int tat)
    {
        this.tat = tat;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getBut() {
        return but;
    }

    public void setBut(int but) {
        this.but = but;
    }

    public int getWt() {
        return wt;
    }

    public void setWt(int wt) {
        this.wt = wt;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }
}
