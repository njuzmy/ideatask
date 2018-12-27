package creature;
import world.Message;
public class Scorpion implements Creature,Runnable{
    private String name;
    private Position position;
    public boolean dead;
    CalabashBrother calabashBrother;
    Soldier soldier;

    public boolean isScorpion() {
        return true;
    }

    public boolean isSoldier() {
        return false;
    }
    public Scorpion(String name){
        this.name = name;
        dead = false;
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

    public void run(){
        try{
            while(true) {
                if (!dead) {
                    boolean b = !Message.calabash(calabashBrother,soldier,this,3);
                    //System.out.println(this.getName()+ " :"+b);
                    if(b)
                        return;
                    else
                        Thread.sleep(1000);
                } else
                    return;
            }}catch(Exception e){}
    }

    public boolean is_dead() {
        return dead;
    }

    @Override
    public String toString(){
        return name;
    }
}
