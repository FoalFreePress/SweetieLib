package org.sweetiebelle.lib.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

final class ErrorReportingExecutor implements Executor {

    final ExecutorService delegate;

    ErrorReportingExecutor(ExecutorService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void execute(Runnable command) {
        this.delegate.execute(new ErrorReportingRunnable(command));
    }
}