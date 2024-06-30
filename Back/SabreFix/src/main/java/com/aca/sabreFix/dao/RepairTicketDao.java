package com.aca.sabreFix.dao;

import com.aca.sabreFix.model.RepairTicket;
import com.aca.sabreFix.model.Type;
import com.aca.sabreFix.model.Weapon;

import java.util.List;

public interface RepairTicketDao {

    List<RepairTicket> getRepairTickets();

    List<RepairTicket> getRepairTicketsByType(Type typeValue);

    List<RepairTicket> getRepairTicketsbyLevel(Integer levelValue);

    List<RepairTicket> getRepairTicketsById(Integer idValue);

    List<RepairTicket> getRepairTicketsByName(String nameValue);

    RepairTicket createRepairTicket(RepairTicket newRepairTicket);

    RepairTicket updateRepairTicket(RepairTicket updateRepairTicket);

    RepairTicket deleteRepairTicketById(Integer idValue);

    List<RepairTicket> getReport(Integer startLevel, Integer endLevel);
}
