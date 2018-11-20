package net.dowster.school.datacomm.program2;

/**
 * Implements a ceaser cipher. Create a PolyAlphabet object with your specified
 * cipher and then encode the string. Encoder is reset after each string and can
 * be used more than once.
 */
public class PolyAlphabet {

    private int[] shifts;

    /**
     * Create a PolyAlphabet class object implements the given ceaser cipher
     * shifts.
     * @param shifts array of shifts to use, IE, for prog2
     *                                              [5, 19, 19, 5, 19]
     */
    public PolyAlphabet(int[] shifts) {
        this.shifts = shifts;
    }

    /**
     * Encode the str with the cipher provided at object creation
     * @param str to encode
     * @return encoded string
     */
    public String encode(String str) {
        int stringLength = str.length();
        int encCursor = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for(int cursor = 0; cursor < stringLength; cursor++)
        {
            char currentChar = str.charAt(cursor);
            if(currentChar < 'A' || (currentChar > 'Z' && currentChar < 'a') ||
                  currentChar > 'z')
                stringBuilder.append(currentChar);
            else {
                currentChar += shifts[encCursor++ % shifts.length];
                if (currentChar > 'z' || (str.charAt(cursor) < 'a' &&
                      currentChar > 'Z'))
                    currentChar -= 26;
                stringBuilder.append(currentChar);
            }
        }
        return stringBuilder.toString();
    }
}
