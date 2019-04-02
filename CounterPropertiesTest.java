import org.junit.quickcheck;
//import org.junit.quickcheck.Property;
//import org.junit.quickcheck.examples.counter.Counter;
//import org.junit.quickcheck.generator.Fields;
//import org.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class CounterPropertiesTest{

}

/*
@RunWith(JUnitQuickcheck.class)
public class CounterPropertiesTest {
    @Property public void incrementing(@From(Fields.class) Counter c) {
        int count = c.count();
        assertEquals(count + 1, c.increment().count());
    }

    @Property public void decrementing(@From(Fields.class) Counter c) {
        int count = c.count();
        assertEquals(count - 1, c.decrement().count());
    }
}
*/