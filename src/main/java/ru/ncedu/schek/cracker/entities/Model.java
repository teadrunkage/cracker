package ru.ncedu.schek.cracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 24.02.2019.
 */
@Getter
@Setter
@Entity
@Table(name= "MODEL")
public class Model {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId;

    @Column(name = "NAME", length = 64, nullable = false)
    private String  modelName;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Phone> phones;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Picture> pictures;

    @Column(name = "PRICE_MIN", nullable = false)
    private long priceMin;

    @Column(name = "PRICE_MAX", nullable = false)
    private long priceMax;

    public Model(String modelName,long priceMin,long priceMax) {
        this.modelName=modelName;
        this.priceMax=priceMax;
        this.priceMin=priceMin;
    }
    public Model() {
    }

    @Override
    public String toString() {
        return "Model{" +
                "priceMax=" + priceMax +
                ", priceMin=" + priceMin +
                ", phones=" + phones +
                ", modelName='" + modelName + '\'' +
                ", modelId=" + modelId +
                '}';
    }
}
