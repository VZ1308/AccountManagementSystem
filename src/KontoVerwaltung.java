import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class KontoVerwaltung {
    private static List<Konto> konten = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\n--- Kontoverwaltung ---");
                System.out.println("1. Konto anlegen");
                System.out.println("2. Konto löschen");
                System.out.println("3. Kontostand anzeigen");
                System.out.println("4. Geld einzahlen");
                System.out.println("5. Geld abheben");
                System.out.println("6. Daten eines Kontos anzeigen");
                System.out.println("7. Daten aller Konten anzeigen");
                System.out.println("8. Programm beenden");
                System.out.print("Bitte wählen Sie eine Option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Eingabepuffer leeren

                switch (choice) {
                    case 1:
                        kontoAnlegen();
                        break;
                    case 2:
                        kontoLoeschen();
                        break;
                    case 3:
                        kontostandAnzeigen();
                        break;
                    case 4:
                        geldEinzahlen();
                        break;
                    case 5:
                        geldAbheben();
                        break;
                    case 6:
                        kontoDatenAnzeigen();
                        break;
                    case 7:
                        alleKontoDatenAnzeigen();
                        break;
                    case 8:
                        System.out.println("Programm beendet.");
                        return;
                    default:
                        System.out.println("Fehler: Ungültige Eingabe. Bitte wählen Sie eine Zahl zwischen 1 und 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Fehler: Ungültige Eingabe. Bitte geben Sie eine Zahl ein.");
                scanner.nextLine(); // Eingabepuffer leeren
            }
        }
    }

    // Methode zum Anlegen eines neuen Kontos
    private static void kontoAnlegen() {
        int kontonummer = 0;
        boolean validInput = false;

        // Eingabe der Kontonummer
        while (!validInput) {
            System.out.print("Kontonummer eingeben: ");
            try {
                kontonummer = scanner.nextInt();
                scanner.nextLine(); // Eingabepuffer leeren
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Fehler: Bitte geben Sie eine gültige Kontonummer ein.");
                scanner.nextLine(); // Eingabepuffer leeren
            }
        }

        // Eingabe des Namens des Kontoinhabers
        System.out.print("Name des Kontoinhabers: ");
        String kontoinhaber = scanner.nextLine().trim();

        // Eingabe des Kontostands
        double kontostand = 0.0;
        validInput = false;

        while (!validInput) {
            System.out.print("Kontostand eingeben: ");
            try {
                kontostand = scanner.nextDouble();
                scanner.nextLine(); // Eingabepuffer leeren
                if (kontostand >= 0) {
                    validInput = true;
                } else {
                    System.out.println("Fehler: Der Kontostand kann nicht negativ sein. Bitte erneut versuchen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Fehler: Bitte geben Sie eine gültige Zahl für den Kontostand ein.");
                scanner.nextLine(); // Eingabepuffer leeren
            }
        }

        // Überprüfung der Eingaben
        if (!kontoinhaber.isEmpty()) {
            // Konto erstellen und zur Liste hinzufügen
            Konto konto = new Konto(kontoinhaber, kontonummer, kontostand);
            konten.add(konto);
            System.out.println("Konto erfolgreich angelegt.");
        } else {
            System.out.println("Fehler: Der Name des Kontoinhabers darf nicht leer sein.");
        }
    }

    // Methode zum Löschen eines Kontos
    private static void kontoLoeschen() {
        zeigeVerfügbareKonten();
        System.out.print("Kontonummer des zu löschenden Kontos: ");
        int kontonummer = scanner.nextInt();
        scanner.nextLine(); // Eingabepuffer leeren
        Konto zuLoeschen = findeKonto(kontonummer);
        if (zuLoeschen != null) {
            konten.remove(zuLoeschen);
            System.out.println("Konto erfolgreich gelöscht.");
        } else {
            System.out.println("Fehler: Kein Konto mit dieser Kontonummer gefunden.");
        }
    }

    // Methode zur Anzeige des Kontostands eines Kontos
    private static void kontostandAnzeigen() {
        zeigeVerfügbareKonten();
        System.out.print("Kontonummer eingeben: ");
        int kontonummer = scanner.nextInt();
        scanner.nextLine(); // Eingabepuffer leeren
        Konto konto = findeKonto(kontonummer);
        if (konto != null) {
            System.out.println("Der Kontostand beträgt: " + konto.getKontostand() + " Euro");
        } else {
            System.out.println("Fehler: Kein Konto mit dieser Kontonummer gefunden.");
        }
    }

    // Methode zum Einzahlen von Geld auf ein Konto
    private static void geldEinzahlen() {
        zeigeVerfügbareKonten();
        System.out.print("Kontonummer eingeben: ");
        int kontonummer = scanner.nextInt();
        scanner.nextLine(); // Eingabepuffer leeren
        Konto konto = findeKonto(kontonummer);
        if (konto != null) {
            try {
                System.out.print("Betrag zum Einzahlen: ");
                double betrag = scanner.nextDouble();
                scanner.nextLine(); // Eingabepuffer leeren
                konto.einzahlen(betrag);
            } catch (InputMismatchException e) {
                System.out.println("Fehler: Ungültige Eingabe. Bitte geben Sie eine Zahl ein.");
                scanner.nextLine(); // Eingabepuffer leeren
            }
        } else {
            System.out.println("Fehler: Kein Konto mit dieser Kontonummer gefunden.");
        }
    }

    // Methode zum Abheben von Geld von einem Konto
    private static void geldAbheben() {
        zeigeVerfügbareKonten();
        System.out.print("Kontonummer eingeben: ");
        int kontonummer = scanner.nextInt();
        scanner.nextLine(); // Eingabepuffer leeren
        Konto konto = findeKonto(kontonummer);
        if (konto != null) {
            try {
                System.out.print("Betrag zum Abheben: ");
                double betrag = scanner.nextDouble();
                scanner.nextLine(); // Eingabepuffer leeren
                konto.abheben(betrag);
            } catch (InputMismatchException e) {
                System.out.println("Fehler: Ungültige Eingabe. Bitte geben Sie eine Zahl ein.");
                scanner.nextLine(); // Eingabepuffer leeren
            }
        } else {
            System.out.println("Fehler: Kein Konto mit dieser Kontonummer gefunden.");
        }
    }

    // Methode zur Anzeige der Daten eines bestimmten Kontos
    private static void kontoDatenAnzeigen() {
        zeigeVerfügbareKonten();
        System.out.print("Kontonummer eingeben: ");
        int kontonummer = scanner.nextInt();
        scanner.nextLine(); // Eingabepuffer leeren
        Konto konto = findeKonto(kontonummer);
        if (konto != null) {
            konto.kontodatenAnzeigen();
        } else {
            System.out.println("Fehler: Kein Konto mit dieser Kontonummer gefunden.");
        }
    }

    // Methode zur Anzeige der Daten aller Konten
    private static void alleKontoDatenAnzeigen() {
        if (konten.isEmpty()) {
            System.out.println("Es sind keine Konten vorhanden.");
        } else {
            System.out.println("--- Alle Kontodaten ---");
            for (Konto konto : konten) {
                konto.kontodatenAnzeigen();
                System.out.println("--------------------------");
            }
        }
    }

    // Hilfsmethode zur Suche eines Kontos anhand der Kontonummer
    private static Konto findeKonto(int kontonummer) {
        for (Konto konto : konten) {
            if (konto.getKontonummer() == kontonummer) {
                return konto;
            }
        }
        return null;
    }

    // Hilfsmethode zur Anzeige aller verfügbaren Kontonummern
    private static void zeigeVerfügbareKonten() {
        if (konten.isEmpty()) {
            System.out.println("Es sind keine Konten vorhanden.");
        } else {
            System.out.println("--- Verfügbare Kontonummern ---");
            for (Konto konto : konten) {
                System.out.println("Kontonummer: " + konto.getKontonummer() + " (Inhaber: " + konto.getInhaber() + ")");
            }
        }
    }
}
