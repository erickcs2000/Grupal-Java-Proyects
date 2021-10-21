package com.utad.project.singletonPattern;

import java.util.List;
import java.util.Scanner;

import com.utad.project.base.Character;
import com.utad.project.base.Player;
import com.utad.project.decoratorPattern.ActiveItemDecorator;
import com.utad.project.decoratorPattern.Item;
import com.utad.project.decoratorPattern.ItemDecorator;

public class ConsoleDisplay implements DisplayStrategy{

	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void informPlayer(String data) {
		System.out.println(data);
	}

	@Override
	public void askPlayer(Player player, List<ActiveItemDecorator> skills, List<Character> characters) {
		int option; 
		
		System.out.println("Select a skill to use:");
		for(int i = 0; i < skills.size(); i++) {
			System.out.println(i + ":"+skills.get(i).getSkillname());
		}
		
		do {
			option = scanner.nextInt();
		}while(option < 0 || option > skills.size()-1);
		
		player.setNextSkill(skills.get(option));
		
		System.out.println("Select the target:");
		for(int i = 0; i < characters.size(); i++) {
			System.out.println(i + ":"+characters.get(i));
		}
		
		do {
			option = scanner.nextInt();
		}while(option < 0 || option > characters.size()-1);
		
		player.setTarget(characters.get(option));
		
		GameManager.getManager().turn();
		
	}

	@Override
	public void paint(Player player, List<Character> characters) {
		System.out.println("Characters alive:");
		for(int i = 0; i < characters.size(); i++) {
			System.out.println(characters.get(i));
		}
		
		
	}

	@Override
	public void askPlayer(Player player, List<ItemDecorator> items) {
		int option; 
		
		System.out.println("Pick an item:");
		for(int i = 0; i < items.size(); i++) {
			System.out.println(i + ":"+items.get(i));
		}
		
		do {
			option = scanner.nextInt();
		}while(option < 0 || option > items.size()-1);
		
		GameManager.getManager().giveItem(items.get(option));
		
		GameManager.getManager().askPlayer();
		
	}

	

}
