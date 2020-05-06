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
    private @Id @GeneratedValue(strategy=TABLE, generator="CUST_GEN") Long id;

    public Expense() {
        System.out.println("generated Id is: " + id);
    }

    public String getAttributes() {
        String output = "userId VARCHAR(250), name VARCHAR(250), cost DOUBLE PRECISION, note " +
                "VARCHAR(250), dateOfPurchase VARCHAR(250)," +
                "id DOUBLE PRECISION PRIMARY KEY";
        return output;
    }
}
