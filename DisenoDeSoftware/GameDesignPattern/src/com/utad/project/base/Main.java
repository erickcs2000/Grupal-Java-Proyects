package com.utad.project.base;

import com.utad.project.singletonPattern.GameManager;

public class Main { //Punto de entrada

	public static void main(String[] args) {
		GameManager.getManager().play();

	}

}
