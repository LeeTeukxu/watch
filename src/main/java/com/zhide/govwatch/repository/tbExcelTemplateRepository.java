package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.tbExcelTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface tbExcelTemplateRepository extends JpaRepository<tbExcelTemplate,Integer> {
    Optional<tbExcelTemplate> findFirstByCode(String Code);
}
