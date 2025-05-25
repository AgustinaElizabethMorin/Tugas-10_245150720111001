import java.util.*;

// Class untuk merepresentasikan data pemain
class Pemain {
    private int tinggi;
    private int berat;
    private String tim;
    private int nomor;
    
    public Pemain(int nomor, String tim, int tinggi, int berat) {
        this.nomor = nomor;
        this.tim = tim;
        this.tinggi = tinggi;
        this.berat = berat;
    }
    
    // Getter methods
    public int getTinggi() { return tinggi; }
    public int getBerat() { return berat; }
    public String getTim() { return tim; }
    public int getNomor() { return nomor; }
    
    // Setter untuk tim (untuk copy ke Tim C)
    public void setTim(String tim) { this.tim = tim; }
    
    @Override
    public String toString() {
        return String.format("No.%d Tim %s - Tinggi: %d cm, Berat: %d kg", 
                           nomor, tim, tinggi, berat);
    }
}

// Comparator untuk sorting berdasarkan tinggi badan
class TinggiComparator implements Comparator<Pemain> {
    private boolean ascending;
    
    public TinggiComparator(boolean ascending) {
        this.ascending = ascending;
    }
    
    @Override
    public int compare(Pemain p1, Pemain p2) {
        if (ascending) {
            return Integer.compare(p1.getTinggi(), p2.getTinggi());
        } else {
            return Integer.compare(p2.getTinggi(), p1.getTinggi());
        }
    }
}

// Comparator untuk sorting berdasarkan berat badan
class BeratComparator implements Comparator<Pemain> {
    private boolean ascending;
    
    public BeratComparator(boolean ascending) {
        this.ascending = ascending;
    }
    
    @Override
    public int compare(Pemain p1, Pemain p2) {
        if (ascending) {
            return Integer.compare(p1.getBerat(), p2.getBerat());
        } else {
            return Integer.compare(p2.getBerat(), p1.getBerat());
        }
    }
}

public class TugasPraktikumFutsal {
    // ArrayList untuk menyimpan data tim
    private static ArrayList<Pemain> timA = new ArrayList<>();
    private static ArrayList<Pemain> timB = new ArrayList<>();
    private static ArrayList<Pemain> timC = new ArrayList<>();
    private static ArrayList<Pemain> semuaPemain = new ArrayList<>();
    
    public static void main(String[] args) {
        // Inisialisasi data
        initializeData();
        
        System.out.println("=".repeat(80));
        System.out.println("TUGAS PRAKTIKUM - COLLECTION SORTING DAN SEARCHING");
        System.out.println("DATA TIM FUTSAL");
        System.out.println("=".repeat(80));
        
        // Tampilkan data awal
        tampilkanDataAwal();
        
        // BAGIAN 1: SORTING DAN OPERASI DATA
        System.out.println("\n" + "=".repeat(50));
        System.out.println("BAGIAN 1: SORTING DAN OPERASI DATA");
        System.out.println("=".repeat(50));
        
        // 1a. Sorting berdasarkan tinggi badan
        sortingBerdasarkanTinggi();
        
        // 1b. Sorting berdasarkan berat badan
        sortingBerdasarkanBerat();
        
        // 1c. Cari nilai maksimum dan minimum
        cariNilaiMaksMinimum();
        
        // 1d. Copy Tim B ke Tim C
        copyTimBKeTimC();
        
        // BAGIAN 2: BINARY SEARCH
        System.out.println("\n" + "=".repeat(50));
        System.out.println("BAGIAN 2: BINARY SEARCH");
        System.out.println("=".repeat(50));
        
        // 2a. ArrayList sudah diimplementasikan di atas
        System.out.println("ArrayList untuk Tim A dan Tim B sudah diimplementasikan.");
        
        // 2b. Binary search tinggi badan di Tim B
        binarySearchTinggiTimB();
        
        // 2c. Binary search berat badan di Tim A
        binarySearchBeratTimA();
        
        // 2d. Cek kesamaan data antar tim
        cekKesamaanDataAntarTim();
    }
    
