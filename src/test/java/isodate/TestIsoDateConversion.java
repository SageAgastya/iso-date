package isodate;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by phanindra on 19/11/17.
 */
public class TestIsoDateConversion {

    private static final String ISO_DATE_STRING_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXX";

    @Test
    public void test() throws ParseException {
        String string1 = "apr 04, 2017";
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        Date date1 = formatter.parse(string1);
        assertEquals("2017-04-04 00:00", getHumanReadableString(date1));
    }

    @Test
    public void utcTime() throws ParseException {
        String string = "2017-09-13 08:23:15.0 UTC";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S z");
        Date date = formatter.parse(string);
        assertEquals("2017-09-13 18:23", getHumanReadableString(date));

        string = "2017-07-07 00:04:16.0 UTC";
        date = formatter.parse(string);
        assertEquals("2017-07-07 10:04", getHumanReadableString(date));

        string = "2017-07-03 19:53:36.0 UTC";
        date = formatter.parse(string);
        assertEquals("2017-07-04 05:53", getHumanReadableString(date));

    }

    @Test
    public void aedt() throws ParseException {
        String string = "Tue Mar 28 09:17:13 AEDT 2017";
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date date = formatter.parse(string);
        String iso8601String = getHumanReadableString(date);
        assertEquals("2017-03-28 09:17", iso8601String);


        string = "Tue Jan 24 16:33:12 AEDT 2017";
        date = formatter.parse(string);
        assertEquals("2017-01-24 16:33", getHumanReadableString(date));


    }

    @Test
    public void timezone() throws ParseException {

        String string = "2016-01-05T10:46:35.000Z";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = formatter.parse(string);
        String iso8601String = getHumanReadableString(date);
        assertEquals("2016-01-05 10:46", iso8601String);

    }

    private String getHumanReadableString(Date date) {
        String isoDateTimeString = convertDateToIso8601String(date);

        DateTime isoDateTime = DateTime.parse(isoDateTimeString);
        org.joda.time.format.DateTimeFormatter isoFormatter = ISODateTimeFormat.dateTime();
        // correctly format isoDateTimeString
        String isoDateTimeFormatted = isoFormatter.print(isoDateTime);

        System.out.println("iso8601 date is : " + isoDateTimeFormatted);


        // human readable
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String humanReadable = formatter.format(isoDateTime.toDate());



        return humanReadable;

    }

    private String convertDateToIso8601String(Date date1) {
        SimpleDateFormat isoFormatter = new SimpleDateFormat(ISO_DATE_STRING_FORMAT);
        String isoFormat1 = isoFormatter.format(date1);
        return isoFormat1;
    }


}
