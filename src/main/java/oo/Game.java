package oo;

import identity.Player;

import static java.lang.String.format;

public class Game {
    private ConsolePrinter consolePrinter;

    public Game(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void fight(Player firstPlayer, Player secondPlayer) {
        Player attacker = firstPlayer;
        Player victim = secondPlayer;
        Player loser = attacker;

        while (attacker.isAlive() && victim.isAlive()) {
            consolePrinter.print(format("%s\n", attacker.attack(victim)));

            if(attacker.pauseRoundDamage <= 0){
                loser = victim;
                victim = attacker;
                attacker = loser;
            }else{
                if(victim.isAlive()) {
                    //System.out.println("李四" + victim.getState(attacker));
                    consolePrinter.print(format("%s\n", victim.getState(attacker)));
                    attacker.setPauseRoundDamage();
                }
            }

        }

        consolePrinter.print(format("%s被打败了", loser.getName()));
    }
}
