package model.scenario.action;


import java.util.function.Supplier;

import model.character.GameCharacter;
import model.character.enemy.Enemy;
import model.character.enemy.EnemyFactory;
import model.character.item.Item;
import model.character.item.factory.ItemFactory;
import model.gameMap.additional.Statics;

public class Action {
	/*
	 * Syntaxe action
	 *  Action-TypeGeneral-TypeParticulier-Info-IdCase
	 * 	S/C/D/A-I/M/m/A/W-' '/{type_monstre}/{type_item}-contenuMessage/idMonstre-CELLID
	 */
	private final static char CREATION = 'C';
	private final static char ITEM = 'I';
	private final static char WALKABLE = 'W';
	private final static char MONSTER=  'M';
	private final static char MESSAGE = 'm';
	private final static char DROP = 'D';
	private final static char SHOW = 'S';
	private final static char ADD = 'A';
	private final static char ATTACK = 'A';
	public final static char NOTHING = 'N';
	
	private static ActionData actionData;
	private char generalType;
	private String specificType;
	private String info;
	private int cellId;
	private boolean isActive;
	
	private Action(char generalType,String specificType,String info,int cellId) {
		this.generalType = generalType;
		this.specificType = specificType;
		this.info = info;
		this.cellId = cellId;
		this.isActive = true;
	}

	
	public boolean isActive() {
		return this.isActive;
	}

	
	private Supplier<Boolean> createShowConsumer(){
		Supplier<Boolean> supplier = (()->{
			actionData.messageProperty().set(this.info);
			return true;
		});
		return supplier;
	}
	
	private Supplier<Boolean> createCreation(){
		Supplier<Boolean> supplier ;

		switch(this.generalType) {
		case ITEM :
			supplier = ()->createItem();
			break;
		case MONSTER:
			supplier = ()->createMonster();
			break;
		default:
			throw new IllegalArgumentException("ERROR IN CREATION WHILE PROCESSING GENERAL TYPE" + this.generalType);
		}
		
		return supplier;
	}

	private boolean createMonster() {
		boolean added = true;
		
		try {	
			Enemy enemy = EnemyFactory.MonsterFactory(this.specificType, actionData.getMap(), Statics.convertToRow(this.cellId),Statics.convertToColomn(this.cellId));
			actionData.getElementsList().put(this.info,enemy);
		}catch(Exception e) {
			added = false;
			System.err.println("ENEMY FACTORY FAILED BECAUSE OF " + this.specificType + e.getMessage());
		}
		
		return added;
	}
	
	private boolean createItem() {
		Item item = ItemFactory.getItem(this.specificType.toUpperCase());
		boolean created = actionData.getMap().addItem(item,this.cellId);
		return created;
	}
	
	private Supplier<Boolean> establishDrop() {
		Supplier<Boolean> supplier;
		switch(this.generalType) {
		case MONSTER : 
			supplier = ()->{
				actionData.getElementsList().get(this.info).removeCharacter(this);
				return true;
			};
			break;
		case WALKABLE : 
			supplier = () -> {
				actionData.getMap().clearBackgroundConstraint(this.cellId,this);
				return true;
			};
			break;
		default : 
				throw new IllegalArgumentException("DROP ACTION FAILED BECAUSE OF " + this.generalType);
		}
		return supplier;
	}
	
	
	private Supplier<Boolean> establishAdd(){
		Supplier<Boolean> supplier;
		switch(this.generalType) {
		case ITEM :
			supplier = ()->addItemToHero();
			break;
		case ATTACK:
			supplier = ()->addAttackToHero();
			break;
		default :
			throw new IllegalArgumentException("ERROR GENERAL TYPE AT ADD :  "+this.generalType);
		}
		return supplier;
	}
	
	private boolean addItemToHero() {
		Item item = ItemFactory.getItem(this.specificType);
		return item.effectOn(GameCharacter.getHero());
	}
	
	private boolean addAttackToHero() {
		//TODO
		return true;
	}
	
	public static Supplier<Boolean> TakeAction(String[] action,ActionData data) {
		ActionEncode encode = new ActionEncode(action);
		Action actionParams = new Action(encode.getGeneralType(),encode.getSpecificType(),encode.getInfo(),encode.idCase());
		System.out.println("We received " +data);
		actionData = data;
		Supplier<Boolean> supplier;
		switch(encode.getAction()) {
		case SHOW :
			supplier = actionParams.createShowConsumer();
			break;
		case CREATION:
			supplier = actionParams.createCreation();
			break;
		case DROP :
			supplier = actionParams.establishDrop();
			break;
		case ADD :
			supplier = actionParams.establishAdd();
			break;
		case NOTHING:
			supplier = ()->(true);
			break;
		default :
			throw new IllegalArgumentException("UNKNOWN ACTION NAME : " + encode.getAction());
		}
		return supplier;
		
	}
		
	
}
