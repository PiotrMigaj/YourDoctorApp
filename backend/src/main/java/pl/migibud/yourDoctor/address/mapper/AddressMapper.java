package pl.migibud.yourDoctor.address.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.yourDoctor.address.Address;
import pl.migibud.yourDoctor.address.dto.AddressDto;
import pl.migibud.yourDoctor.address.dto.CreateAddressRequest;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "zipCode", target = "zipCode"),
            @Mapping(source = "street", target = "street"),
            @Mapping(source = "number", target = "number")
    })
    public AddressDto mapAddressToAddressDto(Address address);

    @Mappings(value = {
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "zipCode", target = "zipCode"),
            @Mapping(source = "street", target = "street"),
            @Mapping(source = "number", target = "number")
    })
    public Address mapCreateAddressRequestToAddress(CreateAddressRequest createAddressRequest);
}
