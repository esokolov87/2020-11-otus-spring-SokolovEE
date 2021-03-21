package ru.sokolovee.spring14.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchComands {
    private final Job job;
    private final JobLauncher jobLauncher;

    @ShellMethod(value = "startMigrationData", key = "start")
    public void startMigrationData() throws Exception {
        JobExecution execution = jobLauncher.run(job, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }

}
