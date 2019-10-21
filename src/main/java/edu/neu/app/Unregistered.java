// instantiate one strategy
public class Unregistered implements UserBehavior {
    // another strategy
    @Override
    public void showRight() {
        System.out.println("Registered users have limited rights");
    }
}
