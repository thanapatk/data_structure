import java.util.Random;

public class TimeComplexity {
	private static final int WARM_UP_COUNT = 10;
	private static final int[] ITEM_COUNTS = { 100, 1000, 10000, 100000, 1000000 };

	public static void main(String[] args) throws Exception {
		for (int count : ITEM_COUNTS) {
			// Warm-up phase
			for (int i = 0; i < WARM_UP_COUNT; i++) {
				testQ(ITEM_COUNTS[2]);
			}

			// Timing phase
			long addTime = 0;
			long popTime = 0;

			for (int i = 0; i < WARM_UP_COUNT; i++) {
				long[] times = testQ(count);
				addTime += times[0];
				popTime += times[1];
			}

			addTime /= WARM_UP_COUNT;
			popTime /= WARM_UP_COUNT;

			System.out.println("ItemCount: " + count);
			System.out.println("AddTime: " + addTime);
			System.out.println("PopTime: " + popTime);
			System.out.println();
		}
	}

	private static void swap(int[] d, int i, int j) {
		int t = d[i];
		d[i] = d[j];
		d[j] = t;
	}

	static long[] testQ(int count) throws Exception {
		Heap h = new Heap();
		Random random = new Random(1234567);
		int[] random_arr = new int[count];

		for (int i = 0; i != count; i++) {
			random_arr[i] = random.nextInt(count);
		}

		long startTime = System.nanoTime();

		for (int i = 0; i < count; i++) {
			h.add(random_arr[i]);
		}
		long addTime = System.nanoTime() - startTime;

		startTime = System.nanoTime();
		for (int i = 0; i < count; i++) {
			h.pop();
		}
		long popTime = System.nanoTime() - startTime;

		return new long[] { addTime, popTime };
	}
}
