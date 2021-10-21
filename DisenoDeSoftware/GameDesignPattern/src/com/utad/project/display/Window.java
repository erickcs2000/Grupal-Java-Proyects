package com.utad.project.display;

import javax.swing.*;

import com.utad.project.base.*;
import com.utad.project.singletonPattern.GameManager;

import java.awt.*;
import java.awt.event.ActionListener;

public class Window extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	
	//Variables necesarias para el diseño de la ventana
	private JTextArea inventory, history;
	private JPanel lateralPanel, optionPanel, logPanel, titlePanel, worldPanel, playerSection, enemySection, inventoryPanel;
	private JScrollPane sbrText;
	private JLabel title, playerInfo, playerItems, playerState, enemiesInfo[];
	private JButton buttons[];
	
	//Referencia al manager para agilizar las llamadas
	private GameManager manager = GameManager.getManager();
	
	//Constructor de la Interfaz
	public Window(String data[], ActionListener listener, Player player) {
		buttons = new JButton[data.length];
		
		//Creamos los paneles
		optionPanel = new JPanel();
		worldPanel = new JPanel();
		logPanel = new JPanel();
		titlePanel = new JPanel();
		lateralPanel = new JPanel();
		playerSection = new JPanel();
		enemySection = new JPanel();
		inventoryPanel = new JPanel();
		
		//Diseñamos panel Titulo
		titlePanel.setLayout(new GridLayout(0,1));
		title = new JLabel("World "+manager.getCurrentLevel().ordinal());
		title.setFont(new Font("Sans-Serif", Font.BOLD, 25));
		titlePanel.add(title);		
		
		//Diseñamos panel de opciones
		
		optionPanel.setBackground(Color.gray);
		optionPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		optionPanel.setLayout(new GridLayout(0,1));
		
		
		for(int i = 0; i < data.length; i++) {
			buttons[i] = new JButton(data[i]);
			buttons[i].setPreferredSize(new Dimension(200,50));
			buttons[i].addActionListener(listener);
			optionPanel.add(buttons[i]);
		}
		
		//Diseñamos worldPanel
		worldPanel.setBackground(Color.WHITE);
		worldPanel.setBorder(BorderFactory.createTitledBorder("World information"));
		worldPanel.setLayout(new BorderLayout());
		
		playerSection.setLayout(new GridLayout(0,1));
		playerSection.setBackground(Color.WHITE);
		
		String playerDesc = player.getName() + " " + player.getEquipment().getLife()+ "/"+ player.getEquipment().getMaxLife();
		playerInfo = new JLabel(playerDesc,new ImageIcon(player.getSprite()), JLabel.CENTER);
		playerInfo.setVerticalTextPosition(JLabel.BOTTOM);
		playerInfo.setHorizontalTextPosition(JLabel.CENTER);
		
		playerSection.add(playerInfo);
		
		worldPanel.add(playerSection,BorderLayout.CENTER);
		
		enemiesInfo = new JLabel[manager.getCharacters().size()-1];

		
		enemySection.setLayout(new GridLayout(0,1));
		enemySection.setBackground(Color.WHITE);
		
		
		for(int i = 0, j = 0; i <manager.getCharacters().size(); i++) {
			if(!(manager.getCharacters().get(i) instanceof Enemy)) {
				continue;
			}
			Enemy enemy = (Enemy) manager.getCharacters().get(i);
			String info ="<html>"+ enemy.getName() + " <br>" + enemy.getEquipment().getLife()+ "/"+ enemy.getEquipment().getMaxLife()+"</html>";
			enemiesInfo[j] = new JLabel( info,new ImageIcon(enemy.getSprite()), JLabel.LEFT);
			enemySection.add(enemiesInfo[j]);
			j++;
		}
		
		worldPanel.add(playerSection, BorderLayout.CENTER);
		worldPanel.add(enemySection, BorderLayout.EAST);
		
		//Construimos el panel Lateral
		lateralPanel.setLayout(new GridLayout(0,1));
		lateralPanel.setPreferredSize(new Dimension(300,700));
		lateralPanel.add(worldPanel);
		lateralPanel.add(optionPanel);
		
		
		//Diseñamos panel Resumen
		logPanel.setBackground(Color.white);
		logPanel.setLayout(new GridLayout(0,1));
		
		history = new JTextArea("Action history:\n"); //Esto será el historial de las acciones
		history.setEditable(false);
		history.setBackground(Color.LIGHT_GRAY);
		
		sbrText = new JScrollPane(history, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//Disñamos el inventario
		inventoryPanel.setBackground(Color.WHITE);
		inventoryPanel.setBorder(BorderFactory.createTitledBorder("Inventory:"));
		inventoryPanel.setLayout(new GridLayout(0,2));
		
		
		inventory = new JTextArea("\nName:"+player.getName()+"\n"
				+"Life:"+player.getEquipment().getLife()+"\n"
				+"Max Life:"+player.getEquipment().getMaxLife()+"\n"
				+"Attack:"+player.getEquipment().getAttack()+"\n"
				+"Defense:"+player.getEquipment().getDefense()+"\n"
				+"Speed:"+player.getEquipment().getSpeed()+"\n");
		
		
		playerItems= new JLabel("<html>Equiped Items:"+player.getEquipment().getDesc()+"</html>");
		playerState = new JLabel("Current State:"+player.getState().getStateName());
		
		
		inventoryPanel.add(inventory);
		inventoryPanel.add(playerItems);
		inventoryPanel.add(playerState);
		
		logPanel.add(sbrText);
		logPanel.add(inventoryPanel);
		
		//Construimos la ventana
		
		add(titlePanel, BorderLayout.NORTH);
		add(lateralPanel, BorderLayout.EAST);
		add(logPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Decision Jugador");
		pack();
		setSize(700,700);
	}
	
	
	public JTextArea getHistory() {
		return history;
	}
	
	public void cambiarBotones(String cosas[], ActionListener listener) {
		for(int i = 0; i < buttons.length; i++) {
			optionPanel.remove(buttons[i]);
		}
		buttons = new JButton[cosas.length];
		for(int i = 0; i < cosas.length; i++) {
				buttons[i] = new JButton(cosas[i]);
				buttons[i].setPreferredSize(new Dimension(200,50));
				buttons[i].addActionListener(listener);
				optionPanel.add(buttons[i]);
			
		}
	
		validate();
		repaint();
	}
	
	public void actualizarVentana(Player player) { //Añadir la nueva informacion de cada turno
		
		//Abajo izquierda
		logPanel.remove(inventoryPanel);
		inventoryPanel.remove(inventory);
		inventoryPanel.remove(playerItems);
		inventoryPanel.remove(playerState);
		
		if(player.getEquipment().getLife() > 0) {
			inventory = new JTextArea("\nName:"+player.getName()+"\n" +"Life:"+player.getEquipment().getLife()+"\n");
					
		}
		else {
			inventory = new JTextArea("\nName:"+player.getName()+"\n"+"Life:0\n");
		}
		
		inventory.append("Max Life:"+player.getEquipment().getMaxLife()+"\n"
				+"Attack:"+player.getEquipment().getAttack()+"\n"
				+"Defense:"+player.getEquipment().getDefense()+"\n"
				+"Speed:"+player.getEquipment().getSpeed()+"\n");
		
		playerItems= new JLabel("<html>Equiped Items:"+player.getEquipment().getDesc()+"</html>");
		playerState = new JLabel("Current State:"+player.getState().getStateName());
		
		
		inventoryPanel.add(inventory);
		inventoryPanel.add(playerItems);
		inventoryPanel.add(playerState);
		
		logPanel.add(inventoryPanel);
		
		//Arriba izquierda
		titlePanel.remove(title);
		title = new JLabel("World "+manager.getCurrentLevel().ordinal());
		title.setFont(new Font("Sans-Serif", Font.BOLD, 25));
		titlePanel.add(title);
		
		
		
		//Arriba derecha
		worldPanel.remove(playerSection);
		playerSection.remove(playerInfo);

		String playerDesc;
		if(player.getEquipment().getLife() > 0) {
			playerDesc = player.getName() + " \n" + player.getEquipment().getLife()+ "/"+ player.getEquipment().getMaxLife();
		}
		else {
			playerDesc = player.getName() + " \n0/"+ player.getEquipment().getMaxLife();
		}
		playerInfo = new JLabel(playerDesc,new ImageIcon(player.getSprite()), JLabel.CENTER);
		playerInfo.setVerticalTextPosition(JLabel.BOTTOM);
		playerInfo.setHorizontalTextPosition(JLabel.CENTER);
		
		playerSection.add(playerInfo);
		worldPanel.add(playerSection,BorderLayout.CENTER);
		
		worldPanel.remove(enemySection);
		
		for(int i = 0; i <enemiesInfo.length; i++) {
			enemySection.remove(enemiesInfo[i]);
		}
		enemiesInfo = new JLabel[manager.getCharacters().size()-1];
		
		for(int i = 0, j = 0; i <manager.getCharacters().size(); i++) {
			if(!(manager.getCharacters().get(i) instanceof Enemy)) {
				continue;
			}
			Enemy enemy = (Enemy) manager.getCharacters().get(i);
			String info = enemy.getName() + " \n" + enemy.getEquipment().getLife()+ "/"+ enemy.getEquipment().getMaxLife();
			enemiesInfo[j] = new JLabel( info,new ImageIcon(enemy.getSprite()), JLabel.LEFT);
			enemySection.add(enemiesInfo[j]);
			j++;
		}
		
		worldPanel.add(enemySection,BorderLayout.EAST);
		
		
		validate();
		repaint();
	}
}