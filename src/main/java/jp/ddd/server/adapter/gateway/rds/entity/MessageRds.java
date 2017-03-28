package jp.ddd.server.adapter.gateway.rds.entity;

import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.adapter.gateway.rds.entity.base.BaseEntity;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the message database table.
 */
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "message")
@Entity
@NamedQueries({ //
  @NamedQuery(name = "Message.findWithReadByRoomIdOrderByIdDesc", //
    query = "SELECT m FROM MessageRds m JOIN FETCH  m.messageReads mr WHERE m.roomId=:rid AND m.status=:status ORDER BY m.id DESC"),
  @NamedQuery(name = "Message.findUnreadByRidAndUid",//
    query = "SELECT m FROM MessageRds m LEFT JOIN FETCH m.messageReads mr WHERE m.roomId=:rid AND m.userId<>:uid AND (mr.id IS NULL OR mr.userId <> :uid) AND m.status=:status") })
public class MessageRds extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "last_edit_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEditDt;

    @Column(name = "message_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageDt;

    @Column(name = "room_id")
    private Integer roomId;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany
    @JoinColumn(name = "message_id")
    private List<MessageReadRds> messageReads;

    public static MessageRds create(Message message) {
        return MessageRds.builder().content(message.getContent()).status(Status.VALID)
          .lastEditDt(message.getLastEditDt().getDate()).messageDt(message.getMessageDt().getDate())
          .roomId(message.getRoomId().getId()).userId(message.getUserId().getId()).build();
    }
}