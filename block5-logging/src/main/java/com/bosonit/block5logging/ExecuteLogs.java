package com.bosonit.block5logging;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExecuteLogs implements CommandLineRunner {
    final SomeLogs somelogs;

    public ExecuteLogs(SomeLogs somelogs) {
        this.somelogs = somelogs;
    }
    @Override
    public void run(String... args) throws Exception {
        somelogs.execute();
    }
    }


