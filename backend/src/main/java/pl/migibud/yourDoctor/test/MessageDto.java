package pl.migibud.yourDoctor.test;

import lombok.*;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class MessageDto {
    private String message;
}
