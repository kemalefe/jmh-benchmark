package jmh.benchmark;

import java.io.IOException;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Integer MEASUREMENT_ITERATIONS = 5;
	private static final Integer WARMUP_ITERATIONS = 2;

	public static void main(String[] args) throws RunnerException {
		Options jmhRunnerOptions = new OptionsBuilder()
				// set the class name regex for benchmarks to search for to the current class
				.include(FibonacciBechmark.class.getSimpleName())
				.warmupIterations(WARMUP_ITERATIONS)
				.measurementIterations(MEASUREMENT_ITERATIONS)
				// do not use forking or the benchmark methods will not see references stored within its class
				.forks(0)
				// do not use multiple threads
				.threads(1)
				.shouldDoGC(true)
				.shouldFailOnError(true)
				.resultFormat(ResultFormatType.JSON)
				.result("C:\\Users\\kemal.efe\\Desktop\\benchmark-results") // set this to a valid filename if you want reports
				.shouldFailOnError(true)
				.jvmArgs("-server")
				.build();

		Runner r = new Runner(jmhRunnerOptions);
		r.run();
	}

}
