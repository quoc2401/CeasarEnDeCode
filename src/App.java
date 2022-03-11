import java.util.Scanner;

public class App { 
    public static class Global {
        public static char[] chars = new char[26];
        public static char[][] Playfair = new char[5][5];

        public Global() {
            for (int i = 0; i < 26; i++) {
                chars[i] = Character.toString(65 + i).charAt(0);
            }
        }

        public Global(String key) {
            int length = key.length();

            for (int i = 0; i < key.length(); i++)
                chars[i] = Character.toUpperCase(key.charAt(i));
            String cs = new String(chars);
            for (int i = 0; i < 26; i++) {
                if (!(cs.contains(Character.toString(65 + i)))) {      
                    chars[length] = Character.toString(65 + i).charAt(0);
                    length += 1;
                }            
            }
            int i = 0;
            for (int row = 0; row < 5; row++)
                for (int col = 0; col < 5; col++) { 
                    if(chars[i] == 'J') {
                        i++;
                    }
                        
                    Playfair[row][col] = chars[i++];  
                }
        }
    }
    public static void main(String[] args) throws Exception { 
        Scanner scanner = new Scanner(System.in);
        String text;
        int option;
        System.out.println("Chon Phuong Phap ma hoa/giai ma:\n 1. CEASAR\n 2.Vigenere\n 3.Playfair");
        option = scanner.nextInt();
        scanner.nextLine();

        switch(option) {
            case 1: {
                System.out.println("Nhap text: ");
                text = scanner.nextLine(); 
                
                int k;
                System.out.println("Nhap key: ");
                k = scanner.nextInt();

                ceasarEncode(k, text);
                System.out.println();
                ceasarDecode(k, text);

                break;
            }
            case 2: {
                System.out.println("Nhap text: ");
                text = scanner.nextLine(); 
                
                String key;
                System.out.println("Nhap key: ");
                key = scanner.nextLine();

                vigenereEncode(key, text);
                System.out.println();
                vigenereDecode(key, text);

                break;
            }

            case 3: {
                System.out.println("Nhap text: ");
                text = scanner.nextLine(); 
                
                String key;
                System.out.println("Nhap key: ");
                key = scanner.nextLine();

                playfairEncode(key, text);
                System.out.println();
                playfairDecode(key, text);
            }
        }

       

        
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

    public static void vigenereEncode(String key, String text) {
        Global chars = new Global();
        int textIndex = -1, keyIndex = -1, cipherIndex;
        int k = 0;
        System.out.print("KET QUA ENCODE: ");
        for(int i = 0; i < text.length(); i++) {          
            if (k >= key.length()) 
                k = 0;

            for (int j = 0; j < 26; j++) {                  
                if (chars.chars[j] == Character.toUpperCase(text.charAt(i))) {
                    textIndex = new String(chars.chars).indexOf(chars.chars[j]);
                }

                if (chars.chars[j] == Character.toUpperCase(key.charAt(k))) {
                    keyIndex = new String(chars.chars).indexOf(chars.chars[j]);
                }
                
                if (textIndex != -1 && keyIndex != -1) {
                    cipherIndex = (textIndex + keyIndex) % 26;
                    textIndex = -1;
                    keyIndex = -1;
                    k++;

                    System.out.print(chars.chars[cipherIndex]);
                    break;
                }
            }           
        }        
    }

    public static void vigenereDecode(String key, String text) {
        Global chars = new Global();
        int textIndex = -1, keyIndex = -1, plainIndex;
        int k = 0;
        System.out.print("KET QUA DECODE: ");
        for(int i = 0; i < text.length(); i++) {          
            if (k >= key.length()) 
                k = 0;

            for (int j = 0; j < 26; j++) {                  
                if (chars.chars[j] == Character.toUpperCase(text.charAt(i))) {
                    textIndex = new String(chars.chars).indexOf(chars.chars[j]);
                }

                if (chars.chars[j] == Character.toUpperCase(key.charAt(k))) {
                    keyIndex = new String(chars.chars).indexOf(chars.chars[j]);
                }
                if (textIndex != -1 && keyIndex != -1) {
                    plainIndex = (textIndex - keyIndex);
                    if (plainIndex < 0)
                        plainIndex = (plainIndex + 26) % 26;
                    textIndex = -1;
                    keyIndex = -1;
                    k++;

                    System.out.print(chars.chars[plainIndex]);
                    break;
                }
            }           
        }        
    }

    public static void playfairEncode(String key, String text) {
        Global chars = new Global(key);
        System.out.print("Ket qua Encode: ");

        for (int i = 0; i < text.length(); i += 2) {
            if(text.charAt(i) == text.charAt(i + 1)) {
                System.out.print(text.charAt(i) + "X");
            }
            else {
                int[] pos1 = new int[2];
                int[] pos2 = new int[2];
                char firstChar = text.charAt(i), secondChar = text.charAt(i + 1);

                for (int row = 0; row < 5; row++)
                    for(int col = 0; col < 5; col++) {      
                        if(chars.Playfair[row][col] == firstChar) {
                            pos1[0] = row;
                            pos1[1] = col; 
                        }

                        if(chars.Playfair[row][col] == secondChar) {
                            pos2[0] = row;
                            pos2[1] = col;
                        }
                    }
                
                // System.out.println(String.format("%d%d  %d%d", pos1[0], pos1[1], pos2[0], pos2[1]));
                if (pos1[0] == pos2[0]) {
                    pos1[1] = (pos1[1] + 1) % 5;
                    pos2[1] = (pos2[1] + 1) % 5;
                }
                else if (pos1[1] == pos2[1]) {
                    pos1[0] = (pos1[0] + 1) % 5;
                    pos2[0] = (pos2[0] + 1) % 5;
                }
                else {
                    int temp = pos1[1];
                    pos1[1] = pos2[1];
                    pos2[1] = temp;
                }
                System.out.print(chars.Playfair[pos1[0]][pos1[1]]);
                System.out.print(chars.Playfair[pos2[0]][pos2[1]]);
            }
        }
    }

    public static void playfairDecode(String key, String text) {
        Global chars = new Global(key);
        System.out.print("Ket qua Decode: ");
        
        for (int i = 0; i < text.length(); i += 2) {
            if(text.charAt(i) == text.charAt(i + 1)) {
                System.out.print(text.charAt(i) + "X");
            }
            else {
                int[] pos1 = new int[2];
                int[] pos2 = new int[2];
                char firstChar = text.charAt(i), secondChar = text.charAt(i + 1);

                for (int row = 0; row < 5; row++)
                    for(int col = 0; col < 5; col++) {      
                        if(chars.Playfair[row][col] == firstChar) {
                            pos1[0] = row;
                            pos1[1] = col; 
                        }

                        if(chars.Playfair[row][col] == secondChar) {
                            pos2[0] = row;
                            pos2[1] = col;
                        }
                    }
                
                // System.out.println(String.format("%d%d  %d%d", pos1[0], pos1[1], pos2[0], pos2[1]));
                if (pos1[0] == pos2[0]) {

                    pos1[1] = (pos1[1] - 1 + 5) % 5;
                    pos2[1] = (pos2[1] - 1 + 5) % 5;
                }
                else if (pos1[1] == pos2[1]) {
                    pos1[0] = (pos1[0] - 1 + 5) % 5;
                    pos2[0] = (pos2[0] - 1 + 5) % 5;
                }
                else {
                    int temp = pos1[1];
                    pos1[1] = pos2[1];
                    pos2[1] = temp;
                }
                System.out.print(chars.Playfair[pos1[0]][pos1[1]]);
                System.out.print(chars.Playfair[pos2[0]][pos2[1]]);
            }
        }
    }
}
