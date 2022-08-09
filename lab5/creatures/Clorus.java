package creatures;

import huglife.*;

import java.awt.Color;
import java.util.*;

public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    @Override
    public double energy() {
        if (this.energy < 0) {
            this.energy = 0;
        }
        return this.energy;
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        this.energy = c.energy();
    }

    public void move() {
        this.energy -= 0.03;
    }

    public void stay() {
        this.energy -= 0.01;
    }

    public Clorus replicate() {
        Clorus babyClorus = new Clorus(this.energy() / 2);
        this.energy = this.energy() / 2;
        return babyClorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Map<Plip, Direction> plipDirectionMap = new HashMap<>();
        Deque<Plip> neighborPlips = new ArrayDeque<>();

        for (Direction currentDirection : neighbors.keySet()) {
            if (neighbors.get(currentDirection).name().equals("plip")) {

                plipDirectionMap.put((Plip) neighbors.get(currentDirection), currentDirection);
                neighborPlips.addLast((Plip) neighbors.get(currentDirection));

            } else if (neighbors.get(currentDirection).name().equals("empty")) {
                emptyNeighbors.add(currentDirection);
            }
        }

        //Below this line Clorus starts to make aciton!

        if (emptyNeighbors.isEmpty()) {//Rule 1
            this.stay();
            return new Action(Action.ActionType.STAY);

        } else if (!plipDirectionMap.isEmpty()) {//Rule 2
            Plip randPlip = neighborPlips.removeFirst();
            this.attack(randPlip);
            return new Action(Action.ActionType.ATTACK, plipDirectionMap.get(randPlip));

        } else if (this.energy() >= 1) {//Rule 3
            this.replicate();
            return new Action(Action.ActionType.REPLICATE, emptyNeighbors.removeFirst());

        } else {//Rule 4
            this.move();
            return new Action(Action.ActionType.MOVE, emptyNeighbors.removeFirst());
        }


    }

}

