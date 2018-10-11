package com.fdmgroup.RBC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "fruit")
public class Fruit {

	@Id
	@Column
	private String FruitName;

	@Column
	private double price;

	public String getFruitName() {
		return FruitName;
	}

	public void setFruitName(String fruitName) {
		FruitName = fruitName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Fruit(String fruitName, double price) {
		super();
		FruitName = fruitName;
		this.price = price;
	}

	public Fruit() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FruitName == null) ? 0 : FruitName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fruit other = (Fruit) obj;
		if (FruitName == null) {
			if (other.FruitName != null)
				return false;
		} else if (!FruitName.equals(other.FruitName))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
