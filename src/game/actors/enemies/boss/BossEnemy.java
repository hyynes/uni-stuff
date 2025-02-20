package game.actors.enemies.boss;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.utils.Status;

/**
 * An abstract class to be extended from enemies that are classified as Bosses.
 * Bosses cannot be despawned and are not removed from the game after the reset action is called.
 * Created by:
 * @author Kenan Baydar
 *
 */
public abstract class BossEnemy extends Enemy {

    /**
     * Original location of the boss.
     */
    private Location originalLocation;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param target      the Actor that the enemy will follow
     */
    public BossEnemy(String name, char displayChar, int hitPoints, Actor target) {
        super(name, displayChar, hitPoints, target);
        this.behaviours.remove(998);
        this.addCapability(Status.BOSS);
    }

    /**
     * Moves the enemy boss back to its original position after reset
     *
     * @param map - the map that the actors are in.
     */
    @Override
    public void reset(GameMap map){
        map.moveActor(this, originalLocation);
    }

    /**
     * Setter for the original position of the boss.
     */
    public void setOriginalLocation(Location originalLocation){
        this.originalLocation = originalLocation;
    }
}
