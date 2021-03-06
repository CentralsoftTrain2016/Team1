package domain.value;

import javafx.util.Duration;

public class InvisiblePeriod {
	private String value;
	private Duration dvalue;

	public InvisiblePeriod(String h, String m, String s) {
		super();
		int hi = Integer.parseInt(h);
		int mi = Integer.parseInt(m);
		int si = Integer.parseInt(s);

		dvalue = Duration.seconds(3600 * hi + 60 * mi + si);
		h = String.format("%02d", hi);
		m = String.format("%02d", mi);
		s = String.format("%02d", si);
		this.value = h + ":" + m + ":" + s;
	}

	public InvisiblePeriod(String value) {
		super();
		this.value = value;
		// 12:13:32
		dvalue = Duration.seconds(3600 * Integer.parseInt(value.substring(0, 2))
				+ 60 * Integer.parseInt(value.substring(3, 5))
				+ Integer.parseInt(value.substring(6, 8)));
	}



	public String get() {
		return value;
	}

	public Duration getDuration() {
		return this.dvalue;
	}
}
