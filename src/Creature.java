import java.util.Scanner;

abstract public class Creature {
    String name;
    int health;
    int exp = 0;
    int agil, str;
    long gold;
    static boolean isAttacked = false;
    boolean isAlive;
    int lvl;
    public Creature() {
        health = 100;
        agil = 10;
        exp = 0;
        str = 10;
        gold = 10;
        isAlive = true;
    }
    public void setName(){
        System.out.println("Enter a character name");
        Scanner scannerName = new Scanner(System.in);
        name = scannerName.nextLine();
    }
    public int getHealth(){
        return this.health;
    }
    public long getGold(){
        return this.gold;
    }
    public int getAgil(){
        return this.agil;
    }
    public int getExp(){
        return this.exp;
    }
    public int getStr(){
        return this.str;
    }
    public String getName(){
        return this.name;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void attack(Creature whoAttacks, Creature beingAttacked){
        double chance = Math.random() * 100;
        double crit = Math.random();

            if (whoAttacks.getAgil() * 3 > chance){
                beingAttacked.setHealth(beingAttacked.getHealth() - whoAttacks.getStr());
                System.out.println(whoAttacks.getName() + " hits " + beingAttacked.getName() + " for " + whoAttacks.getStr() + "HP!");
                System.out.println(beingAttacked.getName() + " has " + beingAttacked.getHealth() + " left.");
                isAttacked = true;
            }else {
                System.out.println(whoAttacks.getName() + " miss!");
            }
        if (isAttacked && crit > 0.8){
            beingAttacked.health -= whoAttacks.getStr();
            System.out.println("Critical hit!!! Ra-ra-ra-RAMPAGE!" + whoAttacks.getName() +" hit " + beingAttacked.getName() + " with double damage! " );
        }
    isAttacked = false;
    }
}
