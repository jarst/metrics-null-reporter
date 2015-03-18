package net.coderefactory.metrics;

import com.codahale.metrics.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

/**
 * NullReporter is a {@link ScheduledReporter} that doesn't report anything.
 * Use it to discard all collected metrics data.
 * @author jarst
 */
public class NullReporter extends ScheduledReporter {

    private final Logger logger;
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
        this(registry, name, LoggerFactory.getLogger(NullReporter.class), filter, rateUnit, durationUnit);
    }

    private NullReporter(final MetricRegistry registry, final String name, final Logger logger,
                         final MetricFilter filter, final TimeUnit rateUnit, final TimeUnit durationUnit) {
        super(registry, name, filter, rateUnit, durationUnit);
        this.name = name;
        this.logger = logger;
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


    public static Builder forRegistry(final MetricRegistry registry) {
        return new Builder(registry);
    }

    public static class Builder {
        private final MetricRegistry registry;
        private Logger logger;
        private String name;

        private Builder(final MetricRegistry registry) {
            this.registry = registry;
            this.logger = LoggerFactory.getLogger("metrics");
            this.name = "default";
        }

        public Builder logger(final Logger logger) {
            this.logger = logger;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public NullReporter build() {
            return new NullReporter(registry, name, logger, MetricFilter.ALL, TimeUnit.HOURS, TimeUnit.HOURS);
        }
    }
}
