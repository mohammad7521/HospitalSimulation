package services;

import entities.Patient;
import entities.Staff;
import exceptions.DuplicateUser;
import exceptions.NoSuchId;
import repositories.PatientRepo;

import java.util.List;

public class PatientServices extends BaseServices<Patient, PatientRepo> {
    public PatientServices(PatientRepo repo) {
        super(repo);
    }


    private static PatientRepo patientRepo=new PatientRepo();
    //check if username already exists
    public boolean checkUsername(String username) {
        boolean flag = false;
        List<Patient> patientList = repo.showAll(Patient.class);

        for (Patient a : patientList) {
            if (username.equals(a.getUsername())) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    //account log in
    public boolean logIn(String username, String password) {

        boolean logInCheck = false;
        Patient patient = showInfo(username);

        if (patient.getPassword().equals(password)) {
            logInCheck = true;
        }
        return logInCheck;
    }



    public Patient showInfo(String username){

        if (!checkUsername(username)){
            throw new NoSuchId();
        }
        return patientRepo.showInfo(username);
    }


    public Patient patientAdd(Patient patient){
        if (checkUsername(patient.getUsername())){
            throw new DuplicateUser();
        }
        return patientRepo.add(patient);
    }
}
