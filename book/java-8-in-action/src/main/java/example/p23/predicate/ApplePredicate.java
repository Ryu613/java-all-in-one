package example.p23.predicate;

import example.p11.predicate.Apple;

/**
 * 只需要扩展此接口即可以满足对List<Apple>的不同筛选需求
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}
