import java.util.Stack;
import java.util.Scanner;

public class EvaluatorStack {

    // 1. Fungsi untuk menentukan tingkat kekuatan (presedensi) operator
    static int getPresedensi(char ch) {
        if (ch == '+' || ch == '-') return 1;
        if (ch == '*' || ch == '/') return 2;
        if (ch == '^') return 3;
        return -1;
    }

    // 2. Fungsi Konversi Infix ke Postfix
    static String infixKePostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        System.out.println("\n>>> PROSES KONVERSI INFIX KE POSTFIX <<<");
        System.out.printf("%-15s | %-15s | %-15s\n", "Karakter", "Stack", "Postfix");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // Jika angka, langsung masukkan ke hasil postfix
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } 
            // Jika kurung buka, push ke stack
            else if (c == '(') {
                stack.push(c);
            } 
            // Jika kurung tutup, pop semua sampai ketemu kurung buka
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // buang '('
            } 
            // Jika operator
            else {
                while (!stack.isEmpty() && getPresedensi(c) <= getPresedensi(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
            // Tampilkan step per step konversi
            System.out.printf("%-15s | %-15s | %-15s\n", c, stack.toString(), postfix.toString());
        }

        // Pop sisa operator di stack
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    // 3. Fungsi Evaluasi (Perhitungan) Postfix
    static int hitungPostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        System.out.println("\n>>> STEP-BY-STEP PERHITUNGAN (EVALUASI) <<<");

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            // Jika karakter adalah angka
            if (Character.isDigit(c)) {
                int angka = c - '0'; // Ubah char ke int
                stack.push(angka);
                System.out.println("Action: Push " + angka + " \t| Stack: " + stack);
            } 
            // Jika karakter adalah operator
            else {
                int angka2 = stack.pop();
                int angka1 = stack.pop();
                int hasilTemp = 0;

                switch (c) {
                    case '+': hasilTemp = angka1 + angka2; break;
                    case '-': hasilTemp = angka1 - angka2; break;
                    case '*': hasilTemp = angka1 * angka2; break;
                    case '/': hasilTemp = angka1 / angka2; break;
                }
                stack.push(hasilTemp);
                System.out.println("Action: " + angka1 + " " + c + " " + angka2 + " \t| Stack: " + stack + " (Hasil: " + hasilTemp + ")");
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== PROGRAM EVALUATOR EKSPRESI STACK ===");
        System.out.print("Masukkan Ekspresi Infix (Tanpa Spasi, contoh 2+3*4): ");
        String ekspresiInfix = input.nextLine();

        // Langkah 1: Infix ke Postfix
        String hasilPostfix = infixKePostfix(ekspresiInfix);
        System.out.println("\n---------------------------------------------------------");
        System.out.println("FINAL POSTFIX EXPRESSION: " + hasilPostfix);
        System.out.println("---------------------------------------------------------");

        // Langkah 2: Hitung Hasil Akhir
        int hasilAkhir = hitungPostfix(hasilPostfix);

        System.out.println("\n========================================");
        System.out.println("HASIL AKHIR PERHITUNGAN: " + hasilAkhir);
        System.out.println("========================================");

        input.close();
    }
}