package identity;

import identity.Job;
import weapon.WeaponCharacter;

public class Assassin extends Job {

    public Assassin(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    @Override
    public String getRole() {
        return "刺客";
    }

    @Override
    public void checkWearWeaponType(String weaponType) throws Exception {
        if(weaponType.equals("中") || weaponType.equals("短")){
            return;
        }else{
            throw new Exception("刺客只可以装备中短武器");
        }
    }

    @Override
    public void checkWeaponCharacter(String weaponType, WeaponCharacter weaponCharacter) throws Exception{
        if(weaponType.equals("长") && !weaponCharacter.characterName.equals("")){
            throw new Exception("只有骑士可以发动长武器技能效果");
        }
        if(weaponType.equals("中") && !weaponCharacter.characterName.equals("")){
            throw new Exception("只有战士可以发动中武器技能效果");
        }else{
            return;
        }
    }

}
