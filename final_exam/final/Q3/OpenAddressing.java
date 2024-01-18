import java.util.Arrays;

public class OpenAddressing {
	protected static int DEFAULT_SIZE = 11;
	protected static final int DELETED = -9999;
	protected static double MAXFACTOR = 0.5;
	protected int currentSize = 0;
	protected int[] array;

	public OpenAddressing() {
		this(DEFAULT_SIZE);
	}

	public OpenAddressing(int size) {
		array = new int[size];
	}

	public String toString() {
		return Arrays.toString(array);
	}
}
