package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.emailTemplateParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface emailTemplateParametersRepository extends JpaRepository<emailTemplateParameters,Integer> {
    List<emailTemplateParameters> findAllByPid(int PID);
}
