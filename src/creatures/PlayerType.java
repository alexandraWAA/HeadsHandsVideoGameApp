package creatures;

import auxiliaries.TextColor;

public enum PlayerType {
    BARBARIAN(20, 10, 15, new Damage(5, 8)),
    KNIGHT(10, 20, 15, new Damage(3, 6)),
    MAGICIAN(5, 10, 30, new Damage(1, 6));

    private final String description;
    public final int strength;
    public final int armor;
    public final double health;
    public final Damage damage;

    public String getDescription() {
        return description;
    }

    PlayerType(int strength, int armor, double health, Damage damage){
        description = makeDescription(strength, armor, health, damage);
        this.strength = strength;
        this.armor = armor;
        this.damage = damage;
        this.health = health;
    }

    private String makeDescription(int strength, int armor, double health, Damage damage){
        return " {\n" +
                TextColor.ANSI_PURPLE + "\tstrength: " + TextColor.ANSI_CYAN + strength + "\n" +
                TextColor.ANSI_PURPLE + "\tarmor: " + TextColor.ANSI_CYAN + armor + "\n" +
                TextColor.ANSI_PURPLE + "\thealth: " + TextColor.ANSI_CYAN + health + "\n" +
                TextColor.ANSI_PURPLE + "\tdamage: " + TextColor.ANSI_CYAN + "[" +
                damage.getValues()[0] + ":" + damage.getValues()[damage.getLength()-1] + "]" +
                TextColor.ANSI_BLUE + "\n }\n";
    }
}
