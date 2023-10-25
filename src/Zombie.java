public class Zombie extends Creature{
    public Zombie(){
        super();
        this.str = 15;
        this.agil = 10;
        this.name = "Zombie";
        this.health = 80;
        this.gold = (long)(Math.random() * 100);
    }
}
