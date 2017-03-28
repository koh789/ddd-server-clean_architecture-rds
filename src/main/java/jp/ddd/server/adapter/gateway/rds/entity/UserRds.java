package jp.ddd.server.adapter.gateway.rds.entity;

import jp.ddd.server.adapter.gateway.rds.entity.base.BaseEntity;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;

import javax.persistence.*;

/**
 * The persistent class for the user database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
@NamedQueries({//
  @NamedQuery(name = "User.getByLid", query = "SELECT u FROM UserRds u WHERE u.loginId=:lid"),
  @NamedQuery(name = "User.getByLidAndPassAndStatus", query = "SELECT u FROM UserRds u WHERE u.loginId=:lid AND u.pass=:pass AND u.status=:status"),
  @NamedQuery(name = "User.findByIdsAndStatus", query = "SELECT u FROM UserRds u WHERE u.id IN (:ids) AND u.status=:status") })
public class UserRds extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @Column(name = "login_id")
    private String loginId;

    private String name;

    private String pass;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String tel;

    public static UserRds create(jp.ddd.server.domain.entity.user.User user) {

        return UserRds.builder().email(user.getUserInfo().getEmail()).status(user.getStatus())
          .loginId(user.getLoginId().getId()).name(user.getUserInfo().getName()).pass(user.getHashPass().getPass())
          .tel(user.getUserInfo().getTel()).build();
    }
}