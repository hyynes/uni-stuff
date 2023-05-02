package game.Actors.Enemies;

import edu.monash.fit2099.engine.actors.Actor;
import game.Utils.Status;
import game.Items.Weapons.Grossmesser;

/**
 * A Heavy Skeletal Swordsman with 153 hp.
 * Carries around a Grossmesser weapon.
 * Created by:
 * @author Kenan Baydar
 * Modified by:
 * @modifier Danny Duong
 */

public class HeavySkeletalSwordsman extends Enemy{

    /**
     * Constructor.
     *
     * @param target Actor the Heavy Skeletal Swordsman will follow.
     */
    public HeavySkeletalSwordsman(Actor target) {
        super("Heavy Skeletal Swordsman", 'q', 153, target);
        this.addCapability(Status.FRIENDLY_TO_SKELETON);
        this.addCapability(Status.SECOND_LIFE);
        this.addWeaponToInventory(new Grossmesser());
    }
}
