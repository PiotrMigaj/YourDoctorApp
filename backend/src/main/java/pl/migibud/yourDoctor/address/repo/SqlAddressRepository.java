package pl.migibud.yourDoctor.address.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.migibud.yourDoctor.address.Address;

import java.util.Optional;

interface SqlAddressRepository extends AddressRepository, JpaRepository<Address,Long> {

    @Override
    @Query("select (count(a) > 0) from Address a " +
            "where a.city = ?1 and a.zipCode = ?2 and a.street = ?3 and a.number = ?4")
    boolean existsByCityAndZipCodeAndStreetAndNumber(String city, String zipCode, String street, String number);

    @Override
    @Query("select a from Address a where a.city = ?1 and a.zipCode = ?2 and a.street = ?3 and a.number = ?4")
    Optional<Address> findByCityAndZipCodeAndStreetAndNumber(String city, String zipCode, String street, String number);



}
