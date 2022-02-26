import java.util.Scanner;

public class App { 
    public static class Global {
        public static char[] chars = new char[26];

        public Global() {
            for (int i = 0; i < 26; i++) {
                chars[i] = Character.toString(65 + i).charAt(0);
            }
        }
    }
    public static void main(String[] args) throws Exception { 
        Scanner scanner = new Scanner(System.in);
        String text;
        System.out.println("Nhap ky tu: ");
        text = scanner.nextLine(); 
        
        int k;
        System.out.println("Nhap k: ");
        k = scanner.nextInt();

        ceasarEncode(k, text);
        System.out.println();
        ceasarDecode(k, text);
    }    

    public static void ceasarEncode(int k, String text) {
        Global chars = new Global();
        System.out.print("KET QUA ENCODE: ");
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < 26; j++) {                                
                if (chars.chars[j] == Character.toUpperCase(text.charAt(i))) {
                    int index = j + k;

                    if (index >= 26) {
                        index = index - 26;
                    }

                    System.out.print(chars.chars[index]);
                    break;
                }
                       
            }
            if (Character.isSpaceChar(text.charAt(i)))
                    System.out.print(' ');       
        }
        
    }

    public static void ceasarDecode(int k, String text) {
        Global chars = new Global();
        System.out.print("KET QUA DECODE: ");
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < 26; j++) {                                
                if (chars.chars[j] == Character.toUpperCase(text.charAt(i))) {
                    int index = j - k;

                    if (index < 0) {
                        index = 26 + index;
                    }

                    System.out.print(chars.chars[index]);
                    break;
                }
                       
            }
            if (Character.isSpaceChar(text.charAt(i)))
                    System.out.print(' ');       
        }
    }
}
