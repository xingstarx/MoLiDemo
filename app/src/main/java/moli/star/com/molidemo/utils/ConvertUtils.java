
package moli.star.com.molidemo.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;

public final class ConvertUtils {
    /**
     * px = dp * (dpi / 160)
     *
     * @param ctx
     * @param dip
     * @return
     */
    public static int dipToPX(@NonNull final Context ctx, float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, ctx.getResources().getDisplayMetrics());
    }

    /**
     * sp*ppi/160 =px
     *
     * @param ctx
     * @param dip
     * @return
     */
    public static int spToPX(@NonNull final Context ctx, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, ctx.getResources().getDisplayMetrics());
    }

    public static int toInt(@NonNull String str) {
        return toInt(str, 0);
    }

    public static int toInt(@Nullable Object str, int def) {
        return str == null ? def : toInt(str.toString(), def);
    }

    public static int toInt(@NonNull String str, int def) {
        if (TextUtils.isEmpty(str)) {
            return def;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
