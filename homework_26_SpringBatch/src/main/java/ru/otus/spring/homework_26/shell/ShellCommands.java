package ru.otus.spring.homework_26.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework_26.repository.mongo.BookRepository;

import static ru.otus.spring.homework_26.configuration.batch.JobConfiguration.*;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommands {
    private final BookRepository bookRepository;

    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    //http://localhost:8080/h2-console/

    @ShellMethod(value = "startMigrationUserJob", key = "smu-jo")
    public void startMigrationJobWithJobOperator() throws Exception {
        Long executionId = jobOperator.start(MIGRATION_USER_JOB_NAME,"");
        System.out.println(jobOperator.getSummary(executionId));
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(MIGRATION_USER_JOB_NAME));
    }
}