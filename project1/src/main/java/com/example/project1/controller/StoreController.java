package com.example.project1.controller;


 import com.example.project1.dto.StoreDTO;
 import com.example.project1.entity.Store;
 import com.example.project1.service.StoreService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;
 import java.util.Optional;


@RestController
@RequestMapping("/api/store")
@CrossOrigin("*")
public class StoreController
{
    @Autowired
    private StoreService storeService;

    @GetMapping
    public List<StoreDTO> getAllStores(){
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Optional<Store> getStoreById(@PathVariable int id){
        return storeService.getStoreById(id);
    }

    @PostMapping
    public void createStore(@RequestBody Store store){
        storeService.createStore(store);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable int id, @RequestBody Store store){
        return storeService.updateStore(id,store);
    }

    @DeleteMapping
    public void deleteStore(@PathVariable int id){
        storeService.deleteStore(id);
    }

}
