package com.utad.project.singletonPattern;

import java.util.List;

import com.utad.project.base.*;
import com.utad.project.base.Character;
import com.utad.project.decoratorPattern.ActiveItemDecorator;
import com.utad.project.decoratorPattern.Item;
import com.utad.project.decoratorPattern.ItemDecorator;

public interface DisplayStrategy {
	public void informPlayer(String data); //Informacion de lo que sucede en el juego
	public void askPlayer(Player player,List<ActiveItemDecorator> skills, List<Character> characters); //Pedir accion y objetivo
	public void askPlayer(Player player, List<ItemDecorator> items); //Pedir al jugador que elija entre algunos items
	public void paint(Player player, List<Character> characters); //Imprimir situacion
}
