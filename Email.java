package pa1;

public class Email {
	String subject;
	int id;
	String message;
	int time;
	boolean isRead;

	public Email(int id, String subject, String message, int time) {
		this.id = id;
		this.subject = subject;
		this.message = message;
		this.time = (int) (System.currentTimeMillis() / 1000);
		this.isRead = false; // default as unread
	}

	public String getSubject() {
		return subject;
	}

	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public int getTime() {
		return time;
	}

	public boolean isRead() {
		return isRead;
	}

	public void markAsRead() {
		this.isRead = true;
	}

	public String timeConverter() {
		long millisInDay = 86400000;

		// Convert milliseconds to total days and remaining milliseconds
		long days = System.currentTimeMillis() / millisInDay;

		int years = 1970; // Start from epoch year

		while (days >= 365) {
			if (isLeapYear(years)) {
				if (days < 366)
					break;
				days -= 366;
			} else {
				days -= 365;
			}
			years++;
		}

		int[] daysInMonth;
		if (isLeapYear(years)) {
			daysInMonth = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		} else {
			daysInMonth = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		}

		int month = 0;
		while (days >= daysInMonth[month]) {
			days -= daysInMonth[month];
			month++;
		}

		int day = (int) days + 1;

		return String.format("%02d/%02d/%04d", day, month + 1, years); // Format as dd/mm/yyyy
	}

	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public String toString() {
		return "Email ID: " + id + "\n" + "Subject: " + subject + "\n" + "Message: " + message + "\n"
				+ "Time received: " + timeConverter() + "\n" + "Status: Sent Read";
	}

	public void printTruncated() {

		System.out.printf("Email ID: %d\n", id);
		System.out.printf("Subject: %.25s\n", subject);
		System.out.printf("Message: %.40s\n", message);
		System.out.printf("Time: %s\n", time);
		System.out.print("Read: ");
		if (isRead) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}
}
