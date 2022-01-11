package ru.otus.spring.homework_29.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework_29.service.LifeCycleService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final LifeCycleService lifeCycleService;

    @ShellMethod(value = "launchCycle", key = "run")
    public void launchCycleOfLife(Long size) {
        lifeCycleService.launchCycleOfLife(size);
    }
}