package org.sundqvist;

import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test class for {@link RulesOfLife}
 */
public class RulesOfLifeTest {

    @Test
    public void testInit() {
        List<Boolean> booleans = new RulesOfLife(3, 3, 0.0d).applyRules();
        Assert.assertFalse(booleans.stream().anyMatch(x -> x));
    }

    @Test
    public void testPopulate() {
        List<Boolean> booleans = new RulesOfLife(5, 25, 0.5).applyRules();
        Assert.assertEquals(25, booleans.size());
    }

    @Test
    public void checkRulesOfNeighbour () {
        final List<Boolean> testCell = List.of(false, false, true, true, false, true, false, false, true);
        int pos = 1;
        Boolean booleans = new RulesOfLife(3, 9, 0.5).shouldCellLive(pos, testCell);
        Assert.assertTrue(booleans);
    }

}
