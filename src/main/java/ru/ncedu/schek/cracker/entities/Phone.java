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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long phoneId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID")
    private Model model;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "COLOR")
    private String color;
    
    @Column(name = "LINK", nullable=true)
    private String link;
    
    @Lob
    @Column(name = "PICTURE_LINK", nullable=true)
    private String picturelink;

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
	public String getPicturelink() {
		return picturelink;
	}
	public void setPicturelink(String picturelink) {
		this.picturelink = picturelink;
	}
    
	
    
}
