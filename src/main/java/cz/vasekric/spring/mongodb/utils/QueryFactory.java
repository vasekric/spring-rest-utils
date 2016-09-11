package cz.vasekric.spring.mongodb.utils;

import cz.vasekric.spring.rest.utils.RequestOptions;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author Richard Vašek.
 */
@Component
public class QueryFactory {

    public static final int defaultLimit = 1000;

    /**
     * Create query and set up options for it. Uses default limit if you do not specify limit.
     * @param options Nullable options
     * @return new Query
     */
    @NotNull
    public Query createQuery(@Nullable RequestOptions options) {
        val query = new Query();
        if(options == null) {
            return query;
        }
        if(options.getFields() != null) {
            val fields = query.fields();
            options.getFields().forEach(fields::include);
        }
        if(options.getLimit() != null) {
            query.limit(options.getLimit());
        }
        else {
            query.limit(defaultLimit);
        }
        if(options.getOffset() != null) {
            query.skip(options.getOffset());
        }

        return query;
    }
}
