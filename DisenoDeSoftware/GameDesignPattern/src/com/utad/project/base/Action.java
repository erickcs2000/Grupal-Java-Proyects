package com.utad.project.base;

public class Action {
	private Stats variation; //variacion de las estadisticas como consecuencia de la accion
	private ActionType actionType; 
	private SkillType skillType;
	private Character user; //Personaje que lanza la accion
	private Character target; //Personaje que sufre la accion
	
	public Action(Stats variation, ActionType actionType, SkillType skillType, Character user, Character target) {
		this.variation = variation;
		this.actionType = actionType;
		this.skillType = skillType;
		this.user = user;
		this.target = target;
	}
	
	public Stats getVariation() {
		return variation;
	}
	public void setVariation(Stats variation) {
		this.variation = variation;
	}
	public ActionType getActionType() {
		return actionType;
	}
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	public SkillType getSkillType() {
		return skillType;
	}
	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}
	public Character getTarget() {
		return target;
	}
	public void setTarget(Character target) {
		this.target = target;
	}

	public Character getUser() {
		return user;
	}

	public void setUser(Character user) {
		this.user = user;
	}
	
}
