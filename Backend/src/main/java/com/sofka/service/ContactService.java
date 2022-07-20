package com.sofka.service;

import com.sofka.dao.ContactDao;
import com.sofka.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService {

    @Autowired // para inyectar ContactDao
    private ContactDao contactDao;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> list() {
        //List<Contact> contacts = null;
        try {
           return  (List<Contact>) contactDao.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        //return contacts;
    }

    @Override
    @Transactional
    public Contact save(Contact contact) {
        return contactDao.save(contact);
    }

    @Override
    @Transactional
    public Contact update(Long id, Contact contact) {
        contact.setId(id);
        return contactDao.save(contact);
    }

    @Transactional
    public void updateName(Long id, Contact contact) {
        contactDao.updateName(id, contact.getName());
    }

    @Transactional
    public void updateCellphone(Long id, Contact contact) {
        contactDao.updateCellphone(id, contact.getCellphone());
    }

    @Transactional
    public void updateEmail(Long id, Contact contact) {
        contactDao.updateEmail(id, contact.getEmail());
    }

    @Transactional
    public void updateDob(Long id, Contact contact) {
        contactDao.updateDob(id, contact.getDob());
    }

    @Transactional
    public void logicDelete(Long id, Contact contact) {
        contactDao.logicDelete(id, contact.getLogicDelete());
    }

    @Override
    @Transactional
    public void delete(Contact contact) {
        contactDao.delete(contact);
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<Contact> findUser(Contact contact) {
        return contactDao.findById(contact.getId());
    }
}
