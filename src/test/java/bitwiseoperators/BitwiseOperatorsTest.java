package bitwiseoperators;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BitwiseOperatorsTest {
    @Test
    public void testANDOperator() {
        assertThat(1,equalTo(1 & 1));
        assertThat(0, equalTo(1 & 0));
    }

    @Test
    public void testNegationForSignedInt() {
        //Given a three bit number
        int a=4;
        //When negated
        int b = ~a;
        //Then
        assertThat(b, equalTo(-a-1));
    }

    @Test
    public void testNegationForUnsignedInt() {
        //Given a 8 bit number
        byte a=4;
        //When negated
        int b = Byte.toUnsignedInt((byte) ~a);
        //Then
        assertThat(b, equalTo(255-a));
    }
}
