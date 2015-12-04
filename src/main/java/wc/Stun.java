package wc;

import weapon.WeaponCharacter;

import static java.lang.String.format;

public class Stun extends WeaponCharacter {
    public Stun(String characterName, int effect, int pauseRound) {
        super(characterName, effect, pauseRound);
    }

    @Override
    public String useWeaponCharacter(){
        return format("晕倒了");
    }
}
