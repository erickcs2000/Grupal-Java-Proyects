package com.utad.project.statePattern;

import com.utad.project.base.Action;
import com.utad.project.singletonPattern.GameManager;

public class StateParalyzed extends AbstractState{
	
	
	public StateParalyzed() {}
	public StateParalyzed(State state) {
		super(state,"Paralyzed");
	}
	
	public void process() {
		if(suggestion == States.STANDARD || this.state.getTurns() <= 0) {
			standard();
		}else if(suggestion == States.PARALYZED || suggestion == States.SERIOUSLYPARALYZED) {
			seriouslyParalyzed();
		}
		suggestion = null;
	}
	
	public Action effect(Action action) { //Niega una accion, todos sus valores a cero
		if((int) Math.random()*1 == 0) {
			action.getVariation().backToNormal();
			action.getVariation().setLife(0);
			GameManager.getManager().informPlayer(action.getUser().getName()+" is paralyzed and cant perform any action");
		}
		this.state.setTurns(this.state.getTurns()-1);
		return action;
	}
	
	protected void standard() {
		this.state.setState(this.state.getPossibleState(States.STANDARD));
	}
	protected void seriouslyParalyzed() {
		this.state.setState(this.state.getPossibleState(States.SERIOUSLYPARALYZED));
		this.state.setTurns(this.state.getTurns()+1);
	}
	
}
