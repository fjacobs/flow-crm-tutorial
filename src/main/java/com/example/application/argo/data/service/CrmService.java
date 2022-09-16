package com.example.application.argo.data.service;

import com.example.application.argo.data.entity.Account;
import com.example.application.argo.data.entity.Asset;
import com.example.application.argo.data.entity.Contact;
import com.example.application.argo.data.repository.AccountRepository;
import com.example.application.argo.data.repository.AssetRepository;
import com.example.application.argo.data.repository.ContactRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

@Service
public class CrmService {

    private final AccountRepository companyRepository;
    private final AssetRepository assetRepository;

    public CrmService(ContactRepository contactRepository,
                      AccountRepository companyRepository, AssetRepository assetRepository) {
        this.companyRepository = companyRepository;
        this.assetRepository = assetRepository;
    }

    public List<Account> findAllAccounts(String stringFilter) {
//        if (stringFilter == null || stringFilter.isEmpty()) {
//        } else {
        //    return contactRepository.search(stringFilter);
//        }
        return companyRepository.findAll();
    }

    public List<Asset> findAllAssets(String stringFilter) {
//        if (stringFilter == null || stringFilter.isEmpty()) {
//        } else {
        //    return contactRepository.search(stringFilter);
//        }
        return assetRepository.findAll();
    }

    public long countContacts() {
        return companyRepository.count();
    }


    public void saveContact(Account contact) {
        if (contact == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        companyRepository.save(contact);
    }


}
