import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PhoneBookTest {
    @Test
    @DisplayName("Тест на успешное создание группы контактов")
    public void successCreateGroupContacts() {
        var phoneBook = new PhoneBook();
        var groupName1 = "Друзья";
        var groupName2 = "Семья";
        var groupName3 = "Друзья";

        Assertions.assertNotNull(phoneBook);

        Assertions.assertTrue(phoneBook.createGroupContacts(groupName1));
        Assertions.assertTrue(phoneBook.createGroupContacts(groupName2));
        Assertions.assertFalse(phoneBook.createGroupContacts(groupName3));
    }

    @Test
    @DisplayName("Тест на успешное добавление контакта в группу/группы")
    public void successAddContactToGroups() {
        var phoneBook = new PhoneBook();
        var groupName1 = "Друзья";
        var groupName2 = "Семья";
        var groupName3 = "Работа";

        var contact1 = new Contact("Коля", "11111");
        var contact2 = new Contact("Петя", "22222");

        Assertions.assertNotNull(phoneBook);

        Assertions.assertTrue(phoneBook.addContactToGroup(groupName1, contact1));
        Assertions.assertTrue(phoneBook.addContactToGroup(groupName2, contact2));
        Assertions.assertTrue(phoneBook.addContactToGroup(groupName3, contact2));

        Assertions.assertFalse(phoneBook.addContactToGroup(groupName2, contact2));

    }

    @Test
    @DisplayName("Тест на успешный поиск контактов по группе")
    public void successSearchContactsByGroup() {
        var phoneBook = new PhoneBook();
        var groupName1 = "Друзья";
        var groupName2 = "Семья";

        var contact1 = new Contact("Коля", "11111");
        var contact2 = new Contact("Петя", "22222");

        var expect1 = new ArrayList<Contact>();
        expect1.add(contact1);
        expect1.add(contact2);
        phoneBook.addContactToGroup(groupName1,contact1);
        phoneBook.addContactToGroup(groupName1,contact2);
        phoneBook.addContactToGroup(groupName2,contact1);

        var result1 = phoneBook.getContactsByGroup(groupName1);

        Assertions.assertNotNull(phoneBook);
        Assertions.assertNotNull(result1);
        Assertions.assertEquals(expect1, result1);


    }

    @Test
    @DisplayName("Тест на успешный поиск контакта по номеру")
    public void successSearchContactByPhoneNumber() {
        var phoneBook = new PhoneBook();
        var groupName1 = "Друзья";
        var groupName2 = "Семья";

        var contact1 = new Contact("Коля", "11111");
        var contact2 = new Contact("Петя", "22222");


        phoneBook.addContactToGroup(groupName1,contact1);
        phoneBook.addContactToGroup(groupName1,contact2);

        var phoneNumber1 = "22222";
        var expect1 = contact2.getName();
        var result1 = phoneBook.getContactByPhoneNumber(phoneNumber1);

        var phoneNumber2 = "55555";
        var expect2 = "Контакта с таким номером не существует";
        var result2 = phoneBook.getContactByPhoneNumber(phoneNumber2);

        Assertions.assertNotNull(phoneBook);
        Assertions.assertNotNull(result1);
        Assertions.assertEquals(expect1, result1);

        Assertions.assertNotNull(result2);
        Assertions.assertEquals(expect2, result2);
    }

}
