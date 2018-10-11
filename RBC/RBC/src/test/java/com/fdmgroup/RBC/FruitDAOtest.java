package com.fdmgroup.RBC;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


import com.fdmgroup.dao.FruitDAO;

public class FruitDAOtest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RBC");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		entityManager.createQuery("DELETE FROM fruit").executeUpdate();
		entityTransaction.commit();
	}

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RBC");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		entityManager.createQuery("DELETE FROM fruit").executeUpdate();
		entityTransaction.commit();
	}

	@Test
	public void test_listAllFruitsReturnsEmptyListWhenNoFruitsHaveBeenAdded() {
		FruitDAO fruit = new FruitDAO();
		List<Fruit> fruits = fruit.listAllFruits();
		assertEquals(0, fruits.size());
	}
	
	@Test
	public void test_WhenFruitWhichHasNotBeenCreatedIsEnteredThrowError() {
		Fruit fruitClass = new Fruit("Apples", 0.50);
		FruitDAO fruitdao = new FruitDAO();
		fruitClass.getFruitName();
		fruitdao.getFruit("Apple");
		assertEquals(null, fruitdao.getFruit("Apple"));
  }
	
	@Test
	public void test_UpdateFruitName() {
		Fruit fruitClass = new Fruit("Apples", 0.50);
		FruitDAO fruitdao = new FruitDAO();
		fruitdao.addFruit(fruitClass);
		fruitClass.setFruitName("Oranges");
		assertEquals(fruitClass, fruitdao.listAllFruits().get(0));
	}
	
	@Test
	public void test_AddMultipleFruitAndRemoveMultipleFruit(){
		Fruit fruitClass = new Fruit("Apples", 0.50);
		Fruit fruitClass1 = new Fruit("Lemons", 0.50);
		Fruit fruitClass2 = new Fruit("Pears", 0.50);
		Fruit fruitClass3 = new Fruit("Apples", 0.50);
		FruitDAO fruitdao = new FruitDAO();
		fruitdao.addFruit(fruitClass);
		fruitdao.addFruit(fruitClass1);
		fruitdao.addFruit(fruitClass2);
		fruitdao.addFruit(fruitClass3);
		fruitdao.removeFruit(fruitClass.getFruitName());
		fruitdao.removeFruit(fruitClass2.getFruitName());
		int listResult = fruitdao.listAllFruits().size();
		assertEquals(1, listResult);		
	}
	
	@Test
	public void test_WhenANonExistenFruitIsUpdatedTheSizeOfTheListDoesntChangeAndReturns0(){
		Fruit fruitClass = new Fruit("Apples", 0.50);
		FruitDAO fruitdao = new FruitDAO();
		fruitdao.updateFruit(fruitClass);
		assertEquals(0, fruitdao.listAllFruits().size());
	   }
	
	
	
}
