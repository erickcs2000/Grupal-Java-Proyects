package com.utad.project.abstractFactoryPattern.enemies.bosses;
import com.utad.project.base.Enemy;
import com.utad.project.strategyPattern.bossStrategies.HardStrategy;
import com.utad.project.strategyPattern.bossStrategies.MediumStrategy;

public abstract class Boss extends Enemy{
	//A diferencia del resto de enemigos, los Bosses no requieren de estadisticas base ya que estos son independientes entre si, 
	//compartiendo unicamente la caracteristica de Boss.
	
	public Boss(String bossName, String sprite) {
		super(bossName, sprite);
	}
	
	//Metodo changeStrategy sobreescrito especialemente para determinar el comportamiento de los objetos Boss
	@Override
	public void changeStrategy() {
		if(this.getEquipment().getLife() < this.getEquipment().getMaxLife()*0.3 && !(this.getBehaviour()instanceof HardStrategy)) {
			this.setBehaviour(new HardStrategy());
		}else if(this.getBehaviour() instanceof HardStrategy){
			if(Math.random()<0.3) {
				this.setBehaviour(new MediumStrategy());
			}
		}
	}
}
