package com.example.application.data.service;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CrmService {


    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {

        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public List<Contact> findAllContacts(String filterByText) {
        if (filterByText == null || filterByText.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(filterByText);
        }
    }

    public long countContacts() {
        return contactRepository.count();
    }

    public void deleteContact(Contact c) {
        contactRepository.delete(c);
    }

    public void saveContact(Contact c) {
        if (c == null) {
            System.err.println("Contact is null");
            return;
        }
        contactRepository.save(c);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Status> findAllStatuses() {
        return statusRepository.findAll();
    }

}
