package world;

import creature.Leader;
import creature.Scorpion;
import creature.Soldier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Monster_Scheduler extends Scheduler<Soldier> {
    private Leader leader;
    private List<Soldier> soldiers = new ArrayList<Soldier>();
    private Scorpion scorpion = new Scorpion("scorpion");
    private int soldiers_num;

    public Monster_Scheduler(Leader x){
        leader = x;
    }

    public Scorpion getScorpion() {
        return scorpion;
    }

    public void init_Soldiers(){
        Random p = new Random();
        int num = p.nextInt(6) + 15;
        soldiers_num = num;
        //System.out.println(num);
        for(int i = 0; i<num;i++) {
            Soldier so = new Soldier(("soldiers" + i).toString(), i);
            so.dead = false;
            soldiers.add(so);
        }
        scorpion.dead = false;
    }

    public List<Soldier> getList(){              //返回士兵list
        return soldiers;
    }

    public void setFormation(String name){
        leader.check_S_formation(soldiers, name);
    }

    public void set_random_Formation() throws IOException{
        leader.check_S_Random_formation (soldiers,soldiers_num,scorpion);
    }

    public void shuffle(){
        Collections.shuffle(soldiers);
    }

    public void display(){
        for (Soldier p:soldiers
                ) {
            System.out.print(p.getName() + " ");
        }
        System.out.println();
    }

    public boolean DeadAll(){
        for (Soldier p:soldiers
                ){
            if(!p.dead)
                return false;
        }
        if(!scorpion.dead)
            return false;
        return true;
    }

    public Soldier aliveSoldier(){                //获取活着的士兵的随机一个
        List<Soldier> sd = new ArrayList<Soldier>() ;
        for(Soldier p:soldiers){
            if(!p.dead)
                sd.add(p);
        }
        /*for (Soldier p:sd
             ) {
            System.out.print(p.getName());
        }
        System.out.println();*/
        Collections.shuffle(sd);
        return sd.get(0);
    }
}
