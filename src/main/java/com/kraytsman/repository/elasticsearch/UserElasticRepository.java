package com.kraytsman.repository.elasticsearch;

import com.kraytsman.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserElasticRepository extends ElasticsearchCrudRepository<User, String> {

}
