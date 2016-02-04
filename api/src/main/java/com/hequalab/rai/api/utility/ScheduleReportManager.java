package com.hequalab.rai.api.utility;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.AbstractScheduledService;

public class ScheduleReportManager extends AbstractScheduledService {
    @Override
    protected void runOneIteration() throws Exception {
	System.out.println("Executing....");
    }

    @Override
    protected Scheduler scheduler() {
	return Scheduler.newFixedRateSchedule(0, 5, TimeUnit.MINUTES);
    }

    @Override
    protected void startUp() {
	// Init
	System.out.println("StartUp Activity....");
    }

    @Override
    protected void shutDown() {
	// Stop dello schedule
    }
}
