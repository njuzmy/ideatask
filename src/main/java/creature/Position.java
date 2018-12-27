package creature;

public class Position {
    private int position_x,position_y;

    public Position(){
        position_y = position_x = 0;
    }

    public Position(int x,int y){
        position_x = x;
        position_y = y;
    }

    Position(Position t){
        this.position_x = t.position_x;
        this.position_y = t.position_y;
    }

    public int get_x(){
        return position_x;
    }

    public int get_y(){
        return position_y;
    }

    public void setPosition(int x , int y){
        this.position_x = x;
        this.position_y = y;
    }
}