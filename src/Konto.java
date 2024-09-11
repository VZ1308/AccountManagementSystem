import java.util.ArrayList;
import java.util.List;

public class Konto {
    private String inhaber;
    private int kontonummer;
    private double kontostand;
    private List<String> transaktionen;

    // Konstruktor
    public Konto(String inhaber, int kontonummer, double kontostand) {
        this.inhaber = inhaber;
        this.kontonummer = kontonummer;
        this.kontostand = kontostand;
        this.transaktionen = new ArrayList<>();
        this.transaktionen.add("Konto erstellt mit initialem Kontostand: " + kontostand + " Euro");
    }

    // Getter- und Setter-Methoden
    public String getInhaber() {
        return inhaber;
    }

    public int getKontonummer() {
        return kontonummer;
    }

    public double getKontostand() {
        return kontostand;
    }

    public List<String> getTransaktionen() {
        return transaktionen;
    }

    // Methode zum Einzahlen von Geld auf das Konto
    public void einzahlen(double betrag) {
        if (betrag > 0) {
            kontostand += betrag;
            String transaction = "Einzahlung: " + betrag + " Euro";
            transaktionen.add(transaction);
            System.out.println(transaction);
            System.out.println("Neuer Kontostand: " + kontostand + " Euro");
        } else {
            System.out.println("Fehler: Bitte geben Sie eine positive Zahl ein.");
        }
    }

    // Methode zum Abheben von Geld vom Konto
    public void abheben(double betrag) {
        if (betrag > 0 && betrag <= kontostand) {
            kontostand -= betrag;
            String transaction = "Abhebung: " + betrag + " Euro";
            transaktionen.add(transaction);
            System.out.println(transaction);
            System.out.println("Neuer Kontostand: " + kontostand + " Euro");
        } else {
            if (betrag <= 0) {
                System.out.println("Fehler: Bitte geben Sie eine positive Zahl ein.");
            } else {
                System.out.println("Fehler: Keine ausreichende Kontodeckung! Betrag zu hoch.");
            }
        }
    }

    // Methode zur Anzeige der Kontodaten
    public void kontodatenAnzeigen() {
        System.out.println("Kontoinhaber: " + inhaber);
        System.out.println("Kontonummer: " + kontonummer);
        System.out.println("Kontostand: " + kontostand + " Euro");
        System.out.println("Transaktionen:");
        for (String transaktion : transaktionen) {
            System.out.println("  - " + transaktion);
        }
    }
}
