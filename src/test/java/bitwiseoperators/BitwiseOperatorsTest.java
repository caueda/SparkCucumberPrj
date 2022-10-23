package bitwiseoperators;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class BitwiseOperatorsTest {
    @Test
    public void testANDOperator() {
        assertThat(1,equalTo(1 & 1));
        assertThat(0, equalTo(1 & 0));
    }

    @Test
    public void testMorse() {
        List<String> ans = new ArrayList<>();
        String morsecode = "....";
        for (int i=0; i< morsecode.length()-1; i++) {
            StringBuilder m = new StringBuilder();
            for(int j=0; j<morsecode.length(); j++) {
                if(j==i || j==(i+1)) {
                    m.append("-");
                } else {
                    m.append(morsecode.charAt(j));
                }
            }
            ans.add(m.toString());
        }
        System.out.println(ans);
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

    @Test
    public void testSecondBitSetted() {
        int a=3;
        int b=1;
        int secondBitSetted=2;
        //Second bit setted
        assert (secondBitSetted & a) != 0;
        //Second bit not setted
        assert (secondBitSetted & b) == 0;
    }

    @Test
    public void testLeftShift() {
        assertThat(2, equalTo(1 << 1));
        assertThat(1, equalTo(2 >> 1));
        //A left shift is equivalent to multiplying by 2
        assertThat(2 * 2, equalTo(2 << 1));
        //A right shift is equivalent to the floor of a division by 2
        assertThat(4 / 2, equalTo(4 >> 1));
    }
}
