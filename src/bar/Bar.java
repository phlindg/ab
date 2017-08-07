package bar;


import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




/*TODO:
 * 	Fixa så att knapparna bara skapas en gång <--- DONE?
 * 	Fixa så att siffror kommer upp när man klickar ppå en köpknapp
 * 	Fixa så att den sparar ner sakerna.
 * 
 * */
public class Bar extends JFrame implements ActionListener{
	Map<String, Drink> drinks = new HashMap<String, Drink>();
	Drink drink;
	static MyScreen screen;
	Properties properties;
	public int totalPrice;
	public int price;
	public int cardShow;
	List<String> names = new ArrayList<String>();
	JPanel cards, main, createPanel, numberPane, reciept;
	JButton create, mainMenu, createDrink, removeDrink,save,load, ok;
	JButton one,two,three,four,five,six,seven,eight,nine;
	JTextField drinkName;
	String command = " ";
	
	public Bar() {
		this.totalPrice = 500;
		this.drinks = drinks;
		Drink vodkaRB = new Drink("VodkaRB", 100);
		Drink tequila = new Drink("Tequila", 100);
		Drink rumCoke = new Drink("Rumcoke", 100);
		Drink pinaColada = new Drink("PinaColada", 100);
		Drink whiteRus = new Drink("White Russian", 100);
		drinks.put("VodkaRB", vodkaRB);
		drinks.put("Tequila", tequila);
		drinks.put("Rumcoke", rumCoke);
		drinks.put("PinaColada", pinaColada);
		drinks.put("White Russan", whiteRus);
		
		setLayout(new FlowLayout());
		setSize(2000,1000);
		setTitle("BAR");
		setResizable(false);
		Dimension dim = new Dimension(200,200);
		drinkName = new JTextField(10);
		
		create = new JButton("Create Menu");
		mainMenu = new JButton("Main");
		createDrink = new JButton("Create Drink");
		removeDrink = new JButton("Remove Drink");
		save = new JButton("Save");
		
		create.setPreferredSize(dim);
		mainMenu.setPreferredSize(dim);
		removeDrink.setPreferredSize(dim);
		createDrink.setPreferredSize(dim);
		
		create.addActionListener(this);
		mainMenu.addActionListener(this);
		createDrink.addActionListener(this);
		removeDrink.addActionListener(this);
		
		
		main = new JPanel();
		main.add(create);
		main.add(save);

		
		createPanel = new JPanel();
		createPanel.add(mainMenu);
		createPanel.add(createDrink);
		createPanel.add(drinkName);
		createPanel.add(removeDrink);
		
		numberPane = new JPanel();
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		
		
		
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		
		
		numberPane.add(one);
		numberPane.add(two);
		numberPane.add(three);
		numberPane.add(four);
		numberPane.add(five);
		numberPane.add(six);
		numberPane.add(seven);
		numberPane.add(eight);
		numberPane.add(nine);
		
		for (Map.Entry<String, Drink> entry : drinks.entrySet()) {
			String name = entry.getKey();
			//System.out.println(drinks.containsKey(name));
			if (names.contains(name) == false) {
				names.add(name);
				Drink drink = entry.getValue();
				JButton buyBtn = new JButton(name);
				buyBtn.setPreferredSize(new Dimension(300,200));
				buyBtn.addActionListener(this);
				main.add(buyBtn);
			}
		}
		
		
		cards = new JPanel(new CardLayout());
		
		cards.add(main, "main");
		cards.add(createPanel, "createPanel");
		cards.add(numberPane, "numberPane");
		
		
		getContentPane().add(cards);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		String prevCommand = command;
		command = e.getActionCommand();
		if (e.getSource() == create) {
			cardLayout.show(cards, "createPanel");
			cardShow = 2;
		}
		if (e.getSource() == mainMenu) {
			cardLayout.show(cards, "main");
			cardShow = 1;
			for (Map.Entry<String, Drink> entry : drinks.entrySet()) {
				String name = entry.getKey();
				//System.out.println(drinks.containsKey(name));
				if (names.contains(name) == false) {
					names.add(drinkName.getText());
					Drink drink = entry.getValue();
					JButton buyBtn = new JButton(name);
					buyBtn.setPreferredSize(new Dimension(300,200));
					buyBtn.addActionListener(this);
					main.add(buyBtn);
				}
			}
		}
		if (e.getSource() == createDrink) {
			createDrink(drinkName.getText());
			
		}
		if (e.getSource() == removeDrink) {
			removeDrink(drinkName.getText());
		}
		for (Map.Entry<String, Drink> entry : drinks.entrySet()) {
			String name = entry.getKey();
			//System.out.println(e.getSource());
			if (name == command) {
				cardLayout.show(cards, "numberPane");
				cardShow = 3;
				
			}
		}
		for (int i = 0; i < 9;i++) {
			if (cardShow == 3) {
				if (isNumeric(command)) {
					if (Integer.parseInt(command) == i) {
						buyDrink(prevCommand, i);
						
						cardLayout.show(cards, "main");
						
						
					}
				}
			}
		}
		if (e.getSource() == ok) {
			cardLayout.show(cards, "main");
		}
	}
		
		
		
		
	
	public void createDrink(String name) {
		price = 10;//this.totalPrice/this.drinks.size();
		drink = new Drink(name, price);
		this.drinks.put(name, drink);
		for (Map.Entry<String, Drink> entry : drinks.entrySet()) {
			String dname = entry.getKey();
			
			Drink ddrink = entry.getValue();
			
			
		}
		
	}
	public void save() {
		properties = new Properties();
		properties.putAll(this.drinks);
		try {
			properties.store(new FileOutputStream("data.properties"), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void load() {
		//this.drinks = new HashMap<String, Drink>(properties);
	}
	public void genReciept(String name, int price, int quantity) {
		
		String recStr = quantity + "x "+name+ " "+price*quantity;
		JOptionPane.showMessageDialog(this, recStr);
		
	}
	

	public void buyDrink(String drinkName, int quantity) {
		drink = this.drinks.get(drinkName);
		genReciept(drink, drink.price, quantity);
		drink.price = drink.price + (names.size() -1)*quantity;
		
		for (Map.Entry<String, Drink> entry: drinks.entrySet()) {
			String name = entry.getKey();
			Drink ddrink = entry.getValue();
			if (name != drinkName) {
				ddrink.price = ddrink.price-quantity;
			}
			MyScreen.update(name, ddrink.price);
			
		}
		
		System.out.println(drinkName + " NAME");
		System.out.println(drink.price + " PRICE");
		
	}
	public void removeDrink(String drinkName) {
		this.drinks.remove(drinkName);
	}
	
	public static boolean isNumeric(String s) {
		try {
			double d = Double.parseDouble(s);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}
