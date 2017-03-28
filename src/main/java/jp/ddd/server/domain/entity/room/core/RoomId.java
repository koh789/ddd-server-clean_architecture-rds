package jp.ddd.server.domain.entity.room.core;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@Value
public class RoomId implements Serializable {
    private static final long serialVersionUID = 4935513382112496976L;

    private final Integer id;
}
