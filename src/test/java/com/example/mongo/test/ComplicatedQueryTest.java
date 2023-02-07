package com.example.mongo.test;

import com.example.mongo.manager.GalleryManager;
import com.example.mongo.pojo.Gallery;
import com.example.mongo.pojo.Picture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author: cevier.wei
 * @date: 2023/2/7 16:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ComplicatedQueryTest {
    @Resource
    private GalleryManager galleryManager;

    @Test
    public void addData() {
        var p1 = new Picture(1L, "img1", "a picture");
        var p2 = new Picture(2L, "img3", "an amazing picture");
        var p3 = new Picture(3L, "img5", "a photo");
        List<Picture> pictures = Arrays.asList(p1, p2, p3);
        var gallery = new Gallery(2L, "myGallery", pictures);
        galleryManager.insertGallery(gallery);
    }

    @Test
    public void queryPictures() {
        List<Picture> picture = galleryManager.getPictures(1L, Arrays.asList(1L, 3L, 5L));
        picture.forEach(System.out::println);
    }
}
