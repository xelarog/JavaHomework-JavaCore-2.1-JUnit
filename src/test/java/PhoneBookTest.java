import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PhoneBookTest {
    @Test
    @DisplayName("Тест на успешное создание группы контактов")
    public void successCreateGroupContacts() {
        var phoneBook = new PhoneBook();
        var groupName1 = "Друзья";
        var groupName2 = "Семья";
        var groupName3 = "Друзья";

        assertThat(phoneBook, notNullValue());

        assertThat(phoneBook.createGroupContacts(groupName1), is(true));
        assertThat(phoneBook.createGroupContacts(groupName2), is(true));
        assertThat(phoneBook.createGroupContacts(groupName3), is(false));

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

        assertThat(phoneBook, notNullValue());

        assertThat(phoneBook.addContactToGroup(groupName1, contact1), is(true));
        assertThat(phoneBook.addContactToGroup(groupName2, contact2), is(true));
        assertThat(phoneBook.addContactToGroup(groupName3, contact2), is(true));

        assertThat(phoneBook.addContactToGroup(groupName2, contact2), is(false));
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
        phoneBook.addContactToGroup(groupName1, contact1);
        phoneBook.addContactToGroup(groupName1, contact2);
        phoneBook.addContactToGroup(groupName2, contact1);

        var result1 = phoneBook.getContactsByGroup(groupName1);

        assertThat(phoneBook, notNullValue());
        assertThat(result1, notNullValue());
        assertThat(result1, hasItems(contact1, contact2));
        assertThat(result1, equalTo(expect1));
    }

    @Test
    @DisplayName("Тест на успешный поиск контакта по номеру")
    public void successSearchContactByPhoneNumber() {
        var phoneBook = new PhoneBook();
        var groupName1 = "Друзья";
        var groupName2 = "Семья";

        var contact1 = new Contact("Коля", "11111");
        var contact2 = new Contact("Петя", "22222");

        phoneBook.addContactToGroup(groupName1, contact1);
        phoneBook.addContactToGroup(groupName1, contact2);

        var phoneNumber1 = "22222";
        var expect1 = contact2.getName();
        var result1 = phoneBook.getContactByPhoneNumber(phoneNumber1);

        var phoneNumber2 = "55555";
        var expect2 = "Контакта с таким номером не существует";
        var result2 = phoneBook.getContactByPhoneNumber(phoneNumber2);

        assertThat(phoneBook, notNullValue());
        assertThat(result1, notNullValue());
        assertThat(result1, equalTo(expect1));

        assertThat(result2, notNullValue());
        assertThat(result2, equalTo(expect2));

    }

    @Test
    @DisplayName("Тест класса Contact")
    public void newTestHamcrestContactClass() {

        var contact1 = new Contact("Коля", "11111");
        var contact2 = new Contact("Петя", "22222");

        assertThat(contact1.getName(), not(isEmptyString()));
        assertThat(contact1.getPhoneNumber(), notNullValue());

        assertThat(contact1, instanceOf(Contact.class));

        assertThat(contact1, hasProperty("name"));
        assertThat(contact1, hasProperty("phoneNumber"));

        assertThat(contact2, hasProperty("name", equalTo("Петя")));
        assertThat(contact2, hasProperty("phoneNumber", equalTo("22222")));

        assertThat(contact1, hasToString("Коля - 11111"));

    }

    @Test
    @DisplayName("Тест PhoneBook класса")
    public void newTestHamcrestPhoneBookClass() {

        var phoneBook = new PhoneBook();
        var groupName1 = "Друзья";
        var groupName2 = "Семья";

        var contact1 = new Contact("Коля", "11111");
        var contact2 = new Contact("Петя", "22222");

        var list = new ArrayList<Contact>();
        list.add(contact1);
        list.add(contact2);

        phoneBook.addContactToGroup(groupName1, contact1);
        phoneBook.addContactToGroup(groupName1, contact2);
        phoneBook.addContactToGroup(groupName2, contact1);

        assertThat(phoneBook.getContacts(), notNullValue());
        assertThat(phoneBook.getContactsByGroup(groupName1), hasSize(2));
        assertThat(phoneBook.getContacts(), hasKey(groupName1));
        assertThat(phoneBook.getContacts(), hasValue(list));
        assertThat(phoneBook.getContacts(), hasEntry(groupName1, list));

    }
}
