package com.utad.project.statePattern;

import com.utad.project.base.Action;

public class StateStandard extends AbstractState{

	
	public StateStandard() {}
	public StateStandard(State state) {
		super(state, "Standard");
	}
	public void process() {
		if(suggestion != States.STANDARD && suggestion != null) {
			this.state.setState(this.state.getPossibleState(suggestion));
			this.state.setTurns(2);
		}
		suggestion = null;
		
	}
	
public Action effect(Action action) { //No hace nada
		return action;
	}


}
