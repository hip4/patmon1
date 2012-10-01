package ch.bfh.ti.sed.patmon1.server.persistence.mock;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.model.ObservationPeriod;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.MeasurementSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;

/**
 * A mock database which contains a set of test-data.
 */
public class MockDatabase {
	private final static String TEST_DOCTOR1_PASSWORD = "12345";
	private final static String TEST_DOCTOR2_PASSWORD = "54321";

	private Set<PatientSBO> patients = new HashSet<PatientSBO>();
	private Set<ObservationPeriodSBO> observationPeriods = new HashSet<ObservationPeriodSBO>();
	private Set<DeviceSBO> devices = new HashSet<DeviceSBO>();
	private Set<MeasurementSBO> measurements = new HashSet<MeasurementSBO>();
	private Set<DoctorSBO> doctors = new HashSet<DoctorSBO>();

	public MockDatabase() {
		doctors.add(testDoctor1);
		doctors.add(testDoctor2);
	}

	public Set<DoctorSBO> getDoctors() {
		return doctors;
	}

	public Set<PatientSBO> getPatients() {
		return patients;
	}

	public Set<ObservationPeriodSBO> getObservationPeriods() {
		return observationPeriods;
	}

	public Set<DeviceSBO> getDevices() {
		return devices;
	}

	public Set<MeasurementSBO> getMeasurements() {
		return measurements;
	}

	public DoctorSBO testDoctor1 = new DoctorSBO("patrick",
			TEST_DOCTOR1_PASSWORD);
	public DoctorSBO testDoctor2 = new DoctorSBO("christian@buergi.ch",
			TEST_DOCTOR2_PASSWORD);

	public PatientSBO testPatient1 = new PatientSBO() {
		private String name = "Christoph";

		// private String id = "asdjl";
		// private Doctor treDoctor = testDoctor1;
		// private ObservationPeriod cuObservationPeriod;

		@Override
		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	};

	public ObservationPeriodSBO testObservationPeriod1 = new ObservationPeriodSBO() {
		private Date start = new Date();
		private Date end = new Date(start.getTime() + 10000);
		private PatientSBO patient = testPatient1;
		private Set<MeasurementSBO> measurements = new HashSet<MeasurementSBO>();

		@Override
		public Date getStart() {
			return start;
		}

		@Override
		public PatientSBO getPatient() {
			return patient;
		}

		@Override
		public Set<MeasurementSBO> getMeasurements() {
			measurements = new HashSet<MeasurementSBO>();

			measurements.add(testMeasurement1);
			measurements.add(testMeasurement2);
			return measurements;
		}

		@Override
		public Date getEnd() {
			return end;
		}

		@Override
		public DeviceSBO getDevice() {
			return null;
		}
	};

	public DeviceSBO testDevice1 = new DeviceSBO() {

		@Override
		public int getId() {
			return 112;
		}

		@Override
		public ObservationPeriodSBO getCurrentlyAssignedObservationPeriod() {
			return testObservationPeriod1;
		}
	};

	public MeasurementSBO testMeasurement1 = new MeasurementSBO() {
		private Date timestamp = new Date();
		private ObservationPeriod observationPeriod = testObservationPeriod1;

		@Override
		public Date getTimestamp() {
			return timestamp;
		}

		@Override
		public ObservationPeriod getObservationPeriod() {
			return observationPeriod;
		}

		@Override
		public BigDecimal getTemperature() {
			return new BigDecimal("36.43");
		}
	};

	public MeasurementSBO testMeasurement2 = new MeasurementSBO() {
		private Date timestamp = new Date();
		private ObservationPeriod observationPeriod = testObservationPeriod1;

		@Override
		public Date getTimestamp() {
			return timestamp;
		}

		@Override
		public ObservationPeriod getObservationPeriod() {
			return observationPeriod;
		}

		@Override
		public BigDecimal getTemperature() {
			return new BigDecimal("37.12");
		}
	};

}
