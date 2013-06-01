package eu.cointelligence.util;

public class JspUtils {

	/**
	 * Quotes metacharacters in HTML.
	 */
	public static String escapeForHTML(String aHTML) {

		if (aHTML == null) return null;

		// deal with ampersands first so we can ignore the ones we add later
		int c, oldC = -1;

		while ( (c = aHTML.substring(oldC + 1).indexOf('&') ) != -1 ) {
			c += oldC + 1;          // adjust back to real string start
			// sim 6/13/01 -- JDK 1.1 does have support StringBuffer.replace.
			// x = new String((new StringBuffer(x)).replace(c, c+1, "&amp;"));
			aHTML = aHTML.substring(0, c) + "&amp;" + aHTML.substring(c + 1);
			oldC = c;
		}
		while ((c = aHTML.indexOf('"')) != -1) {
			// sim 6/13/01 -- JDK 1.1 does have support StringBuffer.replace.
			// x = new String((new StringBuffer(x)).replace(c, c+1, "&quot;"));
			aHTML = aHTML.substring(0, c) + "&quot;" + aHTML.substring(c + 1);
		}
		while ((c = aHTML.indexOf('<')) != -1) {
			// sim 6/13/01 -- JDK 1.1 does have support StringBuffer.replace.
			// x = new String((new StringBuffer(x)).replace(c, c+1, "&lt;"));
			aHTML = aHTML.substring(0, c) + "&lt;" + aHTML.substring(c + 1);
		}
		while ((c = aHTML.indexOf('>')) != -1) {
			// sim 6/13/01 -- JDK 1.1 does have support StringBuffer.replace.
			// x = new String((new StringBuffer(x)).replace(c, c+1, "&gt;"));
			aHTML = aHTML.substring(0, c) + "&gt;" + aHTML.substring(c + 1);
		}
		return aHTML;
	}
	
	/**
	 * Fixes a URL value that is to be embedded in an HTML page.
	 * URL values need to be fixed up prior to being embedded in an
	 * HTML page. This function replaces all 'special' characters so
	 * that they don't become altered when they are subsequently passed
	 * back to the HTTP server.
	 */
	static public String fixFieldValueForHTML(String aValue) {

		if (aValue == null) return null;

		StringBuffer sBuffer = new StringBuffer();
		int nIndex;
		int nLength;

		nLength = aValue.length();

		for (nIndex = 0; nIndex < nLength; nIndex++) {
			if (aValue.charAt(nIndex) == ';') {
				sBuffer.append("%3B");
			} else if (aValue.charAt(nIndex) == '/') {
				sBuffer.append("%2F");
			} else if (aValue.charAt(nIndex) == '?') {
				sBuffer.append("%3F");
			} else if (aValue.charAt(nIndex) == ':') {
				sBuffer.append("%3A");
			} else if (aValue.charAt(nIndex) == '@') {
				sBuffer.append("%40");
			} else if (aValue.charAt(nIndex) == '&') {
				sBuffer.append("%26");
			} else if (aValue.charAt(nIndex) == '=') {
				sBuffer.append("%3D");
			} else if (aValue.charAt(nIndex) == '+') {
				sBuffer.append("%2B");
			} else if (aValue.charAt(nIndex) == '$') {
				sBuffer.append("%24");
			} else if (aValue.charAt(nIndex) == ',') {
				sBuffer.append("%2C");
			} else if (aValue.charAt(nIndex) == ' ') {
				sBuffer.append("%20");
			} else if (aValue.charAt(nIndex) == '%') {
				sBuffer.append("%25");
			} else {
				sBuffer.append(aValue.charAt(nIndex));
			}
		}
		return sBuffer.toString();
	}

}
