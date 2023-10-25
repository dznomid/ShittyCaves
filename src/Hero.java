import javax.net.ssl.SSLContext;
import java.util.Scanner;

import static java.lang.System.in;

public class Hero extends Creature {
    public static int numberOfPotions;
    public Hero(int health, long gold, int agil, int str){
        super();
        this.health = health;
        this.gold = gold;
        this.agil = agil;
        this.str = str;
        setName();
        numberOfPotions = 1;
        isAlive = true;
    };
    public int getLvl(){
        int lvl = this.exp / 100;
        //  lvl = (int)Math.floor(lvl);
        return lvl;
    }
    public int getNumberOfPotions(){
        return numberOfPotions;
    }
    public void useAPotion(){
        Hero.numberOfPotions =  numberOfPotions - 1;
    }

    public static void addPotion(Hero hero){
        numberOfPotions += 1;
    }

}
