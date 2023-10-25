public class Skeleton extends Creature{
    public Skeleton(){
        super();
        this.str = 10;
        this.agil = 14;
        this.name = "Skeleton";
        this.health = 30;
        this.gold = (long)(Math.random() * 100);
    }
}

