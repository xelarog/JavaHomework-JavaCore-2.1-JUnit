public class Main {

    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();

//  создание группы без контактов
        phoneBook.createGroupContacts("Друзья");

//  добавление контакта в одну или несколько групп ( если группы не существует, то она создаётся)
        phoneBook.addContactToGroup("Друзья", new Contact("Николай", "89106587804"));
        phoneBook.addContactToGroup("Друзья", new Contact("Алиса", "89206704587"));
        phoneBook.addContactToGroup("Друзья", new Contact("Денис", "89357705689"));
        phoneBook.addContactToGroup("Работа", new Contact("Николай", "89106587804"));
        phoneBook.addContactToGroup("Работа", new Contact("Сергей", "89158754521"));
        phoneBook.addContactToGroup("Семья", new Contact("Елена", "89206903231"));
        phoneBook.addContactToGroup("Семья", new Contact("Полина", "89206955514"));

//  поиск контактов по группе
        findContactsByGroup(phoneBook, "Работа");
        findContactsByGroup(phoneBook, "Друзья");

//  проверка добавления одинаковых контактов в одну группу
        phoneBook.addContactToGroup("Друзья", new Contact("Алиса", "89206704587"));

//  поиск контакта по номеру телефона
        findContactByPhoneNumber(phoneBook, "89206903231");
        findContactByPhoneNumber(phoneBook, "89206903232");
    }

    public static void findContactByPhoneNumber(PhoneBook phoneBook, String phoneNumber) {
        System.out.println("===================================================");
        System.out.println("Поиск контакта по номеру " + phoneNumber + " :");
        String contactName = phoneBook.getContactByPhoneNumber(phoneNumber);
        System.out.println(contactName);
    }

    public static void findContactsByGroup(PhoneBook phoneBook, String groupName) {
        System.out.println("===================================================");
        System.out.println("Список контактов в группе \"" + groupName + "\":");
        for (Contact contact : phoneBook.getContactsByGroup(groupName))
            System.out.println(contact);
    }
}
