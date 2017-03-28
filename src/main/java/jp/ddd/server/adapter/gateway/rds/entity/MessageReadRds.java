package jp.ddd.server.adapter.gateway.rds.entity;

import jp.ddd.server.adapter.gateway.rds.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the message_read database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "message_read")
@NamedQueries({//
  @NamedQuery(name = "MessageRead.findByUidAndMids",//
    query = "SELECT mr FROM MessageReadRds mr WHERE mr.userId=:uid AND mr.messageId IN (:mids)") })
public class MessageReadRds extends BaseEntity {
    private static final long serialVersionUID = 2377932200497420692L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_dt")
    private Date readDt;

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "user_id")
    private Integer userId;

    public static MessageReadRds create(Long messageId, Integer userId, Date readDt) {
        return MessageReadRds.builder()//
          .readDt(readDt)//
          .messageId(messageId)//
          .userId(userId)//
          .build();
    }
}