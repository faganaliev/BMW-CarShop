# BMW CarShop

BMW dealer mağazasını simulyasiya edən sadə Java proqramı. Avtomobillərə baxmaq, məlumat almaq və satın almaq mümkündür. İki versiyası var:

- **Pəncərəli proqram** (`CarShopApp`): Java Swing ilə, siyahı, məlumat paneli və ödəniş pəncərəsi.
- **Konsol versiyası** (`CarShopConsole`): terminalda işləyən menyu.

## Xüsusiyyətlər

- 7 BMW modeli (G20, G30, M3 G80, M5 E60, M5 F10, M3 F82, M2 CS)
- Ətraflı məlumat: il, at gücü, mühərrik, qiymət
- Ödəniş: məbləğ yoxlanılır, artıq ödənişdə qalıq geri qaytarılır
- Satılan maşın siyahıdan çıxır
- Yanlış giriş proqramı çökdürmür

## İşə salmaq

Tələb olunan: **JDK 17 və ya daha yeni**.

IntelliJ IDEA-da qovluğu açın (`File → Open`), `src` qovluğunu *Sources Root* kimi işarələyin, sonra `CarShopApp.java` faylındakı `main` metodunu işə salın.

Terminaldan:

```bash
javac -encoding UTF-8 -d out src/bmwshop/*.java
java -cp out bmwshop.CarShopApp
# və ya konsol versiyası:
java -cp out bmwshop.CarShopConsole
```

## Layihə strukturu

```
src/bmwshop/
├── Car.java              # avtomobil məlumatı (record)
├── CarCatalog.java       # mövcud avtomobillərin siyahısı
├── CarShopApp.java       # pəncərəli proqram
└── CarShopConsole.java   # konsol versiyası
docs/
└── index.html            # veb versiya (GitHub Pages)
```

## Veb versiya

`docs/index.html` faylı eyni dealer mağazasının brauzerdə işləyən versiyasıdır (rəng seçimi, büdcə filtri, sevimlilər, nağd və hissə-hissə ödəniş). Quraşdırma lazım deyil, faylı brauzerdə açmaq kifayətdir.
**Canlı versiya:** 
https://faganaliev.github.io/BMW-CarShop/

Yeni maşın əlavə etmək üçün `CarCatalog.java` faylına bir sətir əlavə etmək kifayətdir.
