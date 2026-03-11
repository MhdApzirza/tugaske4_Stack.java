# Tugas ke-4 Struktur Data Kelas A

**Nama:** Muhammad Apzirza Rafi  
**NIM:** 24106050077

**Implementasi:** Evaluasi Ekspresi Aritmatika menggunakan Struktur Data Stack (LIFO) dalam bahasa Java dan Python.

## 📝 Deskripsi Tugas
Program ini dibuat untuk mensimulasikan bagaimana komputer memproses ekspresi matematika (Infix) dengan mengubahnya menjadi notasi **Postfix** (Reverse Polish Notation) sebelum menghitung hasil akhirnya. Data dikelola menggunakan struktur data **Stack** manual/built-in untuk mendemonstrasikan prinsip penyimpanan data linear.

Dalam struktur ini, data diatur dengan prinsip **LIFO (Last In, First Out)**. Elemen yang terakhir dimasukkan ke dalam tumpukan akan menjadi elemen pertama yang dikeluarkan, sangat ideal untuk menangani hierarki operator aritmatika dan tanda kurung.

## 🚀 Fitur Menu / Fungsi
- **Infix to Postfix Conversion**: Mengubah input manual (contoh: `2+3*4`) menjadi urutan eksekusi komputer yang efisien (`234*+`).
- **Step-by-Step Scanning**: Menampilkan perubahan isi Stack setiap kali karakter baru diproses (Scanning).
- **Postfix Evaluation**: Melakukan perhitungan aritmatika berdasarkan hasil konversi dengan logika Push dan Pop.
- **Detailed Calculation Log**: Menampilkan proses pengambilan dua operan dari stack untuk dihitung saat operator ditemukan.
- **Final Result**: Menampilkan hasil akhir perhitungan secara akurat.

## ⚙️ Detail Implementasi
- **Logic**: Menggunakan algoritma *Shunting-yard* sederhana untuk menangani prioritas operator (kali/bagi lebih tinggi dari tambah/kurang).
- **Operations**: Menggunakan operasi utama Stack yaitu `push()` (menambah), `pop()` (mengambil), dan `peek()` (melihat puncak).
- **Language**: Tersedia dalam versi Java (`.java`) dan Python (`.py`).
- **Constraints**: Dioptimalkan untuk angka satu digit (0-9) guna memudahkan visualisasi alur data saat demonstrasi.

🎥 Video Demo
Daftar operasi (Insert, Delete, & Display dengan Delay) menggunakan Circular Doubly Linked List:

**[Nonton Video Demo](https://youtu.be/Z4ZfItcZlNU?si=sWllzG78Ms3OmILL)**
*Klik gambar di atas untuk memutar video demo di YouTube @Muhammad Apzirza Rafi.*
