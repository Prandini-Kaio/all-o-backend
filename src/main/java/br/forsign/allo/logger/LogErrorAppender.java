package br.forsign.allo.logger;


import ch.qos.logback.core.AppenderBase;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.event.Level;
import org.slf4j.event.LoggingEvent;

@CommonsLog
public class LogErrorAppender extends AppenderBase<LoggingEvent> {
    @Override
    protected void append(LoggingEvent loggingEvent) {
        if(!loggingEvent.getLevel().equals(Level.ERROR)) return;

        log.error(loggingEvent.getMessage());
    }
}
