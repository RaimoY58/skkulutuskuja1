package com.buutcamp.other;

import javax.persistence.*;

@Entity
@Table(name="kulutus_kuja1")
public class KulutusLukema {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int id;
    @Column(name="time_stamp")
    public String paiva;
    @Column(name="mittari_lukema")
    public double mlukema;
    @Column(name="paiva_kulutus")
    public double plukema;


    public KulutusLukema() {
    }

    public KulutusLukema(String paiva, double mlukema, double plukema) {
        this.paiva = paiva;
        this.mlukema = mlukema;
        this.plukema = plukema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaiva() {
        return paiva;
    }

    public void setPaiva(String paiva) {
        this.paiva = paiva;
    }

    public double getMlukema() {
        return mlukema;
    }

    public void setMlukema(double mlukema) {
        this.mlukema = mlukema;
    }

    public double getPlukema() {
        return plukema;
    }

    public void setPlukema(double plukema) {
        this.plukema = plukema;
    }

    @Override
    public String toString() {
        return "KulutusLukema{" +
                "paiva='" + paiva + '\'' +
                ", mlukema=" + mlukema +
                ", plukema=" + plukema +
                '}';
    }
}
