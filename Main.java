import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    // ===================== DATA MENU =====================
    static String[] menu = {
        // Makanan Berat
        "Nasi Goreng Spesial",
        "Mie Ayam Bakso",
        "Bakso Jumbo",
        "Ayam Geprek Sambal Hijau",
        "Nasi Uduk Komplit",
        "Soto Padang",
        // Snack & Gorengan
        "Pisang Goreng (5 pcs)",
        "Tempe Mendoan",
        "Risol Mayo",
        "Sala Lauak (khas Padang)",
        // Minuman
        "Es Teh Manis",
        "Es Jeruk",
        "Teh Talua (khas Minang)",
        "Kopi Susu",
        "Air Mineral"
    };

    static int[] harga = {
        15000, 12000, 13000, 18000, 14000, 13000,  // Makanan Berat
        6000, 5000, 4000, 5000,                     // Snack
        5000, 6000, 8000, 8000, 3000                // Minuman
    };

    static int[] stok = {
        10, 10, 10, 8, 10, 10,
        15, 15, 20, 20,
        20, 20, 10, 15, 30
    };

    static int[] kategoriMenu = {
        0, 0, 0, 0, 0, 0,
        1, 1, 1, 1,
        2, 2, 2, 2, 2
    };

    static String[] namaKategori = {"Makanan Berat", "Snack & Gorengan", "Minuman"};

    // ===================== DATA PESANAN =====================
    static ArrayList<Integer> pesananIndex = new ArrayList<>();
    static ArrayList<Integer> pesananJumlah = new ArrayList<>();
    static ArrayList<String> riwayat = new ArrayList<>();

    static Scanner input = new Scanner(System.in);
    static String namaPelanggan = "";
    static String mejaNomor = "";
    static String fakultas = "";

    public static void main(String[] args) {

        tampilkanSplash();
        registrasiMahasiswa();

        boolean keluarApp = false;

        while (!keluarApp) {
            tampilkanMenuUtama();
            int pilihanUtama = inputAngka("Pilihan: ", 1, 6);

            switch (pilihanUtama) {
                case 1 -> lihatMenu();
                case 2 -> tambahPesanan();
                case 3 -> lihatKeranjang();
                case 4 -> checkout();
                case 5 -> lihatRiwayat();
                case 6 -> {
                    System.out.println("\n  Sampai jumpa lagi, " + namaPelanggan + "! 👋");
                    System.out.println("  Sukses kuliah di UNP, Rang Minang! 🎓\n");
                    keluarApp = true;
                }
            }
        }

        input.close();
    }

    // ===================== TAMPILAN =====================

    static void tampilkanSplash() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                                              ║");
        System.out.println("║   🎓  UNIVERSITAS NEGERI PADANG (UNP)  🎓   ║");
        System.out.println("║        Kantin Pusat - Sistem Pemesanan       ║");
        System.out.println("║          Digital Mahasiswa & Dosen           ║");
        System.out.println("║                                              ║");
        System.out.println("║       Air Sirah Karang Manyauk 🌊            ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }

    static void registrasiMahasiswa() {
        System.out.println("  Selamat datang di Kantin UNP! Silakan isi data:\n");
        System.out.print("  Nama Mahasiswa/Dosen : ");
        input.nextLine();
        namaPelanggan = input.nextLine().trim();
        if (namaPelanggan.isEmpty()) namaPelanggan = "Civitas UNP";

        System.out.println("  Pilih Fakultas/Unit:");
        System.out.println("  1. FIP   - Fak. Ilmu Pendidikan");
        System.out.println("  2. FMIPA - Fak. Matematika & IPA");
        System.out.println("  3. FT    - Fak. Teknik");
        System.out.println("  4. FIS   - Fak. Ilmu Sosial");
        System.out.println("  5. FE    - Fak. Ekonomi");
        System.out.println("  6. FBS   - Fak. Bahasa & Seni");
        System.out.println("  7. FIK   - Fak. Ilmu Keolahragaan");
        System.out.println("  8. Pascasarjana / Umum");
        int pil = inputAngka("  Pilih (1-8): ", 1, 8);
        String[] listFakultas = {"FIP", "FMIPA", "FT", "FIS", "FE", "FBS", "FIK", "Pascasarjana/Umum"};
        fakultas = listFakultas[pil - 1];

        System.out.print("  Nomor Meja : ");
        mejaNomor = input.nextLine().trim();
        if (mejaNomor.isEmpty()) mejaNomor = "-";

        System.out.println("\n  Halo, " + namaPelanggan + " (" + fakultas + ")!");
        System.out.println("  Meja " + mejaNomor + " siap dilayani 😊");
        tunggu(1800);
    }

    static void tampilkanMenuUtama() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   🎓  KANTIN PUSAT UNP - PADANG             ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf("║  👤 %-41s║%n", namaPelanggan + " | " + fakultas);
        System.out.printf("║  🪑 Meja: %-36s║%n", mejaNomor);
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║            MENU UTAMA APLIKASI               ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. 📋 Lihat Semua Menu                      ║");
        System.out.println("║  2. ➕ Tambah Pesanan                        ║");
        System.out.println("║  3. 🛒 Lihat Keranjang Belanja               ║");
        System.out.println("║  4. 💳 Checkout & Bayar                      ║");
        System.out.println("║  5. 📜 Riwayat Transaksi                     ║");
        System.out.println("║  6. 🚪 Keluar                                ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }

    // ===================== FITUR 1: LIHAT MENU =====================

    static void lihatMenu() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║          DAFTAR MENU - KANTIN PUSAT UNP                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        for (int k = 0; k < namaKategori.length; k++) {
            System.out.println("\n  ▌ " + namaKategori[k].toUpperCase());
            System.out.println("  " + "─".repeat(58));
            System.out.printf("  %-4s %-30s %-12s %-8s%n", "No.", "Menu", "Harga", "Stok");
            System.out.println("  " + "─".repeat(58));

            for (int i = 0; i < menu.length; i++) {
                if (kategoriMenu[i] == k) {
                    String statusStok = stok[i] > 0 ? "Tersedia (" + stok[i] + ")" : "HABIS";
                    String icon = stok[i] == 0 ? "❌" : (stok[i] <= 3 ? "⚠️ " : "✅");
                    System.out.printf("  %-4d %-30s Rp%-10s %s %s%n",
                        (i + 1), menu[i], formatRupiah(harga[i]), icon, statusStok);
                }
            }
        }

        System.out.println("\n  💡 Tips: Menu Teh Talua & Sala Lauak adalah khas Minang!");
        System.out.println("\n  [Enter] Kembali ke menu utama");
        input.nextLine();
        input.nextLine();
    }

    // ===================== FITUR 2: TAMBAH PESANAN =====================

    static void tambahPesanan() {
        boolean lanjut = true;

        while (lanjut) {
            clearScreen();
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║         TAMBAH PESANAN - UNP             ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  Pilih berdasarkan:                      ║");
            System.out.println("║  1. Semua Menu                           ║");
            System.out.println("║  2. Kategori Makanan Berat               ║");
            System.out.println("║  3. Kategori Snack & Gorengan            ║");
            System.out.println("║  4. Kategori Minuman                     ║");
            System.out.println("║  5. Cari Menu (by nama)                  ║");
            System.out.println("║  0. Kembali                              ║");
            System.out.println("╚══════════════════════════════════════════╝");

            int filter = inputAngka("Pilihan: ", 0, 5);

            if (filter == 0) break;

            switch (filter) {
                case 1 -> tampilkanDanPesan(-1); // Semua Menu
                case 2 -> tampilkanDanPesan(0);  // Makanan Berat
                case 3 -> tampilkanDanPesan(1);  // Snack & Gorengan
                case 4 -> tampilkanDanPesan(2);  // Minuman
                case 5 -> cariDanPesan();
            }

            System.out.print("\n  Tambah pesanan lagi? (y/t): ");
            char c = input.next().charAt(0);
            input.nextLine();
            lanjut = (c == 'y' || c == 'Y');
        }
    }

    static void tampilkanDanPesan(int filterKategori) {
        clearScreen();
        String judulFilter = filterKategori == -1 ? "SEMUA MENU" : namaKategori[filterKategori].toUpperCase();
        System.out.println("\n  ▌ " + judulFilter);
        System.out.println("  " + "─".repeat(58));
        System.out.printf("  %-4s %-30s %-12s %-8s%n", "No.", "Menu", "Harga", "Stok");
        System.out.println("  " + "─".repeat(58));

        ArrayList<Integer> indexTampil = new ArrayList<>();
        for (int i = 0; i < menu.length; i++) {
            if (filterKategori == -1 || kategoriMenu[i] == filterKategori) {
                indexTampil.add(i);
                String statusStok = stok[i] > 0 ? "(" + stok[i] + ")" : "Habis";
                String icon = stok[i] == 0 ? "❌" : "✅";
                System.out.printf("  %-4d %-30s Rp%-10s %s %s%n",
                    indexTampil.size(), menu[i], formatRupiah(harga[i]), icon, statusStok);
            }
        }

        System.out.println("  " + "─".repeat(58));
        System.out.print("\n  Pilih nomor menu (0 = batal): ");
        int pilNo = inputAngka("", 0, indexTampil.size());

        if (pilNo == 0) return;

        int idxMenu = indexTampil.get(pilNo - 1);

        if (stok[idxMenu] == 0) {
            System.out.println("\n  ❌ Maaf, stok menu ini sudah habis!");
            tunggu(1500);
            return;
        }

        System.out.println("\n  Menu dipilih : " + menu[idxMenu]);
        System.out.println("  Harga        : Rp" + formatRupiah(harga[idxMenu]));
        System.out.println("  Stok tersisa : " + stok[idxMenu]);
        System.out.print("  Jumlah pesan : ");
        int jumlah = inputAngka("", 1, stok[idxMenu]);

        int existingIdx = -1;
        for (int i = 0; i < pesananIndex.size(); i++) {
            if (pesananIndex.get(i) == idxMenu) {
                existingIdx = i;
                break;
            }
        }

        if (existingIdx >= 0) {
            int totalBaru = pesananJumlah.get(existingIdx) + jumlah;
            if (totalBaru > stok[idxMenu]) {
                System.out.println("\n  ⚠️ Stok tidak cukup! Sudah ada " + pesananJumlah.get(existingIdx) + " di keranjang.");
                tunggu(1500);
                return;
            }
            pesananJumlah.set(existingIdx, totalBaru);
            System.out.println("\n  ✅ Jumlah diperbarui di keranjang! Total: " + totalBaru + " porsi");
        } else {
            pesananIndex.add(idxMenu);
            pesananJumlah.add(jumlah);
            System.out.println("\n  ✅ Berhasil ditambahkan ke keranjang!");
        }

        System.out.println("  Subtotal: Rp" + formatRupiah(harga[idxMenu] * jumlah));
        tunggu(1500);
    }

    static void cariDanPesan() {
        System.out.print("\n  Masukkan nama menu yang dicari: ");
        input.nextLine();
        String keyword = input.nextLine().toLowerCase();

        ArrayList<Integer> hasil = new ArrayList<>();
        for (int i = 0; i < menu.length; i++) {
            if (menu[i].toLowerCase().contains(keyword)) {
                hasil.add(i);
            }
        }

        if (hasil.isEmpty()) {
            System.out.println("\n  ❌ Menu \"" + keyword + "\" tidak ditemukan.");
            tunggu(1500);
            return;
        }

        System.out.println("\n  Hasil pencarian untuk \"" + keyword + "\":");
        System.out.println("  " + "─".repeat(52));
        for (int i = 0; i < hasil.size(); i++) {
            int idx = hasil.get(i);
            System.out.printf("  %d. %-30s Rp%s%n", i + 1, menu[idx], formatRupiah(harga[idx]));
        }

        System.out.print("\n  Pilih nomor (0 = batal): ");
        int pil = inputAngka("", 0, hasil.size());
        if (pil == 0) return;

        int idxMenu = hasil.get(pil - 1);
        System.out.print("  Jumlah pesan: ");
        int jumlah = inputAngka("", 1, stok[idxMenu]);

        pesananIndex.add(idxMenu);
        pesananJumlah.add(jumlah);
        System.out.println("\n  ✅ Ditambahkan ke keranjang!");
        tunggu(1500);
    }

    // ===================== FITUR 3: KERANJANG =====================

    static void lihatKeranjang() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║          KERANJANG BELANJA - KANTIN UNP             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        if (pesananIndex.isEmpty()) {
            System.out.println("\n  🛒 Keranjang masih kosong, Rang Mudo!");
            System.out.println("  Tambahkan menu dari pilihan 'Tambah Pesanan'.\n");
            System.out.println("  [Enter] Kembali");
            try { input.nextLine(); input.nextLine(); } catch (Exception e) {}
            return;
        }

        System.out.println();
        System.out.printf("  %-4s %-30s %-6s %-12s %-12s%n", "No.", "Menu", "Qty", "Harga", "Subtotal");
        System.out.println("  " + "─".repeat(67));

        int total = 0;
        for (int i = 0; i < pesananIndex.size(); i++) {
            int idx = pesananIndex.get(i);
            int qty = pesananJumlah.get(i);
            int subtotal = harga[idx] * qty;
            total += subtotal;
            System.out.printf("  %-4d %-30s %-6d Rp%-10s Rp%s%n",
                i + 1, menu[idx], qty, formatRupiah(harga[idx]), formatRupiah(subtotal));
        }

        System.out.println("  " + "─".repeat(67));
        System.out.printf("  %-54s Rp%s%n", "TOTAL:", formatRupiah(total));
        if (total > 50000) {
            int diskon = (int)(total * 0.1);
            System.out.printf("  %-54s -Rp%s%n", "DISKON 10% (belanja > Rp50.000):", formatRupiah(diskon));
            System.out.printf("  %-54s Rp%s%n", "TOTAL AKHIR:", formatRupiah(total - diskon));
        }

        System.out.println("\n  Opsi:");
        System.out.println("  1. Hapus item dari keranjang");
        System.out.println("  2. Ubah jumlah item");
        System.out.println("  0. Kembali");
        int opt = inputAngka("  Pilihan: ", 0, 2);

        if (opt == 1) hapusItem();
        else if (opt == 2) ubahJumlah();
    }

    static void hapusItem() {
        System.out.print("  Nomor item yang dihapus: ");
        int no = inputAngka("", 1, pesananIndex.size());
        String namaHapus = menu[pesananIndex.get(no - 1)];
        pesananIndex.remove(no - 1);
        pesananJumlah.remove(no - 1);
        System.out.println("\n  ✅ \"" + namaHapus + "\" berhasil dihapus dari keranjang.");
        tunggu(1500);
    }

    static void ubahJumlah() {
        System.out.print("  Nomor item yang diubah: ");
        int no = inputAngka("", 1, pesananIndex.size());
        int idx = pesananIndex.get(no - 1);
        System.out.println("  Jumlah saat ini: " + pesananJumlah.get(no - 1));
        System.out.print("  Jumlah baru (0 = hapus): ");
        int jmlBaru = inputAngka("", 0, stok[idx]);
        if (jmlBaru == 0) {
            pesananIndex.remove(no - 1);
            pesananJumlah.remove(no - 1);
            System.out.println("  ✅ Item dihapus dari keranjang.");
        } else {
            pesananJumlah.set(no - 1, jmlBaru);
            System.out.println("  ✅ Jumlah diperbarui!");
        }
        tunggu(1500);
    }

    // ===================== FITUR 4: CHECKOUT =====================

    static void checkout() {
        if (pesananIndex.isEmpty()) {
            System.out.println("\n  ❌ Keranjang masih kosong! Tambahkan pesanan terlebih dahulu.");
            tunggu(1500);
            return;
        }

        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║            CHECKOUT & BAYAR - KANTIN UNP            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        int totalBelanja = 0;
        for (int i = 0; i < pesananIndex.size(); i++) {
            totalBelanja += harga[pesananIndex.get(i)] * pesananJumlah.get(i);
        }

        int diskon = 0;
        String infoDiskon = "";
        if (totalBelanja > 50000) {
            diskon = (int)(totalBelanja * 0.1);
            infoDiskon = "Diskon 10% (belanja > Rp50.000)";
        }
        int totalAkhir = totalBelanja - diskon;

        System.out.println("\n  📋 Ringkasan Pesanan:");
        System.out.println("  " + "─".repeat(52));
        for (int i = 0; i < pesananIndex.size(); i++) {
            int idx = pesananIndex.get(i);
            System.out.printf("  %-30s x%-3d Rp%s%n",
                menu[idx], pesananJumlah.get(i), formatRupiah(harga[idx] * pesananJumlah.get(i)));
        }
        System.out.println("  " + "─".repeat(52));
        System.out.printf("  %-36s Rp%s%n", "Subtotal:", formatRupiah(totalBelanja));
        if (diskon > 0) {
            System.out.printf("  %-36s -Rp%s%n", infoDiskon + ":", formatRupiah(diskon));
        }
        System.out.printf("  %-36s Rp%s%n", "TOTAL BAYAR:", formatRupiah(totalAkhir));

        System.out.println("\n  💳 Metode Pembayaran:");
        System.out.println("  1. Tunai");
        System.out.println("  2. Transfer Bank (BNI/BSI/Mandiri)");
        System.out.println("  3. Dompet Digital (OVO/GoPay/Dana/QRIS)");
        System.out.println("  4. Kartu Mahasiswa UNP (e-Wallet Kampus)");
        System.out.println("  0. Batal");
        int metodeBayar = inputAngka("  Pilih metode: ", 0, 4);

        if (metodeBayar == 0) return;

        String[] namaMetode = {"", "Tunai", "Transfer Bank", "Dompet Digital", "e-Wallet Kampus UNP"};
        int kembalian = 0;

        if (metodeBayar == 1) {
            System.out.print("\n  Masukkan uang bayar: Rp");
            int bayar = inputAngka("", totalAkhir, 9999999);
            kembalian = bayar - totalAkhir;
            System.out.println("  Kembalian: Rp" + formatRupiah(kembalian));
        } else if (metodeBayar == 4) {
            System.out.println("\n  📲 Tempelkan Kartu Mahasiswa UNP ke mesin...");
            System.out.println("  ✅ Pembayaran via e-Wallet Kampus berhasil!");
            tunggu(1000);
        }

        // Update stok
        for (int i = 0; i < pesananIndex.size(); i++) {
            stok[pesananIndex.get(i)] -= pesananJumlah.get(i);
        }

        // Simpan riwayat
        String waktu = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(waktu).append("] ");
        sb.append(namaPelanggan).append(" (").append(fakultas).append(") | ");
        sb.append("Total: Rp").append(formatRupiah(totalAkhir));
        sb.append(" | Metode: ").append(namaMetode[metodeBayar]);
        sb.append(" | ").append(pesananIndex.size()).append(" item");
        riwayat.add(sb.toString());

        cetakStruk(totalBelanja, diskon, totalAkhir, kembalian, namaMetode[metodeBayar]);

        pesananIndex.clear();
        pesananJumlah.clear();
    }

    static void cetakStruk(int subtotal, int diskon, int total, int kembalian, String metode) {
        String waktu = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        System.out.println("\n\n  ╔══════════════════════════════════════════╗");
        System.out.println("  ║           STRUK PEMBAYARAN               ║");
        System.out.println("  ║     KANTIN PUSAT UNIVERSITAS NEGERI      ║");
        System.out.println("  ║              PADANG (UNP)                ║");
        System.out.println("  ║        Air Sirah Karang Manyauk 🌊       ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf("  ║  Nama      : %-27s║%n", namaPelanggan);
        System.out.printf("  ║  Fakultas  : %-27s║%n", fakultas);
        System.out.printf("  ║  Meja      : %-27s║%n", mejaNomor);
        System.out.printf("  ║  Waktu     : %-27s║%n", waktu);
        System.out.printf("  ║  Metode    : %-27s║%n", metode);
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf("  ║  Subtotal  : Rp%-25s║%n", formatRupiah(subtotal));
        if (diskon > 0) {
            System.out.printf("  ║  Diskon    : -Rp%-24s║%n", formatRupiah(diskon));
        }
        System.out.printf("  ║  TOTAL     : Rp%-25s║%n", formatRupiah(total));
        if (kembalian > 0) {
            System.out.printf("  ║  Kembalian : Rp%-25s║%n", formatRupiah(kembalian));
        }
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.println("  ║    Terima kasih telah makan di Kantin    ║");
        System.out.println("  ║   UNP! Semangat kuliah, Rang Mudo! 🎓   ║");
        System.out.println("  ╚══════════════════════════════════════════╝");

        System.out.println("\n  [Enter] Kembali ke menu utama");
        try { input.nextLine(); input.nextLine(); } catch (Exception e) {}
    }

    // ===================== FITUR 5: RIWAYAT =====================

    static void lihatRiwayat() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    RIWAYAT TRANSAKSI - KANTIN UNP            ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        if (riwayat.isEmpty()) {
            System.out.println("  📜 Belum ada riwayat transaksi hari ini.");
        } else {
            for (int i = 0; i < riwayat.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + riwayat.get(i));
            }
            System.out.println("\n  Total transaksi: " + riwayat.size() + " kali");
        }

        System.out.println("\n  [Enter] Kembali");
        try { input.nextLine(); input.nextLine(); } catch (Exception e) {}
    }

    // ===================== UTILITY =====================

    static String formatRupiah(int angka) {
        String s = String.valueOf(angka);
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (count > 0 && count % 3 == 0) result.insert(0, ".");
            result.insert(0, s.charAt(i));
            count++;
        }
        return result.toString();
    }

    static int inputAngka(String prompt, int min, int max) {
        int val = -1;
        while (val < min || val > max) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            try {
                val = input.nextInt();
                if (val < min || val > max) {
                    System.out.println("  ⚠️ Masukkan angka antara " + min + " - " + max);
                }
            } catch (Exception e) {
                System.out.println("  ⚠️ Input tidak valid! Masukkan angka.");
                input.nextLine();
            }
        }
        return val;
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void tunggu(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}