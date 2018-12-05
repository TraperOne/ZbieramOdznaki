package model;

public class BadgeKgp {

    private String nazwa_pasma;
    private String nazwa_szczytu;
    private String wysokosc;
    private String zdobyte_szczyty;

    public BadgeKgp() {
    }

    public BadgeKgp(String nazwa_pasma, String nazwa_szczytu, String wysokosc, String zdobyte_szczyty) {
        this.nazwa_pasma = nazwa_pasma;
        this.nazwa_szczytu = nazwa_szczytu;
        this.wysokosc = wysokosc;
        this.zdobyte_szczyty = zdobyte_szczyty;
    }

    public String getNazwa_pasma() {
        return nazwa_pasma;
    }

    public void setNazwa_pasma(String nazwa_pasma) {
        this.nazwa_pasma = nazwa_pasma;
    }

    public String getNazwa_szczytu() {
        return nazwa_szczytu;
    }

    public void setNazwa_szczytu(String nazwa_szczytu) {
        this.nazwa_szczytu = nazwa_szczytu;
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
