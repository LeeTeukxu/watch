package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.emailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface emailTemplateRepository extends JpaRepository<emailTemplate,Integer> {
        List<emailTemplate> findAllByCode(String Code);
}
