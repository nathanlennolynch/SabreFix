package com.aca.sabreFix.controller;

import com.aca.sabreFix.model.RepairTicket;
import com.aca.sabreFix.model.Type;
import com.aca.sabreFix.model.Weapon;
import com.aca.sabreFix.model.WeaponException;
import com.aca.sabreFix.service.WeaponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/weapons", produces = "application/json")
@CrossOrigin("*")
public class WeaponController {
    private WeaponService weaponService = new WeaponService();

    @RequestMapping(method = RequestMethod.GET)
    public List<Weapon> getWeapons() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return weaponService.getWeapons();
    }
    @RequestMapping(value = "/type/{typeValue}",
            method = RequestMethod.GET)
    public List<Weapon> getWeaponsByType(@PathVariable Type typeValue) {
        return weaponService.getWeaponsByType(typeValue);
    }
    @RequestMapping(value = "/level/{levelValue}",
            method = RequestMethod.GET)
    public List<Weapon> getWeaponsByLevel(@PathVariable Integer levelValue) throws WeaponException{
        return weaponService.getWeaponsByLevel(levelValue);
    }
    @RequestMapping(value = "/{idValue}",
            method = RequestMethod.GET)
    public List<Weapon> getWeaponsById(@PathVariable Integer idValue) {
        return weaponService.getWeaponsById(idValue);
    }
    @RequestMapping(value = "/name/{nameValue}",
            method = RequestMethod.GET)
    public List<Weapon> getWeaponsByName(@PathVariable String nameValue) {
        return weaponService.getWeaponsByName(nameValue);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Weapon createWeapon(@RequestBody Weapon newWeapon) throws WeaponException {
        return weaponService.createWeapon(newWeapon);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Weapon updateWeapon(@RequestBody Weapon updateWeapon) {
        return weaponService.updateWeapon(updateWeapon);
    }
    @RequestMapping(value = "/{idValue}",
            method = RequestMethod.DELETE)
    public Weapon deleteWeaponsById(@PathVariable Integer idValue) {
        return weaponService.deleteWeaponsById(idValue);
    }

    @RequestMapping(value = "/report",
            method = RequestMethod.GET)
    public List<Weapon> getReport(@RequestParam Integer startLevel,
                                  @RequestParam Integer endLevel) {
        return weaponService.getReport(startLevel, endLevel);
    }
}
