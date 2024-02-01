package com.bosonit.block5logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SomeLogs {

    private static final Logger logger = LoggerFactory.getLogger(SomeLogs.class);

    public void execute() {
        logger.debug("Este es un mensaje de depuración");
        logger.info("Este es un mensaje de información");
        logger.warn("Este es un mensaje de advertencia");
        logger.error("Este es un mensaje de error");
        logger.trace("Este es un mensaje de rastreo");
    }
}

