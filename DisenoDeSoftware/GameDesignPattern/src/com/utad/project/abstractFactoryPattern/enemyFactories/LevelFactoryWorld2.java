package com.utad.project.abstractFactoryPattern.enemyFactories;
import java.util.ArrayList;
import java.util.List;

import com.utad.project.abstractFactoryPattern.FactoryTemplate;
import com.utad.project.abstractFactoryPattern.enemies.bosses.Boss;
import com.utad.project.abstractFactoryPattern.enemies.bosses.BossWorld2;
import com.utad.project.abstractFactoryPattern.enemies.hollow.*;
import com.utad.project.abstractFactoryPattern.enemies.skeleton.*;
import com.utad.project.base.Enemy;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.ItemDecorator;
import com.utad.project.decoratorPattern.RegularItem;
import com.utad.project.decoratorPattern.items.*;
import com.utad.project.statePattern.States;
import com.utad.project.strategyPattern.normalStrategies.DumbStrategy;
import com.utad.project.strategyPattern.normalStrategies.HealerStrategy;

//Mundo basado en el estado envenenado
public class LevelFactoryWorld2 extends FactoryTemplate{ 
	
	//Atributos necesarios para la creacion de objetos tipo Item
	private String[] names = {"pickaxe","sword","spear","armor"};
	private String[] adjectives = {"Poisonous","Mushroom","Snake"};
	private List<ItemDecorator> items = new ArrayList<ItemDecorator>();
	private int basePower = 20;
	
	//Contadores necesarios para la creacion de objetos tipo Enemy
	int skeletonCount=0;
	int hollowCount =0;
	
	public LevelFactoryWorld2() {
		super();
	}
	
	//Genera el Boss final correspondiente al nivel en el que nos encontremos
	public Boss generateBoss() {
		return new BossWorld2();
	}

	//Genera un enemigo.
	protected Enemy createEnemy() {
		if(hollowCount == 0) { //Crea un y solo un enemigo tipo Hollow
			return createHollow();
		}else { //Los demas son todos tipo skeleton
			return createSkeleton();
		}
	}

	//Decora con armas el enemigo generado.
	protected void decorateEnemy(Enemy enemy) {
		int randNum = rand.nextInt(100);
		
		enemy.addItem(new Potion(1));
		enemy.addItem(new Antidote(1));
		
		randNum = rand.nextInt(100);
		if(randNum < 40) {
			enemy.addItem(new RatCrossbow());
		}else {
			enemy.addItem(new Bow());
			enemy.addItem(new Antidote(2));
		}
		
	}

	//Selecciona un comportamiento (y/o un estado inicial) para el enemigo generado.
	protected void finishEnemy(Enemy enemy) {
		int randNum = rand.nextInt(100);
		if(randNum < 30) {
			enemy.setBehaviour(new DumbStrategy());
			enemy.getState().setSuggestion(States.POISONED); //Empieza envenenado
			enemy.getState().process();
		}else {
			enemy.setBehaviour(new HealerStrategy());
		}
	}

	private Enemy createSkeleton() {
		skeletonCount++;
		return new SkeletonWorld2(skeletonCount);
	}
	
	private Enemy createHollow() {
		hollowCount++;
		return new HollowWorld2(hollowCount);
	}
	
	private void createItemList() {
		items.add(new VampiricSword());
		items.add(new Potion(4));
		items.add(new Antidote(2));
		items.add(new RatCrossbow());
		items.add(new Thornmail());
		items.add(new SolarAegis());
	}

	//Crea una lista de objetos adecuados al nivel de la factoria
	public ItemDecorator generateItem() {
		int randNum = rand.nextInt(100);
		ItemDecorator item;
		if(randNum < 50) { //Crear un regular item  con 50% de probabilidad
			//Stats aleatorias
			Stats stats = new Stats(0,rand.nextInt(basePower),rand.nextInt(basePower),rand.nextInt(basePower),rand.nextInt(2));
			
			//Elaborar un nombre aleatorio
			String name = adjectives[rand.nextInt(adjectives.length)] + " "+ names[rand.nextInt(names.length)];
			item = new RegularItem(name,stats);
			
		}else {//Devolver un item de los posibles para el mundo 2
			if(items.size()==0) createItemList(); //Asegurar que se pueden obtener objetos de la lista de manera indefinida
			randNum = rand.nextInt(items.size());
			item = items.get(randNum);
			items.remove(randNum); //De esta manera es más probable obtener todos los items de la lista al menos una vez
		}
		return item;
	}

}
