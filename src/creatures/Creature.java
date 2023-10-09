package creatures;
import auxiliaries.WrongArgumentException;

abstract public class Creature {

    protected int strength;
    protected int armor;
    protected double health;
    protected Damage damage;
    protected boolean isAlive;
    protected CreatureType type;

    protected int maxHealth;

    public abstract Object getType();

    public int getStrength() {
        return strength;
    }

    public int getArmor() {
        return armor;
    }

    public double getHealth() {
        return health;
    }

    public Damage getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void decreaseHealth(int n){
        this.health -= n;
        if (this.health < 0){
            this.health = 0;
            this.isAlive = false;
        }
    }


    public CreatureType getCreatureType(){
        return type;
    }

    private void checkArgument(String name, int value, int start, int end) throws WrongArgumentException {
        if (start > value || value > end)
            throw new WrongArgumentException(name, value, start, end);
    }

    protected void checkAll(int strength, int armor, double health, Damage damage) throws WrongArgumentException {
        checkArgument("strength", strength, 1, 30);
        checkArgument("armor", armor, 1, 30);
        checkArgument("health", (int) health, 1, 30);
        checkArgument("left border of the damage segment", damage.getValues()[0], 1, 10);
        checkArgument("right border of the damage segment", damage.getValues()[damage.getLength()-1], 2, 20);

    }
    abstract public void attack();
}
