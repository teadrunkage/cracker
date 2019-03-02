package ru.ncedu.schek.cracker.entities;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

>>>>>>> ffca83e0ab6ec585be839eecfced318753874092
import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
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
	public Long getModelId() {
		return modelId;
	}
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Set<Phone> getPhones() {
		return phones;
	}
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public long getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(long priceMin) {
		this.priceMin = priceMin;
	}
	public long getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(long priceMax) {
		this.priceMax = priceMax;
	}
    
    
}
