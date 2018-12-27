package creature;

public interface Creature {
    public String getName();
    public Position getPosition();
    public void setPosition(Position p);
    public boolean isSoldier();
    public boolean isScorpion();
    public boolean is_dead();
}
