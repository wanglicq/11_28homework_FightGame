package identity;

import static java.lang.String.format;

public class Player {
    protected String name;
    protected int blood;
    protected int damage;
    protected int characterDamage;
    public int pauseRoundDamage;
    protected String characterState;

    public Player(String name, int blood, int damage) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getBlood() {
        return blood;
    }

    public int getDamage() {
        return damage;
    }

    public String getRole() { return "普通人"; }

    public int getPauseRoundDamage(){ return --pauseRoundDamage; }

    public void setPauseRoundDamage() {}

    public String getStateDamage() { return characterState; }

    protected String setVictimInformation() {
        return format("%s%s", getRole(), name);
    }

    protected String setAttackerInformation() {
        return format("%s%s", getRole(), name);
    }

    public boolean isAlive() {
        return blood >= 0;
    }

    protected int bleed(int damageFromAttacker) {
        return damageFromAttacker;
    }

    public String getState(Player attacker){
        return format("%s%s了，无法攻击，%s还剩：%d轮",
                name, attacker.getStateDamage(), attacker.getStateDamage(), attacker.getPauseRoundDamage());
    }

    public String attack(Player victim) {
        return format("%s攻击了%s，%s%s",
                setAttackerInformation(), victim.setVictimInformation(),
                setVictimEffect(victim), victim.beAttacked(damage+characterDamage));
    }


    protected String setVictimEffect(Player victim){
        return format("%s受到了%d点攻击力，", victim.name, victim.bleed(damage));
    }


    protected String beAttacked(int damageFromAttacker) {
        int bleed = bleed(damageFromAttacker);
        blood -= bleed;
        return format("%s剩余生命：%d", name, blood);
    }


}
