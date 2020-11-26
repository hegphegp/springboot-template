package com.hegp.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private Timestamp createAt;
    private Timestamp updateAt;
    private Boolean del;
}
