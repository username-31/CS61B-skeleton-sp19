package creatures;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.awt.Color;

import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(3);
        assertEquals(3,c.energy(),0.01);
        assertEquals(new Color(34,0,231),c.color());
        c.move();//3-0.03 = 2.97
        assertEquals(2.97,c.energy());
        c.stay();//2.97-0.01=2.96
        assertEquals(2.96,c.energy());
        c.stay();
        assertEquals(2.95,c.energy());


    }

}
