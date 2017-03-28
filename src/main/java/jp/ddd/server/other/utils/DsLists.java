package jp.ddd.server.other.utils;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DsLists {

    public static <T> ImmutableList<T> toImt(List<T> list) {
        return Lists.immutable.ofAll(list);
    }

    public static <T> MutableList<T> toMt(List<T> list) {
        return isEmpty(list) ? Lists.mutable.empty() : Lists.mutable.ofAll(list);
    }

    public static <T> ImmutableList<T> toImt(ImmutableList<Optional<T>> objOptList) {
        return objOptList.flatCollect(objOpt -> {
            if (objOpt.isPresent()) {
                return Lists.immutable.of(objOpt.get());
            } else {
                return Lists.immutable.empty();
            }
        });
    }

    /**
     * MutableListを作成する際はnullチェックが必要なので注意。
     */
    public static <T> List<T> toList(MutableList<T> mtList) {

        return mtList.stream().collect(Collectors.toList());
    }

    public static <T> List<T> toList(ImmutableList<T> imtList) {
        return imtList.toList().stream().collect(Collectors.toList());
    }

    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return CollectionUtils.isEmpty(map);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !CollectionUtils.isEmpty(map);
    }

    /**
     * 対象iterableの最大値を算出します。
     * iterableがemptyでも例外は発生せず{@link Optional#empty()}が返却されます。
     * @param richIterable
     * @param <T>
     * @return
     */
    public static <T> Optional<T> max(RichIterable<T> richIterable) {
        if (richIterable.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(richIterable.max());
    }

    /**
     * 対象iterableの最小値を算出します。
     * iterableがemptyでも例外は発生せず{@link Optional#empty()}が返却されます。
     * @param richIterable
     * @param <T>
     * @return
     */
    public static <T> Optional<T> min(RichIterable<T> richIterable) {
        if (richIterable.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(richIterable.min());
    }

    public static int getMaxSize(RichIterable iteA, RichIterable iteB, RichIterable iteC) {
        int largerSize = iteA.size() > iteB.size() ? iteA.size() : iteB.size();
        return largerSize > iteC.size() ? largerSize : iteC.size();
    }

    public static <T> Optional<T> getOpt(int index, List<T> list) {
        try {
            return Optional.of(list.get(index));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> getOpt(int index, ImmutableList<T> list) {
        try {
            return Optional.of(list.get(index));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> getFirstOpt(ListIterable<T> listIterable) {
        return Optional.ofNullable(listIterable.getFirst());
    }

    public static <T> Optional<T> getFirstOpt(List<T> list) {
        if (list == null || list.size() == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    public static <T> Optional<T> getLastOpt(ListIterable<T> listIterable) {
        return Optional.ofNullable(listIterable.getLast());
    }

    public static byte[] concat(byte[] arr1, byte[] arr2) {
        byte[] concatArr = new byte[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, concatArr, 0, arr1.length);
        System.arraycopy(arr2, 0, concatArr, arr1.length, arr2.length);
        return concatArr;
    }
}
