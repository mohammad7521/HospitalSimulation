package services;

import entities.Medicine;
import repositories.MedicineRepo;

public class MedicineServices extends BaseServices<Medicine, MedicineRepo> {
    public MedicineServices(MedicineRepo repo) {
        super(repo);
    }
}
