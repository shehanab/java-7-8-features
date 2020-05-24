package com.learn;

import junit.framework.TestCase;
import org.junit.Test;

public class InterfaceTest extends TestCase {

    @Test
    public void testWrite() throws Exception {
        ComboDrive comboDrive = new ComboDrive();
        comboDrive.write("Shehan");

        assertTrue(CdDrive.isNull(null));
        assertFalse(CdDrive.isNull("Shehan"));

    }
}