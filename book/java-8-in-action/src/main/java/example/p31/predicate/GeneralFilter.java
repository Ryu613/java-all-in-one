package example.p31.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GeneralFilter {
    /**
     * 选出列表中对应属性的元素
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e : list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
