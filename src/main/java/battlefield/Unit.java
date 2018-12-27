package battlefield;

import creature.*;

public class Unit {
    private boolean exist_creature;
    private Creature unit_creature;
    private Position position;
    public CalabashBrother calabashBrother;
    public Soldier soldier;
    public Scorpion scorpion;

    Unit(){
        exist_creature = false;
        unit_creature = null;
        position = new Position();
    }

    Unit(Position coordinate) {
        unit_creature = null;
        position = coordinate;
        exist_creature = false;
    }

    Unit(Creature creature, Position coordinate) {
        unit_creature = creature;
        position = coordinate;
        exist_creature = true;
    }
    public String getName(){
        return unit_creature.toString();
    }

    public Creature getUnit_creature(){
        return unit_creature;
    }

    public boolean isScorpion() {
        return unit_creature.isScorpion();
    }

    public boolean isSoldier() {
        return unit_creature.isSoldier();
    }

    public  boolean isDead(){
        return unit_creature.is_dead();
    }

    public void clear_unit(){
        exist_creature = false;
        unit_creature = null;
    }

    public boolean setCreature(Creature creature){
        if(exist_creature)
            return false;
        else{
            exist_creature = true;
            unit_creature = creature;
            return true;
        }
    }

    public boolean setCalaCreature(CalabashBrother creature){
        if(exist_creature)
            return false;
        else{
            exist_creature = true;
            unit_creature = creature;
            calabashBrother = creature;
            return true;
        }
    }

    public boolean setSoldierCreature(Soldier creature){
        if(exist_creature)
            return false;
        else{
            exist_creature = true;
            unit_creature = creature;
            soldier = creature;
            return true;
        }
    }

    public boolean if_exist_creature(){
        return exist_creature;
    }
}

