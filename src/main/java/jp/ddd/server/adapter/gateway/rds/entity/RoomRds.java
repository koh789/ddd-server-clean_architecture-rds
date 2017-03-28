package jp.ddd.server.adapter.gateway.rds.entity;

import jp.ddd.server.adapter.gateway.rds.entity.base.BaseEntity;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the room database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "room")
@Entity
@NamedQueries({ //
  @NamedQuery(name = "Room.findWithRoomUserByUidDtStatusDesc",//
    query = "SELECT r FROM RoomRds r JOIN FETCH r.roomUsers ru WHERE r.userId=:uid AND r.status=:status AND ru.status=:status ORDER BY r.lastMessageDt DESC"),
  @NamedQuery(name = "Room.getOptWithRoomUserByRidStatus", //
    query = "SELECT r FROM RoomRds r JOIN FETCH r.roomUsers ru WHERE r.id=:rid AND r.status=:status AND ru.status=:status") })
public class RoomRds extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_message_dt")
    private Date lastMessageDt;

    private String name;

    @Column(name = "user_id")
    private Integer userId;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<RoomUserRds> roomUsers;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<MessageRds> messages;

    public static RoomRds create(Integer userId, String roomName, Date lastMessageDt) {
        return RoomRds.builder().status(Status.VALID).lastMessageDt(lastMessageDt).name(roomName).userId(userId)
          .build();
    }


}