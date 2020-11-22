package cod.currency.configuration.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.Before;

/*@Aspect
@Component*/
public class MetricsAspect {

    private Counter counter;

    public MetricsAspect(MeterRegistry meterRegistry) {
        //this.counter = meterRegistry.counter("account.fetch", "type", "fromAspect");
    }

    @Before("execution(* *.*(..))")
    public void increment() {
        counter.increment();
    }
}
