package pl.migibud.yourDoctor.address.repo;

import pl.migibud.yourDoctor.address.Address;

import java.util.Optional;

public interface AddressRepository {
    Optional<Address> findById(Long id);
    Address save(Address address);
    boolean existsByCityAndZipCodeAndStreetAndNumber(String city, String zipCode, String street, String number);
    Optional<Address> findByCityAndZipCodeAndStreetAndNumber(String city, String zipCode, String street, String number);
}
