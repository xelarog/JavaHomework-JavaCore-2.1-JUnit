import java.util.*;

public class PhoneBook {
    private Map<String, List<Contact>> contacts;

    public PhoneBook() {
        contacts = new HashMap<>();
    }

    public boolean createGroupContacts(String groupName) {
        if (contacts.containsKey(groupName)) {
            System.out.println("Группа с названием " + groupName + " уже существует");
            return false;
        }
        makeGroup(groupName);
        return true;
    }

    private void makeGroup(String groupName) {
        contacts.put(groupName, new ArrayList<>());
    }

    public boolean addContactToGroup(String groupName, Contact contact) {
        if (!contacts.containsKey(groupName))
            makeGroup(groupName);
        if (contacts.get(groupName).contains(contact)) {
            System.out.println("===================================================");
            System.out.println("Ошибка добавления контакта");
            System.out.println("Контакт " + contact + " уже содержится в группе \"" + groupName + "\"");
            return false;
        }
        contacts.get(groupName).add(contact);
        return true;
    }

    public List<Contact> getContactsByGroup(String groupName) {
        return contacts.get(groupName);
    }

    public String getContactByPhoneNumber(String phoneNumber) {

        for (List<Contact> contactList : contacts.values()) {
            for (Contact contact : contactList) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    return contact.getName();
                }
            }
        }
        return "Контакта с таким номером не существует";
    }

    public Map<String, List<Contact>> getContacts() {
        return contacts;
    }
}
