package creatures;

import auxiliaries.TextColor;
import auxiliaries.WrongArgumentException;

public class Player extends Creature {

    private int numberOfHeals;

    public PlayerType playerType;

    public Player(PlayerType type) throws WrongArgumentException {
        super.type = CreatureType.HUMAN;
        super.isAlive = true;

        super.armor = type.armor;
        super.strength = type.strength;
        super.health = type.health;
        super.damage = type.damage;

        checkAll(strength, armor, health, damage);

        super.maxHealth = (int) health;
        this.numberOfHeals = 0;
        this.playerType = type;
    }

    public void heal(){
        if (numberOfHeals > 4){
            System.out.println(TextColor.ANSI_YELLOW + "You can't heal more than 4 times" + TextColor.ANSI_RESET);
        } else {
            super.health += 0.3 * super.maxHealth;
            numberOfHeals++;
            if (super.health > super.maxHealth)
                super.health = super.maxHealth;
        }
    }

    @Override
    public String toString() {
        if (isAlive)
            return TextColor.ANSI_BLUE + type + "(" + playerType +  ") {\n" +
                    TextColor.ANSI_PURPLE + "\theals done: " + TextColor.ANSI_CYAN + numberOfHeals + "\n" +
                    TextColor.ANSI_PURPLE + "\tstrength: " + TextColor.ANSI_CYAN + strength + "\n" +
                    TextColor.ANSI_PURPLE + "\tarmor: " + TextColor.ANSI_CYAN + armor + "\n" +
                    TextColor.ANSI_PURPLE + "\thealth: " + TextColor.ANSI_CYAN + health + "\n" +
                    TextColor.ANSI_PURPLE + "\tmax health: " + TextColor.ANSI_CYAN + maxHealth + "\n" +
                    TextColor.ANSI_PURPLE + "\tdamage: " + TextColor.ANSI_CYAN + "[" +
                    damage.getValues()[0] + ":" + damage.getValues()[damage.getLength()-1] + "]" +
                    TextColor.ANSI_BLUE + "\n }\n" + TextColor.ANSI_RESET;
        else return TextColor.ANSI_BLUE + "Player(" + playerType +  ") {\n" +
                "DEAD" +
                TextColor.ANSI_BLUE + "\n }\n" + TextColor.ANSI_RESET;
    }

    public PlayerType getType(){
        return playerType;
    }

    @Override
    public void attack() {

    }
}
