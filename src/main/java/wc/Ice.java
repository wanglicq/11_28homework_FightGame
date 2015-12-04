package wc;

import weapon.WeaponCharacter;

import static java.lang.String.format;

public class Ice extends WeaponCharacter {
    public Ice(String characterName, int effect, int pauseRound) {
        super(characterName, effect, pauseRound);
    }

    @Override
    public String useWeaponCharacter(){
        return format("冻僵了，受到了%d点冰冻伤害", getEffect());
    }

    @Override
    public int getEffect() {
        return effect;
    }
}
