package androidx.media3.session.legacy;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.util.Util;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class LegacyParcelableUtil {
    private LegacyParcelableUtil() {
    }

    public static <T extends Parcelable, U extends Parcelable> T convert(U u, Parcelable.Creator<T> creator) {
        if (u == null) {
            return null;
        }
        Parcelable parcelable = (Parcelable) maybeApplyMediaDescriptionParcelableBugWorkaround(u);
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            return (T) maybeApplyMediaDescriptionParcelableBugWorkaround(creator.createFromParcel(parcelObtain));
        } finally {
            parcelObtain.recycle();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Parcelable, U extends Parcelable> ArrayList<T> convertList(List<U> list, Parcelable.Creator<T> creator) {
        if (list == null) {
            return null;
        }
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convert(list.get(i), creator));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T maybeApplyMediaDescriptionParcelableBugWorkaround(T t) {
        if (Util.SDK_INT < 21 || Util.SDK_INT >= 23) {
            return t;
        }
        if (!(t instanceof android.support.v4.media.MediaBrowserCompat.MediaItem)) {
            return t instanceof android.support.v4.media.MediaDescriptionCompat ? (T) rebuildMediaDescriptionCompat((android.support.v4.media.MediaDescriptionCompat) t) : t;
        }
        android.support.v4.media.MediaBrowserCompat.MediaItem mediaItem = (android.support.v4.media.MediaBrowserCompat.MediaItem) t;
        return (T) new android.support.v4.media.MediaBrowserCompat.MediaItem(rebuildMediaDescriptionCompat(mediaItem.getDescription()), mediaItem.getFlags());
    }

    private static android.support.v4.media.MediaDescriptionCompat rebuildMediaDescriptionCompat(android.support.v4.media.MediaDescriptionCompat mediaDescriptionCompat) {
        return new android.support.v4.media.MediaDescriptionCompat.Builder().setMediaId(mediaDescriptionCompat.getMediaId()).setTitle(mediaDescriptionCompat.getTitle()).setSubtitle(mediaDescriptionCompat.getSubtitle()).setDescription(mediaDescriptionCompat.getDescription()).setIconBitmap(mediaDescriptionCompat.getIconBitmap()).setIconUri(mediaDescriptionCompat.getIconUri()).setExtras(mediaDescriptionCompat.getExtras()).setMediaUri(mediaDescriptionCompat.getMediaUri()).build();
    }
}
