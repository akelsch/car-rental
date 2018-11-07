package de.htwsaar.prog3.carrental.olbertz;

public class StringUtils {
	public static final String EMPTY_STRING = "";
	
	/*
	 * OLBERTZ Ich schreibe mir immer diese beiden Methoden bzw. ich habe sie immer in einer jar-Datei
	 * mit Werkzeugklassen im Gepaeck. Damit versuche ich einfach zu vermeiden, dass ich aus Versehen
	 * doch mal == statt equals verwende. Das ist ein unterschaetzter Fehler, dem ich auf diese Art aus
	 * dem Weg gehe. Beide Methoden sind getestet und wenn ich sie konsequent verwende, kann ich sicher 
	 * sein, dass die Dinger auch immer funktionieren und mir diese doofen Fehler eben nicht passieren.
	 */
	public static boolean areStringsEqual(final String string1, final String string2) {
		if (string1.equals(string2)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * OLBERTZ Und vor allem diese Methode betrachte ich als wichtig, weil man dieses doofe
	 * Ausrufezeichen wirklich im Eifer des Gefechts schnell mal vergessen hat. 
	 */
	public static boolean areStringsNotEqual(final String string1, final String string2) {
		if (!string1.equals(string2)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isStringEmpty(final String theString) {
		if (theString.trim().equals(EMPTY_STRING)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isStringNotEmpty(final String theString) {
		if (theString.trim().equals(EMPTY_STRING)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * OLBERTZ Einmal eine Methode skizziert, die einen String an den  
	 * Kommas splittet. Das Ding wird noch etwas aufgebaut und getestet
	 * und wir koennen sicher sein, dass sie immer ordnungsgemaess 
	 * funktioniert.   
	 * @param aString
	 * @return
	 */
	public String[] splitAtCommas(final String aString) {
		final String[] elements = aString.split(",");
		return elements;
	}
}
