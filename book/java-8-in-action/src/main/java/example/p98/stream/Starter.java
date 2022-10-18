package example.p98.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Starter {
    public static void main(String[] args) {
        Trader raoul = new Trader("Rauol", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> txs = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //找出2011年所有交易并按交易额排序升序
        List<Transaction> list = txs.stream()
                .filter(tx -> tx.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        //交易员在哪些城市工作过
        List<String> cities = txs.stream()
                .map(a -> a.getTrader().getName())
                .distinct()
                .collect(Collectors.toList());
                //.collect(Collectors.toSet());
        //查找所有来自于剑桥的交易员，按姓名排序
        List<Trader> cambridgeTrader = txs.stream()
                .map(a -> a.getTrader())
                .filter(a -> a.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        //返回所有交易员的姓名字符串，按字母顺序排序
        String traderNames = txs.stream()
                .map(a -> a.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(a,b) -> a + b);
                //.collect(joining());
        //有没有交易员是在米兰工作的
        boolean hasMilanTraders = txs.stream()
                .anyMatch(a -> a.getTrader().getCity().equals("Milan"));
        //打印生活在剑桥的交易员的所有交易额
        txs.stream()
                .filter(a -> a.getTrader().getCity().equals("Cambridge"))
                .forEach(a -> System.out.println(a.getValue()));
        //所有交易中，最高交易额是多少
        Optional<Integer> maxValue = txs.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        Optional<Transaction> minTransaction = txs.stream()
                .reduce((a, b) -> a.getValue() < b.getValue() ? a : b);
        //.min(Comparator.comparing(Transaction::getValue));
    }
}
