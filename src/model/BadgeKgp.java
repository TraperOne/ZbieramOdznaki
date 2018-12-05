package model;

public class BadgeKgp {

    private String nazwa_pasma;
    private String liczba_szczytow;
    private String wysokosc;
    private String zdobyte_szczyty;

    public BadgeKgp() {
    }

    public BadgeKgp(String nazwa_pasma, String liczba_szczytow, String wysokosc, String zdobyte_szczyty) {
        this.nazwa_pasma = nazwa_pasma;
        this.liczba_szczytow = liczba_szczytow;
        this.wysokosc = wysokosc;
        this.zdobyte_szczyty = zdobyte_szczyty;
    }

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

    public String getWysokosc() {
        return wysokosc;
    }

    public void setWysokosc(String wysokosc) {
        this.wysokosc = wysokosc;
    }

    public String getZdobyte_szczyty() {
        return zdobyte_szczyty;
    }

    public void setZdobyte_szczyty(String zdobyte_szczyty) {
        this.zdobyte_szczyty = zdobyte_szczyty;
    }
}
