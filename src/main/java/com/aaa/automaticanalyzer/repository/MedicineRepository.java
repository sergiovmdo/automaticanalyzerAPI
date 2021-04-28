package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    //All the accessing methods are provided by JpaRepository
}
