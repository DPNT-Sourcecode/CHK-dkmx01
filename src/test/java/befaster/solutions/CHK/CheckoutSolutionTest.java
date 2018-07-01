package befaster.solutions.CHK;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class CheckoutSolutionTest {

    private CheckoutSolution checkout;

    @Before
    public void setUp() {

        checkout = new CheckoutSolution();
    }

    @Test
    public void checkout() throws Exception {
        assertThat(checkout.checkout(""), equalTo(0));
        assertThat(checkout.checkout("a"), equalTo(-1));
        assertThat(checkout.checkout("A"), equalTo(50));
        assertThat(checkout.checkout("AA"), equalTo(50 + 50));
        assertThat(checkout.checkout("AAA"), equalTo(130));
        assertThat(checkout.checkout("AAAA"), equalTo(130 + 50));
        assertThat(checkout.checkout("AAAAA"), equalTo(200));
        assertThat(checkout.checkout("AAAAAA"), equalTo(200 + 50));
        assertThat(checkout.checkout("B"), equalTo(30));
        assertThat(checkout.checkout("BB"), equalTo(45));
        assertThat(checkout.checkout("BBB"), equalTo(45 + 30));
        assertThat(checkout.checkout("C"), equalTo(20));
        assertThat(checkout.checkout("D"), equalTo(15));
        assertThat(checkout.checkout("E"), equalTo(40));
        assertThat(checkout.checkout("EE"), equalTo(80));
        assertThat(checkout.checkout("EEB"), equalTo(80));
        assertThat(checkout.checkout("EEEEBB"), equalTo(160));
        assertThat(checkout.checkout("ABCDE"), equalTo(15 + 20 + 30 + 50 +40));
    }

}
