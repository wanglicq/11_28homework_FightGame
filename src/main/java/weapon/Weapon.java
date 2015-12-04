package weapon;

import static java.lang.String.format;

public class Weapon {
    public String name;
    private int damage;
    public String type;
    public WeaponCharacter weaponCharacter;


    public Weapon(String name, int damage, String type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public String weaponBeUsed(){
        return format("用%s", name);
    }

    public void setWeaponCharacter(WeaponCharacter weaponCharacter){
        this.weaponCharacter = weaponCharacter;
    }

    public int getDamage() {
            return damage;
    }
}
