package ru.ncedu.schek.cracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PHONE")
public class Phone {
    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID")
    private Model model;

    @OneToMany(mappedBy = "phone", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Picture> pictures;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "COLOR")
    private String color;
    
    @Column(name = "LINK", nullable=false)
    private String link;

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
	public Long getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
    
    
}
