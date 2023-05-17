package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

public class Dog extends Enemy{

    /**
     * Constructor.
     *
     * @param target Actor the Dog will follow.
     */
    public Dog(Actor target) {
        super("Dog", 'a', 104, target);
        this.addCapability(Status.FRIENDLY_TO_CASTLE);
    }

    /**
     *
     * @return The Dog's Intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

    /**
     *
     * @return minimum number of runes the Dog can generate after being killed.
     */
    @Override
    public int getRuneMin() {
        return 52;
    }

    /**
     *
     * @return maximum number of runes the Dog can generate after being killed.
     */
    @Override
    public int getRuneMax() {
        return 1390;
    }
}
