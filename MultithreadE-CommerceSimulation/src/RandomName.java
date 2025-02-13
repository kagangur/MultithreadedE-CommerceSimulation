
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kagan
 */
public class RandomName {
    
String[] firstNames = {"Ahmet", "Mehmet", "Ayşe", "Fatma", "Can", "Cem", "Elif", "Merve", "Ali", "Burcu","Kağan","Berfin","Neval","Ege","Hakan","Tarık","Emrah","Emre","İrem","Aynur","Onur","Yasemin","Barış","Ozan","Anıl","Gökçe","Ece","Mahmut","Fahrettin"};
String[] lastNames = {"Yılmaz", "Kaya", "Demir", "Şahin", "Çelik", "Koç", "Öztürk", "Kurt", "Arslan", "Aydın","Gür","Kenar","Aydınoğlu","Öz","Doğan","Sevinç","Akça","Kaba","Duvar","Ay","Şensoy","Taş","Kaplan"};
String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
    
    public static String generateFullName(String[] firstNames, String[] lastNames) {
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }
    
        public static String generateUsername(String firstName, String lastName) {
        return firstName.toLowerCase()  + lastName.toLowerCase();
    }

    public static String generatePassword(String firstName,String lastName,String[] numbers) {
        Random random = new Random();
        String number = numbers[random.nextInt(numbers.length)];
        return firstName.toLowerCase()+lastName.toLowerCase() + number;
    }
    
    
    
}
