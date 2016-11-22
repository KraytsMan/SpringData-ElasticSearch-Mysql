package com.kraytsman.repository.elasticsearch;

import com.kraytsman.entity.Company;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyElasticRepository extends ElasticsearchCrudRepository<Company, String>{

}
