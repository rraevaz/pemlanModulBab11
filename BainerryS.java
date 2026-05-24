import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BainerryS {
    static class Pemainf {
        int tinggi;
        int berat;

        public Pemainf(int tinggiBadan, int beratBadan) {
            this.tinggi = tinggiBadan;
            this.berat = beratBadan;
        }

        @Override
        public String toString() {
            return "Tinggi: " + tinggi + " cm, Berat: " + berat + " kg";
        }
    }

    static int binarySearchTinggi(ArrayList<Pemainf> tim, int targetTinggi) {
        int low  = 0;
        int high = tim.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2; // Titik tengah

            if (tim.get(mid).tinggi == targetTinggi) {
                return mid; // Ditemukan di index mid
            } else if (tim.get(mid).tinggi < targetTinggi) {
                low = mid + 1; // Target ada di kanan
            } else {
                high = mid - 1; // Target ada di kiri
            }
        }
        return -1; // Tidak ditemukan
    }

    static int binarySearchBerat(ArrayList<Pemainf> tim, int targetBerat) {
        int low  = 0;
        int high = tim.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (tim.get(mid).berat == targetBerat) {
                return mid;
            } else if (tim.get(mid).berat < targetBerat) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    static int hitungJumlahTinggi(ArrayList<Pemainf> tim, int targetTinggi) {
        int idx = binarySearchTinggi(tim, targetTinggi);
        if (idx == -1) return 0; // Tidak ada sama sekali

        int count = 1;
        // Ekspansi ke KIRI
        int left = idx - 1;
        while (left >= 0 && tim.get(left).tinggi == targetTinggi) {
            count++;
            left--;
        }
        // Ekspansi ke KANAN
        int right = idx + 1;
        while (right < tim.size() && tim.get(right).tinggi == targetTinggi) {
            count++;
            right++;
        }
        return count;
    }

    static int hitungJumlahBerat(ArrayList<Pemainf> tim, int targetBerat) {
        int idx = binarySearchBerat(tim, targetBerat);
        if (idx == -1) return 0;

        int count = 1;
        int left = idx - 1;
        while (left >= 0 && tim.get(left).berat == targetBerat) {
            count++;
            left--;
        }
        int right = idx + 1;
        while (right < tim.size() && tim.get(right).berat == targetBerat) {
            count++;
            right++;
        }
        return count;
    }

    static void cetakTim(String namaTim, ArrayList<Pemainf> tim) {
        System.out.println("=== " + namaTim + " (terurut) ===");
        for (int i = 0; i < tim.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tim.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Pemainf> timA = new ArrayList<>();
        timA.add(new Pemainf(168, 50));
        timA.add(new Pemainf(170, 60));
        timA.add(new Pemainf(165, 56));
        timA.add(new Pemainf(168, 55));
        timA.add(new Pemainf(172, 60));
        timA.add(new Pemainf(170, 70));
        timA.add(new Pemainf(169, 66));
        timA.add(new Pemainf(165, 56));
        timA.add(new Pemainf(171, 72));
        timA.add(new Pemainf(166, 56));

        ArrayList<Pemainf> timB = new ArrayList<>();
        timB.add(new Pemainf(170, 66));
        timB.add(new Pemainf(167, 60));
        timB.add(new Pemainf(165, 59));
        timB.add(new Pemainf(166, 58));
        timB.add(new Pemainf(168, 58));
        timB.add(new Pemainf(175, 71));
        timB.add(new Pemainf(172, 68));
        timB.add(new Pemainf(171, 68));
        timB.add(new Pemainf(168, 65));
        timB.add(new Pemainf(169, 60));

        System.out.println("============================================");
        System.out.println("   SOAL 2a: ARRAYLIST TIM A DAN TIM B");
        System.out.println("============================================\n");
        cetakTim("Tim A (sebelum sort)", timA);
        cetakTim("Tim B (sebelum sort)", timB);
        // Sort Tim B berdasarkan tinggi badan (untuk soal 2b)
        ArrayList<Pemainf> timBSortTinggi = new ArrayList<>(timB);
        Collections.sort(timBSortTinggi, Comparator.comparingInt(p -> p.tinggi));

        // Sort Tim A berdasarkan berat badan (untuk soal 2c)
        ArrayList<Pemainf> timASortBerat = new ArrayList<>(timA);
        Collections.sort(timASortBerat, Comparator.comparingInt(p -> p.berat));

        System.out.println("============================================");
        System.out.println("   SOAL 2b: BINARY SEARCH TINGGI DI TIM B");
        System.out.println("============================================\n");

        cetakTim("Tim B (diurut berdasarkan Tinggi)", timBSortTinggi);

        // Cari pemain dengan tinggi 168 cm di Tim B
        int jumlah168 = hitungJumlahTinggi(timBSortTinggi, 168);
        System.out.println("Pencarian tinggi 168 cm:");
        System.out.println("  -> Jumlah pemain Tim B dengan tinggi 168 cm: " + jumlah168 + " pemain");

        // Cari pemain dengan tinggi 160 cm di Tim B
        int jumlah160 = hitungJumlahTinggi(timBSortTinggi, 160);
        System.out.println("\nPencarian tinggi 160 cm:");
        if (jumlah160 == 0) {
            System.out.println("  -> Tidak ada pemain Tim B dengan tinggi 160 cm (binary search return -1)");
        } else {
            System.out.println("  -> Jumlah pemain Tim B dengan tinggi 160 cm: " + jumlah160 + " pemain");
        }

        System.out.println("\n============================================");
        System.out.println("   SOAL 2c: BINARY SEARCH BERAT DI TIM A");
        System.out.println("============================================\n");

        cetakTim("Tim A (diurut berdasarkan Berat)", timASortBerat);

        // Cari pemain dengan berat 56 kg di Tim A
        int jumlah56 = hitungJumlahBerat(timASortBerat, 56);
        System.out.println("Pencarian berat 56 kg:");
        System.out.println("  -> Jumlah pemain Tim A dengan berat 56 kg: " + jumlah56 + " pemain");

        // Cari pemain dengan berat 53 kg di Tim A
        int jumlah53 = hitungJumlahBerat(timASortBerat, 53);
        System.out.println("\nPencarian berat 53 kg:");
        if (jumlah53 == 0) {
            System.out.println("  -> Tidak ada pemain Tim A dengan berat 53 kg (binary search return -1)");
        } else {
            System.out.println("  -> Jumlah pemain Tim A dengan berat 53 kg: " + jumlah53 + " pemain");
        }

        System.out.println("\n============================================");
        System.out.println("   SOAL 2d: CEK KESAMAAN TINGGI / BERAT");
        System.out.println("       ANTARA TIM A DAN TIM B");
        System.out.println("============================================\n");

        ArrayList<Pemainf> timBSortBerat = new ArrayList<>(timB);
        Collections.sort(timBSortBerat, Comparator.comparingInt(p -> p.berat));

        boolean adaKesamaan = false;

        for (Pemainf pA : timA) {
            // Cek apakah ada pemain Tim B dengan tinggi yang sama
            int idxTinggi = binarySearchTinggi(timBSortTinggi, pA.tinggi);
            // Cek apakah ada pemain Tim B dengan berat yang sama
            int idxBerat  = binarySearchBerat(timBSortBerat, pA.berat);

            if (idxTinggi != -1) {
                System.out.println("MATCH TINGGI: Pemainf Tim A " + pA
                        + " memiliki tinggi yang sama dengan pemain Tim B "
                        + timBSortTinggi.get(idxTinggi));
                adaKesamaan = true;
            }

            if (idxBerat != -1) {
                System.out.println("MATCH BERAT : Pemainf Tim A " + pA
                        + " memiliki berat yang sama dengan pemain Tim B "
                        + timBSortBerat.get(idxBerat));
                adaKesamaan = true;
            }
        }

        if (!adaKesamaan) {
            System.out.println("Tidak ada pemain Tim A yang memiliki tinggi/berat sama dengan Tim B.");
        }

        System.out.println("\nSelesai.");
    }
}
