package edu.neu.app;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "User")
public class User extends UserPrototype implements BasicFunction {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "money")
    private int money;

    @OneToMany
    private List<Ticket> list = new ArrayList<>();

    public User() {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
    }

    public User(String name, int age, int money, UserBehavior userBehavior) {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
        this.name = name;
        this.age = age;
        this.money = money;
        setUserBehavior(userBehavior);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Ticket> getList() {
        return list;
    }

    public void setList(List<Ticket> list) {
        this.list = list;
    }
    
    display();
}