    // Inisialisasi data tim
    private static void initializeData() {
        // Data Tim A
        int[][] dataTimA = {
            {1, 168, 50}, {2, 170, 60}, {3, 165, 56}, {4, 168, 55}, {5, 172, 60},
            {6, 170, 70}, {7, 169, 66}, {8, 165, 56}, {9, 171, 72}, {10, 166, 56}
        };
        
        // Data Tim B
        int[][] dataTimB = {
            {1, 170, 66}, {2, 167, 60}, {3, 165, 59}, {4, 166, 58}, {5, 168, 58},
            {6, 175, 71}, {7, 172, 68}, {8, 171, 68}, {9, 168, 65}, {10, 169, 60}
        };
        
        // Masukkan data ke ArrayList
        for (int[] data : dataTimA) {
            timA.add(new Pemain(data[0], "A", data[1], data[2]));
            semuaPemain.add(new Pemain(data[0], "A", data[1], data[2]));
        }
        
        for (int[] data : dataTimB) {
            timB.add(new Pemain(data[0], "B", data[1], data[2]));
            semuaPemain.add(new Pemain(data[0], "B", data[1], data[2]));
        }
    }
    
    // Tampilkan data awal
    private static void tampilkanDataAwal() {
        System.out.println("\nDATA AWAL:");
        System.out.println("\nTIM A:");
        for (Pemain p : timA) {
            System.out.println(p);
        }
        
        System.out.println("\nTIM B:");
        for (Pemain p : timB) {
            System.out.println(p);
        }
    }
    
    // 1a. Sorting berdasarkan tinggi badan
    private static void sortingBerdasarkanTinggi() {
        System.out.println("\n1a. SORTING BERDASARKAN TINGGI BADAN");
        System.out.println("-".repeat(40));
        
        // Copy data untuk sorting
        ArrayList<Pemain> dataSort = new ArrayList<>(semuaPemain);
        
        // Ascending
        Collections.sort(dataSort, new TinggiComparator(true));
        System.out.println("\nTinggi Badan - ASCENDING (Menaik):");
        for (Pemain p : dataSort) {
            System.out.println(p);
        }
        
        // Descending
        Collections.sort(dataSort, new TinggiComparator(false));
        System.out.println("\nTinggi Badan - DESCENDING (Menurun):");
        for (Pemain p : dataSort) {
            System.out.println(p);
        }
    }
    
    // 1b. Sorting berdasarkan berat badan
    private static void sortingBerdasarkanBerat() {
        System.out.println("\n1b. SORTING BERDASARKAN BERAT BADAN");
        System.out.println("-".repeat(40));
        
        // Copy data untuk sorting
        ArrayList<Pemain> dataSort = new ArrayList<>(semuaPemain);
        
        // Ascending
        Collections.sort(dataSort, new BeratComparator(true));
        System.out.println("\nBerat Badan - ASCENDING (Menaik):");
        for (Pemain p : dataSort) {
            System.out.println(p);
        }
        
        // Descending
        Collections.sort(dataSort, new BeratComparator(false));
        System.out.println("\nBerat Badan - DESCENDING (Menurun):");
        for (Pemain p : dataSort) {
            System.out.println(p);
        }
    }
    
