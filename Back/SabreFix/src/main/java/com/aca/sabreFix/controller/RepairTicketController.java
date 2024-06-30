package com.aca.sabreFix.controller;

import com.aca.sabreFix.model.RepairTicket;
import com.aca.sabreFix.model.Type;
import com.aca.sabreFix.model.Weapon;
import com.aca.sabreFix.model.WeaponException;
import com.aca.sabreFix.service.RepairTicketService;
import com.aca.sabreFix.service.WeaponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/repair_tickets", produces = "application/json")
@CrossOrigin("*")
public class RepairTicketController {

    private RepairTicketService repairTicketService = new RepairTicketService();

    @RequestMapping(method = RequestMethod.GET)
    public List<RepairTicket> getRepairTickets() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return repairTicketService.getRepairTickets();
    }
    @RequestMapping(value = "/type/{typeValue}",
            method = RequestMethod.GET)
    public List<RepairTicket> getRepairTicketsByType(@PathVariable Type typeValue) {
        return repairTicketService.getRepairTicketsByType(typeValue);
    }
    @RequestMapping(value = "/level/{levelValue}",
            method = RequestMethod.GET)
    public List<RepairTicket> getRepairTicketByLevel(@PathVariable Integer levelValue) throws WeaponException{
        return repairTicketService.getRepairTicketsByLevel(levelValue);
    }
    @RequestMapping(value = "/{idValue}",
            method = RequestMethod.GET)
    public List<RepairTicket> getRepairTicketById(@PathVariable Integer idValue) {
        return repairTicketService.getRepairTicketById(idValue);
    }
    @RequestMapping(value = "/name/{nameValue}",
            method = RequestMethod.GET)
    public List<RepairTicket> getRepairTicketByName(@PathVariable String nameValue) {
        return repairTicketService.getRepairTicketsByName(nameValue);
    }
    @RequestMapping(method = RequestMethod.POST)
    public RepairTicket createRepairTicket(@RequestBody RepairTicket newRepairTicket) throws WeaponException {
        return repairTicketService.createRepairTicket(newRepairTicket);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public RepairTicket updateRepairTicket(@RequestBody RepairTicket updateRepairTicket) {
        return repairTicketService.updateRepairTicket(updateRepairTicket);
    }
    @RequestMapping(value = "/{idValue}",
            method = RequestMethod.DELETE)
    public RepairTicket deleteWeaponsById(@PathVariable Integer idValue) {
        return repairTicketService.deleteRepairTicketsById(idValue);
    }

    @RequestMapping(value = "/report",
            method = RequestMethod.GET)
    public List<RepairTicket> getReport(@RequestParam Integer startLevel,
                                  @RequestParam Integer endLevel) {
        return repairTicketService.getReport(startLevel, endLevel);
    }

}