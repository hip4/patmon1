
package ch.bfh.ti.sed.patmon1.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ch.bfh.ti.sed.patmon1.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EndTest_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "endTest");
    private final static QName _TestMeasure_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "testMeasure");
    private final static QName _ConsultObservationPeriodMeasurementsResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "consultObservationPeriodMeasurementsResponse");
    private final static QName _RegisterPatientResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "registerPatientResponse");
    private final static QName _IsActivatedResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "isActivatedResponse");
    private final static QName _GetPatients_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "getPatients");
    private final static QName _InvalidSessionException_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "InvalidSessionException");
    private final static QName _EnterActivationCodeResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "enterActivationCodeResponse");
    private final static QName _PatientList_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "patientList");
    private final static QName _LogoutResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "logoutResponse");
    private final static QName _MeasurementList_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "measurementList");
    private final static QName _TestMeasureResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "testMeasureResponse");
    private final static QName _EntityAlreadyExistsException_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "EntityAlreadyExistsException");
    private final static QName _EntityNotFoundException_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "EntityNotFoundException");
    private final static QName _ReturnDevice_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "returnDevice");
    private final static QName _EndTestResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "endTestResponse");
    private final static QName _ReturnDeviceResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "returnDeviceResponse");
    private final static QName _IllegalStateException_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "IllegalStateException");
    private final static QName _DeviceNotAssignedException_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "DeviceNotAssignedException");
    private final static QName _DeviceAlreadyAssignedException_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "DeviceAlreadyAssignedException");
    private final static QName _RegisterPatient_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "registerPatient");
    private final static QName _IsActivated_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "isActivated");
    private final static QName _Logout_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "logout");
    private final static QName _DefineObservationPeriodResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "defineObservationPeriodResponse");
    private final static QName _DefineObservationPeriod_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "defineObservationPeriod");
    private final static QName _ConsultPatientMeasurements_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "consultPatientMeasurements");
    private final static QName _GetPatientsResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "getPatientsResponse");
    private final static QName _ConsultObservationPeriodMeasurements_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "consultObservationPeriodMeasurements");
    private final static QName _EnterActivationCode_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "enterActivationCode");
    private final static QName _ConsultPatientMeasurementsResponse_QNAME = new QName("http://ch/bfh/ti/sed/patmon1/ws/", "consultPatientMeasurementsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ch.bfh.ti.sed.patmon1.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnterActivationCode }
     * 
     */
    public EnterActivationCode createEnterActivationCode() {
        return new EnterActivationCode();
    }

    /**
     * Create an instance of {@link EndTestResponse }
     * 
     */
    public EndTestResponse createEndTestResponse() {
        return new EndTestResponse();
    }

    /**
     * Create an instance of {@link ReturnDeviceResponse }
     * 
     */
    public ReturnDeviceResponse createReturnDeviceResponse() {
        return new ReturnDeviceResponse();
    }

    /**
     * Create an instance of {@link TestMeasure }
     * 
     */
    public TestMeasure createTestMeasure() {
        return new TestMeasure();
    }

    /**
     * Create an instance of {@link ConsultPatientMeasurements }
     * 
     */
    public ConsultPatientMeasurements createConsultPatientMeasurements() {
        return new ConsultPatientMeasurements();
    }

    /**
     * Create an instance of {@link ConsultPatientMeasurementsResponse }
     * 
     */
    public ConsultPatientMeasurementsResponse createConsultPatientMeasurementsResponse() {
        return new ConsultPatientMeasurementsResponse();
    }

    /**
     * Create an instance of {@link DefineObservationPeriodResponse }
     * 
     */
    public DefineObservationPeriodResponse createDefineObservationPeriodResponse() {
        return new DefineObservationPeriodResponse();
    }

    /**
     * Create an instance of {@link TestMeasureResponse }
     * 
     */
    public TestMeasureResponse createTestMeasureResponse() {
        return new TestMeasureResponse();
    }

    /**
     * Create an instance of {@link RegisterPatient }
     * 
     */
    public RegisterPatient createRegisterPatient() {
        return new RegisterPatient();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link ConsultObservationPeriodMeasurementsResponse }
     * 
     */
    public ConsultObservationPeriodMeasurementsResponse createConsultObservationPeriodMeasurementsResponse() {
        return new ConsultObservationPeriodMeasurementsResponse();
    }

    /**
     * Create an instance of {@link ConsultObservationPeriodMeasurements }
     * 
     */
    public ConsultObservationPeriodMeasurements createConsultObservationPeriodMeasurements() {
        return new ConsultObservationPeriodMeasurements();
    }

    /**
     * Create an instance of {@link MeasurementList }
     * 
     */
    public MeasurementList createMeasurementList() {
        return new MeasurementList();
    }

    /**
     * Create an instance of {@link GetPatientsResponse }
     * 
     */
    public GetPatientsResponse createGetPatientsResponse() {
        return new GetPatientsResponse();
    }

    /**
     * Create an instance of {@link FaultBean }
     * 
     */
    public FaultBean createFaultBean() {
        return new FaultBean();
    }

    /**
     * Create an instance of {@link EndTest }
     * 
     */
    public EndTest createEndTest() {
        return new EndTest();
    }

    /**
     * Create an instance of {@link IsActivated }
     * 
     */
    public IsActivated createIsActivated() {
        return new IsActivated();
    }

    /**
     * Create an instance of {@link Measurement }
     * 
     */
    public Measurement createMeasurement() {
        return new Measurement();
    }

    /**
     * Create an instance of {@link DefineObservationPeriod }
     * 
     */
    public DefineObservationPeriod createDefineObservationPeriod() {
        return new DefineObservationPeriod();
    }

    /**
     * Create an instance of {@link IsActivatedResponse }
     * 
     */
    public IsActivatedResponse createIsActivatedResponse() {
        return new IsActivatedResponse();
    }

    /**
     * Create an instance of {@link EnterActivationCodeResponse }
     * 
     */
    public EnterActivationCodeResponse createEnterActivationCodeResponse() {
        return new EnterActivationCodeResponse();
    }

    /**
     * Create an instance of {@link RegisterPatientResponse }
     * 
     */
    public RegisterPatientResponse createRegisterPatientResponse() {
        return new RegisterPatientResponse();
    }

    /**
     * Create an instance of {@link PatientList }
     * 
     */
    public PatientList createPatientList() {
        return new PatientList();
    }

    /**
     * Create an instance of {@link ReturnDevice }
     * 
     */
    public ReturnDevice createReturnDevice() {
        return new ReturnDevice();
    }

    /**
     * Create an instance of {@link Patient }
     * 
     */
    public Patient createPatient() {
        return new Patient();
    }

    /**
     * Create an instance of {@link GetPatients }
     * 
     */
    public GetPatients createGetPatients() {
        return new GetPatients();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndTest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "endTest")
    public JAXBElement<EndTest> createEndTest(EndTest value) {
        return new JAXBElement<EndTest>(_EndTest_QNAME, EndTest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestMeasure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "testMeasure")
    public JAXBElement<TestMeasure> createTestMeasure(TestMeasure value) {
        return new JAXBElement<TestMeasure>(_TestMeasure_QNAME, TestMeasure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultObservationPeriodMeasurementsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "consultObservationPeriodMeasurementsResponse")
    public JAXBElement<ConsultObservationPeriodMeasurementsResponse> createConsultObservationPeriodMeasurementsResponse(ConsultObservationPeriodMeasurementsResponse value) {
        return new JAXBElement<ConsultObservationPeriodMeasurementsResponse>(_ConsultObservationPeriodMeasurementsResponse_QNAME, ConsultObservationPeriodMeasurementsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "registerPatientResponse")
    public JAXBElement<RegisterPatientResponse> createRegisterPatientResponse(RegisterPatientResponse value) {
        return new JAXBElement<RegisterPatientResponse>(_RegisterPatientResponse_QNAME, RegisterPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsActivatedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "isActivatedResponse")
    public JAXBElement<IsActivatedResponse> createIsActivatedResponse(IsActivatedResponse value) {
        return new JAXBElement<IsActivatedResponse>(_IsActivatedResponse_QNAME, IsActivatedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatients }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "getPatients")
    public JAXBElement<GetPatients> createGetPatients(GetPatients value) {
        return new JAXBElement<GetPatients>(_GetPatients_QNAME, GetPatients.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "InvalidSessionException")
    public JAXBElement<FaultBean> createInvalidSessionException(FaultBean value) {
        return new JAXBElement<FaultBean>(_InvalidSessionException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnterActivationCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "enterActivationCodeResponse")
    public JAXBElement<EnterActivationCodeResponse> createEnterActivationCodeResponse(EnterActivationCodeResponse value) {
        return new JAXBElement<EnterActivationCodeResponse>(_EnterActivationCodeResponse_QNAME, EnterActivationCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "patientList")
    public JAXBElement<PatientList> createPatientList(PatientList value) {
        return new JAXBElement<PatientList>(_PatientList_QNAME, PatientList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasurementList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "measurementList")
    public JAXBElement<MeasurementList> createMeasurementList(MeasurementList value) {
        return new JAXBElement<MeasurementList>(_MeasurementList_QNAME, MeasurementList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestMeasureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "testMeasureResponse")
    public JAXBElement<TestMeasureResponse> createTestMeasureResponse(TestMeasureResponse value) {
        return new JAXBElement<TestMeasureResponse>(_TestMeasureResponse_QNAME, TestMeasureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "EntityAlreadyExistsException")
    public JAXBElement<FaultBean> createEntityAlreadyExistsException(FaultBean value) {
        return new JAXBElement<FaultBean>(_EntityAlreadyExistsException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "EntityNotFoundException")
    public JAXBElement<FaultBean> createEntityNotFoundException(FaultBean value) {
        return new JAXBElement<FaultBean>(_EntityNotFoundException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnDevice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "returnDevice")
    public JAXBElement<ReturnDevice> createReturnDevice(ReturnDevice value) {
        return new JAXBElement<ReturnDevice>(_ReturnDevice_QNAME, ReturnDevice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndTestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "endTestResponse")
    public JAXBElement<EndTestResponse> createEndTestResponse(EndTestResponse value) {
        return new JAXBElement<EndTestResponse>(_EndTestResponse_QNAME, EndTestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnDeviceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "returnDeviceResponse")
    public JAXBElement<ReturnDeviceResponse> createReturnDeviceResponse(ReturnDeviceResponse value) {
        return new JAXBElement<ReturnDeviceResponse>(_ReturnDeviceResponse_QNAME, ReturnDeviceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "IllegalStateException")
    public JAXBElement<FaultBean> createIllegalStateException(FaultBean value) {
        return new JAXBElement<FaultBean>(_IllegalStateException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "DeviceNotAssignedException")
    public JAXBElement<FaultBean> createDeviceNotAssignedException(FaultBean value) {
        return new JAXBElement<FaultBean>(_DeviceNotAssignedException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "DeviceAlreadyAssignedException")
    public JAXBElement<FaultBean> createDeviceAlreadyAssignedException(FaultBean value) {
        return new JAXBElement<FaultBean>(_DeviceAlreadyAssignedException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "registerPatient")
    public JAXBElement<RegisterPatient> createRegisterPatient(RegisterPatient value) {
        return new JAXBElement<RegisterPatient>(_RegisterPatient_QNAME, RegisterPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsActivated }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "isActivated")
    public JAXBElement<IsActivated> createIsActivated(IsActivated value) {
        return new JAXBElement<IsActivated>(_IsActivated_QNAME, IsActivated.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefineObservationPeriodResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "defineObservationPeriodResponse")
    public JAXBElement<DefineObservationPeriodResponse> createDefineObservationPeriodResponse(DefineObservationPeriodResponse value) {
        return new JAXBElement<DefineObservationPeriodResponse>(_DefineObservationPeriodResponse_QNAME, DefineObservationPeriodResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefineObservationPeriod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "defineObservationPeriod")
    public JAXBElement<DefineObservationPeriod> createDefineObservationPeriod(DefineObservationPeriod value) {
        return new JAXBElement<DefineObservationPeriod>(_DefineObservationPeriod_QNAME, DefineObservationPeriod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultPatientMeasurements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "consultPatientMeasurements")
    public JAXBElement<ConsultPatientMeasurements> createConsultPatientMeasurements(ConsultPatientMeasurements value) {
        return new JAXBElement<ConsultPatientMeasurements>(_ConsultPatientMeasurements_QNAME, ConsultPatientMeasurements.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "getPatientsResponse")
    public JAXBElement<GetPatientsResponse> createGetPatientsResponse(GetPatientsResponse value) {
        return new JAXBElement<GetPatientsResponse>(_GetPatientsResponse_QNAME, GetPatientsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultObservationPeriodMeasurements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "consultObservationPeriodMeasurements")
    public JAXBElement<ConsultObservationPeriodMeasurements> createConsultObservationPeriodMeasurements(ConsultObservationPeriodMeasurements value) {
        return new JAXBElement<ConsultObservationPeriodMeasurements>(_ConsultObservationPeriodMeasurements_QNAME, ConsultObservationPeriodMeasurements.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnterActivationCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "enterActivationCode")
    public JAXBElement<EnterActivationCode> createEnterActivationCode(EnterActivationCode value) {
        return new JAXBElement<EnterActivationCode>(_EnterActivationCode_QNAME, EnterActivationCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultPatientMeasurementsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ch/bfh/ti/sed/patmon1/ws/", name = "consultPatientMeasurementsResponse")
    public JAXBElement<ConsultPatientMeasurementsResponse> createConsultPatientMeasurementsResponse(ConsultPatientMeasurementsResponse value) {
        return new JAXBElement<ConsultPatientMeasurementsResponse>(_ConsultPatientMeasurementsResponse_QNAME, ConsultPatientMeasurementsResponse.class, null, value);
    }

}
