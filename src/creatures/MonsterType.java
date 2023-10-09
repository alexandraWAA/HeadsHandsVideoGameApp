package creatures;

import java.util.Random;

public enum MonsterType{
    ZOMBIE(10, 20, 5, new Damage(2, 3)),
    SKELETON(10, 10, 5, new Damage(1, 2)),
    WEREWOLF(7, 2, 10, new Damage(1, 4));

    public final int strength;
    public final int armor;
    public final double health;
    public final Damage damage;


    MonsterType(int strength, int armor, double health, Damage damage){
        this.strength = strength;
        this.armor = armor;
        this.damage = damage;
        this.health = health;
    }

    public static MonsterType getRandomMonster(){
        Random random = new Random();
        switch (random.nextInt(3)){
            case 0 -> {
                return ZOMBIE;
            }
            case 1 -> {
                return SKELETON;
            }
            case 2 -> {
                return WEREWOLF;
            }
        }
        return null;
    }

}
