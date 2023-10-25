import java.util.Scanner;

public class Trader extends Creature{
    public boolean isAlive;
    public Trader(){
        this.str = 100;
        this.agil = 33;
        this.health = 100;
        this.name = "Trader"; // Velial >:) если игрок решит убить торговца и победит, то игра закончится, так как это скрытый антагонист
        this.gold = 5000;
        isAlive = true;
    }
    public void sellPotion(Hero hero){
        if (hero.getGold() > 200) {
            hero.gold -= 200;
            Hero.addPotion(hero);
            super.gold += 200;
            System.out.println("You bought 1 healing potion from Trader. He wish you a good luck in your adventures");
            System.out.println("Now you have " + hero.getNumberOfPotions() + " potions.");
        } else {
            System.out.println("You have no money, Amigo. You have only " + hero.getGold() + " gold. And it costs 200.");
        }
    }
    public void speech(){
        double sp = Math.random();
        if (sp <= 0.3){
            System.out.println("Buy-buy Amigo, po-ku-pai");
        }
        if (sp >0.3 && sp <= 0.6){
            System.out.println("There's no monsters or ahh... demons, just potions and gold. They're all in the woods, Amigo! C'mon!");
        }
        if (sp > 0.6 ){
            System.out.println("Stop babbling! Buy a potion and go chop up some evil, Amigo.");
        }
    }
//    public void whatDoUWant(){
//        System.out.println("Oh, Hola, Amigo! What do U want?");
//        System.out.println("1.Buy a potion 2.Attack a Trader 3.Leave   (Write a number)");
//        Scanner scanner = new Scanner(System.in);
//        String choose = scanner.nextLine();
//        switch (choose){
//            case 1:
//
//        }
//    }

}
