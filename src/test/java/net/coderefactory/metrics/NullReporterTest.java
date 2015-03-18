package net.coderefactory.metrics;


import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class NullReporterTest {

    NullReporter reporter;

    @Before
    public void before() {
        MetricRegistry metrics = mock(MetricRegistry.class);
        reporter = NullReporter.forRegistry(metrics).build();
    }

    @Test
    public void testStart() {
        reporter.start(1, TimeUnit.HOURS);
    }

    @Test
    public void testStop() {
        reporter.stop();
    }

    @Test
    public void testClose() {
        reporter.close();
    }

    @Test
    public void testReport() {
        reporter.report(anySortedMap(), anySortedMap(), anySortedMap(), anySortedMap(), anySortedMap());
    }

    static SortedMap anySortedMap() {
        return any(SortedMap.class);
    }
}
