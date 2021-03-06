package model.character.enemy.boss;

import java.awt.Image;
import java.util.Random;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.attack.statics.boss.NyanAttHori;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class NyaBlock  extends GameCharacter{
	private final static int DEFAULTCYCLE = 3;
	private final static double DEFAULTCOEF = 1;
	private final static int DEFAULTIMG = 8;
	private boolean att;
	private int img;
	private Random ran = new Random();
	private boolean mov;
	public NyaBlock(GameMap map, int startRow, int startColumn,Movement mov) {
		super(map, startRow, startColumn, DEFAULTCYCLE, DEFAULTCOEF, DEFAULTIMG+mov.getIndex());
		img=mov.getIndex();
		att=false;
		this.mov=mov==Movement.LEFT;
		// TODO Auto-generated constructor stub
	}
//
//	public NyaBlock(GameMap map, int cycle, int row, int column, int damage, int cellPerTurn,
//			double coefficient, int defaultImage, int maxDistance) {
//		super(map, cycle, row, column, Movement.STAY, damage, cellPerTurn, coefficient, defaultImage, maxDistance);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	protected void establishAttack(GameCharacter gameCharacter) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Override
//	protected boolean handleMove(byte i) {
//		
//		return true;
//		
//	}

	@Override
	public void launchAttack(Movement move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDmg(Attack attack) {
		
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	protected Move act() {
		if (att) {
			setImage(img);
			new NyanAttHori(getMyMap(), getRow(), getColumn(), mov);
			att = false;
		}
		
		else if (ran.nextInt(10)==0) {
			setWait(100);
			setImage(img-1);
			att=true;
		}
			
		return null;
	}

	
}
