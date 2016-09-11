package cz.vasekric.spring.rest.utils;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

import java.util.List;

/**
 * @author Richard Vašek.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@Wither
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestOptions {
    List<String> fields;
    Integer limit;
    Integer offset;
}
