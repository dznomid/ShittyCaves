import java.util.Scanner;

public class World extends Thread{
    private boolean willToFight = true;//этот параметр будет оставаться правдой до тех пор пока игрок не нажмёт кнопку на выход
    private Hero hero;
    private Trader trader;
    int lastLvl;
    int newLvl;
    boolean lvlUpped;
    public World() {
        this.hero = new Hero(100, 150, 20, 15); 
        this.trader = new Trader();
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        String dest;
        System.out.println("Here is where your story begins, " + hero.getName());
        while (willToFight && trader.isAlive && hero.isAlive){
            System.out.println("Where you want to go? Print a number");
            System.out.println("1.To the Trader 2.Into a Darkwoods 3.To exit(You will end your adventure)");
            dest = scanner.nextLine();
            switch (dest){
                case "1":
                    System.out.println("Oh, Hola, Amigo! What do U want?");
                    System.out.println("1.Buy a potion 2.Speak with Trader 3.Attack a Trader 4.Leave   (Write a number)");
                    Scanner scanner2 = new Scanner(System.in);
                    String choose = scanner2.nextLine();
                    while (!choose.equals("4") && trader.isAlive) {
                        switch (choose) {
                            case "1":
                                trader.sellPotion(hero);
                                break;
                            case "2":
                                trader.speech();
                                break;
                            case "3":
                                Fight fight = new Fight(hero, trader);
                                fight.start();
                                try {
                                    fight.join();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                if (!hero.isAlive){
                                    choose = "4";
                                }
                                break;
                            default:
                                System.out.println("What? (You need to insert a number)");
                                choose = scanner2.nextLine();
                                continue;
                        }
                        if(trader.getHealth() <= 0){
                            trader.isAlive = false;
                        }
                        if (!hero.isAlive){break;}
                        if(trader.isAlive){
                            System.out.println("1.Buy a potion 2.Speak with Trader 3.Attack a Trader 4.Leave   (Write a number)");
                            choose = scanner2.nextLine();
                        } else if(!trader.isAlive){
                            choose = "4";
                            break;
                        }

                    }

                    if (trader.isAlive){
                        System.out.println("Bye-bye Amigo, Ne pro-pa-die");
                    }
                    break;
                case "2":
                    lastLvl = (int)Math.floor(hero.getLvl());
                    System.out.println("You are in the Darkwoods. 1.Keep going 2.Go back 3.Use a healing potion");
                    Scanner scanner3 = new Scanner(System.in);
                    String woodDest = scanner3.nextLine();
                    while (!woodDest.equals("2")){
                        switch(woodDest){
                            case"1":
                                double enemy = Math.random();
                                if (enemy >= 0.5){
                                    Zombie zombie = new Zombie();
                                    System.out.println("You see a " + zombie.getName() + " and attack him.");
                                    Fight fight = new Fight(hero, zombie);
                                    fight.start();
                                    try {
                                        fight.join();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    Skeleton skeleton = new Skeleton();
                                    System.out.println("You see a " + skeleton.getName() + " and attack him.");
                                    Fight fight = new Fight(hero, skeleton);
                                    fight.start();
                                    try {
                                        fight.join();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                if (!hero.isAlive){
                                    if (hero.getNumberOfPotions() > 0){
                                        hero.useAPotion();
                                        hero.health += 70;
                                        hero.isAlive = true;
                                        if (hero.getHealth() > 150){
                                            hero.health = 150;
                                        }
                                        System.out.println("you avoided death by healing with a potion outside of fight. You have " + hero.getNumberOfPotions() + " potions left");
                                    }else{
                                        System.out.println("YOU DIED");
                                        hero.isAlive = false;
                                        break;
                                    }
                                }
                                newLvl = (int)Math.floor(hero.getLvl());
                                if (newLvl > lastLvl && hero.isAlive){
                                    System.out.println("I reached a level " + (int)Math.floor(hero.getLvl()));
                                    System.out.println("1. upgrade strait 2. Upgrade agility");
                                    Scanner scanner4 = new Scanner(System.in);
                                    String lvlUp = scanner4.nextLine();
                                    lvlUpped = false;
                                    while (!lvlUpped){
                                        switch (lvlUp) {
                                            case "1":
                                                lvlUpped = true;
                                                hero.str += 7;
                                                System.out.println("Your strait is now on " + hero.getStr() + " points");
                                                lastLvl = (int)Math.floor(hero.getLvl());
                                                break;
                                            case "2":
                                                lvlUpped = true;
                                                hero.agil += 7;
                                                System.out.println("Your agility is now on " + hero.getAgil() + " points");
                                                lastLvl = (int)Math.floor(hero.getLvl());
                                                break;
                                            default:
                                                System.out.println("You need to choose 1. upgrade strait 2. Upgrade agility");
                                                lvlUp = scanner4.nextLine();
                                                continue;
                                        }

                                    }
                                }break;
                            case "3":
                                if (hero.getNumberOfPotions() > 0){
                                    hero.useAPotion();
                                    hero.health += 70;
                                    if (hero.getHealth() > 150){
                                        hero.health = 150;
                                    }
                                    System.out.println("You've used a healing potion. Now you have " + hero.getHealth() + "HP and " + hero.getNumberOfPotions() + " potions left.");
                                }else {
                                    System.out.println("You have no healing potions...");
                                }
                                break;
                        }
                        if (hero.isAlive){
                            System.out.println("1. Keep going 2. Go out from woods 3. Use a healing potion");
                        }else {break;}
                        woodDest = scanner3.nextLine();
                    }
                    break;
                case "3":
                    System.out.println("Your adventure ends.");
                    willToFight = false;
                    break;
                default:
                    System.out.println("Please enter 1. 2. or 3.");
                    break;
//                if (!hero.isAlive){break;}
            }
        }
        if (!willToFight){
            System.out.println("You shamefully gave up. Your fathers will not be proud of you...");
        }else {
            if (!hero.isAlive){
                System.out.println("You ended your adventure in a pool of blood on the ground. At least you died like a hero.(Most likely)");
            } else {
                if(!trader.isAlive){
                    System.out.println("You won. You've done what no one before you could do. You defeated the demon Velial and rid this land of evil. You are not just a hero, but you are a champion. Honor and glory to you and your family you, " + hero.getName() + ".");
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println("THANKS FOR PLAYING!");
                }
            }
        }
    }
}
