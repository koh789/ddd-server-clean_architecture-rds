package jp.ddd.server.adapter.gateway.rds.entity.base;

import jp.ddd.server.other.utils.Dates;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAt;

    @PrePersist
    public void setPrePersist() {
        this.createAt = Dates.now();
        this.updateAt = Dates.now();
    }

    @PreUpdate
    public void setPreUpdate() {
        this.updateAt = Dates.now();
    }
}
