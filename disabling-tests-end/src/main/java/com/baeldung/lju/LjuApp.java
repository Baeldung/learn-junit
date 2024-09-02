package com.baeldung.lju;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.domain.model.Worker;
import com.baeldung.lju.service.CampaignService;
import com.baeldung.lju.service.ReportsService;
import com.baeldung.lju.service.TaskService;
import com.baeldung.lju.service.WorkerService;
import com.baeldung.lju.service.impl.DefaultCampaignService;
import com.baeldung.lju.service.impl.DefaultReportsService;
import com.baeldung.lju.service.impl.DefaultTaskService;
import com.baeldung.lju.service.impl.DefaultWorkerService;
import com.baeldung.lju.service.reports.DetailedMapReportBuilder;
import com.baeldung.lju.service.reports.SimpleMapReportBuilder;

public class LjuApp {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LjuApp.class);
        logger.info("Running Learn JUnit App");

        CampaignService campaignService = new DefaultCampaignService();
        TaskService taskService = new DefaultTaskService();
        WorkerService workerService = new DefaultWorkerService();

        // create new Campaigns
        Campaign newCampaign = new Campaign("C1", "Campaign 1", "Campaign 1 Description");
        campaignService.create(newCampaign);
        logger.info("Saved new Campaign 1: {}", newCampaign);

        // create new Task
        Task newTask = new Task("Task 1", "Task 1 Description", LocalDate.now(), newCampaign, TaskStatus.TO_DO, null);
        taskService.create(newTask);
        logger.info("Saved new Task: {}", newTask);

        // find Campaign by Id
        Campaign existingCampaign = campaignService.findById(newCampaign.getId())
            .get();
        logger.info("Retrieved Campaign: {}", existingCampaign);
        logger.info("Retrieved Campaign's Tasks: {}", existingCampaign.getTasks());

        // find all Campaigns
        List<Campaign> allExistingCampaigns = campaignService.findCampaigns();
        logger.info("Retrieved all ({}) existing Campaigns: {}", allExistingCampaigns.size(), allExistingCampaigns);

        // create Task and add Assignee
        Worker worker = new Worker("john@test.com", "John", "Doe");
        workerService.create(worker);
        Task newTask2 = new Task("Task 2", "Task 2 Description", LocalDate.now()
            .minusDays(5), newCampaign, TaskStatus.TO_DO, null);
        taskService.create(newTask2);
        taskService.updateAssignee(newTask2.getId(), worker);

        // update task status
        taskService.updateStatus(newTask2.getId(), TaskStatus.IN_PROGRESS);

        // search Tasks
        List<Task> taskSearch = taskService.searchTasks(null, worker.getId());
        logger.info("Assigned task and search using filters");
        logger.info("Quantity of tasks for Assignee {}: {}", worker.getEmail(), taskSearch.size());

        // Close Campaign
        Campaign newCampaign2 = new Campaign("C2", "Campaign 2", "Campaign 2 Description");
        campaignService.create(newCampaign2);
        Task newTask3 = new Task("Task 3", "Task 3 Description", LocalDate.now(), newCampaign2, TaskStatus.TO_DO, null);
        taskService.create(newTask3);
        logger.info("Before closing Campaign - closed? {}", newCampaign2.isClosed());
        logger.info("Before closing Campaign - Task Status: {}", newCampaign2.getTasks()
            .stream()
            .findAny()
            .get()
            .getStatus());
        campaignService.closeCampaign(newCampaign2.getId());
        logger.info("After closing Campaign - closed? {}", newCampaign2.isClosed());
        logger.info("After closing Campaign - Task Status: {}", newCampaign2.getTasks()
            .stream()
            .findAny()
            .get()
            .getStatus());

        // Reports
        ReportsService reportHandler = new DefaultReportsService(taskService, workerService);
        Map<String, Object> workerSimpleReport = reportHandler.generateWorkerReport(new SimpleMapReportBuilder(), worker.getId());
        Map<String, Object> workerDetailedReport = reportHandler.generateWorkerReport(new DetailedMapReportBuilder(), worker.getId());
        Map<String, Object> managerSimpleReport = reportHandler.generateManagerReport(new SimpleMapReportBuilder());
        Map<String, Object> managerDetailedReport = reportHandler.generateManagerReport(new DetailedMapReportBuilder());
        logger.info("Worker simple report: {}", formatMap(workerSimpleReport));
        logger.info("Worker detailed report: {}", formatMap(workerDetailedReport));
        logger.info("Manager simple report: {}", formatMap(managerSimpleReport));
        logger.info("Manager detailed report: {}", formatMap(managerDetailedReport));
    }

    private static String formatMap(Map<String, Object> map) {
        return map.entrySet()
            .stream()
            .map(entry -> "%s: %s".formatted(entry.getKey(), entry.getValue()))
            .collect(Collectors.joining("\n\t", "\n{\n\t", "\n}"));
    }
}
