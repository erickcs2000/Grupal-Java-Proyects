package com.utad.project.statePattern;

import com.utad.project.base.Action;
import com.utad.project.singletonPattern.GameManager;

public class StateSeriouslyParalyzed extends AbstractState{

	public StateSeriouslyParalyzed(State state) {
		super(state,"Seriously paralyzed");
	}
	
	public void process() {
		if(suggestion == States.STANDARD) {
			standard();
		}else if(suggestion == States.PARALYZED){
			this.state.setTurns(this.state.getTurns()+1);
		}else if(this.state.getTurns() <= 0) {
			paralyzed();
		}
		suggestion = null;
	}

	
	public Action effect(Action action) {
		//Devuelve una accion inutilizada
		action.getVariation().backToNormal();
		action.getVariation().setLife(0);
		GameManager.getManager().informPlayer(action.getUser().getName()+" is paralyzed and cant perform any action");
		this.state.setTurns(this.state.getTurns()-1);
		return action;
	}
	
	protected void standard() {
		this.state.setState(this.state.getPossibleState(States.STANDARD));
	}
	protected void paralyzed() {
		this.state.setState(this.state.getPossibleState(States.PARALYZED));
		this.state.setTurns(1);
	}
}
