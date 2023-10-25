public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.start();
        try {
            world.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}