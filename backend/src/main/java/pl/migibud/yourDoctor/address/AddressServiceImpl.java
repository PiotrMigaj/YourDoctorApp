package pl.migibud.yourDoctor.address;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.migibud.yourDoctor.address.dto.CreateAddressRequest;
import pl.migibud.yourDoctor.address.repo.AddressRepository;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public Address createAddress(CreateAddressRequest createAddressRequest) {

        Optional<Address> addressOptional = addressRepository.findByCityAndZipCodeAndStreetAndNumber(
                createAddressRequest.getCity(),
                createAddressRequest.getZipCode(),
                createAddressRequest.getStreet(),
                createAddressRequest.getNumber()
        );
        Address resultAddress = addressOptional
                .orElseGet(() -> {
                            Address address = new Address(
                                    createAddressRequest.getCity(),
                                    createAddressRequest.getZipCode(),
                                    createAddressRequest.getStreet(),
                                    createAddressRequest.getNumber()
                            );
                            return addressRepository.save(address);
                        }
                );
        return resultAddress;
    }
}
