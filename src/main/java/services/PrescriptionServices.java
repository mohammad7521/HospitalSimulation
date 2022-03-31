package services;

import entities.Prescription;
import repositories.PrescriptionRepo;

public class PrescriptionServices extends BaseServices<Prescription, PrescriptionRepo> {
    public PrescriptionServices(PrescriptionRepo repo) {
        super(repo);
    }
}
