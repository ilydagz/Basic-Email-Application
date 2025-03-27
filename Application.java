package pa1;

import java.util.Scanner;

public class Application {
	private ListOfEmails inbox;
	private ListOfEmails archive;
	private ListOfEmails sent;
	int idcounter = 1;

	public Application() {
		inbox = new ListOfEmails();
		archive = new ListOfEmails();
		sent = new ListOfEmails();
	}

	public static void main(String[] args) {
		Application app = new Application();
		Scanner sc = new Scanner(System.in);
		String command;

		while (true) {
			System.out.print("Please enter command (as 'N', 'R', 'A', 'D', 'S', 'U', 'C' and 'exit'): ");
			command = sc.nextLine();

			if (command.equals("N")) {
				System.out.print("Enter subject: ");
				String subject = sc.nextLine();
				System.out.print("Enter message: ");
				String message = sc.nextLine();
				int time = (int) (System.currentTimeMillis() / 1000);
				Email newEmail = new Email(app.idcounter++, subject, message, time);
				app.sent.add(newEmail);

			}

			if (command.equals("R")) {
				System.out.println("Enter email id:");
				int emailId = sc.nextInt();
				sc.nextLine();
				app.sent.read(emailId);

			}

			if (command.equals("A")) {
				System.out.println("Enter email id:");
				int emailId = sc.nextInt();
				sc.nextLine();
				Email emailToArchive = app.sent.delete(emailId);
				if (emailToArchive != null) {
					app.archive.add(emailToArchive);
					System.out.println("Email " + emailId + " is archived from Sent.");
				} else {
					System.out.println("Not found any email with that id.");
				}

			}

			if (command.equals("D")) {
				System.out.println("Enter email id:");
				int emailId = sc.nextInt();
				sc.nextLine();
				Email deletedEmail = app.sent.delete(emailId);

				if (deletedEmail == null) {
					deletedEmail = app.archive.delete(emailId);

				}
				if (deletedEmail == null) {
					deletedEmail = app.inbox.delete(emailId);

				} else {
					System.out.println("Not found any email with that id.");
				}

			}

			if (command.equals("S")) {
				System.out.println("Enter folder type (as 'Inbox', 'Sent' or 'Archive'):");
				String folderType = sc.nextLine();
				ListOfEmails targetFolder = null;
				if (folderType.equals("Inbox")) {
					targetFolder = app.inbox;

				}
				if (folderType.equals("Archive")) {
					targetFolder = app.archive;

				}
				if (folderType.equals("Sent")) {
					targetFolder = app.sent;

				}
				targetFolder.showAll(true);
			}

			if (command.equals("U")) {
				System.out.println("Enter folder type (as 'Inbox', 'Sent' or 'Archive'):");
				String folderType = sc.nextLine();
				ListOfEmails targetFolder = null;
				if (folderType.equals("Inbox")) {
					targetFolder = app.inbox;
				}
				if (folderType.equals("Archive")) {
					targetFolder = app.archive;
				}
				if (folderType.equals("Sent")) {
					targetFolder = app.sent;
				}
				targetFolder.showAll(false);
			}

			if (command.equals("C")) {
				System.out.println("Enter folder type (as 'Inbox', 'Sent' or 'Archive'):");
				String folderTypeToClear = sc.nextLine();

				if (folderTypeToClear.equals("Inbox") || folderTypeToClear.equals("Sent")) {

					app.inbox.moveAllTo(app.archive);
					app.sent.moveAllTo(app.archive);

					System.out.println("The emails in Inbox and Sent are moved to Archive.");

					app.archive.clear();

				}
				if (folderTypeToClear.equals("Archive")) {
					app.archive.clear();
				}

			}

			if (command.equals("exit")) {
				break;
			}

		}
		sc.close();
	}
}
