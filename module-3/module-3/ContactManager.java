import java.util.*;

public class ContactManager {
  private HashMap<String, Contact> contacts = new HashMap<>();

  public ContactManager() {}

  public Contact lookup(String contactName) {
    return contacts.get(contactName);
  }

  public void add(String name, String number) {
    contacts.put(name, new Contact(name, number));
  }

  public void remove(String name) {
    contacts.remove(name);
  }

  public HashMap<String, Contact> getContacts() {
    return contacts;
  }

  public static void main(String[] arguments) {
    ContactManager manager = new ContactManager();

    manager.add("Ada Lovelace", "+1 617 555 0101");
    manager.add("Contact 2", "234");
    manager.add("Contact 3", "345");
    manager.add("Contact 4", "456");
    manager.add("Contact 5", "567");

    Contact contact1 = manager.lookup("Ada Lovelace");
    System.out.println("Name: " + contact1.getName() + " Number: " + contact1.getPhone());

    ArrayList<Contact> contactList = new ArrayList<>(manager.getContacts().values());
    contactList.sort((a, b) -> a.getName().compareTo(b.getName()));

    System.out.println();
    System.out.println("=== All Contacts ===");
    for (Contact c : contactList) {
      System.out.println("Phone: " + c.getName() + ", Number: " + c.getPhone());
    }

    manager.remove("Contact 3");

    contactList = new ArrayList<>(manager.getContacts().values());
    contactList.sort((a, b) -> a.getName().compareTo(b.getName()));
    System.out.println();
    System.out.println("=== Filtered Contacts ===");
    for (Contact c : contactList) {
      System.out.println("Phone: " + c.getName() + ", Number: " + c.getPhone());
    }
  }
}
