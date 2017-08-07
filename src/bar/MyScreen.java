package bar;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyScreen extends JFrame{
	int price;
	String name;
	Drink drink;
	static Map<String, Drink> drinks;
	public MyScreen() {
		Bar bar = new Bar();
		//JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		List<String> names = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1000,1000));
		
		drinks = bar.drinks;
		
		for(Map.Entry<String, Drink> entry : drinks.entrySet()) {
			name = entry.getKey();
			names.add(name);
			drink = entry.getValue();
			price = drink.price;
			JLabel dLabel = new JLabel(name + " " + price);
			drink.label = dLabel;
			panel.add(dLabel);
		}
		
		setContentPane(panel);
		pack();
		setVisible(true);
	}
	public static void update(String name, int price) {
		
		for(Map.Entry<String, Drink> entry : drinks.entrySet()) {
			String dname = entry.getKey();
			if (dname == name) {
				Drink drink = entry.getValue();
				drink.price = price;
				//System.out.println(drink.label + " AWDAWD");
				if (drink.label != null) {
						drink.label.setText(name + " " + price);
				}
				
			}
			
		}
	}
	public static void main(String[] args) {
		new MyScreen();
	}
	
}
