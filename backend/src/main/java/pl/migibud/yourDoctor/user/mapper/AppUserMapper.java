package pl.migibud.yourDoctor.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.yourDoctor.user.AppUser;
import pl.migibud.yourDoctor.user.dto.AppUserDto;

@Mapper(componentModel = "spring", imports = {java.util.stream.Collectors.class})
public interface AppUserMapper {
    @Mappings(value = {
            @Mapping(source = "id", target="id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "securityUser.email", target = "email"),
    })
    AppUserDto mapAppUserToAppUserDto(AppUser appUser);
}
