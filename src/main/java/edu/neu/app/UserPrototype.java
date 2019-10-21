// parent class to apply strategy design pattern
class UserPrototype {
    UserBehavior userBehavior;

    protected void disPlay(){
        userBehavior.showRight();
    }

    public void setUserBehavior(UserBehavior userBehavior) {
        this.userBehavior = userBehavior;
    }
}
