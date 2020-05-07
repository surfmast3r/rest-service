package com.ingsw.restservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accommodations")
public class Accommodation {
	
	@Id
	private long id;
	
	private String description;
	private String name ;
	private String logourl;
	private Double latitude;
	private Double longitude;
	private String city;
	private String address;
	private Float rating;
	private String category;
	private String subCategory;
	private String images;

	public Accommodation() {
	
	}

	private Accommodation(Builder accommodationBuilder) {
		this.id = accommodationBuilder.id;
		this.description = accommodationBuilder.description;
		this.name = accommodationBuilder.name;
		this.address=accommodationBuilder.address;
		this.logourl = accommodationBuilder.logoUrl;
		this.city = accommodationBuilder.city;
		this.latitude = accommodationBuilder.latitude;
		this.longitude = accommodationBuilder.longitude;
		this.category = accommodationBuilder.category;
		this.subCategory = accommodationBuilder.subCategory;
		this.rating = accommodationBuilder.rating;
		this.images = accommodationBuilder.images;
				
	}

	

	/*getter*/
	public String getImages() {
		return images;
	}
	
	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLogoUrl() {
		return logourl;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public Double getLatitude() {
		return latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public Float getRating() {
		return rating;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getSubCategory() {
		return subCategory;
	}

	
	/*Setter*/
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}	

	public void setCategory(String category) {
		this.category = category;
	}

	/*Builder*/
	static class Builder {

		private long id;
		private String name ;
		private Double latitude;
		private Double longitude;
		private String logoUrl;
		private String city;
		private String address;
		private Float rating;
		private String category; 
		private String subCategory; 
		private String description;
		private String images;
		
		public Builder setId(long id) {
			this.id = id;
			return this;
		}
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setLogoUrl(String logoUrl) {
			this.logoUrl = logoUrl;
			return this;
		}
		public Builder setCity(String city) {
			this.city = city;
			return this;
		}
		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}
		public Builder setLatitude(Double latitude) {
			this.latitude = latitude;
			return this;
		}
		public Builder setLongitude(Double longitude) {
			this.longitude = longitude;
			return this;
		}
		public Builder setRating(Float rating) {
			this.rating = rating;
			return this;
		}
		public Builder setCategory(String category) {
			this.category = category;
			return this;
		}
		public Builder setSubCategory(String subCategory) {
			this.subCategory = subCategory;
			return this;
		}
		 public Builder setImages(String images) {
	            this.images = images;
	            return this;
	        }
		
		public Accommodation build() {
            return new Accommodation(this);
        }
	}
	
}
