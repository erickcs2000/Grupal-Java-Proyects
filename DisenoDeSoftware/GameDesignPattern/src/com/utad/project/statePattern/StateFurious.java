package com.utad.project.statePattern;

import com.utad.project.base.Action;
import com.utad.project.singletonPattern.GameManager;

public class StateFurious extends AbstractState{
	
	public StateFurious() {}
	public StateFurious(State state) {
		super(state,"Furious");
	}
	
	public void process() {		
		if(suggestion == States.SERIOUSLYPARALYZED) {
			seriouslyParalyzed();
		}else if(this.state.getTurns() <= 0) {
			if(Math.random() <= 0.5) {
				standard();
			}else {
				confused();
			}
		}
		suggestion = null;
	}
	
	public Action effect(Action action) { //Duplica la fuerza de un ataque
		action.getVariation().setAttack(action.getVariation().getAttack()*2);
		GameManager.getManager().informPlayer(action.getUser().getName()+" is furious and deals doble damage");
		this.state.setTurns(this.state.getTurns()-1);
		return action;
	}

	protected void confused() {
		this.state.setState(this.state.getPossibleState(States.CONFUSED));
		this.state.setTurns(1);
	}

	protected void standard() {
		this.state.setState(this.state.getPossibleState(States.STANDARD));
	}
	
	protected  void seriouslyParalyzed() {
		this.state.setState(this.state.getPossibleState(States.STANDARD));
		this.state.setTurns(2);
	}
	
}
