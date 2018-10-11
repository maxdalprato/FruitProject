package com.fdmgroup.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.RBC.Fruit;
import com.fdmgroup.dao.FruitDAO;
import java.math.*;

@Controller
public class HomeController {

	//Hash maps for the basket and fruit basket
	private HashMap<String, Integer> basket = new HashMap<String, Integer>();
	private HashMap<Fruit, Integer> basketFruit = new HashMap<Fruit, Integer>();
	double totalPrice = 0.0;

	
	//Syncs the two hash maps together 
	public void syncHashMaps() {
		for (String eachItemId : basket.keySet()) {
			Integer quantity = basket.get(eachItemId);
			FruitDAO fruitDAO = new FruitDAO();
			Fruit fruit = fruitDAO.getFruit(eachItemId);
			basketFruit.put(fruit, quantity);
		}
	}

	
	//A request mapping which allows the user to add a fruit to their basket
	@RequestMapping(value = "addItemToBasket/{fruitName}")
	public String addProduct(@PathVariable String fruitName, Model model, HttpServletRequest request) {
		FruitDAO fruitdao = new FruitDAO();
		Fruit fruit = fruitdao.getFruit(fruitName);
		HttpSession session = request.getSession();
		if (basket.containsKey(fruitName)) {
			int quantity = basket.get(fruitName);
			basket.put(fruitName, quantity + 1);
			totalPrice += fruit.getPrice();
			double totalPriceUnrounded = totalPrice;
			totalPrice = Math.round(totalPriceUnrounded * 100.0) / 100.0;
			session.setAttribute("totalPrice", totalPrice);
		} else {
			basket.put(fruitName, 1);
			totalPrice += fruit.getPrice();
			double totalPriceUnrounded = totalPrice;
			totalPrice = Math.round(totalPriceUnrounded * 100.0) / 100.0;
			session.setAttribute("totalPrice", totalPrice);
		}
		System.out.println(basket);
		syncHashMaps();
		model.addAttribute("basketFruit", basketFruit);
		return "basket";
	}

	
	//A request mapping which allows the user to remove a fruit from their basket
	@RequestMapping(value = "removeItemFromBasket/{fruitName}")
	public String removeProducts(@PathVariable String fruitName, Model model, HttpServletRequest request) {

		FruitDAO fruitdao = new FruitDAO();
		Fruit fruit = fruitdao.getFruit(fruitName);
		HttpSession session = request.getSession();
		if (basket.get(fruitName) == (null)) {
			request.setAttribute("message", "You cannot remove a fruit if it is not in the basket to begin with!");
			return "basket";
		}
		if (basket.containsKey(fruitName) && basket.get(fruitName) > 1) {
			
			int quantity = basket.get(fruitName);
			
			basket.put(fruitName, quantity - 1);
			totalPrice -= fruit.getPrice();
			
			double totalPriceUnrounded = totalPrice;
			totalPrice = Math.round(totalPriceUnrounded * 100.0) / 100.0;
			
			session.setAttribute("totalPrice", totalPrice);
			
		} else if (basket.get(fruitName) == 1) {

			basket.put(fruitName, 0);
			basketFruit.put(fruit, 0);

			totalPrice -= fruit.getPrice();
			double totalPriceUnrounded = totalPrice;
			totalPrice = Math.round(totalPriceUnrounded * 100.0) / 100.0;
			session.setAttribute("totalPrice", totalPrice);

			basket.remove(fruitName);
			basketFruit.remove(fruitName);

			syncHashMaps();
			
		} else if (basket.get(fruitName) == 0) {
			request.setAttribute("message", "empty");
		}

			System.out.println(basket);
			syncHashMaps();
			model.addAttribute("basketFruit", basketFruit);
			return "basket";

	}

	
	//Adds for total price of each item in the basket
	public double totalPrice() {
		for (Entry<Fruit, Integer> fruit : basketFruit.entrySet()) {
			double price = fruit.getKey().getPrice();
			int quantity = fruit.getValue();
			totalPrice += price * quantity;
		}
		return totalPrice;
	}

	
	//Provides a list of all fruits located in database
	@RequestMapping("listFruit")
	public String listFruit(Model model) {
		FruitDAO fruitdao = new FruitDAO();
		List<Fruit> listFruit = fruitdao.listAllFruits();
		model.addAttribute("listFruit", listFruit);
		return "listFruit";
	}

	//Allows the user to checkout their current basket
	@RequestMapping(value = "checkOut")
	public String enrolPage(Model model) {
		model.addAttribute("basketFruit", basketFruit);
		return "checkOut";
	}

	//Where the first page will load
	@RequestMapping(value = "/")
	public String loginHandler(Model model) {
		model.addAttribute("fruit", new Fruit());
		return "fruit";
	}
}
