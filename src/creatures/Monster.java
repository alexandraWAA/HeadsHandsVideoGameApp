package creatures;

import auxiliaries.TextColor;
import auxiliaries.WrongArgumentException;

import java.util.Objects;

public class Monster extends Creature{

    public static Monster[] monsters;

    MonsterType monsterType;

    public static void makeMonsters(int n) throws WrongArgumentException {
        monsters = new Monster[n];
        for (int i = 0; i < n; i++) {
            monsters[i] = new Monster(Objects.requireNonNull(MonsterType.getRandomMonster()));
        }
    }

    public Monster(MonsterType type) throws WrongArgumentException {
        super.type = CreatureType.MONSTER;
        super.isAlive = true;

        super.armor = type.armor;
        super.strength = type.strength;
        super.health = type.health;
        super.damage = type.damage;

        checkAll(strength, armor, health, damage);

        super.maxHealth = (int) health;
        this.monsterType = type;
    }

    @Override
    public String toString() {
        if (isAlive)
            return TextColor.ANSI_BLUE + type + "(" + monsterType + ") {\n" +
                    TextColor.ANSI_PURPLE + "\tstrength: " + TextColor.ANSI_CYAN + strength + "\n" +
                    TextColor.ANSI_PURPLE + "\tarmor: " + TextColor.ANSI_CYAN + armor + "\n" +
                    TextColor.ANSI_PURPLE + "\thealth: " + TextColor.ANSI_CYAN + health + "\n" +
                    TextColor.ANSI_PURPLE + "\tmax health: " + TextColor.ANSI_CYAN + maxHealth + "\n" +
                    TextColor.ANSI_PURPLE + "\tdamage: " + TextColor.ANSI_CYAN + "[" +
                    damage.getValues()[0] + ":" + damage.getValues()[damage.getLength() - 1] + "]" +
                    TextColor.ANSI_BLUE + "\n }\n" + TextColor.ANSI_RESET;
        else return TextColor.ANSI_BLUE + "Player(" + monsterType + ") {\n" +
                TextColor.ANSI_PURPLE + "DEAD" +
                TextColor.ANSI_BLUE + "\n }\n" + TextColor.ANSI_RESET;
    }

    public MonsterType getType(){
        return monsterType;
    }

    @Override
    public void attack() {

    }
}