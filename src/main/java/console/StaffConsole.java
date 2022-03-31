package console;

import entities.*;
import exceptions.DuplicateUser;
import exceptions.NoSuchUser;
import exceptions.NoSuchId;
import repositories.ClinicRepo;
import repositories.DoctorRepo;
import repositories.StaffRepo;
import repositories.VisitRepo;
import services.ClinicServices;
import services.DoctorServices;
import services.StaffServices;
import services.VisitServices;

import javax.print.Doc;
import java.time.DayOfWeek;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class StaffConsole {

    private static StaffRepo staffRepo = new StaffRepo();
    private static ClinicRepo clinicRepo=new ClinicRepo();
    private static StaffServices staffServices = new StaffServices(staffRepo);
    private static ClinicServices clinicServices=new ClinicServices(clinicRepo);
    private static DoctorRepo doctorRepo=new DoctorRepo();
    private static DoctorServices doctorServices=new DoctorServices(doctorRepo);
    private static VisitRepo visitRepo=new VisitRepo();
    private static VisitServices visitServices=new VisitServices(visitRepo);
    private static Scanner scanner = new Scanner(System.in);


    public static void staffLogIn() {
        staffServices.adminSetter();

        while (true) {
            System.out.println("1-Register: ");
            System.out.println("2-Sign in: ");
            System.out.println("0-exit: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        System.out.println("enter a username: ");
                        String username = scanner.next();
                        System.out.println("enter a password: ");
                        String password = scanner.next();
                        System.out.println("enter first name: ");
                        String firstName = scanner.next();
                        System.out.println("enter last name: ");
                        String lastName = scanner.next();

                        Staff staff = new Staff();
                        staff.setFirstname(firstName);
                        staff.setLastname(lastName);
                        staff.setUsername(username);
                        staff.setPassword(password);
                        staffServices.staffAd(staff);
                        System.out.println("sign up successfull");
                        break;
                    case 2:
                        System.out.println("please enter your username: ");
                        username = scanner.next();
                        System.out.println("please enter your password: ");
                        password = scanner.next();
                        if (staffServices.logIn(username, password)) {
                            System.out.println("log in successful! ");
                            staffManagementMenu(username);
                            break;
                        } else System.out.println("password is wrong! ");
                        break;
                    case 0:
                        MainConsole.mainMenu();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number ! ");
            } catch (NoSuchId e) {
                System.out.println("id does not exist");
            } catch (DuplicateUser e) {
                System.out.println("username already exists!");
            }
        }
    }

    public static void staffManagementMenu(String username) {

        Staff staff = staffServices.showInfo(username);
        boolean flag = true;
        while (flag) {
            System.out.println("1-staff management: ");
            System.out.println("2-Clinic management: ");
            System.out.println("3-Doctor management: ");
            System.out.println("4-Medicine management: ");
            System.out.println("5-visit time management: ");
            System.out.println("0-exit: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        staffManagement();
                        break;
                    case 2:
                        clinicManagementMenu();
                        break;
                    case 3:
                        doctorManagementMenu(username);
                        break;
//                    case 4:
//                        lessonManagementMenu();
//                        break;

                    case 5:
                        visitManagement();
                        break;

                    case 0:
                        staffLogIn();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number ! ");
            }
        }
    }

    //staff management
    public static void staffManagement() {

        System.out.println("1-add new employee");
        System.out.println("2-remove employee");
        System.out.println("3-modify employee");
        System.out.println("0-exit");

        Scanner scanner = new Scanner(System.in);

        try {
            switch (scanner.nextInt()) {

                case 1:
                    System.out.println("enter name:");
                    String name = scanner.next();
                    System.out.println("enter last name:");
                    String lastName = scanner.next();
                    System.out.println("enter username:");
                    String username = scanner.next();
                    staffServices.checkUsername(username);
                    System.out.println("enter password:");
                    String password = scanner.next();
                    Staff staff = new Staff();
                    staff.setFirstname(name);
                    staff.setLastname(lastName);
                    staff.setUsername(username);
                    staff.setPassword(password);
                    staffServices.staffAd(staff);
                    System.out.println("user added successfully! ");
                    System.out.println();

                    break;

                case 2:
                    System.out.println("enter the id of the staff that you want to remove:");
                    int id = scanner.nextInt();
                    Staff removed=staffServices.showInfo(id,Staff.class);
                    staffServices.remove(removed,removed.getId(),Staff.class);
                    System.out.println("user removed successfully");
                    break;

                case 3:
                    System.out.println("enter the id of the staff that you want to change:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter new name:");
                    name = scanner.next();
                    System.out.println("enter new last name:");
                    lastName = scanner.next();
                    System.out.println("enter new username:");
                    username = scanner.next();
                    System.out.println("enter new password:");
                    password = scanner.next();
                    staffServices.checkUsername(username);
                    Staff modifiedStaff = staffServices.showInfo(id,Staff.class);
                    modifiedStaff.setFirstname(name);
                    modifiedStaff.setLastname(lastName);
                    modifiedStaff.setPassword(password);
                    staffServices.update(modifiedStaff,modifiedStaff.getId(),Staff.class);
                    System.out.println("update successful!");
                    break;
                case 0:
                    staffLogIn();
                    break;
            }
        } catch (DuplicateUser e) {
            System.out.println("username already exists!");
        } catch (NoSuchId e) {
            System.out.println("id does not exist!");
        } catch (NoSuchUser e){
            System.out.println("the user does not exist!");
        }
    }


    //clinic management
    public static void clinicManagementMenu() {

        System.out.println("1-add new clinic:");
        System.out.println("2-remove clinic:");
        System.out.println("3-modify clinic: ");
        System.out.println("4-show all clinics:");
        System.out.println("0-exit");

        Scanner scanner = new Scanner(System.in);

        try {
            switch (scanner.nextInt()) {

                case 1:
                    System.out.println("enter name:");
                    String name = scanner.next();
                    System.out.println("enter address:");
                    String address = scanner.next();
                    Clinic clinic = new Clinic();
                    clinic.setName(name);
                    clinic.setAddress(address);
                    clinicServices.add(clinic);
                    System.out.println("clinic added successfully! ");
                    System.out.println();

                    break;

                case 2:
                    System.out.println("enter the id of the clinic that you want to remove:");
                    int id = scanner.nextInt();
                    Clinic removed=clinicServices.showInfo(id,Clinic.class);
                    clinicServices.remove(removed,removed.getId(),Clinic.class);
                    System.out.println("clinic removed successfully");
                    break;

                case 3:
                    System.out.println("enter the id of the clinic that you want to change:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter new name:");
                    name = scanner.next();
                    System.out.println("enter new address:");
                    address = scanner.next();
                    Clinic modifiedClinic = clinicServices.showInfo(id,Clinic.class);
                    modifiedClinic.setName(name);
                    modifiedClinic.setAddress(address);
                    clinicServices.update(modifiedClinic,modifiedClinic.getId(),Clinic.class);
                    System.out.println("update successful!");
                    break;

                case 4:
                    List<Clinic> allClinics=clinicServices.showAll(Clinic.class);
                    for (Clinic c:allClinics){
                        System.out.println(c);
                    }
                case 0:
                    staffLogIn();
                    break;
            }
        } catch (NoSuchId e) {
            System.out.println("id does not exist!");
        } catch (NoSuchUser e){
            System.out.println("the clinic does not exist!");
        }
    }



    //doctor management
    public static void doctorManagementMenu(String username) {

        System.out.println("1-add new doctor");
        System.out.println("2-remove doctor");
        System.out.println("3-modify doctor");
        System.out.println("4-assign to clinic:");
        System.out.println("5-set visit hours:");
        System.out.println("6-show all doctors:");
        System.out.println("0-exit");

        Scanner scanner = new Scanner(System.in);

        try {
            switch (scanner.nextInt()) {

                case 1:
                    System.out.println("enter name:");
                    String name = scanner.next();
                    System.out.println("enter last name:");
                    String lastName = scanner.next();
                    System.out.println("enter username:");
                    String doctorUsername = scanner.next();
                    doctorServices.checkUsername(username);
                    System.out.println("enter password:");
                    String password = scanner.next();
                    System.out.println("enter expert:");
                    String expert=scanner.next();
                    Doctor doctor = new Doctor();
                    doctor.setFirstname(name);
                    doctor.setLastname(lastName);
                    doctor.setUsername(doctorUsername);
                    doctor.setPassword(password);
                    doctor.setExpert(expert);
                    doctorServices.doctorAdd(doctor);
                    System.out.println("user added successfully! ");
                    System.out.println();

                    break;

                case 2:
                    System.out.println("enter the id of the doctor that you want to remove:");
                    int id = scanner.nextInt();
                    Doctor removed = doctorServices.showInfo(id, Doctor.class);
                    doctorServices.remove(removed, removed.getId(), Doctor.class);
                    System.out.println("user removed successfully");
                    break;

                case 3:
                    System.out.println("enter the id of the doctor that you want to change:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter new name:");
                    name = scanner.next();
                    System.out.println("enter new last name:");
                    lastName = scanner.next();
                    System.out.println("enter new username:");
                    username = scanner.next();
                    System.out.println("enter new password:");
                    password = scanner.next();
                    System.out.println("enter new expert:");
                    expert=scanner.next();

                    doctorServices.checkUsername(username);
                    Doctor modifiedDoctor = doctorServices.showInfo(id, Doctor.class);
                    modifiedDoctor.setFirstname(name);
                    modifiedDoctor.setLastname(lastName);
                    modifiedDoctor.setPassword(password);
                    modifiedDoctor.setExpert(expert);
                    doctorServices.update(modifiedDoctor, modifiedDoctor.getId(), Doctor.class);
                    System.out.println("update successful!");
                    break;

                case 4:
                    MainConsole.showDoctors();
                    System.out.println("enter the doctor Id:");
                    int doctorId=scanner.nextInt();
                    System.out.println("enter the clinic Id:");

                    MainConsole.showClinics();
                    System.out.println("enter the clinic Id:");
                    int clinicId=scanner.nextInt();

                    Doctor assignedDoc=doctorServices.showInfo(doctorId,Doctor.class);
                    Clinic clinic=clinicServices.showInfo(clinicId,Clinic.class);
                    assignedDoc.setDoctorClinic(clinic);
                    doctorServices.update(assignedDoc,assignedDoc.getId(),Doctor.class);
                    break;

                case 5:
                    MainConsole.showDoctors();

                    System.out.println("enter doctor id:");

                    doctorId=scanner.nextInt();
                    Doctor visitDoctor=doctorServices.showInfo(doctorId,Doctor.class);
                    System.out.println("enter day of the week(starting from 1 as monday):");
                    DayOfWeek dayOfWeek= DayOfWeek.of(scanner.nextInt());

                    System.out.println("enter visiting time as number (MORNING,AFTERNOON,EVENING,NIGHT)");
                    VisitTime vt=VisitTime.values()[scanner.nextInt()];

                    Visit visit=new Visit();
                    visit.setDoctorVisit(visitDoctor);
                    visit.setVisitTime(vt);
                    visit.setDayOfWeek(dayOfWeek);
                    visitServices.add(visit);
                    System.out.println(vt.name());
                    System.out.println("success!");
                    break;
                case 6:
                    List<Doctor> allDoctors=doctorServices.showAll(Doctor.class);
                    for (Doctor d:allDoctors){
                        System.out.println(d);
                    }
                    break;
                case 0:
                    staffManagementMenu(username);
                    break;
            }
        } catch (DuplicateUser e) {
            System.out.println("username already exists!");
        } catch (NoSuchId e) {
            System.out.println("id does not exist!");
        } catch (NoSuchUser e) {
            System.out.println("the user does not exist!");
        }
    }




    public static void visitManagement(){


        System.out.println("1-show all visit times:");
        System.out.println("2-show visit times of a specific doctor:");
        System.out.println("0-exit");

        try {
            switch (scanner.nextInt()) {

                case 1:
                    List<Visit> allVisitTimes=visitServices.showAll(Visit.class);
                    for (Visit v:allVisitTimes){
                        System.out.println(v);
                    }
                    break;

                case 2:
                    MainConsole.showDoctors();
                    System.out.println("enter doctor Id:");
                    int doctorId=scanner.nextInt();
                    List<Visit> allDoctorVisit=visitServices.allDoctorVisit(doctorId);
                    for (Visit v:allDoctorVisit){
                        System.out.println(v);
                    }
                    break;
                case 0:
                    break;
            }
        } catch (DuplicateUser e) {
            System.out.println("username already exists!");
        } catch (NoSuchId e) {
            System.out.println("id does not exist!");
        } catch (NoSuchUser e) {
            System.out.println("the user does not exist!");
        }
    }
}
