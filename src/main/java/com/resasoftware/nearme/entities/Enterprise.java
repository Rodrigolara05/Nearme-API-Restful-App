package com.resasoftware.nearme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="enterprises")
@NamedQueries({
	@NamedQuery(name="Enterprise.enterpriseByCategory",
			query="select e from Enterprise e where e.categoryId=?1")
})
public class Enterprise {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Size(min=4, message="The name must have a minimum of 4 characters")
	@Column(name="name", nullable=false, length=100)
	private String name;
	@Size(min=10, message="The description must have a minimum of 10 characters")
	@Column(name="desription", nullable=false, length=300)
	private String description;
	@Size(min=10, message="The location must have a minimum of 10 characters")
	@Column(name="location", nullable=false, length=100)
	private String location;
	@Size(min=10, message="The location must have a minimum of 10 characters")
	@Column(name="longitude", nullable=false, length=50)
	private String longitude;
	@Size(min=10, message="The location must have a minimum of 10 characters")
	@Column(name="latitude", nullable=false, length=50)
	private String latitude;
	@Size(min=10, message="The image must have a minimum of 10 characters")
	@Column(name="image", nullable=false, length=300)
	private String image;
	@Size(min=5, message="")
	@Column(name="star", nullable=false, length=1000)
	private String star;
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category categoryId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Category getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
}
