package net.coderefactory.metrics;

import com.codahale.metrics.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jarst
 */
public class NullReporter extends ScheduledReporter {

    public static final Logger logger = LoggerFactory.getLogger(NullReporter.class);

    private final String name;

    /**
     * Creates a new {@link NullReporter} instance.
     *
     * @param registry the {@link com.codahale.metrics.MetricRegistry} containing the metrics this
     *                 reporter will report
     * @param name     the reporter's name
     * @param filter   the filter for which metrics to report
     */
    protected NullReporter(final MetricRegistry registry, final String name,
                           final MetricFilter filter, final TimeUnit rateUnit, final TimeUnit durationUnit) {
        super(registry, name, filter, rateUnit, durationUnit);
        this.name = name;
        logger.info("Creating NullReporter: {}", name);
    }

    @Override
    public void start(long period, TimeUnit unit) {
        logger.debug("Starting NullReporter: {}", name);
    }

    @Override
    public void stop() {
        close();
    }

    @Override
    public void close() {
        logger.debug("Stopping NullReporter: {}", name);
    }

    @Override
    public void report(SortedMap<String, Gauge> gauges,
                       SortedMap<String, Counter> counters,
                       SortedMap<String, Histogram> histograms,
                       SortedMap<String, Meter> meters,
                       SortedMap<String, Timer> timers) {
        // This method will not be invoked.
    }

}
