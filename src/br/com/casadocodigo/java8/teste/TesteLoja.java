package br.com.casadocodigo.java8.teste;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.casadocodigo.java8.loja.Customer;
import br.com.casadocodigo.java8.loja.Payment;
import br.com.casadocodigo.java8.loja.Product;
import br.com.casadocodigo.java8.loja.Subscription;

import static java.util.Arrays.asList;

public class TesteLoja {
	
	public void printPayments(List<Payment> payments) {
		payments.stream()
			.sorted(Comparator.comparing(Payment::getDate))
			.forEach(System.out::println);
	}
	
	public void totalPayment(Payment payment) {
		payment.getProducts()
			.stream()
			.map(Product::getPrice)
			.reduce(BigDecimal::add)
			.ifPresent(System.out::println);
	}
	
	public BigDecimal totalAllPayments(List<Payment> payments) {
		BigDecimal total = payments
				.stream()
				.flatMap(p -> p.getProducts()
						.stream()
						.map(Product::getPrice))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return total;
	}
	
	public static Map<Product, Long> topProducts(List<Payment> payments){
		return payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()));
	}

	public static void listTopProducts(List<Payment> payments) {
		topProducts(payments).entrySet()
			.stream().forEach(System.out::println);
	}
	
	public static void maxTopProducts(List<Payment> payments) {
		topProducts(payments).entrySet().stream()
			.max(Comparator.comparing(Map.Entry::getValue))
			.ifPresent(System.out::println);
	}
	
	public static void totalValuePerProduct(List<Payment> payments) {
		Map<Product, BigDecimal> totalValues = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.reducing(BigDecimal.ZERO, 
								Product::getPrice,
								BigDecimal::add)));
		totalValues.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);
	}
	
	public static void customerToProducts(List<Payment> payments) {
		Map<Customer, List<List<Product>>> customerToProductsList = 
				payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer,
						Collectors.mapping(Payment::getProducts,
								Collectors.toList())));
		
		Map<Customer, List<Product>> customerToProducts2steps =
				customerToProductsList.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, 
						e -> e.getValue().stream()
							.flatMap(List::stream)
							.collect(Collectors.toList())));
	
		customerToProducts2steps.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getName()))
			.forEach(System.out::println);
	}
	
	public static void customerToProductsReduce(List<Payment> payments) {
		Map<Customer, List<Product>> customerToProductsReduce = 
				payments.stream().collect(
						Collectors.groupingBy(Payment::getCustomer,
								Collectors.reducing(
										Collections.emptyList(),
										Payment::getProducts,
										(l1,l2) -> {List<Product> l = 
										new ArrayList<>();
										l.addAll(l1);
										l.addAll(l2);
										return l;})));
	
		customerToProductsReduce.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getName()))
			.forEach(System.out::println);
	}
	
	public static void totalValuePerCustomer(List<Payment> payments) {
		Function<Payment, BigDecimal> paymentToTotall = p -> p.getProducts()
				.stream().map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Map<Customer, BigDecimal> totalValuePerCustomer = payments
				.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer,
						Collectors.reducing(BigDecimal.ZERO,
				paymentToTotall,
				BigDecimal::add)));		
						
		totalValuePerCustomer.entrySet().stream()
		.sorted(Comparator.comparing(Map.Entry::getValue))
		.forEach(System.out::println);
	}
	
	public static void paymentsPerMonth(List<Payment> payments) {
		payments.stream()
		.collect(Collectors
				.groupingBy(p -> YearMonth.from(p.getDate()),
						Collectors.reducing(BigDecimal.ZERO,
								p -> p.getProducts().stream()
								.map(Product::getPrice)
								.reduce(BigDecimal.ZERO,
										BigDecimal::add),
								BigDecimal::add)))
		.entrySet().stream()
		.forEach(System.out::println);
	}
	
	public static long intervalSubscription(Subscription s) {
		return ChronoUnit.MONTHS
				.between(s.getBegin(), 
						s.getEnd().orElse(LocalDateTime.now()));
	}
	
	
	public static BigDecimal totalBySubscriptions(List<Subscription> subscriptions) {
		return subscriptions.stream()
				.map(Subscription::getTotalPaid)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public static void main(String[] args) {

		Customer paulo = new Customer("Paulo Silveira");
		Customer rodrigo = new Customer("Rodrigo Turini");
		Customer guilherme = new Customer("Guilherme Silveira");
		Customer adriano = new Customer("Adriano Almeida");

		Product one = new Product("The One", 
				Paths.get("/music/The One.mp3"), 
				new BigDecimal(100));
		Product adventure = new Product("Adventure Of A Lifetime", 
				Paths.get("/music/Adventure Of A Lifetime.mp3"), 
				new BigDecimal(90));
		Product hymn = new Product("Hymn For The Weekend", 
				Paths.get("/music/Hymn For The Weekend.mp3"), 
				new BigDecimal(50));
		Product that = new Product("Thats What I Like", 
				Paths.get("/music/Thats What I Like.mp3"), 
				new BigDecimal(150));
		Product hino = new Product("Hino Da PMMA", 
				Paths.get("/music/HINO DA PMMA.mp3"), 
				new BigDecimal(200));
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);

		Payment payment1 = new Payment(asList(one, adventure),
				today, paulo);
		Payment payment2 = new Payment(asList(one, adventure, hino),
				yesterday, rodrigo);
		Payment payment3 = new Payment(asList(hymn, that, one),
				today, adriano);
		Payment payment4 = new Payment(asList(hino, that, adventure),
				lastMonth, guilherme);
		Payment payment5 = new Payment(asList(hino, hymn),
				yesterday, paulo);
		
		List<Payment> payments = asList(payment1, payment2, payment3,
				payment4, payment5);
		
		paymentsPerMonth(payments);
		
		BigDecimal monthlyFee = new BigDecimal("99.00");
		
		Subscription s1 = new Subscription(monthlyFee, yesterday.minusMonths(5), paulo);
		Subscription s2 = new Subscription(monthlyFee, 
				yesterday.minusMonths(8),
				today.minusMonths(1), rodrigo);
		Subscription s3 = new Subscription(monthlyFee, 
				yesterday.minusMonths(5), today.minusMonths(2), adriano);
		List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);
		
//		System.out.println(intervalSubscription(s1));
		
		System.out.println(totalBySubscriptions(subscriptions));	
	}
	
}
