package com.baeldung.lju.service.reports;

import static java.util.Map.copyOf;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.domain.model.Worker;

public abstract class MapReportBuilder implements ReportBuilder<Map<String, Object>> {

    protected final Map<String, Object> report = new HashMap<>();

    @Override
    public Map<String, Object> obtainReport() {
        return copyOf(report);
    }

    // Common Map Keys and mappings:

    // WORKERS
    public static final String WORKERS_COUNT_KEY = "Workers Count";
    protected Consumer<List<Task>> workersCount = (tasks) -> this.report.put(WORKERS_COUNT_KEY, tasks.stream()
        .map(Task::getAssignee)
        .filter(Objects::nonNull)
        .distinct()
        .count());

    public static final String WORKERS_EMAILS_KEY = "Workers Emails";
    protected Consumer<List<Task>> workersEmails = (tasks) -> this.report.put(WORKERS_EMAILS_KEY, tasks.stream()
        .map(Task::getAssignee)
        .filter(Objects::nonNull)
        .map(Worker::getEmail)
        .distinct()
        .toList());

    // CAMPAIGNS
    public static final String OPEN_CAMPAIGNS_COUNT_KEY = "Open Campaigns Count";
    protected Consumer<List<Task>> campaignsCount = (tasks) -> this.report.put(OPEN_CAMPAIGNS_COUNT_KEY, tasks.stream()
        .map(Task::getCampaign)
        .filter(p -> !p.isClosed())
        .distinct()
        .count());

    public static final String OPEN_CAMPAIGNS_CODES_KEY = "Open Campaigns Codes";
    protected Consumer<List<Task>> campaignsCodes = (tasks) -> this.report.put(OPEN_CAMPAIGNS_CODES_KEY, tasks.stream()
        .map(Task::getCampaign)
        .filter(p -> !p.isClosed())
        .map(Campaign::getCode)
        .distinct()
        .toList());

    public static final String CLOSED_CAMPAIGNS_COUNT_KEY = "Closed Campaigns Count";
    protected Consumer<List<Task>> closedCampaignsCount = (tasks) -> this.report.put(CLOSED_CAMPAIGNS_COUNT_KEY, tasks.stream()
        .map(Task::getCampaign)
        .filter(p -> p.isClosed())
        .distinct()
        .count());

    public static final String CLOSED_CAMPAIGNS_CODES_KEY = "Closed Campaigns Codes";
    protected Consumer<List<Task>> closedCampaignsCodes = (tasks) -> this.report.put(CLOSED_CAMPAIGNS_CODES_KEY, tasks.stream()
        .map(Task::getCampaign)
        .filter(p -> p.isClosed())
        .map(Campaign::getCode)
        .distinct()
        .toList());

    public static final String TOTAL_CAMPAIGNS_COUNT_KEY = "Total Campaigns Count";
    protected Consumer<List<Task>> totalCampaignsCount = (tasks) -> this.report.put(TOTAL_CAMPAIGNS_COUNT_KEY, tasks.stream()
        .map(Task::getCampaign)
        .distinct()
        .count());

    // TASKS
    public static final String OVERDUE_TASKS_COUNT_KEY = "Overdue Task Count";
    protected Consumer<List<Task>> overDueTasksCount = (tasks) -> this.report.put(OVERDUE_TASKS_COUNT_KEY, tasks.stream()
        .filter(t -> !t.getStatus()
            .equals(TaskStatus.DONE) && t.getDueDate()
                .isBefore(LocalDate.now()))
        .count());

    public static final String OVERDUE_TASKS_IDS_KEY = "Overdue Task Ids";
    protected Consumer<List<Task>> overDueTasksIds = (tasks) -> this.report.put(OVERDUE_TASKS_IDS_KEY, tasks.stream()
        .filter(t -> !t.getStatus()
            .equals(TaskStatus.DONE) && t.getDueDate()
                .isBefore(LocalDate.now()))
        .map(Task::getId)
        .toList());

    public static final String IN_PROGRESS_TASKS_COUNT_KEY = "In Progress Task Count";
    protected Consumer<List<Task>> inProgressTasksCount = (tasks) -> this.report.put(IN_PROGRESS_TASKS_COUNT_KEY, tasks.stream()
        .filter(t -> t.getStatus()
            .equals(TaskStatus.IN_PROGRESS))
        .count());

    public static final String IN_PROGRESS_TASKS_IDS_KEY = "In Progress Task Ids";
    protected Consumer<List<Task>> inProgressTasksIds = (tasks) -> this.report.put(IN_PROGRESS_TASKS_IDS_KEY, tasks.stream()
        .filter(t -> t.getStatus()
            .equals(TaskStatus.IN_PROGRESS))
        .map(Task::getId)
        .toList());

    // WORKER SPECIFICS
    public static final String WORKER_NAME_KEY = "Worker Name";
    protected Consumer<Worker> workerName = (worker) -> this.report.put(WORKER_NAME_KEY, "%s %s".formatted(worker.getFirstName(), worker.getLastName()));

    public static final String WORKER_EMAIL_KEY = "Worker Email";
    protected Consumer<Worker> workerEmail = (worker) -> this.report.put(WORKER_EMAIL_KEY, worker.getEmail());
}