    // 1c. Cari nilai maksimum dan minimum
    private static void cariNilaiMaksMinimum() {
        System.out.println("\n1c. NILAI MAKSIMUM DAN MINIMUM");
        System.out.println("-".repeat(40));
        
        // Tim A
        System.out.println("\nTIM A:");
        int maxTinggiA = Collections.max(timA, Comparator.comparing(Pemain::getTinggi)).getTinggi();
        int minTinggiA = Collections.min(timA, Comparator.comparing(Pemain::getTinggi)).getTinggi();
        int maxBeratA = Collections.max(timA, Comparator.comparing(Pemain::getBerat)).getBerat();
        int minBeratA = Collections.min(timA, Comparator.comparing(Pemain::getBerat)).getBerat();
        
        System.out.printf("Tinggi Badan - Max: %d cm, Min: %d cm%n", maxTinggiA, minTinggiA);
        System.out.printf("Berat Badan  - Max: %d kg, Min: %d kg%n", maxBeratA, minBeratA);
        
        // Tim B
        System.out.println("\nTIM B:");
        int maxTinggiB = Collections.max(timB, Comparator.comparing(Pemain::getTinggi)).getTinggi();
        int minTinggiB = Collections.min(timB, Comparator.comparing(Pemain::getTinggi)).getTinggi();
        int maxBeratB = Collections.max(timB, Comparator.comparing(Pemain::getBerat)).getBerat();
        int minBeratB = Collections.min(timB, Comparator.comparing(Pemain::getBerat)).getBerat();
        
        System.out.printf("Tinggi Badan - Max: %d cm, Min: %d cm%n", maxTinggiB, minTinggiB);
        System.out.printf("Berat Badan  - Max: %d kg, Min: %d kg%n", maxBeratB, minBeratB);
    }
    
    // 1d. Copy Tim B ke Tim C
    private static void copyTimBKeTimC() {
        System.out.println("\n1d. COPY TIM B KE TIM C");
        System.out.println("-".repeat(40));
        
        // Copy setiap pemain dari Tim B ke Tim C
        for (Pemain pemain : timB) {
            Pemain pemainBaru = new Pemain(pemain.getNomor(), "C", pemain.getTinggi(), pemain.getBerat());
            timC.add(pemainBaru);
        }
        
        System.out.println("\nTIM C (Copy dari Tim B):");
        for (Pemain p : timC) {
            System.out.println(p);
        }
        System.out.println("Total anggota Tim C: " + timC.size());
    }
    
    // 2b. Binary search tinggi badan di Tim B
    private static void binarySearchTinggiTimB() {
        System.out.println("\n2b. BINARY SEARCH - TINGGI BADAN TIM B");
        System.out.println("-".repeat(40));
        
        // Buat ArrayList untuk tinggi badan Tim B dan sort
        ArrayList<Integer> tinggiTimB = new ArrayList<>();
        for (Pemain p : timB) {
            tinggiTimB.add(p.getTinggi());
        }
        Collections.sort(tinggiTimB);
        
        System.out.println("Data tinggi badan Tim B (sorted): " + tinggiTimB);
        
        // Binary search untuk tinggi 168 cm
        int jumlah168 = countOccurrences(tinggiTimB, 168);
        System.out.println("Jumlah pemain dengan tinggi badan 168 cm: " + jumlah168);
        
        // Binary search untuk tinggi 160 cm
        int jumlah160 = countOccurrences(tinggiTimB, 160);
        System.out.println("Jumlah pemain dengan tinggi badan 160 cm: " + jumlah160);
    }
    
    // 2c. Binary search berat badan di Tim A
    private static void binarySearchBeratTimA() {
        System.out.println("\n2c. BINARY SEARCH - BERAT BADAN TIM A");
        System.out.println("-".repeat(40));
        
        // Buat ArrayList untuk berat badan Tim A dan sort
        ArrayList<Integer> beratTimA = new ArrayList<>();
        for (Pemain p : timA) {
            beratTimA.add(p.getBerat());
        }
        Collections.sort(beratTimA);
        
        System.out.println("Data berat badan Tim A (sorted): " + beratTimA);
        
        // Binary search untuk berat 56 kg
        int jumlah56 = countOccurrences(beratTimA, 56);
        System.out.println("Jumlah pemain dengan berat badan 56 kg: " + jumlah56);
        
        // Binary search untuk berat 53 kg
        int jumlah53 = countOccurrences(beratTimA, 53);
        System.out.println("Jumlah pemain dengan berat badan 53 kg: " + jumlah53);
    }
    
    // Method untuk menghitung jumlah kemunculan dengan binary search
    private static int countOccurrences(ArrayList<Integer> sortedList, int target) {
        int firstIndex = findFirstOccurrence(sortedList, target);
        if (firstIndex == -1) {
            return 0; // Tidak ditemukan
        }
        
        int lastIndex = findLastOccurrence(sortedList, target);
        return lastIndex - firstIndex + 1;
    }
    
