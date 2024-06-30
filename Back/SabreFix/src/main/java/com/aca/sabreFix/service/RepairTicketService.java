package com.aca.sabreFix.service;

import com.aca.sabreFix.dao.RepairTicketDao;
import com.aca.sabreFix.dao.RepairTicketDaoImpl;
import com.aca.sabreFix.model.RepairTicket;
import com.aca.sabreFix.model.Type;

import java.util.List;

public class RepairTicketService {
    RepairTicketDao repairTicketDao = new RepairTicketDaoImpl();
    public List<RepairTicket> getRepairTickets() {
        return repairTicketDao.getRepairTickets();
    }

    public List<RepairTicket> getRepairTicketsByType(Type typeValue) {
        return repairTicketDao.getRepairTicketsByType(typeValue);
    }

    public List<RepairTicket> getRepairTicketsByLevel(Integer levelValue) {
        return repairTicketDao.getRepairTicketsbyLevel(levelValue);
    }

    public List<RepairTicket> getRepairTicketById(Integer idValue) {
        return repairTicketDao.getRepairTicketsById(idValue);
    }
    public List<RepairTicket> getRepairTicketsByName(String nameValue) {
        return repairTicketDao.getRepairTicketsByName(nameValue);
    }

    public RepairTicket createRepairTicket(RepairTicket newRepairTicket) {
        return repairTicketDao.createRepairTicket(newRepairTicket);
    }

    public RepairTicket updateRepairTicket(RepairTicket updateRepairTicket) {
        return repairTicketDao.updateRepairTicket(updateRepairTicket);
    }

    public RepairTicket deleteRepairTicketsById(Integer idValue) {
        return repairTicketDao.deleteRepairTicketById(idValue);
    }

    public List<RepairTicket> getReport(Integer startLevel, Integer endLevel) {
        return repairTicketDao.getReport(startLevel, endLevel);
    }

}
