package com.baeldung.lju.service.reports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.domain.model.Worker;

class SimpleMapReportBuilderUnitTest {

    private SimpleMapReportBuilder reportBuilder;

    @BeforeEach
    void beforeEach() {
        reportBuilder = new SimpleMapReportBuilder();
    }

    @ParameterizedTest
    @ValueSource(strings = { "John", "Dr. John", "John-William" })
    void whenBuildingReport_thenWorkerNameIsPresent(String firstName) {
        reportBuilder.addSpecificWorkerData(aWorkerWithName(firstName, "DOE"));

        Map<String, Object> report = reportBuilder.obtainReport();

        assertTrue(report.containsKey("Worker Name"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenWorkerWithNullOrEmptyFirstName_whenBuildingReport_thenWorkerNameIsPresent(String firstName) {
        reportBuilder.addSpecificWorkerData(aWorkerWithName(firstName, "DOE"));

        Map<String, Object> report = reportBuilder.obtainReport();

        assertTrue(report.containsKey("Worker Name"));
    }

    @ParameterizedTest
    @EnumSource(TaskStatus.class)
    void whenCheckingTaskStatuses_thenTheyAllHaveALabel(TaskStatus statuses) {
        assertFalse(StringUtils.isBlank(statuses.getLabel()));
    }

    @ParameterizedTest
    @CsvSource({ "John,John DOE", "Dr. John,Dr. John DOE", "John-William,John-William DOE" })
    void givenWorkerFirstName_whenBuildingReport_thenWorkerNameIsCorrect(String firstName, String expectedFullName) {
        reportBuilder.addSpecificWorkerData(aWorkerWithName(firstName, "DOE"));

        Map<String, Object> report = reportBuilder.obtainReport();

        assertEquals(expectedFullName, report.get("Worker Name"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/worker-names.csv")
    void givenWorkerFirstNameFromCsv_whenBuildingReport_thenWorkerNameIsCorrect(String firstName, String expectedFullName) {
        reportBuilder.addSpecificWorkerData(aWorkerWithName(firstName, "DOE"));

        Map<String, Object> report = reportBuilder.obtainReport();

        assertEquals(expectedFullName, report.get("Worker Name"));
    }

    @ParameterizedTest
    @MethodSource("customWorkersAndExpectedFullName")
    void givenCustomWorker_whenBuildingReport_thenWorkerNameIsCorrect(Worker worker, String expectedFullName) {
        reportBuilder.addSpecificWorkerData(worker);

        Map<String, Object> report = reportBuilder.obtainReport();

        assertEquals(expectedFullName, report.get("Worker Name"));
    }

    static Stream<Arguments> customWorkersAndExpectedFullName() {
        return Stream.of(Arguments.of(aWorkerWithName("J.", "DOE"), "J. DOE"), Arguments.of(aWorkerWithName("John", "DOE"), "John DOE"),
            Arguments.of(aWorkerWithName("Dr. John", "DOE"), "Dr. John DOE"), Arguments.of(aWorkerWithName("John-William", "DOE"), "John-William DOE"));
    }

    static Worker aWorkerWithName(String firstName, String lastName) {
        return new Worker("john.doe@yahoo.com", firstName, lastName);
    }
}
