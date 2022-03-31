package console;

import entities.*;
import repositories.*;
import services.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainConsole {

    protected static StaffRepo staffRepo = new StaffRepo();
    protected static ClinicRepo clinicRepo=new ClinicRepo();
    protected static StaffServices staffServices = new StaffServices(staffRepo);
    protected static ClinicServices clinicServices=new ClinicServices(clinicRepo);
    protected static DoctorRepo doctorRepo=new DoctorRepo();
    protected static DoctorServices doctorServices=new DoctorServices(doctorRepo);
    protected static VisitRepo visitRepo=new VisitRepo();
    protected static VisitServices visitServices=new VisitServices(visitRepo);
    protected static Scanner scanner=new Scanner(System.in);

    public static void mainMenu()  {

        while (true) {
            System.out.println("1-staff: ");
            System.out.println("2-Patient: ");
            System.out.println("3-Doctor: ");

            try {
                int userSelect = scanner.nextInt();
                switch (userSelect) {
                    case 1:
                        StaffConsole.staffLogIn();
                        break;
                    case 2:
                        PatientConsole.patientLogIn();
                        break;
                    case 3:
                        DoctorConsole.doctorLogIn();
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
