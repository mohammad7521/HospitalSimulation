package services;

import entities.Clinic;
import entities.Doctor;
import repositories.ClinicRepo;

import java.util.List;

public class ClinicServices extends BaseServices<Clinic, ClinicRepo> {
    public ClinicServices(ClinicRepo repo) {
        super(repo);
    }

    private static ClinicRepo clinicRepo=new ClinicRepo();

    //get doctor list of a certain Clinic
    public List<Doctor> clinicDoctorList(int clinicId){
        return clinicRepo.getDoctorList(clinicId);
    }
}
