package cod.currency.configuration.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.Duration;

@Aspect
@Component
public class MetricsAspect {

    private final Counter counter;
    private final Counter counterFail;
    private final Timer timer;

    public MetricsAspect(MeterRegistry meterRegistry) {

        this.counter = Counter.builder("controllerCounter")
                .tag("type", "fromAspect")
                .description("The number of requests ever done in all controllers")
                .baseUnit("numeric")
                .register(meterRegistry);
        this.counterFail = Counter.builder("controllerCounterFail")
                .tag("type", "fromAspect")
                .description("The number of requests already made that failed to run for all controllers")
                .baseUnit("numeric")
                .register(meterRegistry);
        this.timer = Timer.builder("controllerTimer")
                .tag("type", "fromAspect")
                .description("Execution time for controllers")
                .register(meterRegistry);
    }

    @Around("execution(* cod.currency.controller..*(..)))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        counter.increment();

        StopWatch stopWatch = new StopWatch();

        Object proceed = null;
        stopWatch.start();

        try {
            proceed = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            counterFail.increment();
            throw throwable;
        } finally {
            stopWatch.stop();
        }

        timer.record(Duration.ofDays(stopWatch.getTotalTimeMillis()));

        return proceed;
    }
}
