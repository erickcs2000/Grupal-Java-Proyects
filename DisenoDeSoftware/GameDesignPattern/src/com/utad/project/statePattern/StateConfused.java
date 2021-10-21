package com.utad.project.statePattern;

import com.utad.project.base.Action;
import com.utad.project.singletonPattern.GameManager;

public class StateConfused extends AbstractState{
	
	public StateConfused() {}
	public StateConfused(State state) {
		super(state, "Confused");
	}
	
	public void process() {
		if(suggestion == States.STANDARD) {
			standard();
		}else if(suggestion == States.CONFUSED) {
			confused();
		}
		
		if(this.state.getTurns() <= 0) {
			standard();
		}
		suggestion = null;
	}
	
	public Action effect(Action action) {
		this.state.setTurns(this.state.getTurns()-1);
		
		//Cambia el objetivo al usuario con un 60% de probabilidad
		if(Math.random() < 0.4) { 
			action.setTarget(action.getUser());
			GameManager.getManager().informPlayer(action.getUser().getName()+" is confused and deals himself the damage");
		}
		return action;
	}
	
	protected void standard() {
		this.state.setState(this.state.getPossibleState(States.STANDARD));
	}
	protected void confused() {
		this.state.setTurns(this.state.getTurns()+1);
	}
	
}
