package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.RBC.Fruit;

public class FruitDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("RBC");
	// entity manager will be used in all interactions with database
	EntityManager entityManager = emf.createEntityManager();
	// entity transaction will be used any time a row is inserted, added, or updated
	EntityTransaction entityTransaction = entityManager.getTransaction();

	// Allows you to retrieve a list from the database
	public List<Fruit> listAllFruits() {
		TypedQuery<Fruit> queryFruit = entityManager.createQuery("Select f from fruit f", Fruit.class);
		List<Fruit> listFruit = queryFruit.getResultList();
		return listFruit;
	}

	// Allows you to get Fruit from database by fruitName
	public Fruit getFruit(String fruitName) {
		Fruit fruit = entityManager.find(Fruit.class, fruitName);
		return fruit;
	}

	// Allows you to add fruit by fruitName to the database
	public void addFruit(Fruit fruitName) {
		entityTransaction.begin();
		entityManager.persist(fruitName);
		entityTransaction.commit();

	}

	// Allows you to remove Fruit from database by FruitName
	public void removeFruit(String fruitName) {
		Fruit fruit = entityManager.find(Fruit.class, fruitName);

		entityTransaction.begin();
		entityManager.remove(fruitName);
		entityTransaction.commit();

	}

	// Allows you to update the fruitClass by FruitName
	public void updateFruit(Fruit fruitClass) {
		Fruit upFruit = entityManager.find(Fruit.class, fruitClass.getFruitName());

		if (upFruit != null) {
			entityTransaction.begin();
			entityManager.merge(fruitClass);
			entityTransaction.commit();
		}

	}

}
