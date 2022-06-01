package jmh.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class FibonacciBechmark {

	private static final int SERIES_NUM = 10;

	@Benchmark
	public long benchmarkRecursiveFibonacci() {
		long[] array = new long[SERIES_NUM + 1];
		return recursiveFibonacci(SERIES_NUM, array);
	}

	@Benchmark
	public long benchmarkForLoopFibonacci() {
		return forLoopFibonacci(SERIES_NUM);
	}

	private long recursiveFibonacci(int n, long[] array) {
		if (n <= 1)
			return n;
		
		if (array[n] != 0) {
			return array[n];
		}

		long val =  recursiveFibonacci(n - 1, array) + recursiveFibonacci(n - 2, array);
		array[n] = val;
		return val;
	}

	private long forLoopFibonacci(int n) {
		int n1 = 0, n2 = 1, n3 = 0, i;

		for (i = 2; i < n; ++i) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
		}

		return n3;
	}
}
