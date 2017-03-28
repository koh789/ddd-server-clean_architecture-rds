package jp.ddd.server.adapter.web.presenter.api.output;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@Data
public class ErrorJson implements Error, Json {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

}
