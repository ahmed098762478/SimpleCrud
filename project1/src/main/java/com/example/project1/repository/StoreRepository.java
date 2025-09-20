package com.example.project1.repository;

import com.example.project1.dto.StoreDTO;
import com.example.project1.entity.Store;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query("select new com.example.project1.dto.StoreDTO(s.id, s.nameStore) from Store s")
    List<StoreDTO> findAllStores();

    @Query("select s from Store s where s.id = ?1")
    Store findStoreById(int id);

    @Modifying
    @Transactional
    @Query("delete from Store s where s.id = ?1")
    void deleteStoreById(int id);

}
