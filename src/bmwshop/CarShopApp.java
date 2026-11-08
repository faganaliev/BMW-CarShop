package bmwshop;

import javax.swing.*;
import java.awt.*;

/**
 * BMW Dealership – pəncərəli (Swing) versiya.
 */
public class CarShopApp extends JFrame {

    private final DefaultListModel<Car> model = new DefaultListModel<>();
    private final JList<Car> list = new JList<>(model);
    private final JLabel basliq = new JLabel(" ");
    private final JTextArea tefsilat = new JTextArea();
    private final JButton alButton = new JButton("Satın al");

    public CarShopApp() {
        super("BMW Dealership");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        model.addAll(CarCatalog.all());
        qur();
        goster(null);
        setSize(720, 440);
        setMinimumSize(new Dimension(600, 360));
        setLocationRelativeTo(null);
    }

    private void qur() {
        JPanel kok = new JPanel(new BorderLayout(12, 12));
        kok.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        setContentPane(kok);

        JLabel ust = new JLabel("BMW Dealership-ə xoş gəlmisiniz");
        ust.setFont(ust.getFont().deriveFont(Font.BOLD, 22f));
        kok.add(ust, BorderLayout.NORTH);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(list.getFont().deriveFont(14f));
        JScrollPane solScroll = new JScrollPane(list);
        solScroll.setBorder(BorderFactory.createTitledBorder("Mövcud avtomobillər"));
        solScroll.setPreferredSize(new Dimension(280, 0));
        kok.add(solScroll, BorderLayout.WEST);

        basliq.setFont(basliq.getFont().deriveFont(Font.BOLD, 20f));
        tefsilat.setEditable(false);
        tefsilat.setOpaque(false);
        tefsilat.setLineWrap(true);
        tefsilat.setWrapStyleWord(true);
        tefsilat.setFont(tefsilat.getFont().deriveFont(16f));
        tefsilat.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        JPanel sag = new JPanel(new BorderLayout(0, 10));
        sag.setBorder(BorderFactory.createTitledBorder("Məlumat"));
        sag.add(basliq, BorderLayout.NORTH);
        sag.add(tefsilat, BorderLayout.CENTER);
        kok.add(sag, BorderLayout.CENTER);

        JButton cixis = new JButton("Çıxış");
        JPanel alt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        alt.add(alButton);
        alt.add(cixis);
        kok.add(alt, BorderLayout.SOUTH);

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                goster(list.getSelectedValue());
            }
        });
        alButton.addActionListener(e -> satinAl());
        cixis.addActionListener(e -> System.exit(0));
    }

    private void goster(Car car) {
        if (car == null) {
            basliq.setText(model.isEmpty() ? "Bütün avtomobillər satılıb" : "Bir avtomobil seçin");
            tefsilat.setText("");
            alButton.setEnabled(false);
        } else {
            basliq.setText(car.model());
            tefsilat.setText(car.tefsilat());
            alButton.setEnabled(true);
        }
    }

    private void satinAl() {
        Car car = list.getSelectedValue();
        if (car == null) {
            return;
        }

        String mesaj = car.model() + "\nQiymət: " + car.qiymetMetni()
                + "\n\nÖdəniş USD ilə olunur. Ödəyəcəyiniz məbləği daxil edin:";

        while (true) {
            String giris = JOptionPane.showInputDialog(this, mesaj, "Ödəniş", JOptionPane.QUESTION_MESSAGE);
            if (giris == null) {
                return; // istifadəçi ləğv etdi
            }
            try {
                int mebleg = Integer.parseInt(giris.trim().replace(",", "").replace(" ", ""));
                if (mebleg < car.qiymet()) {
                    mesaj = "Məbləğ kifayət deyil. Ən azı " + car.qiymetMetni() + " daxil edin:";
                    continue;
                }
                int qaliq = mebleg - car.qiymet();
                model.removeElement(car);
                JOptionPane.showMessageDialog(this,
                        "Alış tamamlandı: " + car.model()
                                + "\nGeri qaytarılacaq məbləğ: " + Car.pul(qaliq)
                                + "\n\nSizinlə iş görmək xoş idi. Xoş sürüşlər!",
                        "Təbriklər!", JOptionPane.INFORMATION_MESSAGE);
                return;
            } catch (NumberFormatException ex) {
                mesaj = "Zəhmət olmasa, yalnız rəqəm daxil edin:";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new CarShopApp().setVisible(true);
        });
    }
}
