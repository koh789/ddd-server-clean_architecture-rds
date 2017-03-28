package jp.ddd.server.domain.entity.core;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@Value
public class MessageDt implements Serializable {
    private static final long serialVersionUID = 4935513382112496976L;

    private final Date date;
}
