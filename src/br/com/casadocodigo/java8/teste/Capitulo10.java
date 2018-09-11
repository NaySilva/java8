package br.com.casadocodigo.java8.teste;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Capitulo10 {
	
	public static void main(String[] args) {
		
		LocalDate mesQueVem = LocalDate.now().plusMonths(1);
		System.out.println(mesQueVem);
		
		LocalDate anoPassado = LocalDate.now().minusYears(1);
		System.out.println(anoPassado);
		
		LocalDateTime agora = LocalDateTime.now();
		System.out.println(agora);
		
		LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12,0);
		System.out.println(hojeAoMeioDia);
		
		ZonedDateTime dataComHoraETimezone = agora.atZone(
				ZoneId.of("America/Sao_Paulo"));
		System.out.println(dataComHoraETimezone);
		
		LocalDateTime semTimeZone = dataComHoraETimezone.toLocalDateTime();
		System.out.println(semTimeZone);
		
		LocalDate date = LocalDate.of(2018, 04, 29);
		LocalDateTime dateTime = LocalDateTime.of(2018, 04, 29, 1, 0);
		System.out.println(date.toString() + dateTime);
		
		LocalDate dataDoPassado = LocalDate.now().withYear(1988);
		System.out.println(dataDoPassado.getYear());
		
		LocalDate hoje = LocalDate.now();
		LocalDate amanha = hoje.plusDays(1);
		System.out.println(hoje.isBefore(amanha));
		System.out.println(hoje.isAfter(amanha));
		System.out.println(hoje.isEqual(amanha));
		
		ZonedDateTime tokyo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime saoPaulo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
		tokyo = tokyo.plusHours(12);
		System.out.println(tokyo.isEqual(saoPaulo));

		System.out.println("Hoje é dia: "+ MonthDay.now().getDayOfMonth());
		
		YearMonth ym = YearMonth.from(date);
		System.out.println(ym.getMonth() + " " + ym.getYear());
		
		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2));
		System.out.println(Month.DECEMBER.minus(1));
		
		Locale pt = new Locale("pt");
		
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));
		
		String resultado = agora.format(DateTimeFormatter.ISO_LOCAL_TIME);
		
		System.out.println(resultado);
		System.out.println(agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		
		LocalDate outraData = LocalDate.of(1989, Month.JANUARY, 25);
		long dias = ChronoUnit.DAYS.between(outraData, agora);
		long meses = ChronoUnit.MONTHS.between(outraData, agora);
		long anos = ChronoUnit.YEARS.between(outraData, agora);
		
		System.out.println(dias);
		System.out.println(meses);
		System.out.println(anos);
		
		LocalDate agora2 = LocalDate.now();
		
		Period periodo = Period.between(outraData, agora2);
		System.out.printf("%s dias, %s meses e %s anos",
				periodo.getDays(), periodo.getMonths(), periodo.getYears());
		
		LocalDate dataFutura = LocalDate.of(2030, Month.JANUARY, 25);
		Period periodo2 = Period.between(dataFutura, agora2);
		if (periodo2.isNegative()) {
			periodo2 = periodo2.negated();			
		}
		System.out.printf("%s dias, %s meses e %s anos",
				periodo2.getDays(), periodo2.getMonths(), periodo2.getYears());
		
		LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
		Duration duration = Duration.between(agora, daquiAUmaHora);
		
		if (duration.isNegative()) {
			duration = duration.negated();
		}
		
		System.out.printf("%s horas, %d minutos e %s segundos",
				duration.toHours(), duration.toMinutes(), duration.getSeconds());
	}

}
