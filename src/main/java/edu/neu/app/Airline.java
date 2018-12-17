package edu.neu.app;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "Airline")
public class Airline implements BasicFunction{
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "profit")
    private int profit;

    @OneToMany
    private List<Ticket> list = new ArrayList<>();

    public Airline() {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
    }

    public Airline(String name) {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
        this.name = name;
        this.profit = 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public List<Ticket> getList() {
        return list;
    }

    public void setList(List<Ticket> list) {
        this.list = list;
    }

}
