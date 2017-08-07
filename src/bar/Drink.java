package bar;

import javax.swing.JLabel;

public class Drink {
	public int price;
	public String name;
	public JLabel label;
	
	public Drink(String name, int price) {
		this.name = name;
		this.price = price;
		this.label = null;
	}
}
