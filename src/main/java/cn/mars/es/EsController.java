package cn.mars.es;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
public class EsController {

    @Autowired
    private TransportClient client;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/book/novel/{id}")
    @ResponseBody
    public ResponseEntity search(@PathVariable(required = true) String id){
        GetResponse response = client.prepareGet("book", "novel", id).get();
        if(!response.isExists()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response.getSource(), HttpStatus.OK);
    }

    @PostMapping("add/book/novel")
    @ResponseBody
    public ResponseEntity add(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "author") String author,
            @RequestParam(name = "word_count") int wordCount,
            @RequestParam(name = "publish_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date publishDate){
        try {
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("author", author)
                    .field("word_count", wordCount)
                    .field("publish_date", publishDate.getTime())
                    .endObject();
            IndexResponse response = client.prepareIndex("book", "novel")
                    .setSource(content)
                    .get();
            return new ResponseEntity(response.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("delete/book/novel/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable(required = true) String id){
        DeleteResponse response = client.prepareDelete("book", "novel", id)
                .get();
        return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
    }

    @PutMapping("update/book/novel")
    @ResponseBody
    public ResponseEntity update(
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "word_count", required = false) Integer wordCount,
            @RequestParam(name = "publish_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate
    ){
        UpdateRequest update = new UpdateRequest("book", "novel", id);

        try {
            XContentBuilder jsonBuilder = XContentFactory.jsonBuilder().startObject();
            if(title != null){
                jsonBuilder.field("title", title);
            }
            if(author != null){
                jsonBuilder.field("author", author);
            }
            if(wordCount != null){
                jsonBuilder.field("word_count", wordCount);
            }
            if(publishDate != null){
                jsonBuilder.field("publish_date", publishDate.getTime());
            }
            jsonBuilder.endObject();
            update.doc(jsonBuilder);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            UpdateResponse response = client.update(update).get();
            return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("query/book/novel")
    @ResponseBody
    public ResponseEntity query(
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "gt_word_count", defaultValue = "0") int gtWordCount,
            @RequestParam(name = "lt_word_count", required = false) Integer ltWordCount
    ){
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(author != null){
            boolQuery.must(QueryBuilders.matchQuery("author", author));
        }
        if(title != null){
            boolQuery.must(QueryBuilders.matchQuery("title", title));
        }
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("word_count")
                .from(gtWordCount);
        if(ltWordCount != null && ltWordCount > gtWordCount){
            rangeQuery.to(ltWordCount);
        }

        boolQuery.filter(rangeQuery);
        SearchRequestBuilder builder = client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);
        System.out.println(builder);

        SearchResponse response = builder.get();
        List<Map<String, Object>> result = new ArrayList<>();
        for(SearchHit hit : response.getHits()){
            result.add(hit.getSource());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }


	public static void main(String[] args) {
		SpringApplication.run(EsController.class, args);
	}
}
