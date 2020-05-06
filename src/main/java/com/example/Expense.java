package com.example;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.TABLE;

@Data
@Entity
public class Expense {
    private String userId;
    private String name;
    private double cost;
    private String note;
    private String dateOfPurchase;
    private @Id int id;
    private static int totalId = 0;

    public Expense() {
        this.id = totalId++;
        System.out.println("generated Id is: " + id);
    }

    public Expense(String userId, String name, double cost, String note, String dateOfPurchase) {
        this.userId = userId;
        this.name = name;
        this.cost = cost;
        this.note = note;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getAttributes() {
        String output = "userId VARCHAR(250), name VARCHAR(250), cost DOUBLE PRECISION, note " +
                "VARCHAR(250), dateOfPurchase VARCHAR(250)," +
                "id INTEGER PRIMARY KEY";
        return output;
    }
}
