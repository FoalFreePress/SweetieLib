package org.sweetiebelle.lib.scheduler;

final class ErrorReportingRunnable implements Runnable {

    private final Runnable delegate;

    ErrorReportingRunnable(Runnable delegate) {
        this.delegate = delegate;
    }

    @Override
    public void run() {
        try {
            this.delegate.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}