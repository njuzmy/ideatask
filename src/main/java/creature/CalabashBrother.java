package creature;

import world.Message;
import battlefield.Battlefield;
import battlefield.Unit;

import java.util.concurrent.TimeUnit;

enum Rank {

    FIRST("老大"), SECOND("老二"), THIRD("老三"), FOURTH("老四"), FIFTH("老五"), SIXTH("老六"), SEVENTH("老七");

    private String rank;

    Rank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank;
    }
}

public enum CalabashBrother implements Creature,Runnable {
    RED(Color.RED, Rank.FIRST,1,"老大"), ORANGE(Color.ORANGE, Rank.SECOND,2,"老二"), YELLOW(Color.YELLOW, Rank.THIRD,3,"老三"),
    GREEN(Color.GREEN, Rank.FOURTH,4,"老四"), CYAN(Color.CYAN, Rank.FIFTH,5,"老五"), BLUE(Color.BLUE, Rank.SIXTH,6,"老六"),
    PURPLE(Color.PURPLE, Rank.SEVENTH,7,"老七");

    private Color colour;
    private Rank rank;
    private Position position;
    private String name;
    private int no;
    public boolean dead;
    Soldier soldier;
    Scorpion scorpion;
    //private Battlefield battlefield;

    CalabashBrother(Color colour, Rank rank, int no, String name){
        this.colour = colour;
        this.rank = rank;
        this.no = no;
        this.name = name;
        dead = false;
    }

    public boolean isScorpion() {
        return false;
    }

    public boolean isSoldier() {
        return false;
    }

    public Rank getRank() {
        return rank;
    }

    public Color getColour() {
        return colour;
    }

    public int getNo(){
        return no;
    }
    public String getName(){
        return name;
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position p){
        position = p;
    }

    public void run(){
        try{
            while(true) {
                //System.out.println(this.getName() + " running");
                if (!dead || this.getNo() == 1) {
                    if(!Message.calabash(this,soldier,scorpion,1)){
                        //System.out.println("here");
                        return;
                    }
                    else
                        Thread.sleep(1000);
                } else
                    return;
            }
        }catch (Exception e){

        }
    }

    public boolean is_dead() {
        return dead;
    }

    @Override
    public String toString(){
        return name;
    }
}
