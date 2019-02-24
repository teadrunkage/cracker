package ru.ncedu.schek.cracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 24.02.2019.
 */
@Data
@Entity
@Table(name = "PHONE")
public class Phone {
    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model model;

    @OneToMany(mappedBy = "phones", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Picture> pictures;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "COLOR")
    private String color;

    public Phone( Model model, long price, String color) {
        this.model = model;
        this.price = price;
        this.color = color;
    }
    public Phone(Model model, String color) {
        this.model = model;
        this.color = color;
    }
    public Phone (){
    }

    @Override
    public String toString() {
        return "Phone{" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", model=" + model +
                ", phoneId=" + phoneId +
                '}';
    }
}
