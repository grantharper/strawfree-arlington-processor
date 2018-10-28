package org.grantharper.strawfreeapi;

import org.junit.Test;

public class StrawfreeUtilsTests {


    @Test
    public void testUrlEncode() {
        String encoded = StrawfreeUtils.composeMapsUrl("Busboys and Poets");

        System.out.println(encoded);

    }

}
