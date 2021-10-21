package com.utad.project.abstractFactoryPattern.enemyFactories;
import com.utad.project.abstractFactoryPattern.FactoryTemplate;
import com.utad.project.abstractFactoryPattern.enemies.blackKnight.*;
import com.utad.project.abstractFactoryPattern.enemies.bosses.Boss;
import com.utad.project.abstractFactoryPattern.enemies.bosses.BossWorld4;
import com.utad.project.abstractFactoryPattern.enemies.capraDemon.*;
import com.utad.project.abstractFactoryPattern.enemies.giant.*;
import com.utad.project.base.Enemy;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.ItemDecorator;
import com.utad.project.decoratorPattern.RegularItem;
import com.utad.project.decoratorPattern.items.*;
import com.utad.project.statePattern.States;
import com.utad.project.strategyPattern.normalStrategies.AgressiveStrategy;
import com.utad.project.strategyPattern.normalStrategies.DefensiveStrategy;
import com.utad.project.strategyPattern.normalStrategies.DumbStrategy;

//Mundo basado en el estado furioso 
public class LevelFactoryWorld4 extends FactoryTemplate{
	
	//Contadores
	int demonCount =0;
	int giantCount = 0;
	int knightCount = 0;
	int itemCount = 0;

	public LevelFactoryWorld4() {
		super();
	}

	//Genera el Boss final correspondiente al nivel en el que nos encontremos
	public Boss generateBoss() {
		return new BossWorld4();
	}

	//Genera un enemigo.
	protected Enemy createEnemy() {
		int randNum = rand.nextInt(100);
		if(randNum < 40) {
			return createCapraDemon();
		}
		else if(randNum >= 40 && randNum < 70 || giantCount > 0) {
			return createBlackKnight();
		}
		else {
			return createGiant();
		}
	}

	//Selecciona un arma para el enemigo generado.
	protected void decorateEnemy(Enemy enemy) {
		int randNum = rand.nextInt(100);

		if(randNum < 80) {
			enemy.addItem(new DemonSpear());
		}else {
			enemy.addItem(new Thornmail());
		}
	}

	//Selecciona un comportamiento (y/o un estado inicial) para el enemigo generado.
	protected void finishEnemy(Enemy enemy) {
		int randNum = rand.nextInt(100);
		if(randNum < 80) {
			enemy.getState().setSuggestion(States.FURIOUS);
			enemy.setBehaviour(new AgressiveStrategy());
		}else {
			enemy.getState().setSuggestion(States.CONFUSED);
			enemy.setBehaviour(new DumbStrategy());
		}
		enemy.getState().process(); //Hace efectivo el cambio de estado
		
	}

	public Enemy createCapraDemon() {
		demonCount++;
		return new CapraDemonWorld4(demonCount);
	}
	
	public Enemy createGiant() {
		giantCount++;
		return new GiantWorld4(giantCount);
	}
	
	public Enemy createBlackKnight() {
		knightCount++;
		return new BlackKnightWorld4(knightCount);
	}
	
	public ItemDecorator generateItem() {
		//Genera items (de tres en tres) pertenencientes a una clase concreta
		int randNum = rand.nextInt(100);
		itemCount++;
		if(itemCount%3==0) { //Regulares
			//Estadisticas desequilibradas
			Stats stats = new Stats(0,rand.nextInt(30),rand.nextInt(60),rand.nextInt(30),rand.nextInt(3));
			return new RegularItem("Bloody axe",stats);
		}else if (itemCount%3 == 1) { //Defensivos
			if(randNum < 40) {
				return new Thornmail();
			}else {
				return new ElectricShield();
			}
		}else { //Neutrales
			if(randNum < 70) {
				return new Antidote(2);
			}else {
				return new Potion(3);
			}

		}
		
	}

}
