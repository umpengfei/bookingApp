class UserPrototype {
    UserBehavior userBehavior;

    protected void disPlay(){
        userBehavior.fly();
    }

    public void setUserBehavior(UserBehavior userBehavior) {
        this.userBehavior = userBehavior;
    }
}
