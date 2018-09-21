package lambdasinaction._02stream.collect;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summingDouble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _01GroupingTransactions {

    public static List<Transaction> transactions = 
    		Arrays.asList(new Transaction(Currency.EUR, 1500.0),
                          new Transaction(Currency.USD, 2300.0),
                          new Transaction(Currency.GBP, 9900.0),
                          new Transaction(Currency.EUR, 1100.0),
                          new Transaction(Currency.JPY, 7800.0),
                          new Transaction(Currency.CHF, 6700.0),
                          new Transaction(Currency.EUR, 5600.0),
                          new Transaction(Currency.USD, 4500.0),
                          new Transaction(Currency.CHF, 3400.0),
                          new Transaction(Currency.GBP, 3200.0),
                          new Transaction(Currency.USD, 4600.0),
                          new Transaction(Currency.JPY, 5700.0),
                          new Transaction(Currency.EUR, 6800.0) );
    public static void main(String ... args) {
        groupImperatively();
        groupFunctionally();
        
        //Currency별로 Transaction을 Grouping 한 후에 Transaction의 합계를 계산
        Map<Currency,Double> txSumByCurrency = transactions
        														.stream()
        														.collect(groupingBy(tx->tx.getCurrency(),
        																				summingDouble(tx->tx.getValue()) ));
        System.out.println(txSumByCurrency);

        //Currency별로 Transaction을 Grouping 한 후에 Transaction이 5000을 초과인 경우
        Map<Currency,Map<Boolean,List<Transaction>>> tx500gtByCurrency =
        		transactions.stream().collect(groupingBy(Transaction::getCurrency,partitioningBy(tx->tx.getValue()>5000)));
        System.out.println(tx500gtByCurrency);
    }
    //Java 7
    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    //Java8 groupingBy 사용
    //Currency 별로 Transaction 을 grouping
    private static void groupFunctionally() {
//    	Map<Currency, List<Transaction>> 


    }

    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
}
