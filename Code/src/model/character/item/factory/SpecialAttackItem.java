package model.character.item.factory;

import model.character.attack.dynamic.Launcher;
import model.character.attack.statics.BoomerangLauncher;
import model.character.attack.statics.enemyMover.EnemyMoverLauncher;
import model.character.hero.Hero;
import model.character.item.Item;

public class SpecialAttackItem extends Item{

	private final static int LAUNCHERBOOMERANG = 816;
	private final static int LAUNCHERENEMYMOVER = 817;
	
	private Launcher launcher;
	public SpecialAttackItem(int launcherValue) {
		super(launcherValue);
		switch(launcherValue) {
		case LAUNCHERBOOMERANG : 
			launcher = new BoomerangLauncher();
			break;
		case LAUNCHERENEMYMOVER : 
			launcher = new EnemyMoverLauncher();
			break;
		default :
			throw new IllegalArgumentException("UNKNOWN LAUNCHER VALUE :" + launcherValue);
		}
	}

	public SpecialAttackItem(String launcherName) {
		this(getValue(launcherName));
	}
	
	private final static int getValue(String launcherName) {
		int value ;
		if(launcherName.equalsIgnoreCase("Boomerang"))
			value = LAUNCHERBOOMERANG;
		else if(launcherName.equalsIgnoreCase("EnemyMover"))
			value = LAUNCHERENEMYMOVER;
		else
			value = -1;
		return value;
		
	}
	@Override
	protected void applyTo(Hero hero) {
		hero.addLauncher(this.launcher);
	}

}
