package com.shop.beans;

public class Product {
	private long id_product;
	private String name;
	private int Qte;
	private String Category;
	private int price;
	private String photo;
	private String description;
	
	
	
	
	@Override
	public String toString() {
		return "Product [id_product=" + id_product + ", name=" + name + ", Qte=" + Qte + ", Category=" + Category
				+ ", price=" + price + ", linkPhotoAvant=" + photo + ", Description=" + description
				+"]";
	}
	
	
	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getId_product() {
		return id_product;
	}
	public void setId_product(long id_product) {
		this.id_product = id_product;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQte() {
		return Qte;
	}
	public void setQte(int qte) {
		Qte = qte;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
