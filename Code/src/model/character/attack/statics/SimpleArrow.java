
package model.character.attack.statics;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class SimpleArrow extends Attack {
 

	private final static int DEFAULTCYCLE = 9;
	private final static int DEFAULTCELLPERTURN = 1;
	private final static int DEFAULTIMAGE = 1616;
	private final static double DEFAULTCOEFFICIENT = 2;
	private final static int MAXDISTANCE = 2;
	
	public SimpleArrow(GameMap map, int row, int column, Movement direction, int damage, int cellPerTurn) {
		super(map, DEFAULTCYCLE, row, column, direction, damage, DEFAULTCELLPERTURN, DEFAULTCOEFFICIENT,DEFAULTIMAGE,MAXDISTANCE);
	}

	public void establishAttack(GameCharacter gameCharac) {
		gameCharac.getDmg(this);

	}

}