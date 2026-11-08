package bmwshop;

import java.util.Locale;

/**
 * Bir avtomobilin məlumatları. Qiymət və xüsusiyyətlər yalnız burada saxlanılır.
 */
public record Car(String model, int il, int atGucu, String muherrik, int qiymet, String qeyd) {

    public static String pul(int mebleg) {
        return String.format(Locale.US, "%,d USD", mebleg);
    }

    public String qiymetMetni() {
        return pul(qiymet);
    }

    public String tefsilat() {
        String metn = "İl: " + il
                + "\nAt gücü: " + atGucu + " hp"
                + "\nMühərrik: " + muherrik
                + "\nQiymət: " + qiymetMetni();
        return qeyd.isEmpty() ? metn : metn + " (" + qeyd + ")";
    }

    @Override
    public String toString() {
        return model + "  –  " + qiymetMetni();
    }
}
