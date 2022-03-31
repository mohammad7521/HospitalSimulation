package console;
import entities.*;
import exceptions.*;
import repositories.*;
import services.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DoctorConsole {

    private static DoctorRepo doctorRepo=new DoctorRepo();
    private static DoctorServices doctorServices=new DoctorServices(doctorRepo);
    private static VisitRepo visitRepo=new VisitRepo();
    private static VisitServices visitServices=new VisitServices(visitRepo);
    private static PrescriptionRepo prescriptionRepo=new PrescriptionRepo();
    private static PrescriptionServices prescriptionServices=new PrescriptionServices(prescriptionRepo);
    private static Scanner scanner=new Scanner(System.in);



    public static void doctorLogIn() {
        while (true) {
            System.out.println("1-log in: ");
            System.out.println("0-exit: ");

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        System.out.println("please enter your username: ");
                        String username = scanner.next();
                        System.out.println("please enter your password: ");
                        String password = scanner.next();
                        if (doctorServices.logIn(username, password)) {
                            System.out.println("log in successful! ");
                            doctorManagementMenu(username);
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




    public static void doctorManagementMenu(String username) {

        Doctor doctor = doctorServices.showInfo(username);
        boolean flag = true;
        while (flag) {
            System.out.println("1-write patient prescription: ");
            System.out.println("0-exit: ");

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        writePrescription(doctor.getId());
                    case 0:
                        doctorLogIn();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number ! ");
            } catch (NoSuchUser e){
                System.out.println("please enter a valid prescription Id! ");
            }
        }
    }


    public static void writePrescription(int doctorId){
        List<Visit> notPrescripted=visitServices.NotPrescriptedVisit(doctorId);
        for (Visit v:notPrescripted){
            System.out.println(v);
        }
        System.out.println("select the visit that you want to write prescription for:");
        int visitId=scanner.nextInt();
        Visit visit=visitServices.showInfo(visitId,Visit.class);
        System.out.println("write your prescription:");
        String prescriptionText=scanner.next();
        Prescription prescription=new Prescription();
        prescription.setDescription(prescriptionText);
        prescriptionServices.add(prescription);
        visit.setPrescriptionVisit(prescription);
        visitServices.update(visit,visitId,Visit.class);
    }
}
