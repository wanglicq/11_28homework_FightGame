package weapon;

import static java.lang.String.format;

public class Armor {
    public String name;
    private int defence;

    public Armor(String name, int defence) {
        this.name = name;
        this.defence = defence;
    }

    public String armorBeUsed(){
        return format("用%s防御", name);
    }

    public int getDefence(){
        return defence;
    }

}
