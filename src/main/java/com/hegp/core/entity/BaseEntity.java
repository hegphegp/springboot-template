package com.hegp.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @TableId
    private String id;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String createBy;
    private String updateBy;
    private Boolean del;
}
