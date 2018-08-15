package model;

public class ArchiveTrip {
    private String data_wycieczki;
    private String nazwa_pasma;
    private String nazwa_szczytu;
    private String wysokosc;

    public ArchiveTrip() {
    }

    public ArchiveTrip(String data_wycieczki, String nazwa_pasma, String nazwa_szczytu, String wysokosc) {
        this.data_wycieczki = data_wycieczki;
        this.nazwa_pasma = nazwa_pasma;
        this.nazwa_szczytu = nazwa_szczytu;
        this.wysokosc = wysokosc;
    }

    public String getData_wycieczki() {
        return data_wycieczki;
    }

    public void setData_wycieczki(String data_wycieczki) {
        this.data_wycieczki = data_wycieczki;
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

}
