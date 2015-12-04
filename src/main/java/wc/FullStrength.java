package wc;

import weapon.WeaponCharacter;

import static java.lang.String.format;

public class FullStrength extends WeaponCharacter {
    public FullStrength(String characterName, int effect, int pauseRound) {
        super(characterName, effect, pauseRound);
    }

    @Override
    public String useWeaponCharacter(){
        return format("受到了全力一击，受到%d点加倍伤害", getEffect());
    }

    @Override
    public int getEffect() {
        return 3 * effect;
    }
}
