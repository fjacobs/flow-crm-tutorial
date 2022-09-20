package nl.argo360.inventory.argo.data.service;

import nl.argo360.inventory.argo.data.entity.Account;
import nl.argo360.inventory.argo.data.entity.Asset;
import nl.argo360.inventory.argo.data.entity.Lot;
import nl.argo360.inventory.argo.data.repository.AccountRepository;
import nl.argo360.inventory.argo.data.repository.AssetRepository;
import nl.argo360.inventory.argo.data.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrmService {

    private final AccountRepository accountRepository;
    private final AssetRepository assetRepository;

    public CrmService(ContactRepository contactRepository,
                      AccountRepository accountRepository, AssetRepository assetRepository) {
        this.accountRepository = accountRepository;
        this.assetRepository = assetRepository;
    }

    public List<Account> findAllAccounts(String stringFilter) {
//        if (stringFilter == null || stringFilter.isEmpty()) {
//        } else {
        //    return contactRepository.search(stringFilter);
//        }
        return accountRepository.findAll();
    }

    public List<Asset> findAllAssets() {
        return assetRepository.findAll();
    }

    public List<Asset> findAssetsByAccount(String account) {
        List<Asset> assetList = new ArrayList<>();

        if(!account.isEmpty()){
            Account account1 = accountRepository
                    .findById(account).get();



            for (Lot lot : account1.getLots()) {
                for (Asset asset : lot.getAssets()) {
                    assetList.add(asset);
                }
            }
        }

        return assetList;
    }

//    public List<Asset> findAllAssets(String stringFilter) {
////        if (stringFilter == null || stringFilter.isEmpty()) {
////        } else {
//        //    return contactRepository.search(stringFilter);
////        }
//        return assetRepository.findAll();
//    }

    public long countContacts() {
        return accountRepository.count();
    }

    public void saveContact(Account contact) {
        if (contact == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        accountRepository.save(contact);
    }

    public Collection<String> getAllAccountNames() {
        return accountRepository
                .findAll()
                .stream().map(Account::getCompanyName)
                .collect(Collectors.toList());
    }
}
