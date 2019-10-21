class UserPrototype {
    UserBehavior userBehavior;

    protected void disPlay(){
        userBehavior.showRight();
    }

    public void setUserBehavior(UserBehavior userBehavior) {
        this.userBehavior = userBehavior;
    }
}
