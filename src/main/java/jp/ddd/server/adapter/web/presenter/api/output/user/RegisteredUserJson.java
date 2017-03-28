package jp.ddd.server.adapter.web.presenter.api.output.user;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class RegisteredUserJson implements Serializable {
    private static final long serialVersionUID = 910139523300534581L;

    private Integer userId;
}
