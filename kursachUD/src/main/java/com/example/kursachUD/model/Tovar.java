package com.example.kursachUD.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "tovar")
public class Tovar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tovarId;

    @ManyToMany
    @JoinTable (name="zakaz_tovar",
            joinColumns=@JoinColumn (name="tovar_id"),
            inverseJoinColumns=@JoinColumn(name="zakaz_id"))
    private Collection<Zakaz> zakazs = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="tovar_harakteristik",
            joinColumns=@JoinColumn (name="tovar_id"),
            inverseJoinColumns=@JoinColumn(name="harakteristiki_id"))
    private Collection<Harakteristiki> harakteristiks = new ArrayList<>();

    private String tovarName;
    private String tovarPrice;

    public Tovar() { }

    public void setTovarId(Integer tovarId) {
        this.tovarId = tovarId;
    }

    public Tovar(Integer tovarId) {
        this.tovarId = tovarId;
    }

    public Integer getTovarId() {
        return tovarId;
    }

    public String getTovarName() {
        return tovarName;
    }

    public Tovar(String tovarName, String tovarPrice) {
        this.tovarName = tovarName;
        this.tovarPrice = tovarPrice;
    }

    public void setTovarName(String tovarName) {
        this.tovarName = tovarName;
    }

    public String getTovarPrice() {
        return tovarPrice;
    }

    public void setTovarPrice(String tovarPrice) {
        this.tovarPrice = tovarPrice;
    }

    public Collection<Zakaz> getZakazs() {
        return this.zakazs;
    }

    public void setZakazs(Collection<Zakaz> zakazs) {
        this.zakazs = zakazs;
    }

    public Collection<Harakteristiki> getHarakteristiks() {
        return harakteristiks;
    }

    public void setHarakteristiks(Collection<Harakteristiki> harakteristiks) {
        this.harakteristiks = harakteristiks;
    }
}
