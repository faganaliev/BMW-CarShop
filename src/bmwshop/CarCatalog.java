package bmwshop;

import java.util.List;

/**
 * Dealerdə mövcud olan avtomobillərin siyahısı.
 * Yeni maşın əlavə etmək üçün bura bir sətir yazmaq kifayətdir.
 */
public final class CarCatalog {

    private CarCatalog() {
    }

    public static List<Car> all() {
        return List.of(
                new Car("BMW G20", 2021, 184, "2.0L Turbo Benzin", 38_000, ""),
                new Car("BMW G30", 2020, 252, "2.0L Turbo Benzin", 45_000, ""),
                new Car("BMW M3 G80", 2023, 510, "3.0L TwinPower Turbo (S58)", 80_000, ""),
                new Car("BMW M5 E60", 2008, 507, "5.0L V10 (S85)", 35_000, "yaxşı vəziyyətdə"),
                new Car("BMW M5 F10", 2015, 560, "4.4L V8 Twin-Turbo (S63)", 42_000, ""),
                new Car("BMW M3 F82", 2018, 431, "3.0L Twin-Turbo (S55)", 50_000, ""),
                new Car("BMW M2 CS", 2020, 450, "3.0L Twin-Turbo (S55)", 75_000, "təxmini qiymət")
        );
    }
}
