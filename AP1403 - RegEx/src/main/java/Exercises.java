import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercises {

    /*
        complete the method below, so it will validate an email address
     */
    public boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        // [a-zA-Z0-9_+&*-]+ Local part
        // ?:\.[a-zA-Z0-9_+&*-]+)* Optional Part
        // @ seprate local from Optional
        // (?:[a-zA-Z0-9-]+\.)+ Domain Part
        // [a-zA-Z]{2,7} Top Level Domain
        // https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s01.html
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /*
        this method should find a date in string
        note that it should be in british or american format
        if there's no match for a date, return null
     */
    public String findDate(String string) {
        // British > (DD/MM/YYYY)
        String britishRegex = "\\b(0?[1-9]|[12][0-9]|3[01])[/-](0?[1-9]|1[0-2])[/-](\\d{4})\\b";
        // American > (MM/DD/YYYY)
        String americanRegex = "\\b(0?[1-9]|1[0-2])[/-](0?[1-9]|[12][0-9]|3[01])[/-](\\d{4})\\b";
        // ISO format :/ > (YYYY-MM-DD or YYYY/MM/DD) (u said british or american format!)
        String isoRegex = "\\b(\\d{4})[/-](0?[1-9]|1[0-2])[/-](0?[1-9]|[12][0-9]|3[01])\\b";

        Pattern pattern = Pattern.compile(britishRegex);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            return matcher.group();
        }
        pattern = Pattern.compile(americanRegex);
        matcher = pattern.matcher(string);
        if (matcher.find()) {
            return matcher.group();
        }
        pattern = Pattern.compile(isoRegex);
        matcher = pattern.matcher(string);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /*
        given a string, implement the method to detect all valid passwords
        then, it should return the count of them

        a valid password has the following properties:
        - at least 8 characters
        - has to include at least one uppercase letter, and at least a lowercase
        - at least one number and at least a special char "!@#$%^&*"
        - has no white-space in it
     */
    public int findValidPasswords(String string) {
        String[] allPass = string.split("\\s+"); //split
        int counti = 0;
        String pasPattern =
                "^(?=.*[a-z])" +          // at least a lowercase
                        "(?=.*[A-Z])" +          // at least one uppercase lette
                        "(?=.*\\d)" +            // at least one number
                        "(?=.*[!@#$%^&*])" +     // at least a special char "!@#$%^&*"
                        "[^\\s]{8,}$";           // at least 8 characters + has no white-space in it



        Pattern pattern = Pattern.compile(pasPattern);

        for (String password : allPass) {
            if (pattern.matcher(password).find()) {
                counti++;
            }
        }

        return counti;
    }

    /*
        you should return a list of *words* which are palindromic
        by word we mean at least 3 letters with no whitespace in it

        note: your implementation should be case-insensitive, e.g. Aba -> is palindrome
     */
    public List<String> findPalindromes(String string) {
        List<String> list = new ArrayList<>();

        // at least 3 letters + no whitespace
        String wordRegex = "\\b\\w{3,}\\b";
        Pattern pattern = Pattern.compile(wordRegex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String word = matcher.group();

            // Check for palindrome
            if (testPalindrome(word.toLowerCase())) {
                list.add(word);
            }
        }

        return list;
    }

    private boolean testPalindrome(String word) {
        int leftPoint = 0;
        int rightPoint = word.length() - 1;
        while (leftPoint < rightPoint) {
            if (word.charAt(leftPoint) != word.charAt(rightPoint)) {
                return false;
            }
            leftPoint++;
            rightPoint--;
        }

        return true;
    }

    public static void main(String[] args) {
        new Exercises().findValidPasswords("NoSpecial1 WeakPass! NoDigits@");
    }
}
