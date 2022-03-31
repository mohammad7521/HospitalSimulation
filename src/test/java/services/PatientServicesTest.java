package services;

import entities.Patient;
import exceptions.NoSuchUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.ClinicRepo;
import repositories.PatientRepo;
import utils.HibernateSingleton;

import static org.junit.jupiter.api.Assertions.*;

class PatientServicesTest {


    private static PatientRepo patientRepo = new PatientRepo();
    private static PatientServices patientServices = new PatientServices(patientRepo);
    private static ClinicRepo clinicRepo=new ClinicRepo();
    private static ClinicServices clinicServices=new ClinicServices(clinicRepo);



    @BeforeAll
    static void setup() {
        var sessionFactory = HibernateSingleton.getInstance();
    }


    @Test
    void addShowInfo() {

        //arrange
        Patient patient = new Patient();
        String firstName = "firstName";
        String lastName = "lastName";
        String username="username";
        String password="password";
        patient.setFirstname(firstName);
        patient.setLastname(lastName);
        patient.setPassword(password);
        patient.setUsername(username);


        //act
        patientServices.add(patient);
        Patient loadedPatient = patientServices.showInfo(patient.getId(), Patient.class);


        //assert
        assertAll(
                ()->assertEquals(firstName,patient.getFirstname()),
                ()->assertEquals(lastName,patient.getLastname()),
                ()->assertEquals(password,loadedPatient.getPassword()),
                ()->assertEquals(username,loadedPatient.getUsername())
        );
    }


    @Test
    void remove() {
        //arrange
        Patient patient = new Patient();
        String firstName = "firstName";
        String lastName = "lastName";
        patient.setFirstname(firstName);
        patient.setLastname(lastName);

        //act
        patientServices.add(patient);
        patientServices.remove(patient, patient.getId(), Patient.class);

        boolean thrown = false;
        try {
            Patient loadedPatient = patientServices.showInfo(patient.getId(), Patient.class);
        } catch (NoSuchUser e) {
            thrown = true;
        }


        //assert
        assertTrue(thrown);
    }






    @Test
    void update(){
        //arrange
        Patient patient = new Patient();
        String firstName = "firstName";
        String lastName = "lastName";
        patient.setFirstname(firstName);
        patient.setLastname(lastName);

        //act
        patientServices.add(patient);
        String updatedFirstName="updatedFirstName";
        String updatedNameLastName="updatedLastName";
        patient.setFirstname(updatedFirstName);
        patient.setLastname(updatedNameLastName);
        patientServices.update(patient,patient.getId(),Patient.class);

        Patient loadedPatient=patientServices.showInfo(patient.getId(), Patient.class);

        //assert
        assertAll(
                ()->assertEquals(loadedPatient.getFirstname(),updatedFirstName),
                ()->assertEquals(loadedPatient.getLastname(),updatedNameLastName)
        );
    }

    @AfterEach
    void tearDown() {
        patientServices.hqlTruncate("Visit");
        patientServices.hqlTruncate("Patient");
        patientServices.hqlTruncate("Clinic");

    }
}