package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Actions.AttackAction;
import game.Weapons.Club;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @modifier Danny Duong
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	Rune runes = new Rune();
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addWeaponToInventory(new Club());
		this.addItemToInventory(runes);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void reset() {}

	/**
	 * Add Runes function.
	 * Adds a number of runes to the player's inventory.
	 *
	 * @param min	the minimum number of runes that can be generated
	 * @param max	the maximum number of runes that can be generated
	 * @see AttackAction
	 */
	public void addRunes(int min, int max){
		int generatedRunes = RandomNumberGenerator.getRandomInt(min, max);
		if (runes.addStacks(generatedRunes)){
			System.out.println("The player has picked up " + generatedRunes);
		}
		else {
			System.out.println("Something went wrong.");
		}
	}
}
