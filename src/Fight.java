public class Fight extends Thread{

    private Hero fighterHero;
    private Creature fighterOpponent;
    boolean someoneDead;
    public Fight(Hero fighterHero, Creature fighterOpponent){           // Метод чтобы передать учасников метода
        this.fighterHero = fighterHero;                                 // attack из класса Creature в поток боя Fight
        this.fighterOpponent = fighterOpponent;
    }

    public void run(){
        someoneDead = false;
        double gfm = Math.random() * 100;
        int goldFromMonster = (int)Math.floor(gfm);
        while (!someoneDead){

            fighterHero.attack(fighterHero, fighterOpponent);
            if (fighterHero.getHealth() <= 0 || fighterOpponent.getHealth() <=0){
                someoneDead = true;
            }
            if(!someoneDead) {
                fighterOpponent.attack(fighterOpponent, fighterHero);
                if (fighterHero.getHealth() <= 0 || fighterOpponent.getHealth() <= 0) {
                    someoneDead = true;
                }
            }
            if (fighterHero.getHealth() <= 0){
                fighterHero.isAlive = false;
                System.out.println("YOU TOOK FATAL DAMAGE.");
                return;
            }
            if (fighterOpponent.getHealth() <= 0){
                fighterOpponent.isAlive = false;
                System.out.println(fighterOpponent.getName() + " is dead. You got 30 exp and " + goldFromMonster + " gold! You have " + fighterHero.getHealth() + " HP left! And " + fighterHero.getGold() + " gold.");
                fighterHero.exp += 30;
                fighterHero.gold += goldFromMonster;
                return;
            }
        }

    }

}
