package game.Actors.Enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Utils.Status;

/**
 * A giant crayfish.
 * Created by:
 * @author Kenan Baydar
 * Modified by:
 * @modifier Danny Duong
 */

public class GiantCrayfish extends Enemy{
    public GiantCrayfish(Actor target) {
        super("Giant Crayfish", 'R', 4803, target);
        this.addCapability(Status.SLAM_ATTACK);
        this.addCapability(Status.FRIENDLY_TO_SEA);
        this.runeMin = 500;
        this.runeMax = 2374;
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "uses its Giant Pincer to slam", 100);
    }
}
