package com.epam.task1;

public class Coffee {
    private String physicalState;
    private String kind;
    private double amount;
    private double packAmount;
    private double cost;
    private double weight;

    public Coffee(String physicalState, String kind, double amount, double packAmount, double cost, double weight){
     this.physicalState=physicalState;
     this.kind=kind;
     this.amount=amount;
     this.packAmount=packAmount;
     this.cost=cost;
     this.weight=weight;
    }

    public double getAmount() {
        return amount;
    }

    public double getPackAmount() {
        return packAmount;
    }

    public double getCost() {
        return cost;
    }

    public double getWeight() {
        return weight;
    }

    public String getKind() {
        return kind;
    }

    public String getPhysicalState() {
        return physicalState;
    }
}
