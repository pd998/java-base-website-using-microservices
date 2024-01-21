package edu.stevens.cs548.clinic.service.init;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import edu.stevens.cs548.clinic.service.IPatientService;
import edu.stevens.cs548.clinic.service.IProviderService;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentDto;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.PhysiotherapyTreatmentDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.RadiologyTreatmentDto;
import edu.stevens.cs548.clinic.service.dto.SurgeryTreatmentDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDtoFactory;

@Singleton
@LocalBean
@Startup
// @ApplicationScoped
// @Transactional
public class InitBean {

	private static final Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());

	private static final ZoneId ZONE_ID = ZoneOffset.UTC;

	private PatientDtoFactory patientFactory = new PatientDtoFactory();

	private ProviderDtoFactory providerFactory = new ProviderDtoFactory();

	private TreatmentDtoFactory treatmentFactory = new TreatmentDtoFactory();

	// TODO
	@Inject
	private IPatientService patientService;

	// TODO
	@Inject
	private IProviderService providerService;

	/*
	 * Initialize using EJB logic
	 */
	@PostConstruct
	/*
	 * This should work to initialize with CDI bean, but there is a bug in
	 * Payara.....
	 */
	// public void init(@Observes @Initialized(ApplicationScoped.class)
	// ServletContext init) {
	public void init() {
		/*
		 * Put your testing logic here. Use the logger to display testing output in the
		 * server logs.
		 */
		logger.info("Parthkumar Darji");
		System.err.println("Parthkumar Darji");

		try {

			/*
			 * Clear the database and populate with fresh data.
			 * 
			 * Note that the service generates the external ids, when adding the entities.
			 */

			providerService.removeAll();
			patientService.removeAll();

			PatientDto john = patientFactory.createPatientDto();
			john.setName("John Doe");
			john.setDob(LocalDate.parse("1995-08-15"));

			john.setId(patientService.addPatient(john));

			ProviderDto jane = providerFactory.createProviderDto();
			jane.setName("Jane Doe");
			jane.setNpi("1234");

			jane.setId(providerService.addProvider(jane));

			DrugTreatmentDto drug01 = treatmentFactory.createDrugTreatmentDto();
			drug01.setPatientId(john.getId());
			drug01.setPatientName(john.getName());
			drug01.setProviderId(jane.getId());
			drug01.setProviderName(jane.getName());
			drug01.setDiagnosis("Headache");
			drug01.setDrug("Aspirin");
			drug01.setDosage(20);
			drug01.setFrequency(7);
			drug01.setStartDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));
			drug01.setEndDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));

			providerService.addTreatment(drug01);

			// TODO add more testing, including treatments and providers
			PatientDto parth = patientFactory.createPatientDto();
			parth.setName("Parth Darji");
			parth.setDob(LocalDate.parse("1999-03-15"));
			parth.setId(patientService.addPatient(parth));

			PatientDto param = patientFactory.createPatientDto();
			param.setName("Param Shah");
			param.setDob(LocalDate.parse("1999-03-15"));
			param.setId(patientService.addPatient(param));

			ProviderDto megh = providerFactory.createProviderDto();
			megh.setName("Megh Shah");
			megh.setNpi("1234");
			megh.setId(providerService.addProvider(megh));

			ProviderDto vedant = providerFactory.createProviderDto();
			vedant.setName("Vedant Gupta");
			vedant.setNpi("1234");
			vedant.setId(providerService.addProvider(vedant));

			List<LocalDate> dates = new ArrayList<LocalDate>();
			dates.add(LocalDate.ofInstant(Instant.now(), ZONE_ID));

			PhysiotherapyTreatmentDto phy01 = treatmentFactory.createPhysiotherapyTreatmentDto();
			phy01.setPatientId(john.getId());
			phy01.setPatientName(john.getName());
			phy01.setProviderId(jane.getId());
			phy01.setProviderName(jane.getName());
			phy01.setDiagnosis("Back Pain");
			phy01.setTreatmentDates(dates);
			providerService.addTreatment(phy01);

			RadiologyTreatmentDto radio01 = treatmentFactory.createRadiologyTreatmentDto();
			radio01.setPatientId(john.getId());
			radio01.setPatientName(john.getName());
			radio01.setProviderId(jane.getId());
			radio01.setProviderName(jane.getName());
			radio01.setDiagnosis("Brian demage");
			radio01.setTreatmentDates(dates);
			providerService.addTreatment(radio01);

			SurgeryTreatmentDto sur01 = treatmentFactory.createSurgeryTreatmentDto();
			sur01.setPatientId(john.getId());
			sur01.setPatientName(john.getName());
			sur01.setProviderId(jane.getId());
			sur01.setProviderName(jane.getName());
			sur01.setDiagnosis("kidney stone");
			sur01.setDischargeInstructions("Drink beer");
			sur01.setSurgeryDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));
			providerService.addTreatment(sur01);

			DrugTreatmentDto drug02 = treatmentFactory.createDrugTreatmentDto();
			drug02.setPatientId(parth.getId());
			drug02.setPatientName(parth.getName());
			drug02.setProviderId(megh.getId());
			drug02.setProviderName(megh.getName());
			drug02.setDiagnosis("Headache");
			drug02.setDrug("Aspirin");
			drug02.setDosage(20);
			drug02.setFrequency(7);
			drug02.setStartDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));
			drug02.setEndDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));
			providerService.addTreatment(drug02);

			PhysiotherapyTreatmentDto phy02 = treatmentFactory.createPhysiotherapyTreatmentDto();
			phy02.setPatientId(parth.getId());
			phy02.setPatientName(parth.getName());
			phy02.setProviderId(megh.getId());
			phy02.setProviderName(megh.getName());
			phy02.setDiagnosis("Back Pain");
			phy02.setTreatmentDates(dates);
			providerService.addTreatment(phy02);

			RadiologyTreatmentDto radio02 = treatmentFactory.createRadiologyTreatmentDto();
			radio02.setPatientId(parth.getId());
			radio02.setPatientName(parth.getName());
			radio02.setProviderId(megh.getId());
			radio02.setProviderName(megh.getName());
			radio02.setDiagnosis("Brian demage");
			radio02.setTreatmentDates(dates);
			providerService.addTreatment(radio02);

			SurgeryTreatmentDto sur02 = treatmentFactory.createSurgeryTreatmentDto();
			sur02.setPatientId(parth.getId());
			sur02.setPatientName(parth.getName());
			sur02.setProviderId(megh.getId());
			sur02.setProviderName(megh.getName());
			sur02.setDiagnosis("kidney stone");
			sur02.setDischargeInstructions("Drink beer");
			sur02.setSurgeryDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));
			providerService.addTreatment(sur02);

			DrugTreatmentDto drug03 = treatmentFactory.createDrugTreatmentDto();
			drug03.setPatientId(param.getId());
			drug03.setPatientName(param.getName());
			drug03.setProviderId(vedant.getId());
			drug03.setProviderName(vedant.getName());
			drug03.setDiagnosis("Headache");
			drug03.setDrug("Aspirin");
			drug03.setDosage(20);
			drug03.setFrequency(7);
			drug03.setStartDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));
			drug03.setEndDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));

			PhysiotherapyTreatmentDto phy03 = treatmentFactory.createPhysiotherapyTreatmentDto();
			phy03.setPatientId(param.getId());
			phy03.setPatientName(param.getName());
			phy03.setProviderId(vedant.getId());
			phy03.setProviderName(vedant.getName());
			phy03.setDiagnosis("Back Pain");
			phy03.setTreatmentDates(dates);
			providerService.addTreatment(phy03);

			RadiologyTreatmentDto radio03 = treatmentFactory.createRadiologyTreatmentDto();
			radio03.setPatientId(param.getId());
			radio03.setPatientName(param.getName());
			radio03.setProviderId(vedant.getId());
			radio03.setProviderName(vedant.getName());
			radio03.setDiagnosis("Brian demage");
			radio03.setTreatmentDates(dates);

			List<TreatmentDto> treatments = new ArrayList<TreatmentDto>();
			treatments.add(drug03);
			treatments.add(radio03);

			SurgeryTreatmentDto sur03 = treatmentFactory.createSurgeryTreatmentDto();
			sur03.setPatientId(param.getId());
			sur03.setPatientName(param.getName());
			sur03.setProviderId(vedant.getId());
			sur03.setProviderName(vedant.getName());
			sur03.setDiagnosis("kidney stone");
			sur03.setDischargeInstructions("Drink beer");
			sur03.setSurgeryDate(LocalDate.ofInstant(Instant.now(), ZONE_ID));
			sur03.setFollowupTreatments(treatments);
			providerService.addTreatment(sur03);
			// Now show in the logs what has been added

			Collection<PatientDto> patients = patientService.getPatients();
			for (PatientDto p : patients) {
				logger.info(String.format("Patient %s, ID %s, DOB %s", p.getName(), p.getId().toString(),
						p.getDob().toString()));
				logTreatments(p.getTreatments());
			}

			Collection<ProviderDto> providers = providerService.getProviders();
			for (ProviderDto p : providers) {
				logger.info(String.format("Provider %s, ID %s, NPI %s", p.getName(), p.getId().toString(), p.getNpi()));
				logTreatments(p.getTreatments());
			}

		} catch (Exception e) {

			throw new IllegalStateException("Failed to add record.", e);

		}
		
	}

	public void shutdown(@Observes @Destroyed(ApplicationScoped.class) ServletContext init) {
		logger.info("App shutting down....");
	}

	private void logTreatments(Collection<TreatmentDto> treatments) {
		for (TreatmentDto treatment : treatments) {
			if (treatment instanceof DrugTreatmentDto) {
				logTreatment((DrugTreatmentDto) treatment);
			} else if (treatment instanceof SurgeryTreatmentDto) {
				logTreatment((SurgeryTreatmentDto) treatment);
			} else if (treatment instanceof RadiologyTreatmentDto) {
				logTreatment((RadiologyTreatmentDto) treatment);
			} else if (treatment instanceof PhysiotherapyTreatmentDto) {
				logTreatment((PhysiotherapyTreatmentDto) treatment);
			}
			if (!treatment.getFollowupTreatments().isEmpty()) {
				logger.info("============= Follow-up Treatments");
				logTreatments(treatment.getFollowupTreatments());
				logger.info("============= End Follow-up Treatments");
			}
		}
	}

	private void logTreatment(DrugTreatmentDto t) {
		logger.info(String.format("...Drug treatment for %s, drug %s", t.getPatientName(), t.getDrug()));
	}

	private void logTreatment(RadiologyTreatmentDto t) {
		logger.info(String.format("...Radiology treatment for %s", t.getPatientName()));
	}

	private void logTreatment(SurgeryTreatmentDto t) {
		logger.info(String.format("...Surgery treatment for %s", t.getPatientName()));
	}

	private void logTreatment(PhysiotherapyTreatmentDto t) {
		logger.info(String.format("...Physiotherapy treatment for %s", t.getPatientName()));
	}

}
