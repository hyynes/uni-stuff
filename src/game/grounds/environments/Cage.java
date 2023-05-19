package game.grounds.environments;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.regular.Dog;

public class Cage extends Environment{

    public Cage() {
        super('<');
    }

    /**
     * Spawn chance of enemies.
     *
     * @return the chance that a certain enemy will spawn depending on whether the Graveyard is located east
     * or west of the map.
     */
    @Override
    public int getSpawnChance() {
        return 37;
    }

    /**
     * The enemy that will spawn from the Gust of Wind
     *
     * @return A certain actor depending on whether the Graveyard is located east or west of the mop.
     */
    @Override
    public Actor spawningEnemy() {
        return new Dog(target);
    }
}
