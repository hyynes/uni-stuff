package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Actors.Enemies.PileOfBones;
import game.Actors.FriendlyActors.PlayableCharacter;
import game.Utils.Status;
import game.Actors.Enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    private final Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        Location location = map.locationOf(target);

        ActionList dropActions = new ActionList();

        // Instead of dying, Actors that have a second life will turn into a Pile of Bones.
        if (target.hasCapability(Status.SECOND_LIFE)) {

            map.removeActor(target);

            // Transfer all weapons to the Pile of Bones
            List<WeaponItem> allWeapons = new ArrayList<>(target.getWeaponInventory());

            map.addActor(new PileOfBones(null, allWeapons), location);
            return System.lineSeparator() + target + " has turned into a Pile of Bones!";
        }

        // drop all items
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);

        // remove actor
        map.removeActor(target);

        // Player obtains runes from enemy, printed to the screen.
        if (attacker.hasCapability(Status.HOSTILE_TO_ENEMY)){
            result += System.lineSeparator() + ((PlayableCharacter) attacker).enemyDefeatedRunes(target,(((Enemy) target).runeMin), ((Enemy) target).runeMax);
        }

        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed." + System.lineSeparator();
    }
}
