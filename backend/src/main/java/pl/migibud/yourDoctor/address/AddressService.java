package pl.migibud.yourDoctor.address;

import pl.migibud.yourDoctor.address.dto.CreateAddressRequest;

public interface AddressService {

    Address createAddress(CreateAddressRequest createAddressRequest);


}
