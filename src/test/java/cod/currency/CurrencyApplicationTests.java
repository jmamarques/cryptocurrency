package cod.currency;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.autoconfigure.metrics.export.wavefront.WavefrontMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {WavefrontMetricsExportAutoConfiguration.class})
class CurrencyApplicationTests {

	@Test
	void contextLoads() {
	}

}
