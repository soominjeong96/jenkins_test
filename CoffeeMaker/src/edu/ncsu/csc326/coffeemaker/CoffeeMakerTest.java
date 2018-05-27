package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class CoffeeMakerTest {
	
	CoffeeMaker cm = new CoffeeMaker();
	Recipe r1, r2, r3; //revised 28.03.2018
	
	@Before	
	public void setUp() throws RecipeException {
		
		r1=new Recipe();
		r1.setName("latte");		r1.setPrice("5000");
		r1.setAmtCoffee("3");	r1.setAmtMilk("3");
		r1.setAmtSugar("1");		r1.setAmtChocolate("0");
		
		r2=new Recipe();
		r2.setName("hot chocolate");	r2.setPrice("6000");
		r2.setAmtCoffee("1");		r2.setAmtMilk("4");
		r2.setAmtSugar("3");			r2.setAmtChocolate("4");
		
		//revised 28.03.2018
		r3=new Recipe();
		r3.setName("bomb");			r3.setPrice("10000");
		r3.setAmtCoffee("25");		r3.setAmtMilk("25");
		r3.setAmtSugar("25");		r3.setAmtChocolate("25");
		
	}
	
	/**
	 * add & edit & delete Recipe Test
	 */
	@Test
	public void a_e_d_RecipeTest() {
		Recipe [] rb = cm.getRecipes(); 
		
		//add Test
		cm.addRecipe(r1);
		assertEquals(r1.getName(), "latte");
		assertEquals(r1.getAmtMilk(), 3);
		
		//Edit Test
		cm.addRecipe(r2);
		cm.editRecipe(0, r2);
		assertEquals(rb[0].getName(), r2.getName());
		assertEquals(rb[0].getAmtChocolate(), r2.getAmtChocolate());
		
		//Delete Test
		cm.deleteRecipe(0);
		assertEquals(rb[0].getName(), "");
		
		//revised 28.03.2018
		assertEquals(cm.editRecipe(3, r2), null);
		assertEquals(cm.deleteRecipe(3), null);
	}
	
	@Test
	public void addInventoryTest() throws InventoryException {
		Inventory i = new Inventory();
		cm.addInventory("1", "2", "3", "4");
		assertEquals(i.getCoffee(), 1+15);
		
		//revised 28.03.2018
		assertEquals(cm.checkInventory(), "Coffee: 16\nMilk: 17\nSugar: 18\nChocolate: 19\n" );
	}
	
	
	
	@Test
	public void makeCoffeeTest() {
		cm.addRecipe(r1); cm.addRecipe(r2); cm.addRecipe(r3);
		int change1=cm.makeCoffee(0, 5000);
		assertEquals(change1, 0);
		int change2=cm.makeCoffee(1,  5000);
		assertEquals(change2, 5000);
		int change3=cm.makeCoffee(1,  8000);
		assertEquals(change3, 2000);
		
		//revised 28.03.2018
		int change4=cm.makeCoffee(2,  8000);
		assertEquals(change4, 8000);
		int change5=cm.makeCoffee(1,  1000);
		assertEquals(change5, 1000);
		int change6=cm.makeCoffee(3, 10000);
		assertEquals(change6, 10000);
	}
	

}
