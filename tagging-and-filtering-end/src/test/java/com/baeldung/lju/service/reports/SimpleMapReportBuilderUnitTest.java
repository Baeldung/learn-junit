package com.baeldung.lju.service.reports;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.TaskStatus;

class SimpleMapReportBuilderUnitTest {

    @Test
    @Tag("service")
    @Tag("reporting")
    void givenSimpleReportBuilder_whenAddingCampaignData_thenTotalCampaignsCountIsCorrect() {
        SimpleMapReportBuilder reportBuilder = new SimpleMapReportBuilder();
        Campaign campaign = new Campaign("CODE-1", "Campaign Name", "Test campaign description");
        Task task = new Task("Task Name", "description", LocalDate.now(), campaign, TaskStatus.TO_DO, null);

        reportBuilder.addCampaignsData(singletonList(task));

        Map<String, Object> report = reportBuilder.obtainReport();
        assertEquals(1L, report.get("Total Campaigns Count"));
    }

}