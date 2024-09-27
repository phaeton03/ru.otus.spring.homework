package ru.otus.spring2021.homework_3.wrapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring2021.homework_3.config.MessageSourceConfig;

import java.util.Locale;

@Component
public class MessageSourceWrapper {
    private final MessageSource msg;
    private final String locale;

    public MessageSourceWrapper(MessageSource msg, MessageSourceConfig msgConfig) {
        this.msg = msg;
        locale = msgConfig.getLocale();
    }

    public String getMessage(String code) {
        return msg.getMessage(code, new Object[]{}, Locale.forLanguageTag(locale.trim()));
    }
}
