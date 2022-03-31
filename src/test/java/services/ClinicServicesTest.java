package services;

import entities.Clinic;
import exceptions.NoSuchUser;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ClinicRepo;
import utils.HibernateSingleton;

import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ClinicServicesTest {

    private static ClinicRepo clinicRepo = new ClinicRepo();
    private static ClinicServices clinicServices = new ClinicServices(clinicRepo);

    @BeforeAll
    static void setup() {
        var sessionFactory = HibernateSingleton.getInstance();
    }


    @Test
    void addShowInfo() {
        //arrange
        Clinic clinic = new Clinic();
        String name = "newName";
        String address = "newAddress";
        clinic.setName(name);
        clinic.setAddress(address);

        //act
        clinicServices.add(clinic);
        Clinic loadedClinic = clinicServices.showInfo(clinic.getId(), Clinic.class);


        //assert
        assertEquals(name, clinic.getName());
        assertEquals(address, clinic.getAddress());
    }


    @Test
    void remove() {
        //arrange
        Clinic clinic = new Clinic();
        String name = "newName";
        String address = "newAddress";
        clinic.setName(name);
        clinic.setAddress(address);

        //act
        clinicServices.add(clinic);
        clinicServices.remove(clinic, clinic.getId(), Clinic.class);

        boolean thrown = false;
        try {
            Clinic loadedClinic = clinicServices.showInfo(clinic.getId(), Clinic.class);
        } catch (NoSuchUser e) {
            thrown = true;
        }


        //assert
        assertTrue(thrown);
    }






    @Test
    void update(){
        //arrange
        Clinic clinic = new Clinic();
        String name = "newName";
        String address = "newAddress";
        clinic.setName(name);
        clinic.setAddress(address);

        //act
        clinicServices.add(clinic);
        String updatedName="updatedName";
        String updatedAddress="updatedAddress";
        clinic.setName("updatedName");
        clinic.setAddress("updatedAddress");
        clinicServices.update(clinic,clinic.getId(),Clinic.class);

        Clinic loadedClinic=clinicServices.showInfo(clinic.getId(), Clinic.class);

        //assert
        assertAll(
                ()->assertEquals(loadedClinic.getName(),updatedName),
                ()->assertEquals(loadedClinic.getAddress(),updatedAddress)
        );
    }

    @AfterEach
    void tearDown() {
        clinicServices.hqlTruncate("Visit");
        clinicServices.hqlTruncate("Doctor");
        clinicServices.hqlTruncate("Clinic");

    }
}