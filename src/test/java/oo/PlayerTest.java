package oo;

import identity.Assassin;
import identity.Knight;
import identity.Player;
import identity.Soldier;
import org.junit.Test;
import weapon.*;
import wc.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldCreatePlayerSuccessfully() {
        Player player = new Player("王二麻子", 100, 10);

        assertThat(player.getName(), is("王二麻子"));
        assertThat(player.getBlood(), is(100));
        assertThat(player.getDamage(), is(10));
    }

    @Test
    public void shouldCanAttackOtherPlayer() {
        Player attacker = new Player("张三", 100, 10);
        Player victim = new Player("李四", 80, 20);

        attacker.attack(victim);

        assertThat(victim.getBlood(), is(80 - attacker.getDamage()));
    }


    @Test
    public void shouldAliveWhenBloodIs0() {
        Player survivor = new Player("王二麻子", 0, 0);

        assertThat(survivor.isAlive(), is(true));
    }

    @Test
    public void shouldAliveWhenBloodGreatThan0() {
        Player survivor = new Player("王二麻子", 1, 0);

        assertThat(survivor.isAlive(), is(true));
    }

    @Test
    public void shouldDeadWhenBloodLessThan0() {
        Player dead = new Player("王二麻子", -1, 0);

        assertThat(dead.isAlive(), is(false));
    }

    @Test
    public void shouldReturnWhoAttackVictimAndHowMuchVictimBleedAndHowMuchBloodLeft() {
        Player attacker = new Player("张三", 100, 10);
        Player victim = new Player("李四", 80, 20);

        assertThat(attacker.attack(victim), is("普通人张三攻击了普通人李四，李四受到了10点攻击力，李四剩余生命：70"));
    }

    @Test
    public void shouldReturnCommonAttackSoldierAndHowMuchSoldierBleedAndHowMuchBloodLeft() {
        Player common = new Player("张三", 100, 10);
        Soldier soldier = new Soldier("李四", 80, 20);
        Armor armor = new Armor("", 0);
        soldier.wearArmor(armor);

        assertThat(common.attack(soldier), is("普通人张三攻击了战士李四，李四受到了10点攻击力，李四剩余生命：70"));
    }

    @Test
    public void shouldReturnCommonAttackSoldierAndHowMuchSoldierDefenceAndHowMuchBloodLeft() {
        Player common = new Player("张三", 100, 10);
        Soldier soldier = new Soldier("李四", 80, 20);
        Armor armor = new Armor("金丝软甲", 2);
        soldier.wearArmor(armor);

        assertThat(common.attack(soldier), is("普通人张三攻击了战士李四，李四用金丝软甲防御，李四受到了8点攻击力，李四剩余生命：72"));
    }

    @Test
    public void shouldReturnSoldierAttackCommonAndHowMuchCommonBleedAndHowMuchBloodLeft() {
        Soldier soldier = new Soldier("张三", 100, 10);
        Player common = new Player("李四", 80, 20);
        WeaponCharacter weaponCharacter = new WeaponCharacter("", 0, 0);
        Weapon weapon = new Weapon("", 0, "中");
        weapon.setWeaponCharacter(weaponCharacter);
        soldier.wearWeapon(weapon);

        assertThat(soldier.attack(common), is("战士张三攻击了普通人李四，李四受到了10点攻击力，李四剩余生命：70"));
    }

    @Test
    public void shouldReturnSoldierAttackCommonWithWeaponAndHowMuchCommonBleedAndHowMuchBloodLeft() {
        Soldier soldier = new Soldier("张三", 100, 10);
        Player common = new Player("李四", 80, 20);
        WeaponCharacter weaponCharacter = new WeaponCharacter("", 0, 0);
        Weapon weapon = new Weapon("优质木棒", 3, "中");
        weapon.setWeaponCharacter(weaponCharacter);
        soldier.wearWeapon(weapon);

        assertThat(soldier.attack(common), is("战士张三用优质木棒攻击了普通人李四，李四受到了13点攻击力，李四剩余生命：67"));
    }

    @Test
    public void shouldReturnSoldierAttackCommonWithPoisonousWeaponAndHowMuchCommonEffectAndHowMuchBloodLeft(){
        Soldier soldier = new Soldier("张三", 100, 10);
        Player common = new Player("李四", 80, 20);
        Poison poison = new Poison("毒性", 2, 0);
        Weapon weapon = new Weapon("优质毒剑", 3, "中");
        weapon.setWeaponCharacter(poison);
        soldier.wearWeapon(weapon);

        assertThat(soldier.attack(common),
                is("战士张三用优质毒剑攻击了普通人李四，李四受到了13点攻击力，李四中毒了，受到2点毒性伤害，李四剩余生命：65"));
    }

    @Test
    public void shouldReturnSoldierAttackCommonWithHammerAndHowMuchCommonEffectAndHowMuchBloodLeft(){
        Soldier soldier = new Soldier("张三", 100, 10);
        Player common = new Player("李四", 80, 20);
        Stun stun = new Stun("眩晕",0, 2);
        Weapon weapon = new Weapon("晕锤", 2, "中");
        weapon.setWeaponCharacter(stun);
        soldier.wearWeapon(weapon);

        assertThat(soldier.attack(common),
                is("战士张三用晕锤攻击了普通人李四，李四受到了12点攻击力，李四晕倒了，李四剩余生命：68"));
    }

    @Test
    public void shouldReturnCommonStateAfterSoldierUseStunWeaponCharacter(){
        Soldier soldier = new Soldier("张三", 100, 10);
        Player common = new Player("李四", 80, 20);
        Stun stun = new Stun("眩晕", 0, 2);
        Weapon weapon = new Weapon("晕锤", 2, "中");
        weapon.setWeaponCharacter(stun);
        soldier.wearWeapon(weapon);
        soldier.attack(common);

        assertThat(common.getState(soldier), is("李四眩晕了，无法攻击，眩晕还剩：1轮"));
    }

    @Test
    public void shouldReturnSoldierAttackCommonWithFullStrengthAndHowMuchCommonEffectAndHowMuchBloodLeft(){
        Soldier soldier = new Soldier("张三", 100, 10);
        Player common = new Player("李四", 80, 20);
        FullStrength fullStrength = new FullStrength("全力一击", 3, 0);
        Weapon weapon = new Weapon("利剑", 5, "中");
        weapon.setWeaponCharacter(fullStrength);
        soldier.wearWeapon(weapon);

        assertThat(soldier.attack(common),
                is("战士张三用利剑攻击了普通人李四，李四受到了15点攻击力，李四受到了全力一击，受到9点加倍伤害，李四剩余生命：56"));
    }

    @Test
    public void shouldReturnAssassinAttackSoldierWithLongWeaponSpearAndThrowException(){
        Assassin assassin = new Assassin("张三", 100, 10);
        Soldier soldier = new Soldier("李四", 80, 20);
        WeaponCharacter weaponCharacter = new WeaponCharacter("", 0, 0);
        Weapon weapon = new Weapon("矛", 3, "长");
        Armor armor = new Armor("", 0);
        weapon.setWeaponCharacter(weaponCharacter);
        assassin.wearWeapon(weapon);
        try {
            assassin.checkWearWeaponType(weapon.type);
            fail("异常抛出测试失败！");
        }catch(Exception e){
            assertThat(e.getMessage(), is("刺客只可以装备中短武器"));
        }
    }

    @Test
    public void shouldReturnKnightAttackAssassinWithMiddleWeaponIceSwordAndThrowException(){
        Knight knight = new Knight("张三", 100, 10);
        Assassin assassin = new Assassin("李四", 80, 20);
        Ice ice = new Ice("冰冻", 3, 0);
        Weapon weapon = new Weapon("冰剑", 3, "中");
        Armor armor = new Armor("", 0);
        weapon.setWeaponCharacter(ice);
        knight.wearWeapon(weapon);
        try {
            knight.checkWeaponCharacter(weapon.type, ice);
            fail("异常抛出测试失败！");
        }catch(Exception e){
            assertThat(e.getMessage(), is("只有战士可以发动中武器技能效果"));
        }
    }

    @Test
    public void shouldReturnAssassinAttackKnightWithEmeiciAndHowMuchKnightBloodLeft(){
        Assassin assassin = new Assassin("张三", 100, 10);
        Knight knight = new Knight("李四", 80, 20);
        Ice ice = new Ice("冰冻", 3, 0);
        Weapon weapon = new Weapon("冰雪峨嵋刺", 3, "短");
        Armor armor = new Armor("", 0);
        weapon.setWeaponCharacter(ice);
        assassin.wearWeapon(weapon);
        knight.wearArmor(armor);

        assertThat(assassin.attack(knight),
                is("刺客张三用冰雪峨嵋刺攻击了骑士李四，李四受到了13点攻击力，李四冻僵了，受到了3点冰冻伤害，李四剩余生命：64"));

    }
}