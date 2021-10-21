package com.utad.project.singletonPattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.utad.project.base.Character;
import com.utad.project.base.Player;
import com.utad.project.decoratorPattern.ActiveItemDecorator;
import com.utad.project.decoratorPattern.Item;
import com.utad.project.decoratorPattern.ItemDecorator;
import com.utad.project.display.Window;

public class WindowDisplay implements DisplayStrategy, ActionListener{
	private Window window;
	private int state = 0;
	private List<Character> currentCharacters;
	private List<ActiveItemDecorator> currentSkills;
	private List<ItemDecorator> currentItems;
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String button = event.getActionCommand();
		if(state ==0) {
			for(int i = 0; i < currentItems.size(); i++) {
				if(button.contains(currentItems.get(i).getName())) {
					GameManager.getManager().giveItem(currentItems.get(i));
					GameManager.getManager().askPlayer();
					break;
				}if(state == 2) informPlayer("Warning: Something went wrong");
			}
		}else if(state == 1) {
			for(int i = 0; i < currentSkills.size(); i++) {
				if(button.equals(currentSkills.get(i).getSkillname())) {
					GameManager.getManager().getPlayer().setNextSkill(currentSkills.get(i));
					state = 2;
					window.cambiarBotones(getCharactersString(currentCharacters), this);
					break;
				}
			}
			if(state == 1) informPlayer("Warning: Something went wrong");
		}else if(state == 2) {
			for(int i = 0; i < currentCharacters.size(); i++) {
				if(button.equals(currentCharacters.get(i).getName())) {
					GameManager.getManager().getPlayer().setTarget(currentCharacters.get(i));
					state = 0;
					GameManager.getManager().turn();
					break;
				}
			}
			if(state == 2) informPlayer("Warning: Something went wrong");
		}
		
	}

	@Override
	public void informPlayer(String data) {
		window.getHistory().append(data+"\n");
	}

	@Override
	public void askPlayer(Player player, List<ActiveItemDecorator> skills, List<Character> characters) {
		state = 1;
		currentCharacters = characters;
		currentSkills = skills;
		window.cambiarBotones(getSkillsString(skills),this);
	}
	@Override
	public void askPlayer(Player player, List<ItemDecorator> items) {
		state = 0;
		currentItems = items;
		window.cambiarBotones(getItemsString(items),this);
	}

	@Override
	public void paint(Player player, List<Character> characters) {
		if(window==null) {
			String data[] = getSkillsString(player);
			window = new Window(data,this,player);
			window.setVisible(true);
		}else {
			window.actualizarVentana(player);
		}
		
	}
	
	private String[] getSkillsString(List<ActiveItemDecorator> list) {
		String data[] = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			data[i] = list.get(i).getSkillname();
		}
		
		return data;
	}
	private String[] getSkillsString(Player player) {
		List<ActiveItemDecorator> skills = new ArrayList<ActiveItemDecorator>();
		skills = player.getEquipment().areThereAnyActives(skills);
		return getSkillsString(skills);
	}
	private String[] getCharactersString(List<Character> list) {
		String data[] = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			data[i] = list.get(i).getName();
		}
		
		return data;
	}
	private String[] getItemsString(List<ItemDecorator> list) {
		String data[] = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			data[i] = "<html>"+list.get(i).getName()+" " + list.get(i).getTier()
					+"<br>"+list.get(i).getStats();
		}
		
		return data;
	}

	
	

}
