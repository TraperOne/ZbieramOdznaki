package model;

public class Badges {
    private String lancuchy_gorskie;
    private String nazwa_pasma;
    private String liczba_szczytow;
    private String stopnie_odznak;

    public Badges() {

    }

    public Badges(String lancuchy_gorskie, String nazwa_pasma, String liczba_szczytow, String stopnie_odznak) {
        this.lancuchy_gorskie = lancuchy_gorskie;
        this.nazwa_pasma = nazwa_pasma;
        this.liczba_szczytow = liczba_szczytow;
        this.stopnie_odznak = stopnie_odznak;
    }

    public String getLancuchy_gorskie() {
        return lancuchy_gorskie;
    }

    public void setLancuchy_gorskie(String lancuchy_gorskie) {
        this.lancuchy_gorskie = lancuchy_gorskie;
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

    public String getStopnie_odznak() {
        return stopnie_odznak;
    }

    public void setStopnie_odznak(String stopnie_odznak) {
        this.stopnie_odznak = stopnie_odznak;
    }
}
