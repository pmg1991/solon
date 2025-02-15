package org.noear.solon.validation.annotation;

import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Result;
import org.noear.solon.validation.util.StringUtils;
import org.noear.solon.validation.Validator;

/**
 *
 * @author noear
 * @since 1.0
 * */
public class MaxValidator implements Validator<Max> {
    public static final MaxValidator instance = new MaxValidator();

    @Override
    public String message(Max anno) {
        return anno.message();
    }

    @Override
    public Class<?>[] groups(Max anno) {
        return anno.groups();
    }

    @Override
    public Result validateOfValue(Max anno, Object val0, StringBuilder tmp) {
        if (val0 instanceof Number == false) {
            return Result.failure();
        }

        Number val = (Number) val0;

        if (val == null || val.longValue() > anno.value()) {
            return Result.failure();
        } else {
            return Result.succeed();
        }
    }

    @Override
    public Result validateOfContext(Context ctx, Max anno, String name, StringBuilder tmp) {
        String val = ctx.param(name);

        if (StringUtils.isInteger(val) == false || Long.parseLong(val) > anno.value()) {
            tmp.append(',').append(name);
        }

        if (tmp.length() > 1) {
            return Result.failure(tmp.substring(1));
        } else {
            return Result.succeed();
        }
    }
}
