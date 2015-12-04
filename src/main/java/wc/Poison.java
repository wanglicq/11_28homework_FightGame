package wc;


import weapon.WeaponCharacter;

import static java.lang.String.format;

public class Poison extends WeaponCharacter {
    public Poison(String characterName, int effect, int pauseRound) {
        super(characterName, effect, pauseRound);
    }

    @Override
    public String useWeaponCharacter(){
        return format("中毒了，受到%d点毒性伤害", this.effect);
    }

    @Override
    public int getEffect() {
        return effect;
    }
}
