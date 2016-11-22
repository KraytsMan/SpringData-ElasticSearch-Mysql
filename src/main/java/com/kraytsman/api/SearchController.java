package com.kraytsman.api;

import com.kraytsman.dto.SearchDTO;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping
    public List<SearchDTO> get(@RequestParam String keyword) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("_all", keyword))
                .withIndices(searchIndexes())
                .build();

        return elasticsearchTemplate.query(searchQuery, response -> {
            List<SearchDTO> list = new ArrayList<>();
            SearchHits hits = response.getHits();
            Arrays.stream(hits.getHits()).forEach(hit -> {
                SearchDTO dto = new SearchDTO();
                dto.setIndex(hit.getIndex());
                dto.setId(hit.getSource().get("id").toString());
                list.add(dto);
            });
            return list;
        });
    }

    private String[] searchIndexes() {
        return elasticsearchTemplate.getClient().admin().cluster().prepareState().get().getState().getMetaData().concreteAllIndices();
    }

}
