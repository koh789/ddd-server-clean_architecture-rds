package jp.ddd.server.other.data.common;

import jp.ddd.server.other.data.Dto;
import lombok.Builder;
import lombok.Value;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Value
public class Page implements Dto {
    private static final long serialVersionUID = 4295148173038043011L;

    private final int offset;
    private final int limit;
    private final int page;

    public static Page init(int limit) {
        return Page.builder() //
          .offset(0) //
          .limit(limit) //
          .page(1) //
          .build();
    }

    public static Page createByOffset(int offset, int limit) {
        int page = offset / limit + 1;
        return Page.builder() //
          .offset(offset) //
          .limit(limit) //
          .page(page) //
          .build();
    }

    public static Page create(int pageNum, int limit) {
        int offset = limit * (pageNum - 1);
        return Page.builder() //
          .offset(offset) //
          .limit(limit) //
          .page(pageNum) //
          .build();
    }
}
