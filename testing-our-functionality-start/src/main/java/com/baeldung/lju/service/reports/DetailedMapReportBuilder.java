package com.baeldung.lju.service.reports;

import java.util.List;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.Worker;

public class DetailedMapReportBuilder extends MapReportBuilder {

    @Override
    public void addWorkersData(List<Task> tasks) {
        super.workersCount.accept(tasks);
        super.workersEmails.accept(tasks);

    }

    @Override
    public void addCampaignsData(List<Task> tasks) {
        super.campaignsCount.accept(tasks);
        super.campaignsCodes.accept(tasks);
        super.closedCampaignsCount.accept(tasks);
        super.closedCampaignsCodes.accept(tasks);

    }

    @Override
    public void addTasksData(List<Task> tasks) {
        super.overDueTasksCount.accept(tasks);
        super.overDueTasksIds.accept(tasks);
        super.inProgressTasksCount.accept(tasks);
        super.inProgressTasksIds.accept(tasks);

    }

    @Override
    public void addSpecificWorkerData(Worker worker) {
        super.workerName.accept(worker);
        super.workerEmail.accept(worker);
    }

}
