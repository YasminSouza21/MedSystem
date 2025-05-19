package com.yasmin.projects.medsystem.api.domain.patient.address;

public record ResponseAddressInfoDTO(
        Integer id,
        String street,
        String neighborhood,
        String city,
        String state
) {
    public ResponseAddressInfoDTO(Address address) {
        this(address.getId(), address.getStreet(), address.getNeighborhood(), address.getCity(), address.getState());
    }
}
