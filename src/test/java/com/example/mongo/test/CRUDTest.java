package com.example.mongo.test;

import com.example.mongo.manager.StudentManager;
import com.example.mongo.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author: cevier.wei
 * @date: 2023/2/7 16:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CRUDTest {

    @Resource
    private StudentManager studentManager;

    @Test
    public void addStu() {
        Student s = new Student();
        s.setId(124L);
        s.setUsername("cevier");
        System.out.println(studentManager.insertStudent(s));
    }

    @Test
    public void getStu() {
        System.out.println(studentManager.queryStudent(124L));
    }

    @Test
    public void updateStu() {
        Student s = new Student();
        s.setId(124L);
        s.setTimer(LocalDateTime.now());
        s.setUsername("cevier cvr");
        System.out.println(studentManager.updateStudent(s));
        System.out.println(studentManager.queryStudent(s.getId()));
    }

    @Test
    public void delStu() {
        System.out.println(studentManager.removeStudent(124L));
    }
}
