package console;

import entities.*;
import exceptions.*;
import repositories.*;
import services.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PatientConsole {

    private static PatientRepo patientRepo = new PatientRepo();
    private static PatientServices patientServices = new PatientServices(patientRepo);
    private static VisitRepo visitRepo = new VisitRepo();
    private static VisitServices visitServices = new VisitServices(visitRepo);
    private static ClinicRepo clinicRepo = new ClinicRepo();
    private static ClinicServices clinicServices = new ClinicServices(clinicRepo);
    private static DoctorRepo doctorRepo = new DoctorRepo();
    private static DoctorServices doctorServices = new DoctorServices(doctorRepo);
    private static Scanner scanner = new Scanner(System.in);

    private static void selectVisitTime(List<Visit> visitList,Patient patient) {


        if (visitList == null) {
            System.out.println("there are no times available for this doctor at the moment!please choose another doctor!");
        }
        for (Visit v:visitList){
            System.out.println(v);
        }
        System.out.println("select the visit time Id:");
        int visitId = scanner.nextInt();
        Visit assignedVisit=visitServices.showInfo(visitId,Visit.class);
        assignedVisit.setVisitPatient(patient);
        visitServices.update(assignedVisit,visitId,Visit.class);
        System.out.println("success");
    }


    public static void patientLogIn() {

        while (true) {
            System.out.println("1-Register: ");
            System.out.println("2-Sign in: ");
            System.out.println("0-exit: ");

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

                        Patient patient = new Patient();
                        patient.setFirstname(firstName);
                        patient.setLastname(lastName);
                        patient.setUsername(username);
                        patient.setPassword(password);
                        patientServices.patientAdd(patient);
                        System.out.println("sign up successfull");
                        break;
                    case 2:
                        System.out.println("please enter your username: ");
                        username = scanner.next();
                        System.out.println("please enter your password: ");
                        password = scanner.next();
                        if (patientServices.logIn(username, password)) {
                            System.out.println("log in successful! ");
                            patientManagementMenu(username);
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
            } catch (NoSuchUser e){
                System.out.println("please enter a correct value!");
            }
        }
    }


    public static void patientManagementMenu(String username) {

        Patient patient = patientServices.showInfo(username);
        boolean flag = true;
        while (flag) {
            System.out.println("1-show info: ");
            System.out.println("2-show medical history: ");
            System.out.println("3-browse clinics and doctors(book a visit): ");
            System.out.println("0-exit: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        System.out.println(patient);
                        break;
                    case 2:
                        List<Visit> patientVisits = visitServices.patientVisits(patient.getId());

                        for (Visit v : patientVisits) {
                            System.out.println(v);
                        }
                        break;
                    case 3:
                        System.out.println("start by selecting the clinic (id):");
                        MainConsole.showClinics();
                        System.out.println();
                        int clinicId = scanner.nextInt();
                        System.out.println("select you doctor from the list below: ");
                        List<Doctor> clinicDoctorList = clinicServices.clinicDoctorList(clinicId);
                        for (Doctor d : clinicDoctorList) {
                            System.out.println(d.patientToString());
                        }

                        System.out.println("now select your doctor: ");
                        int doctorId = scanner.nextInt();
                        List<Visit> doctorAvailableVisit = visitServices.doctorAvailableVisits(doctorId);
                        selectVisitTime(doctorAvailableVisit,patient);
                        break;

                    case 0:
                        patientLogIn();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number ! ");
            } catch (NoSuchUser e){
                System.out.println("please enter a correct value!");
            }catch (NoSuchId e){
                System.out.println("pleas enter a correct Id! ");
            }
        }
    }
}
