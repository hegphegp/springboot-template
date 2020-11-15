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
    private String createBy;
    private String updateBy;
    private Boolean del;

    public Timestamp getCreateAt() {
        if (createAt==null) {
            return new Timestamp(System.currentTimeMillis());
        }
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        if (updateAt==null) {
            return new Timestamp(System.currentTimeMillis());
        }
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public Boolean getDel() {
        if (del==null) {
            return false;
        }
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
