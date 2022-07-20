package com.sofka.controller;

import com.sofka.domain.Contact;
import com.sofka.service.ContactService;
import com.sofka.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    private Response response = new Response();

    @GetMapping(path = "/")
    public Map<String, String> index() {
        var respuesta = new HashMap<String, String>();
        respuesta.put("message", "Hola Mundo");
        return respuesta;
    }


    @GetMapping(path = "/contacts")
    public List<Contact> listado() {

        return contactService.list();


    }

    @PostMapping(path = "/contact")
    public ResponseEntity<Response> create(@RequestBody Contact contact) {
        response.data = contact;
        try {
            log.info("to create: {}", contact);
            contactService.save(contact);
            return new ResponseEntity<Response>(response, HttpStatus.CREATED);
        } catch (Exception exc) {
            response.status = exc.getCause().toString();
            response.error = true;
            if (Pattern.compile("(contact.name_and_cellphone_UNIQUE)").matcher(exc.getMessage()).find()) {
                response.message = "contact already exist";
                return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
            } else {
                response.message = exc.getMessage();
                return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(path = "/contact/{id}")
    public ResponseEntity<Contact> erase(Contact contact) {
        log.info("Contacto a borrar: {}", contact);
        contactService.delete(contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping(path = "/contact/{id}")
    public ResponseEntity<Contact> update(@RequestBody Contact contact, @PathVariable("id") Long id) {
        log.info("Contacto a modificar: {}", contact);
        contactService.update(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PatchMapping(path = "/contact/name/{id}")
    public ResponseEntity<Contact> updateName(@RequestBody Contact contact, @PathVariable("id") Long id) {
        log.info("Modify name: {}", contact);
        contactService.updateName(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PatchMapping(path = "/contact/cellphone/{id}")
    public ResponseEntity<Contact> updateCellphone(@RequestBody Contact contact, @PathVariable("id") Long id) {
        log.info("Modify cellphone: {}", contact);
        contactService.updateCellphone(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PatchMapping(path = "/contact/email/{id}")
    public ResponseEntity<Contact> updateEmail(@RequestBody Contact contact, @PathVariable("id") Long id) {
        log.info("Modify email: {}", contact);
        contactService.updateEmail(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PatchMapping(path = "/contact/dob/{id}")
    public ResponseEntity<Contact> updateDob(@RequestBody Contact contact, @PathVariable("id") Long id) {
        log.info("Modify Date of Birth: {}", contact);
        contactService.updateDob(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
    @PostMapping(path = "/contact/logicDelete/{id}")
    public ResponseEntity<Contact> logicDelete(@RequestBody Contact contact, @PathVariable("id") Long id) {
        log.info("Modify logicDelete: {}", contact);
        contactService.logicDelete(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

}
