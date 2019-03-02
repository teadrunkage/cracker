package ru.ncedu.schek.cracker.entities;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
>>>>>>> ffca83e0ab6ec585be839eecfced318753874092

import javax.persistence.*;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
    
    
}
