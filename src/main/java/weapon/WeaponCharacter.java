package weapon;

public class WeaponCharacter {
    public String characterName;
    public int effect;
    public int pauseRound;

    public WeaponCharacter(String characterName, int effect, int pauseRound) {
        this.characterName = characterName;
        this.effect = effect;
        this.pauseRound = pauseRound;
    }

    public String useWeaponCharacter(){
        return "";
    }

    public int getEffect() {
        return effect;
    }
}
