package identity;

import weapon.WeaponCharacter;

public class Soldier extends Job {
    public Soldier(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    @Override
    public String getRole() {
        return "战士";
    }

    @Override
    public void checkWearWeaponType(String weaponType) throws Exception {
        if(weaponType.equals("中")){
            return;
        }else{
            throw new Exception("战士只可以装备中武器");
        }
    }

    @Override
    public void checkWeaponCharacter(String weaponType, WeaponCharacter weaponCharacter) throws Exception{
        if(weaponType.equals("长") && !weaponCharacter.characterName.equals("")){
            throw new Exception("只有骑士可以发动长武器技能效果");
        }
        if(weaponType.equals("短") && !weaponCharacter.characterName.equals("")){
            throw new Exception("只有刺客可以发动短武器技能效果");
        }else{
            return;
        }
    }
}
