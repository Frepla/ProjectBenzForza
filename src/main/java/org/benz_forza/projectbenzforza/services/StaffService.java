package org.benz_forza.projectbenzforza.services;
import org.benz_forza.projectbenzforza.DAO.StaffDAO;
import org.benz_forza.projectbenzforza.entities.Staff;

import java.util.List;

public class StaffService {
    private StaffDAO staffDAO = new StaffDAO();

    public void addStaff(Staff staff) {
        staffDAO.create(staff);
    }
    public Staff getStaff(long id) {
        return staffDAO.findById(id);
    }
    public List<Staff> getAllStaff() {
        return staffDAO.findAll();
    }
    public void updateStaff(Staff staff) {
        staffDAO.update(staff);
    }
    public void deleteStaff(long id) {
        staffDAO.delete(id);
    }
}