    // Binary search untuk mencari indeks pertama
    private static int findFirstOccurrence(ArrayList<Integer> sortedList, int target) {
        int left = 0, right = sortedList.size() - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (sortedList.get(mid) == target) {
                result = mid;
                right = mid - 1; // Cari di sebelah kiri untuk occurrence pertama
            } else if (sortedList.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    // Binary search untuk mencari indeks terakhir
    private static int findLastOccurrence(ArrayList<Integer> sortedList, int target) {
        int left = 0, right = sortedList.size() - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (sortedList.get(mid) == target) {
                result = mid;
                left = mid + 1; // Cari di sebelah kanan untuk occurrence terakhir
            } else if (sortedList.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    // 2d. Cek kesamaan data antar tim
    private static void cekKesamaanDataAntarTim() {
        System.out.println("\n2d. CEK KESAMAAN DATA ANTAR TIM");
        System.out.println("-".repeat(40));
        
        // HashSet untuk menyimpan data unique dari masing-masing tim
        HashSet<Integer> tinggiTimA = new HashSet<>();
        HashSet<Integer> beratTimA = new HashSet<>();
        HashSet<Integer> tinggiTimB = new HashSet<>();
        HashSet<Integer> beratTimB = new HashSet<>();
        
        // Isi HashSet
        for (Pemain p : timA) {
            tinggiTimA.add(p.getTinggi());
            beratTimA.add(p.getBerat());
        }
        
        for (Pemain p : timB) {
            tinggiTimB.add(p.getTinggi());
            beratTimB.add(p.getBerat());
        }
        
        // Cek kesamaan tinggi badan
        HashSet<Integer> kesamaanTinggi = new HashSet<>(tinggiTimA);
        kesamaanTinggi.retainAll(tinggiTimB); // Intersection
        
        System.out.println("KESAMAAN TINGGI BADAN:");
        if (!kesamaanTinggi.isEmpty()) {
            System.out.println("Ada kesamaan tinggi badan: " + kesamaanTinggi + " cm");
            
            // Tampilkan detail pemain yang memiliki tinggi sama
            for (int tinggi : kesamaanTinggi) {
                System.out.printf("Tinggi %d cm:%n", tinggi);
                System.out.print("  Tim A: ");
                for (Pemain p : timA) {
                    if (p.getTinggi() == tinggi) {
                        System.out.printf("No.%d ", p.getNomor());
                    }
                }
                System.out.print("\n  Tim B: ");
                for (Pemain p : timB) {
                    if (p.getTinggi() == tinggi) {
                        System.out.printf("No.%d ", p.getNomor());
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("Tidak ada kesamaan tinggi badan antara Tim A dan Tim B.");
        }
        
        // Cek kesamaan berat badan
        HashSet<Integer> kesamaanBerat = new HashSet<>(beratTimA);
        kesamaanBerat.retainAll(beratTimB); // Intersection
        
        System.out.println("\nKESAMAAN BERAT BADAN:");
        if (!kesamaanBerat.isEmpty()) {
            System.out.println("Ada kesamaan berat badan: " + kesamaanBerat + " kg");
            
            // Tampilkan detail pemain yang memiliki berat sama
            for (int berat : kesamaanBerat) {
                System.out.printf("Berat %d kg:%n", berat);
                System.out.print("  Tim A: ");
                for (Pemain p : timA) {
                    if (p.getBerat() == berat) {
                        System.out.printf("No.%d ", p.getNomor());
                    }
                }
                System.out.print("\n  Tim B: ");
                for (Pemain p : timB) {
                    if (p.getBerat() == berat) {
                        System.out.printf("No.%d ", p.getNomor());
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("Tidak ada kesamaan berat badan antara Tim A dan Tim B.");
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PROGRAM SELESAI");
        System.out.println("=".repeat(80));
    }
}
