package com.yasmin.projects.medsystem.api.domain.patient.address;

import com.yasmin.projects.medsystem.api.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cep;
    private String street;
    private String neighborhood;
    private String city;
    private String state;

    @OneToOne(mappedBy = "address")
    @Setter
    private Patient patient;

    public Address(CepDataByApiDTO data, String cep) {
        this.cep = cep;
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.city = data.city();
        this.state = data.state();
    }

    public Address update(Address addressNew){
        this.cep = addressNew.cep;
        this.street = addressNew.street;
        this.neighborhood = addressNew.neighborhood;
        this.city = addressNew.city;
        this.state = addressNew.state;

        return this;
    }
}
