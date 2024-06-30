

package com.aca.sabreFix.service;

import com.aca.sabreFix.dao.WeaponDao;
import com.aca.sabreFix.dao.WeaponDaoImpl;
import com.aca.sabreFix.model.Type;
import com.aca.sabreFix.model.Weapon;
import com.aca.sabreFix.model.WeaponException;

import java.util.List;

public class WeaponService {
    private WeaponDao weaponDao = new WeaponDaoImpl();

    public List<Weapon> getWeapons() {
        return weaponDao.getWeapons();
    }
    public List<Weapon> getWeaponsByType(Type type) {
        return weaponDao.getWeaponsByType(type);
    }

    public List<Weapon> getWeaponsByLevel(Integer levelValue) throws WeaponException{
        validateLevel(levelValue);
        return weaponDao.getWeaponsByLevel(levelValue);
    }

    private void validateLevel(Integer levelValue) throws WeaponException{
        if (null == levelValue) {
            throw new WeaponException("Invalid value for weapon level, null value not allowed");
        } else if (levelValue < 0 || levelValue > 81) {
            throw new WeaponException("Invalid value for weapon level. Must be values 1 - 80");
        }
    }

    public List<Weapon> getWeaponsById(Integer idValue) {
        return weaponDao.getWeaponsById(idValue);
    }

    public List<Weapon> getWeaponsByName(String nameValue) {
        return weaponDao.getWeaponsByName(nameValue);
    }

    public Weapon createWeapon(Weapon newWeapon) throws WeaponException {
        validateLevel(newWeapon.getLevel());
        return weaponDao.createWeapon(newWeapon);
    }

    public Weapon updateWeapon(Weapon updateWeapon) {
        return weaponDao.updateWeapon(updateWeapon);
    }

    public Weapon deleteWeaponsById(Integer idValue) {
        return weaponDao.deleteWeaponById(idValue);
    }

    public List<Weapon> getReport(Integer fromLevel, Integer toLevel) {
        return weaponDao.getReport(fromLevel, toLevel);
    }
}
