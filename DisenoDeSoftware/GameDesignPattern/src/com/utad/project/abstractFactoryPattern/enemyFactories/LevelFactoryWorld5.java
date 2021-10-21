package com.utad.project.abstractFactoryPattern.enemyFactories;
import com.utad.project.abstractFactoryPattern.FactoryTemplate;
import com.utad.project.abstractFactoryPattern.enemies.blackKnight.*;
import com.utad.project.abstractFactoryPattern.enemies.bosses.Boss;
import com.utad.project.abstractFactoryPattern.enemies.bosses.BossWorld5;
import com.utad.project.abstractFactoryPattern.enemies.giant.*;
import com.utad.project.base.Enemy;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.ItemDecorator;
import com.utad.project.decoratorPattern.RegularItem;
import com.utad.project.decoratorPattern.items.*;
import com.utad.project.strategyPattern.normalStrategies.AgressiveStrategy;
import com.utad.project.strategyPattern.normalStrategies.DefensiveStrategy;

public class LevelFactoryWorld5 extends FactoryTemplate{
	//Contadores
	int giantCount = 0;
	int knightCount = 0;
	private int itemCount;
	
	public LevelFactoryWorld5() {
		super();
	}
	
	//Genera el Boss final correspondiente al nivel en el que nos encontremos
	public Boss generateBoss() {
		return new BossWorld5();
	}
	
	//Genera un enemigo.
	protected Enemy createEnemy() {
		int randNum = rand.nextInt(100);
		if(randNum < 55) {
			return createGiant();
		}
		else {
			return createBlackKnight();
		}
	}

	//Selecciona un arma para el enemigo generado.
	protected void decorateEnemy(Enemy enemy) {
		int randNum = rand.nextInt(100);
		enemy.getEquipment().addItem(new Potion(3));
		randNum = rand.nextInt(100);
		if(randNum < 30) {
			enemy.addItem(new DemonSpear());
		}
		else if (randNum >= 30 && randNum < 60) {
			enemy.addItem(new RatCrossbow());
		}
		else {
			enemy.addItem(new SolarAegis());
		}
	}

	//Selecciona un comportamiento para el enemigo generado.
	protected void finishEnemy(Enemy enemy) {
		int randNum = rand.nextInt(100);

		if (randNum < 65) {
			enemy.setBehaviour(new AgressiveStrategy());
		}
		else {
			enemy.setBehaviour(new DefensiveStrategy());
		}
	}
	
	
	private Enemy createGiant() {
		giantCount++;
		return new GiantWorld5(giantCount);
	}
	
	private Enemy createBlackKnight() {
		knightCount++;
		return new BlackKnightWorld5(knightCount);
	}

	public ItemDecorator generateItem() {
		//Genera items (de tres en tres) pertenencientes a una clase concreta
		int randNum = rand.nextInt(100);
		itemCount++;
		if(itemCount%3==0) { //Regulares
			//Estadisticas desequilibradas
			Stats stats = new Stats(0,rand.nextInt(30),rand.nextInt(60),rand.nextInt(30),rand.nextInt(3));
			return new RegularItem("Bloody axe",stats);
		}else if (itemCount%3 == 1) { //Con Activa
			if(randNum < 40) {
				return new FireStaff();
			}else {
				return new RatCrossbow();
			}
		}else { //Con Pasiva
			return new DemonSpear();

		}
		
	}

}
