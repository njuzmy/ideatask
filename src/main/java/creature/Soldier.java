package creature;

import world.Message;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Soldier implements Creature,Runnable{
    private String name;
    private Position position;
    private int num;
    public boolean dead;
    CalabashBrother calabashBrother ;
    Scorpion scorpion;


    public boolean isScorpion() {
        return false;
    }


    public boolean isSoldier() {
        if(dead)
            return false;
        return true;
    }


    public boolean is_dead() {
        return dead;
    }
    public Soldier(){dead = false;}
    public Soldier(String name,int i){
        this.name = name;
        this.num = i;
        dead = false;
    }
    public Soldier(Position p){
        this.position = p;
        dead = false;
    }

    public void setNum(int id){
        num = id;
    }

    public  int getNum(){
        return num;
    }

    public void run(){
        try{
            while(true) {
                if (!dead) {
                    boolean b = !Message.calabash(calabashBrother,this,scorpion,2);
                    //System.out.println(this.getName()+ " :"+b);
                    if(b)
                        return;
                    else
                        Thread.sleep(1000);
                } else
                    return;
            }}catch(Exception e){}
    }
    public Soldier(Position p, String name){this.position = p; this.name = name;dead = false;}
    public void setPosition(Position t){
        this.position = t;
    }

    public Position getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return name;
    }
}
