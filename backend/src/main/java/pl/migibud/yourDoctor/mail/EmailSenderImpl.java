package pl.migibud.yourDoctor.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.migibud.yourDoctor.user.vo.AppUserEvent;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailMessageDto emailMessageDto) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(emailMessageDto.getTo());
        mimeMessageHelper.setSubject(emailMessageDto.getTitle());
        mimeMessageHelper.setText(emailMessageDto.getContent(),false);
        javaMailSender.send(mimeMessage);
    }

}
