package creature;

import world.Message;
import formation.*;

import java.io.IOException;
import java.util.List;

public class Grandpa extends Leader implements Creature,Runnable{
    private Position position;
    private String name;
    CalabashBrother calabashBrother;
    Soldier soldier;
    Scorpion scorpion;
    public static boolean dead;
    public static boolean skillUsed;

    public boolean isScorpion() {
        return false;
    }

    public boolean isSoldier() {
        return false;
    }

    public Grandpa(String name){
        this.name = name;
        position = new Position(10,0);
        skillUsed = false;
    }
    public void setPosition(Position t){
        position = t;
    }

    public Position getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public void support(){}

    public boolean is_used() {
        return skillUsed;
    }

    public void setskill(){
        skillUsed = true;
    }

    public void run(){
        try{
            while(true) {
                //System.out.println(this.getName() + " running");
                if (!dead) {
                    if(!Message.calabash(calabashBrother,soldier,scorpion,4))
                        return;
                    else
                        Thread.sleep(1000);
                } else
                    return;
            }
        }catch (Exception e){

        }
    }
    public void check_C_formation(List<CalabashBrother> calabash,String formation){
        switch(formation){
            case "Line": {
                Formation f = new Line();
                f.form_C_formation(calabash);
                break;
            }
            case "Arrow":{
                Formation f = new Arrow();
                f.form_C_formation(calabash);
                break;
            }
        }
    }

    public boolean is_dead() {
        return dead;
    }

    @Override
    public void check_S_Random_formation(List<Soldier> x, int num,Scorpion scorpion) throws IOException {}

    public void check_S_formation(List<Soldier> calabash, String formation) {}
    @Override
    public String toString (){
        return name;
    }
}
