package bmwshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * BMW Dealership – konsol versiyası (təmizlənmiş orijinal kod).
 */
public class CarShopConsole {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Car> cars = new ArrayList<>(CarCatalog.all());

    public static void main(String[] args) {
        System.out.println("BMW Dealership-ə xoş gəlmisiniz...");
        boolean davam = true;
        while (davam) {
            System.out.println("\nHansı xidmətlə maraqlanırsız:\n 1. Baxmaq\n 2. Ətraflı məlumat və alış\n 3. Çıxış");
            switch (oxu("Seçim edin: ", 1, 3)) {
                case 1 -> siyahi();
                case 2 -> secVeAl();
                case 3 -> davam = false;
            }
        }
        System.out.println("Sağolun, yenə gözləyirik.");
    }

    private static void siyahi() {
        if (cars.isEmpty()) {
            System.out.println("Hazırda satışda avtomobil yoxdur.");
            return;
        }
        System.out.println("Əlimizdə olan avtomobillər:");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + cars.get(i));
        }
    }

    private static void secVeAl() {
        if (cars.isEmpty()) {
            System.out.println("Hazırda satışda avtomobil yoxdur.");
            return;
        }
        siyahi();
        int secim = oxu("Maraqlandığınız avtomobilin nömrəsi (geri qayıtmaq üçün 0): ", 0, cars.size());
        if (secim == 0) {
            return;
        }
        Car car = cars.get(secim - 1);
        System.out.println("\n" + car.model() + "\n" + car.tefsilat());
        System.out.println("Almaq istəyirsiniz?\n 1. Bəli\n 2. Xeyr");
        if (oxu("Seçim edin: ", 1, 2) == 1) {
            odenis(car);
        }
    }

    private static void odenis(Car car) {
        System.out.println("Ödəniş USD ilə olunur. Ləğv etmək üçün 0 yazın.");
        while (true) {
            int mebleg = oxu("Ödəyəcəyiniz məbləği daxil edin: ", 0, Integer.MAX_VALUE);
            if (mebleg == 0) {
                System.out.println("Alış ləğv edildi.");
                return;
            }
            if (mebleg < car.qiymet()) {
                System.out.println("Məbləğ kifayət deyil. Qiymət: " + car.qiymetMetni());
                continue;
            }
            System.out.println("Geri qaytarılacaq məbləğ: " + Car.pul(mebleg - car.qiymet()));
            System.out.println("Sizinlə iş görmək xoş idi. Xoş sürüşlər!");
            cars.remove(car);
            return;
        }
    }

    /** Düzgün ədəd daxil edilənə qədər soruşur; proqram çökmür. */
    private static int oxu(String mesaj, int min, int max) {
        while (true) {
            System.out.print(mesaj);
            String giris = scanner.nextLine().trim();
            try {
                int n = Integer.parseInt(giris);
                if (n >= min && n <= max) {
                    return n;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Təəssüf ki, belə seçim yoxdur. Yenidən cəhd edin.");
        }
    }
}
