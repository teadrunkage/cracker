package ru.ncedu.schek.cracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Admin on 24.02.2019.
 */
@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PICTURE")
public class Picture {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COLOR")
    private String color;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PHONE_ID", nullable = true)
    private Phone phone;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model model;

    @Lob
    @Column(name = "PICTURE", columnDefinition = "BLOB", nullable = false)
    private byte[] bytes;

    public Picture(Phone phone, String color, String name, byte[] bytes) {
        this.color = color;
        this.phone = phone;
        this.name = name;
        this.bytes = bytes;
    }

    public Picture(Model model, String color, String name, byte[] bytes) {
        this.color = color;
        this.model = model;
        this.name = name;
        this.bytes = bytes;
    }

    public Picture() {
    }
}
