package org.openmrs.module.fhir.api.diagnosticreport.handler;

public class LaboratoryHandler extends AbstractDiagnosticReportHandler {

	private static final String SERVICE_CATEGORY = "LAB";

	private static final String SERVICE_CATEGORY_DESCRIPTION = "Laboratory";

	@Override
	public String getServiceCategory() {
		return SERVICE_CATEGORY;
	}

	@Override
	public String getServiceCategoryDescription() { return SERVICE_CATEGORY_DESCRIPTION; }
}
