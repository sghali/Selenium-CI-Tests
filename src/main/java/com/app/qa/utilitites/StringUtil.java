package com.app.qa.utilitites;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static String removeWSpace(String value) {
		String returnValue = "";
		StringTokenizer st = new StringTokenizer(value, " ");
		while (st.hasMoreTokens()) {
			returnValue = returnValue + st.nextToken();
		}
		return returnValue;
	}

	public static List<String> strToList(String sString, String tokenizer) {
		return strToList(sString, tokenizer, false);
	}

	public static List<String> strToList(String sString, String tokenizer,
			boolean trim) {
		List<String> returnValue = new ArrayList<String>();
		if (tokenizer == null)
			tokenizer = ",";
		StringTokenizer st = new StringTokenizer(sString, tokenizer);
		while (st.hasMoreTokens()) {
			String tok = st.nextToken();
			returnValue.add(trim ? tok.trim() : tok);
		}
		return returnValue;
	}

	public static String[] strToArray(String sString, String tokenizer) {
		List<String> returnValue = new ArrayList<String>();
		returnValue = strToList(sString, tokenizer, true);
		return returnValue.toArray(new String[returnValue.size()]);
	}

	public static String arrayToString(Object[] values, String delimiter,
			boolean useQuoteIfStr) {
		String returnValue = "";
		boolean isFirst = true;
		for (Object value : values) {
			if (value != null) {
				if (!isFirst) {
					returnValue += delimiter;
				}
				if (value.getClass().isInstance(new String()) && useQuoteIfStr) {
					returnValue += "\"" + value.toString() + "\"";
				} else {
					returnValue += value.toString();
				}
				isFirst = false;
			}
		}
		return returnValue;
	}

	public static String booleanToYesNo(Boolean value) {
		String returnValue = "no";

		if (value != null && value)
			returnValue = "yes";

		return returnValue;
	}

	/**
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static Boolean bbEquals(String value1, String value2) {
		Boolean isEqual = null;

		isEqual = value1.toLowerCase().trim().equals(
				value2.toLowerCase().trim());

		return isEqual;
	}

	public static boolean yesNoToBoolean(String yesNo) {
		boolean booleanValue = false;

		if (yesNo != null) {
			if ((yesNo.trim().toLowerCase()).equals("yes")) {
				booleanValue = true;
			}
		}

		return booleanValue;
	}

	/**
	 * This method is used to check for pattern matching. Returns true if
	 * actualMessage meets with expectedMessage pattern. The isRegExRequired
	 * parameter must specify whether regExp pattern matching is required.
	 * 
	 * @param actualMessage
	 * @param expectedMessage
	 * @param isRegExRequired
	 *            If true , performs regExp matching .Otherwise, does general
	 *            pattern matching
	 * 
	 * @return
	 * 
	 */
	public static boolean isPatternMatch(String actualMessage,
			String expectedMessage, boolean isRegExRequired) {

		Pattern pattern = null;
		if (isRegExRequired) {

			pattern = Pattern.compile(StringUtil
					.prepareRegExMessage(expectedMessage),
					Pattern.CASE_INSENSITIVE | Pattern.DOTALL
							| Pattern.MULTILINE | Pattern.CANON_EQ);
		} else {

			pattern = Pattern.compile(expectedMessage, Pattern.CASE_INSENSITIVE
					| Pattern.DOTALL | Pattern.MULTILINE | Pattern.CANON_EQ);
		}

		Matcher matcher = pattern.matcher(actualMessage.trim());
		return (matcher.matches());
	}

	/**
	 * This method prepares regular expression message taking into account
	 * special characters such as space,number,'(' and ')'etc within the
	 * message.
	 * 
	 * @param message
	 * @return
	 */
	public static String prepareRegExMessage(String message) {
		// Trims the message
		String regExMessage = message.trim();

		// Adds wild character at leading and trailing places
		regExMessage = ".*" + regExMessage + ".*";

		// replaces multiple white spaces between words with single blank
		regExMessage = regExMessage.replaceAll("\\s+", " ");

		// Replaces {0} with wild card
		if (regExMessage.indexOf("{0}") >= 0) {
			regExMessage = regExMessage.substring(0, message.indexOf("{0}"))
					+ ".*"
					+ regExMessage.substring(regExMessage.indexOf("{0}") + 3);
		}

		// Replaces {1} with wild card
		if (regExMessage.indexOf("{1}") >= 0) {
			regExMessage = regExMessage.substring(0, regExMessage
					.indexOf("{1}"))
					+ ".*"
					+ regExMessage.substring(regExMessage.indexOf("{1}") + 3);
		}
		// Handles Special characters
		if (regExMessage.indexOf("(") >= 0) {
			regExMessage = regExMessage.replaceAll("\\(", ".?");
		}

		if (regExMessage.indexOf(")") >= 0) {
			regExMessage = regExMessage.replaceAll("\\)", ".?");
		}
		
		//Replace @X@ ... @X@ parameters
		//find first set of characters
		int index=regExMessage.indexOf("@X@");
		if (index>=0){
			String temp =regExMessage.substring(0,index);
			//find second set of characters;
			regExMessage = temp + ".*" + regExMessage.substring(regExMessage.indexOf("@X@"));
			regExMessage.replaceAll("@X@",".*"); 
		}

		// replaces all white spaces with wild character.
		regExMessage = regExMessage.replaceAll(" ", ".?");
		return regExMessage;
	}

	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getVariableInfo(Object object) {
		return (object + object.toString());
	}

	/**
	 * This method takes emailAddresses as parameter and splits the email values
	 * based on the delimiter and assigning them to string array.Based on the
	 * values in the String array,this method generates the text file with all
	 * the emailAddresses specified in database.
	 * 
	 * @param sString
	 *            contains the emailAddresses
	 * @param delimiter
	 *            the delimiter used in the file
	 * @param fullFileName
	 *            file name required for generation of email text file
	 * @throws IOException
	 */
	public static void strToFile(String sString, String delimiter,
			String fullFileName) throws IOException {
		String[] emailAddress = sString.split(delimiter);
		File f = new File(fullFileName);
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		for (String email : emailAddress) {
			bw.write(email);
			bw.newLine();
		}
		bw.close();
	}

	/**
	 * This convenience method determines if a String is "empty". If a String is
	 * <code>null</code> or does not contain any characters, then it is
	 * considered empty.
	 * 
	 * @param sString
	 *            The string to evaluate
	 * @return Returns <code>true</code> if the string is empty.
	 */
	public static boolean isEmpty(String sString) {
		if ((sString == null) || (sString.equals(""))) {
			return true;
		} else {
			return false;
		}
	}
}
