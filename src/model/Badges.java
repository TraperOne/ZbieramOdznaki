package model;

public class Badges {
    private String nazwa_pasma;
    private String liczba_szczytow;
    private String odznaki;

    public Badges(String nazwa_pasma, String liczba_szczytow, String odznaki) {
        this.nazwa_pasma = nazwa_pasma;
        this.liczba_szczytow = liczba_szczytow;
        this.odznaki = odznaki;
    }

    public Badges() {   }

    public String getNazwa_pasma() {
        return nazwa_pasma;
    }

    public void setNazwa_pasma(String nazwa_pasma) {
        this.nazwa_pasma = nazwa_pasma;
    }

    public String getLiczba_szczytow() {
        return liczba_szczytow;
    }

    public void setLiczba_szczytow(String liczba_szczytow) {
        this.liczba_szczytow = liczba_szczytow;
    }

    public String getOdznaki() {
        return odznaki;
    }

    public void setOdznaki(String odznaki) {
        this.odznaki = odznaki;
    }
}
