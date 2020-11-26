package com.hegp.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hegp.core.entity.TreeEntity;
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
@TableName("sys_dict_item")
public class DictItemEntity extends TreeEntity {
    private String name;
    private String code;
    private String dictTypeId;
    private String type;
}
