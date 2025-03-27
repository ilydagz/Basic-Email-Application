package pa1;

import java.util.LinkedList;

public class ListOfEmails {
	LinkedList<Email> emailList;

	public ListOfEmails() {
		this.emailList = new LinkedList<>();
	}

	public void add(Email e) {
		emailList.add(e);
	}

	public void read(int id) {
		for (int i = 0; i < emailList.size(); i++) {
			Email email = emailList.get(i);
			if (email.getId() == id) {
				email.markAsRead();
				System.out.println(email);
				return;
			}
		}
	}

	public Email delete(int id) {
		for (int i = 0; i < emailList.size(); i++) {
			Email email = emailList.get(i);
			if (email.getId() == id) {
				emailList.remove(i);
				System.out.println("Email has been deleted.");
				return email;

			}
		}
		return null;
	}

	public void showAll(boolean flag) {
		for (int i = 0; i < emailList.size(); i++) {
			Email email = emailList.get(i);
			if (flag || !email.isRead()) {
				email.printTruncated();
			} else {
				System.out.println("The folder is read.");
			}
		}
	}

	public void clear() {
		emailList.clear();
		System.out.println("All emails have been cleared from the list.");
	}

	public void moveAllTo(ListOfEmails target) {
		target.emailList.addAll(this.emailList);
	}
}
