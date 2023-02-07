package com.example.mongo.manager;

import com.example.mongo.pojo.Student;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author: cevier.wei
 * @date: 2023/2/7 16:05
 */
@Repository
public class StudentManager {
    @Resource
    private MongoTemplate mongoTemplate;

    public int insertStudent(Student student) {
        student.setTimer(LocalDateTime.now());
        mongoTemplate.insert(student);
        return 1;
    }

    public Student queryStudent(Long id) {
        return mongoTemplate.findById(id, Student.class);
    }

    public int updateStudent(Student student) {
        //通过query根据id查询出对应对象，通过update对象进行修改
        Query query = new Query(Criteria.where("_id").is(student.getId()));
        Update update = new Update().set("username", student.getUsername());
        mongoTemplate.updateFirst(query, update, Student.class);
        return 1;
    }

    public int removeStudent(Long id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Student.class);
        return 1;
    }

}
