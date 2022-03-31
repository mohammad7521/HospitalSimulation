package services;

import entities.Clinic;
import entities.Doctor;
import entities.Visit;
import exceptions.NoSuchUser;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.ClinicRepo;
import repositories.DoctorRepo;
import repositories.DoctorRepo;
import utils.HibernateSingleton;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServicesTest {
    
    
    private static DoctorRepo doctorRepo = new DoctorRepo();
    private static DoctorServices doctorServices = new DoctorServices(doctorRepo);
    private static ClinicRepo clinicRepo=new ClinicRepo();
    private static ClinicServices clinicServices=new ClinicServices(clinicRepo);



    @BeforeAll
    static void setup() {
        var sessionFactory = HibernateSingleton.getInstance();
    }

    
    @Test
    void addShowInfo() {

        //arrange
        Doctor doctor = new Doctor();
        String firstName = "firstName";
        String lastName = "lastName";
        String expert="expert";
        String username="username";
        String password="password";
        Clinic clinic=new Clinic();
        clinicServices.add(clinic);
        doctor.setFirstname(firstName);
        doctor.setLastname(lastName);
        doctor.setExpert(expert);
        doctor.setPassword(password);
        doctor.setUsername(username);
        doctor.setDoctorClinic(clinic);


        //act
        doctorServices.add(doctor);
        Doctor loadedDoctor = doctorServices.showInfo(doctor.getId(), Doctor.class);


        //assert
        assertAll(
                ()->assertEquals(clinic.getId(),doctor.getDoctorClinic().getId()),
                ()->assertEquals(firstName,doctor.getFirstname()),
                ()->assertEquals(lastName,doctor.getLastname()),
                ()->assertEquals(password,loadedDoctor.getPassword()),
                ()->assertEquals(username,loadedDoctor.getUsername()),
                ()->assertEquals(expert,loadedDoctor.getExpert())
        );
    }


    @Test
    void remove() {
        //arrange
        Doctor doctor = new Doctor();
        String firstName = "firstName";
        String lastName = "lastName";
        doctor.setFirstname(firstName);
        doctor.setLastname(lastName);

        //act
        doctorServices.add(doctor);
        doctorServices.remove(doctor, doctor.getId(), Doctor.class);

        boolean thrown = false;
        try {
            Doctor loadedDoctor = doctorServices.showInfo(doctor.getId(), Doctor.class);
        } catch (NoSuchUser e) {
            thrown = true;
        }


        //assert
        assertTrue(thrown);
    }






    @Test
    void update(){
        //arrange
        Doctor doctor = new Doctor();
        String firstName = "firstName";
        String lastName = "lastName";
        doctor.setFirstname(firstName);
        doctor.setLastname(lastName);

        //act
        doctorServices.add(doctor);
        String updatedFirstName="updatedFirstName";
        String updatedNameLastName="updatedLastName";
        doctor.setFirstname(updatedFirstName);
        doctor.setLastname(updatedNameLastName);
        doctorServices.update(doctor,doctor.getId(),Doctor.class);

        Doctor loadedDoctor=doctorServices.showInfo(doctor.getId(), Doctor.class);

        //assert
        assertAll(
                ()->assertEquals(loadedDoctor.getFirstname(),updatedFirstName),
                ()->assertEquals(loadedDoctor.getLastname(),updatedNameLastName)
        );
    }

    @AfterEach
    void tearDown() {
        doctorServices.hqlTruncate("Visit");
        doctorServices.hqlTruncate("Doctor");
        doctorServices.hqlTruncate("Clinic");

    }
}