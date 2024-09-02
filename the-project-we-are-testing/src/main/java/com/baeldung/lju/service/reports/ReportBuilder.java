package com.baeldung.lju.service.reports;

import java.util.List;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.Worker;

public interface ReportBuilder<T> {

    void addWorkersData(List<Task> tasks);

    void addCampaignsData(List<Task> tasks);

    void addTasksData(List<Task> tasks);

    void addSpecificWorkerData(Worker worker);

    T obtainReport();
}
