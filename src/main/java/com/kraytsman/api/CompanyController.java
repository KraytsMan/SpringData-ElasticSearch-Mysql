package com.kraytsman.api;

import com.kraytsman.entity.Company;
import com.kraytsman.entity.User;
import com.kraytsman.repository.elasticsearch.CompanyElasticRepository;
import com.kraytsman.repository.elasticsearch.UserElasticRepository;
import com.kraytsman.repository.jpa.CompanyRepository;
import com.kraytsman.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyRepository jpaRepository;

    @Autowired
    private CompanyElasticRepository elasticRepository;

    @PostMapping
    public Company create(@RequestBody Company company) {
        Company saved = jpaRepository.save(company);
        Company saved2 = elasticRepository.save(saved);
        return saved;
    }

}
