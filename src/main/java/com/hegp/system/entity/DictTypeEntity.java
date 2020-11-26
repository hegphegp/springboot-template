package com.hegp.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hegp.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_type")
public class DictTypeEntity extends BaseEntity {
    private String name;
    private String code;
    private Long orderNo;
}
