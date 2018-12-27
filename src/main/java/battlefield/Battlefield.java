package battlefield;

import world.Calabash_Scheduler;
import world.Monster_Scheduler;
import creature.CalabashBrother;
import creature.Position;
import creature.Scorpion;
import creature.Soldier;


public class Battlefield {
    private Unit[][] field;
    private int row;
    private int coloumn;
    private Calabash_Scheduler CS;             //战场上的两个阵营
    private Monster_Scheduler MS;

    public Battlefield(int row,int col,Calabash_Scheduler cs, Monster_Scheduler ms){
        this.row =row;
        this.coloumn = col;

        field = new Unit[row][col];
        for(int i = 0; i<row;i++)
            for(int j = 0;j<col;j++)
                field[i][j] = new Unit(new Position(i,j));

        CS = cs;
        MS = ms;
    }

    public boolean is_empty(int x,int y){
        if(!field[x][y].if_exist_creature())
            return true;
        else if(field[x][y].isDead())
            return true;
        return false;
    }

    public boolean is_empty(Position x){return  !field[x.get_x()][x.get_y()].if_exist_creature();}

    public boolean isScorpion(Position x) {
        return field[x.get_x()][x.get_y()].isScorpion();
    }

    public boolean isSoldier(Position x) {
        return field[x.get_x()][x.get_y()].isSoldier();
    }

    public CalabashBrother getPositionCala(Position x){
        return field[x.get_x()][x.get_y()].calabashBrother;
    }

    public Soldier getPositionSoldier(Position x){
        return field[x.get_x()][x.get_y()].soldier;
    }

    public Scorpion getPositionScorpion(Position x){
        return field[x.get_x()][x.get_y()].scorpion;
    }

    public void display(){
        for(int i = 0; i < row; i++){
            for(int j = 0 ;j<coloumn;j++)
                if(is_empty(i,j))
                    System.out.print("Null ");
                else
                    System.out.print(field[i][j].getName() + " ");
            System.out.println();
        }
        System.out.println();
    }

    public void debugDisplay(){
        for(int i = 0; i < row; i++){
            for(int j = 0 ;j<coloumn;j++) {
                Position p = new Position(i, j);
                if (is_empty(i, j))
                    System.out.print("em ");
                else{
                    if (isSoldier(p))
                        System.out.print("iS ");
                    else if(isScorpion(p))
                        System.out.print("iP ");
                    else
                        System.out.print("Cb ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void clear(){
        for(int i = 0; i<row;i++)
            for(int j = 0; j<coloumn;j++)
                field[i][j].clear_unit();
    }

    public void setField_calabash(){
        for (CalabashBrother i: CS.getList()
                ) {
            if(!i.dead)
                field[i.getPosition().get_x()][i.getPosition().get_y()].setCalaCreature(i);
        }
    }

    public void setField_soldier(){
        for (Soldier i: MS.getList()
                ) {
            if(!i.dead)
                field[i.getPosition().get_x()][i.getPosition().get_y()].setSoldierCreature(i);
            //System.out.print(i.getName()+ " ");
        }
        if(!MS.getScorpion().dead)
            field[MS.getScorpion().getPosition().get_x()][MS.getScorpion().getPosition().get_y()].setCreature(MS.getScorpion());
    }

    public boolean CsDeadAll(){
        return CS.deadAll();                              //判断战场上是否还有葫芦娃
    }

    public boolean MsDeadAll(){
        return MS.DeadAll();
    }

    public Calabash_Scheduler getCS() {
        return CS;
    }

    public Monster_Scheduler getMS() {
        return MS;
    }
}

