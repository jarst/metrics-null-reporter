package net.coderefactory.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.junit.Test;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class NullReporterIntegrationTest {

    @Test
    public void testReporter() {
        final MetricRegistry metrics = new MetricRegistry();
        final ScheduledReporter reporter = spy(NullReporter.forRegistry(metrics).build());

        try {
            reporter.start(10, TimeUnit.MILLISECONDS);

            Counter cnt = metrics.counter("cnt");
            assertThat("Initial value is 0", cnt.getCount(), equalTo(0L));
            cnt.inc();
            cnt.dec();
            cnt.inc();
            Thread.sleep(20);
            assertThat("Counter still works", cnt.getCount(), equalTo(1L));
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        } finally {
            reporter.stop();
        }

        // Since reporter doesn't log anything, there's no need to call #report
        verify(reporter, never()).report(anySortedMap(), anySortedMap(), anySortedMap(), anySortedMap(), anySortedMap());
    }

    static SortedMap anySortedMap() {
        return any(SortedMap.class);
    }
}
