package game.actors.enemies.regular;
import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.Enemy;
import game.utils.Status;
import game.items.weapons.Scimitar;

/**
 * A Skeletal Bandit with 184 hp.
 * Carries around a Scimitar weapon.
 * Created by:
 * @author Kenan Baydar
 * Modified by:
 * @modifier Danny Duong
 */
public class SkeletalBandit extends RegularEnemy {

    /**
     * Constructor.
     *
     * @param target Actor the Skeletal Bandit will follow.
     */
    public SkeletalBandit(Actor target) {
        super("Skeletal Bandit", 'b', 184, target);
        this.addCapability(Status.FRIENDLY_TO_SKELETON);
        this.addCapability(Status.RESPAWNABLE);
        this.addWeaponToInventory(new Scimitar(target));
    }

    /**
     *
     * @return minimum number of runes the Skeletal Bandit can generate after being killed.
     */
    @Override
    public int getRuneMin() {
        return 35;
    }

    /**
     *
     * @return maximum number of runes the Skeletal Bandit can generate after being killed.
     */
    @Override
    public int getRuneMax() {
        return 892;
    }
}
