# Nama : Muhammad Apzirza Rafi
# NIM  : 24106050077

def get_presedensi(op):
    if op in ('+', '-'): return 1
    if op in ('*', '/'): return 2
    if op == '^': return 3
    return -1

def infix_ke_postfix(infix):
    postfix = []
    stack = []
    i = 0
    
    print("\n>>> PROSES KONVERSI INFIX KE POSTFIX <<<")
    
    while i < len(infix):
        c = infix[i]

        # Jika karakter adalah angka, ambil seluruh digitnya (multi-digit)
        if c.isdigit():
            num = ""
            while i < len(infix) and infix[i].isdigit():
                num += infix[i]
                i += 1
            postfix.append(num)
            i -= 1 # Kembalikan index karena while utama akan i += 1
        
        elif c == '(':
            stack.append(c)
        
        elif c == ')':
            while stack and stack[-1] != '(':
                postfix.append(stack.pop())
            stack.pop() # Buang '('
            
        elif c in ('+', '-', '*', '/', '^'):
            while stack and get_presedensi(c) <= get_presedensi(stack[-1]):
                postfix.append(stack.pop())
            stack.append(c)
        
        i += 1
        print(f"Karakter: {c} | Stack: {stack} | Postfix: {' '.join(postfix)}")

    while stack:
        postfix.append(stack.pop())
    
    return postfix

def hitung_postfix(postfix_list):
    stack = []
    print("\n>>> STEP-BY-STEP PERHITUNGAN (EVALUASI) <<<")
    
    for token in postfix_list:
        # Jika token adalah angka (bisa multi-digit)
        if token.isdigit():
            stack.append(int(token))
            print(f"Action: Push {token} \t| Stack: {stack}")
        
        # Jika token adalah operator
        else:
            angka2 = stack.pop()
            angka1 = stack.pop()
            hasil_temp = 0
            
            if token == '+': hasil_temp = angka1 + angka2
            elif token == '-': hasil_temp = angka1 - angka2
            elif token == '*': hasil_temp = angka1 * angka2
            elif token == '/': hasil_temp = angka1 / angka2
            
            stack.append(hasil_temp)
            print(f"Action: {angka1} {token} {angka2} \t| Stack: {stack} (Hasil: {hasil_temp})")
            
    return stack.pop()

# Main Program
if __name__ == "__main__":
    print("=== PROGRAM EVALUATOR EKSPRESI STACK (PYTHON FIXED) ===")
    ekspresi = input("Masukkan Ekspresi Infix (contoh 2+2*2): ")
    
    # Langkah 1: Konversi
    hasil_postfix = infix_ke_postfix(ekspresi)
    print(f"\nFINAL POSTFIX: {' '.join(hasil_postfix)}")
    
    # Langkah 2: Hitung
    hasil_akhir = hitung_postfix(hasil_postfix)
    
    print("\n" + "="*40)
    print(f"HASIL AKHIR PERHITUNGAN: {hasil_akhir}")
    print("="*40)