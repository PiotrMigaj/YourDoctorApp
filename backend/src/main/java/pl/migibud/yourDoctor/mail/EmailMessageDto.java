package pl.migibud.yourDoctor.mail;

import lombok.Data;

@Data
class EmailMessageDto {
    private final String to;
    private final String title;
    private final String content;
}
