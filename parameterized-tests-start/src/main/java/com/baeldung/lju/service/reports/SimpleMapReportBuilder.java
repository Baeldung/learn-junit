package com.baeldung.lju.service.reports;

import java.util.List;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.Worker;

public class SimpleMapReportBuilder extends MapReportBuilder {

    @Override
    public void addWorkersData(List<Task> tasks) {
        super.workersCount.accept(tasks);
    }

    @Override
    public void addCampaignsData(List<Task> tasks) {
        super.totalCampaignsCount.accept(tasks);
    }

    @Override
    public void addTasksData(List<Task> tasks) {
        super.overDueTasksCount.accept(tasks);
        super.inProgressTasksCount.accept(tasks);
    }

    @Override
    public void addSpecificWorkerData(Worker worker) {
        super.workerName.accept(worker);
    }

}
