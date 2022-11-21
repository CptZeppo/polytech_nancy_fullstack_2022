package org.polytech.covid.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Booking {
    @Id
    private Integer id;
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_patient", nullable=false,
        foreignKey = @ForeignKey(name="fk_patient"))
    private Patient patient;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="id_vaccination_center", nullable=false, 
        foreignKey = @ForeignKey(name="fk_vaccination_center"))
    private VaccinationCenter center;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public VaccinationCenter getCenter() {
        return center;
    }
    public void setCenter(VaccinationCenter center) {
        this.center = center;
    }
    
}
