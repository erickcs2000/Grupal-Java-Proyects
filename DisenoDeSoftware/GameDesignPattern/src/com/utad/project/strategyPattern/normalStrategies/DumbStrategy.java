package com.utad.project.strategyPattern.normalStrategies;

import java.util.List;

import com.utad.project.base.ActionType;
import com.utad.project.base.Character;
import com.utad.project.base.Enemy;
import com.utad.project.base.Player;
import com.utad.project.decoratorPattern.ActiveItemDecorator;
import com.utad.project.strategyPattern.DecisionTemplate;

public class DumbStrategy extends DecisionTemplate{
	
//No analiza de ninguna manera la situacion
	@Override
	protected int worthAttack(Enemy user, Player player) {
		return 0;
	}
	@Override
	protected int worthDefend(Enemy user, Player player) {
		return 0;
	}
	@Override
	protected int worthNeutral(Enemy user, Player player) {
		return 0;
	}
//Usa un objeto aleatorio de entre su lista
	@Override
	protected void selectSkill(int[] options, List<ActiveItemDecorator> skills, Enemy user, Player target) {
		int randomResult = (int)(Math.random()*skills.size());
		if(skills.get(randomResult).getActionType() == ActionType.NEUTRAL) {
			skills.get(randomResult).useSkill(user, user);
		}else {
			skills.get(randomResult).useSkill(user, target);
		}
	}

	public String toString() {
		return "Dumb";
	}
}
