package com.example.mongo.manager;

import com.example.mongo.pojo.Gallery;
import com.example.mongo.pojo.Picture;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: cevier.wei
 * @date: 2023/2/7 16:05
 */
@Repository
public class GalleryManager {
    @Resource
    private MongoTemplate mongoTemplate;

    public void insertGallery(Gallery gallery) {
        mongoTemplate.insert(gallery);
    }

    public List<Picture> getPicture(Long galleryId, List<Long> pictureIds) {
        Query query = new Query(Criteria.where("_id").is(galleryId).and("pictures.id").in(pictureIds));
        List<Gallery> galleries = mongoTemplate.find(query, Gallery.class);
        if (CollectionUtils.isEmpty(galleries)) {
            return new ArrayList<>();
        }
        return galleries.get(0).getPictures();
    }

    public List<Picture> getPictures(Long galleryId, List<Long> pictureIds) {
        //1. 指定查询主文档
        MatchOperation match1 = Aggregation.match(Criteria.where("_id").is(galleryId));
        //2. 拆分内嵌文档(将数组中的每一个元素转为每一条文档)
        UnwindOperation unwind = Aggregation.unwind("pictures");
        //3. 指定查询子文档
        MatchOperation match2 = Aggregation.match(Criteria.where("pictures._id").in(pictureIds));
//        //4. 限制查询条数
//        LimitOperation limit = Aggregation.limit(1000);
        //5. 指定投影，返回哪些字段
        ProjectionOperation project = Aggregation.project( "pictures.id", "pictures.name", "pictures.description");
        //创建管道查询对象
        Aggregation aggregation = Aggregation.newAggregation(match1, unwind, match2, project);
//        Aggregation aggregation = Aggregation.newAggregation(match1, unwind, match2, limit, project);
        AggregationResults<Picture> results = mongoTemplate.aggregate(aggregation, "gallery", Picture.class);

        return results.getMappedResults();
    }
}
