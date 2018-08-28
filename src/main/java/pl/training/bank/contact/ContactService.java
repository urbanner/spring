package pl.training.bank.contact;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import pl.training.bank.common.ResultPage;
import pl.training.bank.generator.AccountNumberGenerator;

import java.util.Optional;

@Log
@AllArgsConstructor
public class ContactService {

    private ContactRepository contactRepository;

    public Contact createContact() {
        String contactName = "szymon";
        Contact contact = new Contact(contactName);
        return contactRepository.save(contact);
    }

    public ResultPage<Contact> getContacts(int pageNumber, int pageSize) {
        return contactRepository.get(pageNumber, pageSize);
    }

    public Optional<Contact> getContacts(String name) {
        return contactRepository.getByName(name);
    }

    public void init() {
        log.info("### Initializing: " + getClass().getSimpleName());
    }

    public void destroy() {
        log.info("### Closing: " + getClass().getSimpleName());
    }

}
