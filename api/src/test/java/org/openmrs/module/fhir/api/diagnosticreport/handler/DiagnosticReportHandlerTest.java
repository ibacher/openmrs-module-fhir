package org.openmrs.module.fhir.api.diagnosticreport.handler;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.hl7.fhir.dstu3.model.DiagnosticReport;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.fhir.api.diagnosticreport.DiagnosticReportHandler;

public class DiagnosticReportHandlerTest {

	private DiagnosticReportHandler diagnosticReportHandler;

	@Before
	public void setup() {
		this.diagnosticReportHandler = new DefaultDiagnosticReportHandler();
	}


	public void shouldGetDiagnosticReportById() {
		DiagnosticReport report = diagnosticReportHandler.getFHIRDiagnosticReportById("123");
		assertThat(report, is(notNullValue()));
		assertThat(report.getId(), equalTo("123"));
	}

}
