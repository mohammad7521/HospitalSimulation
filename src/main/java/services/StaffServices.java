package services;

import entities.Doctor;
import entities.Staff;
import exceptions.DuplicateUser;
import exceptions.NoSuchId;
import repositories.StaffRepo;
import java.util.List;

public class StaffServices extends BaseServices<Staff, StaffRepo> {

    private StaffRepo staffRepo=new StaffRepo();
    public StaffServices(StaffRepo repo) {
        super(repo);
    }


    //checks if the admin account exists
    public void adminSetter() {
        Staff admin = new Staff();
        admin.setUsername("admin");
        admin.setPassword("admin");

        if (checkUsername("admin")) ;

        else repo.add(admin);
    }


    //check if username already exists
    public boolean checkUsername(String username) {
        boolean flag = false;
        List<Staff> patientList = repo.showAll(Staff.class);

        for (Staff a : patientList) {
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
        Staff staff = showInfo(username);

        if (staff.getPassword().equals(password)) {
            logInCheck = true;
        }
        return logInCheck;
    }



    public Staff showInfo(String username){

        if (!checkUsername(username)){
            throw new NoSuchId();
        }
        return staffRepo.showInfo(username);
    }


    public Staff staffAd(Staff staff){
        if (checkUsername(staff.getUsername())){
            throw new DuplicateUser();
        }
        return staffRepo.add(staff);
    }
}
