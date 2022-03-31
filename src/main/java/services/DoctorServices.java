package services;

import entities.Doctor;
import entities.Staff;
import exceptions.DuplicateUser;
import exceptions.NoSuchId;
import repositories.DoctorRepo;

import javax.print.Doc;
import java.util.List;


public class DoctorServices extends BaseServices<Doctor, DoctorRepo> {
    public DoctorServices(DoctorRepo repo) {
        super(repo);
    }


    private static DoctorRepo doctorRepo=new DoctorRepo();

    //check if username already exists
    public boolean checkUsername(String username) {
        boolean flag = false;
        List<Doctor> doctorList = repo.showAll(Doctor.class);

        for (Doctor a : doctorList) {
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
        Doctor doctor = showInfo(username);

        if (doctor.getPassword().equals(password)) {
            logInCheck = true;
        }
        return logInCheck;
    }



    public Doctor showInfo(String username){

        if (!checkUsername(username)){
            throw new NoSuchId();
        }
        return doctorRepo.showInfo(username);
    }


    public Doctor doctorAdd(Doctor doctor){
        if (checkUsername(doctor.getUsername())){
            throw new DuplicateUser();
        }
        return doctorRepo.add(doctor);
    }
}
