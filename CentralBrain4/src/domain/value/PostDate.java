package domain.value;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostDate extends Value {
	private String stringValue;
	private LocalDateTime ldtValue;
//	2016-06-21T10:59:31.883

	public PostDate(String stringValue) {
		super();
		this.stringValue = stringValue;
		this.ldtValue = LocalDateTime.parse( stringValue );
	}

	public PostDate(LocalDateTime ldtValue) {
		super();
		this.ldtValue = ldtValue;
		this.stringValue =ldtValue.format( DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	public String getString() {
		return stringValue;
	}

	public LocalDateTime getLocalDateTime() {
		return ldtValue;
	}

}
