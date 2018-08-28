package pl.training.bank.contact;

import pl.training.bank.common.ResultPage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
public class ContactRepository {
    private Map<String, Contact> contacts = new HashMap<>();
    private long counter;


    public Contact save(Contact contact) {
        contact.setId(++counter);
        contacts.put(contact.getName(), contact);
        return contact;
    }

    public ResultPage<Contact> get(int pageNumber, int pageSize) {
        return new ResultPage<>(new ArrayList<>(contacts.values()));
    }

    public Optional<Contact> getByName(String name) {
        return Optional.ofNullable(contacts.get(name));
    }

}
