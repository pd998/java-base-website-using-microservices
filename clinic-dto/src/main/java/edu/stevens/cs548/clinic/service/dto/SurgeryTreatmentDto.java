package edu.stevens.cs548.clinic.service.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class SurgeryTreatmentDto extends TreatmentDto {

	/*
	 * TODO add two fields:
	 *  surgeryDate (type LocalDate)
	 *  dischargeInstructions (type String)
	 * Annotate with @SerializedName (surgery-date and discharge-instructions)
	 * Also add getter and setter methods for these properties.
	 */
	@SerializedName("discharge-instructions")
	private String dischargeInstructions;

	@SerializedName("surgery-date")
	private LocalDate surgeryDate;

	public String getDischargeInstructions() {
		return dischargeInstructions;
	}

	public LocalDate getSurgeryDate() {
		return surgeryDate;
	}

	public void setDischargeInstructions(String dischargeInstructions) {
		this.dischargeInstructions = dischargeInstructions;
	}

	public void setSurgeryDate(LocalDate surgeryDate) {
		this.surgeryDate = surgeryDate;
	}
}
