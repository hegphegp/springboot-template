package com.hegp.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TreeEntity extends BaseEntity {
    private String parentId;
    private Long orderNo;
    private Short level;
    private String path;
}
