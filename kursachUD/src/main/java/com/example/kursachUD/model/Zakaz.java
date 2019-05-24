package com.example.kursachUD.model;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table
public class Zakaz{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer zakazId;
    private String zakazName;

    @ManyToMany
    @JoinTable (name="zakaz_tovar",
            joinColumns=@JoinColumn (name="zakaz_id"),
            inverseJoinColumns=@JoinColumn(name="tovar_id"))
    private Collection<Tovar> Tovars = new ArrayList<>();

    public Zakaz() {
    }


    public Zakaz(Integer zakazId) {
        this.zakazId = zakazId;
    }

    public Zakaz(String zakazName) {
        this.zakazName = zakazName;
    }

    public Integer getzakazId() {
        return zakazId;
    }

    public Integer getZakazId() {
        return zakazId;
    }

    public void setZakazId(Integer zakazId) {
        this.zakazId = zakazId;
    }

    public void setzakazId(Integer zakazId) {
        this.zakazId = zakazId;
    }

    public String getzakazName() {
        return zakazName;
    }

    public void setzakazName(String zakazName) {
        this.zakazName = zakazName;
    }

    public Collection<Tovar> getTovars() {
        return Tovars;
    }

    public void setTovars(Collection<Tovar> tovars) {
        Tovars = tovars;
    }
}

