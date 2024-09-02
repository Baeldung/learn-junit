package com.baeldung.lju.service.impl;

import java.util.List;
import java.util.Optional;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.Worker;
import com.baeldung.lju.service.ReportsService;
import com.baeldung.lju.service.TaskService;
import com.baeldung.lju.service.WorkerService;
import com.baeldung.lju.service.reports.ReportBuilder;

public class DefaultReportsService implements ReportsService {

    private TaskService taskService;
    private WorkerService workerService;

    public DefaultReportsService() {
        super();
        this.taskService = new DefaultTaskService();
        this.workerService = new DefaultWorkerService();
    }

    public DefaultReportsService(TaskService taskService, WorkerService workerService) {
        super();
        this.taskService = taskService;
        this.workerService = workerService;
    }

    @Override
    public <T> T generateWorkerReport(ReportBuilder<T> builder, Long workerId) {
        List<Task> relevantTasks = taskService.searchTasks(null, workerId);

        Optional<Worker> requestingWorker = workerService.findById(workerId);
        builder.addSpecificWorkerData(requestingWorker.get());
        builder.addTasksData(relevantTasks);

        return builder.obtainReport();
    }

    @Override
    public <T> T generateManagerReport(ReportBuilder<T> builder) {
        List<Task> relevantTasks = taskService.searchTasks(null, null);

        builder.addTasksData(relevantTasks);
        builder.addCampaignsData(relevantTasks);
        builder.addWorkersData(relevantTasks);

        return builder.obtainReport();
    }

}
