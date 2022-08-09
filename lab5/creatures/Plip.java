package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    @Override
    public double energy() {
        if (this.energy < 0) {
            this.energy = 0;
        } else if (this.energy > 2) {
            this.energy = 2;
        }
        return this.energy;
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        r = 99;
        g = (int) (96 * this.energy() + 63);
        b = 76;
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        this.energy -= 0.15;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        this.energy += 0.2;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        Plip babyPlip = new Plip(this.energy() / 2);
        this.energy = this.energy() / 2;
        return babyPlip;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:<br>
     * 1. If no empty adjacent spaces, STAY.<br>
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction<br>
     * chosen at random.<br>
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,<br>
     * towards an empty direction chosen at random.<br>
     * 4. Otherwise, if nothing else, STAY.<br>
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Below this line Plip checks its surroundings!
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();// Stores the empty squares around the Plip
        boolean anyClorus = false;

        for (Direction currentDirection : neighbors.keySet()) {
            if (neighbors.get(currentDirection).name().equals("clorus")) {// check if there are Cloruses
                anyClorus = true;
            } else if (neighbors.get(currentDirection).name().equals("empty")) {//Add empty squares to Deque
                emptyNeighbors.addLast(currentDirection);
            }
        }

        // Below this line Plip starts to make action!

        if (emptyNeighbors.isEmpty()) {//Rule 1
            this.stay();
            return new Action(Action.ActionType.STAY);

        } else if (this.energy() >= 1.0) { //Rule 2
            this.replicate();
            return new Action(Action.ActionType.REPLICATE, emptyNeighbors.removeFirst());//TODO: randomAccess?

        } else {
            if (anyClorus && Math.random() <= 0.5) {// Rule 3
                this.move();
                return new Action(Action.ActionType.MOVE, emptyNeighbors.removeFirst());

            } else {// Rule 4
                this.stay();
                return new Action(Action.ActionType.STAY);
            }
        }

        // HINT: randomEntry(emptyNeighbors)

    }
}
