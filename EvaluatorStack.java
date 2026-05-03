import java.util.Stack;
import java.util.Scanner;

public class EvaluatorStack {

    // 1. Fungsi Presedensi Operator
    static int getPresedensi(char ch) {
        if (ch == '+' || ch == '-') return 1;
        if (ch == '*' || ch == '/') return 2;
        if (ch == '^') return 3;
        return -1;
    }

    // 2. Konversi Infix ke Postfix (Mendukung Multi-digit dengan spasi sebagai pemisah)
    static String infixKePostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // Jika angka, baca terus sampai angka tersebut habis (multi-digit)
            if (Character.isDigit(c)) {
                while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
                    postfix.append(infix.charAt(i));
                    i++;
                }
                postfix.append(" "); // Beri spasi agar angka tidak nempel
                i--; // Kembalikan index
            } 
            else if (c == '(') {
                stack.push(c);
            } 
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop();
            } 
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                while (!stack.isEmpty() && getPresedensi(c) <= getPresedensi(stack.peek())) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }
        return postfix.toString().trim();
    }

    // 3. Evaluasi Postfix (Mendukung Angka Multi-digit)
    static int hitungPostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfix.split(" "); // Pecah berdasarkan spasi

        System.out.println("\n>>> STEP-BY-STEP PERHITUNGAN (EVALUASI) <<<");

        for (String s : tokens) {
            if (s.isEmpty()) continue;

            // Jika token adalah angka (bisa multi-digit)
            if (Character.isDigit(s.charAt(0))) {
                int angka = Integer.parseInt(s);
                stack.push(angka);
                System.out.println("Action: Push " + angka + " \t| Stack: " + stack);
            } 
            // Jika token adalah operator
            else {
                int angka2 = stack.pop();
                int angka1 = stack.pop();
                int hasilTemp = 0;
                char op = s.charAt(0);

                switch (op) {
                    case '+': hasilTemp = angka1 + angka2; break;
                    case '-': hasilTemp = angka1 - angka2; break;
                    case '*': hasilTemp = angka1 * angka2; break;
                    case '/': hasilTemp = angka1 / angka2; break;
                }
                stack.push(hasilTemp);
                System.out.println("Action: " + angka1 + " " + op + " " + angka2 + " \t| Stack: " + stack + " (Hasil: " + hasilTemp + ")");
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("=== PROGRAM EVALUATOR EKSPRESI STACK (FIXED) ===");
        System.out.print("Masukkan Ekspresi Infix (contoh 2+2*2): ");
        String ekspresiInfix = input.nextLine();

        String hasilPostfix = infixKePostfix(ekspresiInfix);
        System.out.println("\nPOSTFIX: " + hasilPostfix);

        int hasilAkhir = hitungPostfix(hasilPostfix);
        System.out.println("\nHASIL AKHIR: " + hasilAkhir); // Hasilnya akan jadi 32
        input.close();
    }
}