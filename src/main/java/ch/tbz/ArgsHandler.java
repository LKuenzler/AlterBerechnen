package ch.tbz;

import com.beust.jcommander.Parameter;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ArgsHandler {

    @Parameter(names = {"--date", "-d"}, required = true)
    public String dateString;

    public void parse() {
        valdate();
        calculate();
    }

    private void calculate() {
        try {
            DateTime nowDate = new DateTime().withZone(DateTimeZone.forID("Europe/Zurich"));
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
            DateTime birthDate = formatter.parseDateTime(dateString);
            Period period = new Period(birthDate, nowDate);
            int totalDays = Days.daysBetween(birthDate.toLocalDate(), nowDate.toLocalDate()).getDays();
            if(nowDate.isBefore(birthDate)) {
                System.out.println("Dieses Datum ist in der Zukunft");
                System.exit(0);
            }
            System.out.println(
                    "Das alter ist "
                            + period.getYears() + " Jahre "
                            + period.getMonths() + " Monate "
                            + "und " + period.getDays() + " Tage "
                            + "das sind " + totalDays + " Tage");

        } catch (IllegalFieldValueException e) {
            System.out.println("Dieser Tag ist nicht möglich");
            System.exit(0);
        }
    }

    private void valdate() {
        try {
            new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (Exception ex) {
            Main.printWrongInputText();
        }
    }
}
