package com.hps.integrator.infrastructure.emums;

public enum AdditionalAmountType {
    HealthCareTotal("45"),
    PrescriptionTotal("4U"),
    VisionOpticalTotal("4V"),
    ClinicOrQualifiedMedicalTotal("4W"),
    DentalTotal("4X");

    String value;
    AdditionalAmountType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
