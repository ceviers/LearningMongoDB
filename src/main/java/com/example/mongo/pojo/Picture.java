package com.example.mongo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: cevier.wei
 * @date: 2023/2/7 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    private Long id;
    private String name;
    private String description;
}
