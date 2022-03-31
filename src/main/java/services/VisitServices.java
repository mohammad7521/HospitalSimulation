package services;

import entities.Visit;
import repositories.VisitRepo;

import java.util.List;

public class VisitServices extends BaseServices<Visit, VisitRepo> {
    public VisitServices(VisitRepo repo) {
        super(repo);
    }

    private static VisitRepo visitRepo=new VisitRepo();


    //returns patients visits from the repository
    public List<Visit> patientVisits(int patientId){
        return visitRepo.patientVisits(patientId);
    }


    //return available visit hours of a doctor in order for the patient to select
    public List<Visit> doctorAvailableVisits (int doctorId){
        return visitRepo.doctorAvailableVisit(doctorId);
    }


    //show all visits of a doctor
    public List<Visit> allDoctorVisit(int doctorId){
        return visitRepo.allVisitDoctor(doctorId);
    }




    //get treated patients of a doctor from database which a prescription has not been set for them
    public List<Visit> NotPrescriptedVisit(int doctorId){
        return visitRepo.notPrescriptedVisit(doctorId);
    }
}
