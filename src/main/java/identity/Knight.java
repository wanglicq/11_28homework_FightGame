package identity;

import weapon.WeaponCharacter;

public class Knight extends Job {
    public Knight(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    @Override
    public String getRole() {
        return "骑士";
    }

    @Override
    public void checkWearWeaponType(String weaponType) throws Exception {
        if(weaponType.equals("中") || weaponType.equals("长")){
            return;
        }else{
            throw new Exception("骑士只可以装备中长武器");
        }
    }

    @Override
    public void checkWeaponCharacter(String weaponType, WeaponCharacter weaponCharacter) throws Exception{
        if(weaponType.equals("短") && !weaponCharacter.characterName.equals("")){
            throw new Exception("只有刺客可以发动短武器技能效果");
        }
        if(weaponType.equals("中") && !weaponCharacter.characterName.equals("")){
            throw new Exception("只有战士可以发动中武器技能效果");
        }else{
            return;
        }
    }
}
