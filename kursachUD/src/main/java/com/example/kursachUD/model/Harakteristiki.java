package com.example.kursachUD.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="harakteristiki")
public class Harakteristiki {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer harakteristikiId;

    private String harktDescription;

    @ManyToMany
    @JoinTable(name="tovar_harakteristik",
            joinColumns=@JoinColumn (name="harakteristiki_id"),
            inverseJoinColumns=@JoinColumn(name="tovar_id"))
    private Collection<Tovar> tovars = new ArrayList<>();


    public Harakteristiki() {
    }

    public Harakteristiki(Integer harakteristikiId) {
        this.harakteristikiId = harakteristikiId;
    }

    public Harakteristiki(String harktDescription) {
        this.harktDescription = harktDescription;
    }


    public Integer getHarakteristikiId() {
        return harakteristikiId;
    }

    public void setHarakteristikiId(Integer harakteristikiId) {
        this.harakteristikiId = harakteristikiId;
    }

    public String getDescriptionHarkt() {
        return harktDescription;
    }

    public void setDescriptionHarkt(String harktDescription) {
        this.harktDescription = harktDescription;
    }

    public Collection<Tovar> getTovars() {
        return tovars;
    }

    public void setTovars(Collection<Tovar> tovars) {
        this.tovars = tovars;
    }
}
