package identity;

import weapon.Armor;
import weapon.Weapon;
import weapon.WeaponCharacter;

import static java.lang.String.format;

public class Job extends Player {

    protected Weapon weapon;
    private Armor armor;

    public Job(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    public void wearWeapon(Weapon weapon){
        this.weapon = weapon;
        try {
            checkWearWeaponType(weapon.type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            checkWeaponCharacter(weapon.type, weapon.weaponCharacter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        characterDamage = weapon.weaponCharacter.getEffect();
        damage += weapon.getDamage();
        pauseRoundDamage = weapon.weaponCharacter.pauseRound;
        characterState = weapon.weaponCharacter.characterName;
    }

    public void wearArmor(Armor armor){
        this.armor = armor;
    }

    private String setWeaponInformation() {
        if(weapon.name.equals("")){
            return "";
        }else{
            return weapon.weaponBeUsed();
        }
    }

    private String setWeaponCharacterEffect(Player victim) {
        if(weapon.name.equals("") || weapon.weaponCharacter.characterName.equals("")){
            return "";
        }else{
            return format("%s%s，", victim.name, weapon.weaponCharacter.useWeaponCharacter());
        }
    }

    private String defenceInformation() {
        if(armor.name.equals("")){
            return "";
        }else {
            return format("，%s%s", this.name, armor.armorBeUsed());
        }
    }

    public void checkWearWeaponType(String weaponType) throws Exception {}

    public void checkWeaponCharacter(String weaponType, WeaponCharacter weaponCharacter) throws Exception {};

    @Override
    public String getRole() {
        return "非普通人";
    }


    @Override
    public void setPauseRoundDamage() {
        pauseRoundDamage += weapon.weaponCharacter.pauseRound;
    }

    @Override
    protected int bleed(int damageFromAttacker) {
        return (damageFromAttacker > armor.getDefence()) ? damageFromAttacker - armor.getDefence() : 0;
    }

    @Override
    protected String setAttackerInformation() {
        return format("%s%s", super.setAttackerInformation(), setWeaponInformation());
    }


    @Override
    protected String setVictimInformation() {
        return format("%s%s%s",this.getRole(), this.name, defenceInformation());
    }


    @Override
    protected String setVictimEffect(Player victim){
        return format("%s%s",super.setVictimEffect(victim), setWeaponCharacterEffect(victim));
    }

}
