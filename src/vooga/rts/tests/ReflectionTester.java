package vooga.rts.tests;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import vooga.rts.util.ReflectionHelper;


/**
 * Tests the ReflectionHelper's set and get methods to see if they work correctly
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ReflectionTester {

    BananaSeller test;

    @BeforeClass
    public static void setUpBeforeClass () throws Exception {

    }

    @Test
    public void checkSetValue () {
        BananaSeller tempTest = new BananaSeller(50);
        ReflectionHelper.<Integer> setValue("myBananas", tempTest, 30);
        assertEquals(tempTest.getBananas(), 30);
    }

    @Test
    public void checkGetValue () {
        BananaSeller tempTest = new BananaSeller(100);
        Integer val = ReflectionHelper.<Integer> getValue("myBananas", tempTest);
        assertEquals(val.intValue(), 100);
    }

    @Test
    public void checkChangeValue () {
        BananaSeller tempTest = new BananaSeller(70);
        ReflectionHelper.<Integer> changeValue("myBananas", tempTest, 30);
        assertEquals(tempTest.getBananas(), 100);
    }

    @Test
    public void checkHierarchySetValue () {
        BananaSeller tempTest = new BananaSeller(50);
        ReflectionHelper.<Integer> setValue("myFruit", tempTest, 30);
        assertEquals(tempTest.getFruit(), 30);
    }

    @Test
    public void checkHierarchyGetValue () {
        BananaSeller tempTest = new BananaSeller(100);
        Integer val = ReflectionHelper.<Integer> getValue("myFruit", tempTest);
        assertEquals(val.intValue(), 100);
    }

    @Test
    public void checkHierarchyChangeValue () {
        BananaSeller tempTest = new BananaSeller(70);
        ReflectionHelper.<Integer> changeValue("myFruit", tempTest, 30);
        assertEquals(tempTest.getFruit(), 100);
    }

    private class BananaSeller extends FruitSeller {
        private int myBananas;

        public BananaSeller (int val) {
            super(val);
            myBananas = val;
        }

        public int getBananas () {
            return myBananas;
        }
    }

    private class FruitSeller {

        private int myFruit;

        public FruitSeller (int val) {
            myFruit = val;
        }

        public int getFruit () {
            return myFruit;
        }
    }

}
