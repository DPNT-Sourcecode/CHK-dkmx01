package befaster.solutions.HLO;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class HelloSolutionTest {

    private HelloSolution hello;

    @Before
    public void setUp() {

        hello = new HelloSolution();
    }

    @Test
    public void hello() throws Exception {
        assertThat(hello.hello("john"), equalTo("Hello, john!"));
    }

}