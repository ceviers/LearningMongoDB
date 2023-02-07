package com.example.mongo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: cevier.wei
 * @date: 2023/2/7 16:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gallery {
    private Long id;
    private String name;
    private List<Picture> pictures;
}
