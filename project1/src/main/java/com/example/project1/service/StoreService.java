package com.example.project1.service;


import com.example.project1.dto.StoreDTO;
import com.example.project1.entity.Store;
import com.example.project1.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<StoreDTO> getAllStores(){
        return storeRepository.findAllStores();
    }

    public Optional<Store> getStoreById(int id){
        return Optional.ofNullable(storeRepository.findStoreById(id));
    }

    public void createStore(Store store){
        storeRepository.save(store);
    }

    public void deleteStore(int id){
        storeRepository.deleteStoreById(id);
    }

    public Store updateStore(int id,Store updateStore){
        return storeRepository.findById(id).map(store ->{
            store.setNameStore(updateStore.getNameStore());
            return storeRepository.save(updateStore);
        }).orElse(null);
    }
}

