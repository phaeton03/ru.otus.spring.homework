package ru.otus.spring2021.homework_3.service;


import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring2021.homework_3.service.exception.IncorrectNameOrSurname;
import ru.otus.spring2021.homework_3.wrapper.MessageSourceWrapper;

import java.util.Scanner;

@Service
@AllArgsConstructor
public class UserService {
    private final MessageSourceWrapper msg;

    public void signIn() {
        String regEx = msg.getMessage("string.regex");
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg.getMessage("string.name"));
        String name = scanner.nextLine();
        System.out.println(msg.getMessage("string.surname"));
        String surname = scanner.nextLine();
        if (!name.matches(regEx) || !surname.matches(regEx)) {
            throw new IncorrectNameOrSurname();
        }
        System.out.printf(msg.getMessage("string.result"), name, surname);
    }
}
