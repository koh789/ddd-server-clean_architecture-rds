package jp.ddd.server.adapter.gateway.rds.entity;

import jp.ddd.server.adapter.gateway.rds.entity.base.BaseEntity;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the room database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "room_user")
@Entity
@NamedQueries({ //
  @NamedQuery(name = "RoomUser.getByUnq", query = "SELECT r FROM RoomUserRds r WHERE r.roomId=:rid AND r.userId=:uid"),
  //
  @NamedQuery(name = "RoomUser.getByRoomIdAndStatus", query = "SELECT r FROM RoomUserRds r WHERE r.roomId=:rid AND r.status=:status ") })
public class RoomUserRds extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_dt")
    private Date joinDt;

    @Column(name = "room_id")
    private Integer roomId;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "user_id")
    private Integer userId;

    public static RoomUserRds create(Integer roomId, Integer userId, Date joinDt) {
        return RoomUserRds.builder().status(Status.VALID).joinDt(joinDt).roomId(roomId).userId(userId).build();
    }
}