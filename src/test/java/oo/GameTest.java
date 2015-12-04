package oo;

import identity.Player;
import identity.Soldier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import weapon.Armor;
import wc.Poison;
import wc.Stun;
import weapon.Weapon;


import static org.mockito.Mockito.inOrder;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    private Game game;
    @Mock
    private ConsolePrinter consolePrinter;
    InOrder inOrder;

    @Before
    public void setUp() {
        inOrder = inOrder(consolePrinter);
        game = new Game(consolePrinter);
    }

    @Test
    public void shouldSecondPlayerLoseWhenFirstPlayerIsPowerful() {
        Player firstPlayer = new Player("张三", 10, 10);
        Player secondPlayer = new Player("李四", 9, 10);

        game.fight(firstPlayer, secondPlayer);

        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了普通人李四，李四受到了10点攻击力，李四剩余生命：-1\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldFirstPlayerLoseWhenSecondPlayerIsPowerful() {
        Player firstPlayer = new Player("张三", 10, 8);
        Player secondPlayer = new Player("李四", 20, 9);

        game.fight(firstPlayer, secondPlayer);

        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了普通人李四，李四受到了8点攻击力，李四剩余生命：12\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了普通人张三，张三受到了9点攻击力，张三剩余生命：1\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了普通人李四，李四受到了8点攻击力，李四剩余生命：4\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了普通人张三，张三受到了9点攻击力，张三剩余生命：-8\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }

    @Test
    public void shouldReturnSoldierBeatCommonWithWeaponAndPosion(){
        Soldier soldier = new Soldier("张三", 20, 8);
        Player common = new Player("李四", 15, 6);
        Poison poison = new Poison("毒性", 2, 0);
        Weapon weapon = new Weapon("优质毒剑", 3, "中");
        Armor armor = new Armor("金丝软甲", 2);
        weapon.setWeaponCharacter(poison);
        soldier.wearWeapon(weapon);
        soldier.wearArmor(armor);
        game.fight(soldier, common);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用优质毒剑攻击了普通人李四，李四受到了11点攻击力，李四中毒了，受到2点毒性伤害，李四剩余生命：2\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了战士张三，张三用金丝软甲防御，张三受到了4点攻击力，张三剩余生命：16\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用优质毒剑攻击了普通人李四，李四受到了11点攻击力，李四中毒了，受到2点毒性伤害，李四剩余生命：-11\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    //some problem exist，不能打印出Game第30行的内容......
    @Test
    public void shouldReturnSoldierBeatCommonWithWeaponAndStun(){
        Soldier soldier = new Soldier("张三", 20, 8);
        Player common = new Player("李四", 25, 6);
        Stun stun = new Stun("眩晕", 0, 2);
        Weapon weapon = new Weapon("晕锤", 2, "中");
        Armor armor = new Armor("", 0);
        weapon.setWeaponCharacter(stun);
        soldier.wearWeapon(weapon);
        soldier.wearArmor(armor);
        game.fight(soldier, common);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了10点攻击力，李四晕倒了，李四剩余生命：15\n");
        inOrder.verify(consolePrinter, times(1)).print("李四眩晕了，无法攻击，眩晕还剩：1轮");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了10点攻击力，李四晕倒了，李四剩余生命：5\n");
        inOrder.verify(consolePrinter, times(1)).print("李四眩晕了，无法攻击，眩晕还剩：2轮");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了10点攻击力，李四晕倒了，李四剩余生命：-5\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }
}