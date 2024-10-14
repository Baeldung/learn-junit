package com.baeldung.lju.service;

import com.baeldung.lju.service.reports.ReportBuilder;

public interface ReportsService {

    public <T> T generateWorkerReport(ReportBuilder<T> builder, Long workerId);

    public <T> T generateManagerReport(ReportBuilder<T> builder);
}
