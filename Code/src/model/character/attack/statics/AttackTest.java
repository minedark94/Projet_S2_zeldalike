package model.character.attack.statics;

import model.character.GameCharacter;

import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class AttackTest extends Attack{

	private final static int DEFAULTCYCLE = 40;
	private final static int DEFAULTCELLPERTURN = 1;
	private final static double DEFAULTCOEFFICIENT = 2;
	private final static int DEFAULTIMAGE = 1612;
	private final static int MAXDISTANCE = 2;
	
	public AttackTest(GameMap map,  int row, int column, Movement direction, int damage) {
		super(map, DEFAULTCYCLE, row, column, direction, damage, DEFAULTCELLPERTURN,DEFAULTCOEFFICIENT,DEFAULTIMAGE,MAXDISTANCE);
	}	
	
	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);	
	}
}
