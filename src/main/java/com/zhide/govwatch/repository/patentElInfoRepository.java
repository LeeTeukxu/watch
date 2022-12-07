package com.zhide.govwatch.repository;

import com.zhide.govwatch.model.patentElInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface patentElInfoRepository extends ElasticsearchRepository<patentElInfo,String> {
    Page<patentElInfo> findAllByCountynameContaining(String countyName, Pageable pageable);

    List<patentElInfo> findAllByShenqinghIn(List<String> listWord);
}
