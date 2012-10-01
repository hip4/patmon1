package ch.bfh.ti.sed.patmon1.bind;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

/**
 * JAXB adapter for mapping xs:dateTime to java.util.Date. This is the only
 * not-generated class in this project.
 */
public class DateAdapter {

	/**
	 * Converts the string argument into a Date value.
	 * 
	 * @param lexicalXSDDate
	 *            A string containing lexical representation of xsd:Date.
	 * @return A Date value represented by the string argument.
	 * @throws IllegalArgumentException
	 *             if string parameter does not conform to lexical value space
	 *             defined in XML Schema Part 2: Datatypes for xsd:dateTime.
	 * @see DatatypeConverter#parseDate(String)
	 */
	public static Date parseDateTime(String lexicalXSDDate) {
		return DatatypeConverter.parseDateTime(lexicalXSDDate).getTime();
	}

	/**
	 * Converts a Date value into a string.
	 * 
	 * @param val
	 *            A Date value
	 * @return A string containing a lexical representation of xsd:dateTime
	 * @throws IllegalArgumentException
	 *             if <tt>val</tt> is null.
	 * @see DatatypeConverter#printDateTime(Calendar)
	 */
	public static String printDateTime(Date val) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(val);
		return DatatypeConverter.printDateTime(cal);
	}
}