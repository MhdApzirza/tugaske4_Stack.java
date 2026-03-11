def presedensi(op):
    if op in ('+', '-'): return 1
    if op in ('*', '/'): return 2
    return 0

def infix_ke_postfix(infix):
    stack = []
    postfix = ""
    
    print("\n--- PROSES SCANNING (INFIX -> POSTFIX) ---")
    print(f"{'Karakter':<10} | {'Stack':<15} | {'Postfix':<15}")
    print("-" * 45)
    
    for c in infix:
        # Jika karakter adalah angka
        if c.isdigit():
            postfix += c
        # Jika operator
        else:
            while stack and presedensi(c) <= presedensi(stack[-1]):
                postfix += stack.pop()
            stack.append(c)
        
        print(f"{c:<10} | {str(stack):<15} | {postfix:<15}")
    
    # Ambil sisa di stack
    while stack:
        postfix += stack.pop()
        
    return postfix

def hitung_hasil(postfix):
    stack = []
    print("\n--- PROSES PERHITUNGAN (EVALUASI) ---")
    
    for c in postfix:
        if c.isdigit():
            # Push angka ke stack (diubah ke integer)
            stack.append(int(c))
            print(f"Push Angka: {c} \t| Stack: {stack}")
        else:
            # Pop 2 angka teratas
            angka2 = stack.pop()
            angka1 = stack.pop()
            
            hasil_temp = 0
            if c == '+': hasil_temp = angka1 + angka2
            elif c == '-': hasil_temp = angka1 - angka2
            elif c == '*': hasil_temp = angka1 * angka2
            elif c == '/': hasil_temp = angka1 / angka2
            
            stack.append(hasil_temp)
            print(f"Hitung ({angka1}{c}{angka2}): {hasil_temp} \t| Stack: {stack}")
            
    return stack.pop()

# Main Program
print("=== PROGRAM STACK ARITMATIKA PYTHON (1 DIGIT) ===")
ekspresi = input("Masukkan ekspresi (Contoh: 2+3*4): ")

# Jalankan Konversi
hasil_postfix = infix_ke_postfix(ekspresi)
print(f"\nEKSPRESI POSTFIX: {hasil_postfix}")

# Jalankan Perhitungan
hasil_akhir = hitung_hasil(hasil_postfix)
print(f"\nHASIL AKHIR: {hasil_akhir}")