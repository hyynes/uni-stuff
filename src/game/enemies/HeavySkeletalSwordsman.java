package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Actions.AttackAction;
import game.Behaviours.AttackBehaviour;
import game.Behaviours.Behaviour;
import game.Behaviours.FollowBehaviour;
import game.Behaviours.WanderBehaviour;
import game.PileOfBones;
import game.Status;
import game.Weapons.Grossmesser;

import java.util.*;

public class HeavySkeletalSwordsman extends Actor implements Skeleton{
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private int turnsAfterDeath = 0;
    private boolean initialCheck = true;
    private boolean isPileOfBones = false;
    PileOfBones pileOfBones = new PileOfBones();

    public HeavySkeletalSwordsman(Actor target) {
        super("Heavy Skeletal Swordsman", 'q', 153);
        this.addCapability(Status.FRIENDLY_TO_SKELETON);
        this.addWeaponToInventory(new Grossmesser());
        this.behaviours.put(998, new WanderBehaviour());
        this.behaviours.put(997, new AttackBehaviour());
        this.behaviours.put(996, new FollowBehaviour(target));
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Extra steps for Skeleton types with Pile of Bones ability
        if (this instanceof Skeleton){
        // If Skeleton just turned into a Pile of Bones that turn, initialises all values.
            if (pileOfBones.checkState(this, getTurnsAfterDeath(),getPileOfBones()) && initialCheck){
                this.setDisplayChar('X');
                // Set its health to 1
                this.hitPoints = 1;
                initialCheck = false;
            }

            // If Skeleton is in Pile of Bones state
            if (pileOfBones.checkState(this, getTurnsAfterDeath(),getPileOfBones())) {
                setTurnsAfterDeath(++turnsAfterDeath);
                return new DoNothingAction();
            }

            else {
                if (!initialCheck) {
                    // Set its display character back to that of Heavy Skeletal Swordsman
                    this.setDisplayChar(this.getOriginalDisplayChar());
                    this.hitPoints = 153;
                    setTurnsAfterDeath(0);
                }
                initialCheck = true;
            }

        // General playTurn step for all enemies
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
            }
        }
        return new DoNothingAction();
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            List<Weapon> weapons = new ArrayList<>();
            // Checks if the Player has a weapon
            for (Item weaponItem : otherActor.getWeaponInventory()) {
                if (weaponItem instanceof WeaponItem) {
                    weapons.add((Weapon) weaponItem);
                }
            }
            // If Player has a weapon, it may choose to either use it or its intrinsic weapon
            if (!weapons.isEmpty()) {
                // Use equipped weapon
                for (Weapon weapon : weapons) {
                    actions.add(new AttackAction(this, direction, weapon));
                    //actions.add(new SurroundingAttack(this,weapon);
                }
            } else {
                // Use intrinsic weapon
                actions.add(new AttackAction(this, direction));
                return actions;
            }
            // If player has no weapon in its inventory, it may only choose to use its intrinsic weapon.
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    @Override
    public int getTurnsAfterDeath(){
        return turnsAfterDeath;
    }

    @Override
    public void setTurnsAfterDeath(int turnsAfterDeath){
        this.turnsAfterDeath = turnsAfterDeath;
    }

    public boolean getPileOfBones() {
        return isPileOfBones;
    }

    public void setPileOfBones(boolean pileOfBones) {
        isPileOfBones = pileOfBones;
    }

    public final char getOriginalDisplayChar() {
        return 'q';
    }
}
