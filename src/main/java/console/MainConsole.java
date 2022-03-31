package console;

import entities.Clinic;
import entities.Doctor;
import repositories.ClinicRepo;
import repositories.DoctorRepo;
import repositories.StaffRepo;
import repositories.VisitRepo;
import services.ClinicServices;
import services.DoctorServices;
import services.StaffServices;
import services.VisitServices;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainConsole {

    private static StaffRepo staffRepo = new StaffRepo();
    private static ClinicRepo clinicRepo=new ClinicRepo();
    private static StaffServices staffServices = new StaffServices(staffRepo);
    private static ClinicServices clinicServices=new ClinicServices(clinicRepo);
    private static DoctorRepo doctorRepo=new DoctorRepo();
    private static DoctorServices doctorServices=new DoctorServices(doctorRepo);
    private static VisitRepo visitRepo=new VisitRepo();
    private static VisitServices visitServices=new VisitServices(visitRepo);

    public static void mainMenu()  {

        while (true) {
            System.out.println("1-staff: ");
            System.out.println("2-Patient: ");
            System.out.println("3-Doctor: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userSelect = scanner.nextInt();
                switch (userSelect) {
                    case 1:
                        StaffConsole.staffLogIn();
                        break;
                    case 2:
                        PatientConsole.patientLogIn();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            }
        }
    }

    public static void showClinics(){
        List<Clinic> clinics=clinicServices.showAll(Clinic.class);

        for (Clinic c:clinics){
            System.out.println(c.toString());
        }
    }

    public static void showDoctors(){
        List<Doctor> doctorList=doctorServices.showAll(Doctor.class);

        for (Doctor d:doctorList){
            System.out.println(d);
        }
    }



}
