package com.aca.sabreFix.dao;

import com.aca.sabreFix.model.Type;
import com.aca.sabreFix.model.Weapon;
import com.aca.sabreFix.model.Weapon;

import java.util.List;

public interface WeaponDao {
    public List<Weapon> getWeapons();
    public List<Weapon> getWeaponsByType(Type type);
    public List<Weapon> getWeaponsByLevel(Integer level);
    public List<Weapon> getWeaponsById(Integer WeaponId);
    public List<Weapon> getWeaponsByName(String name);
    public Weapon createWeapon(Weapon newWeapon);

    public Weapon updateWeapon(Weapon updatedWeapon);

    public Weapon deleteWeaponById(Integer WeaponIdValue);

    List<Weapon> getReport(Integer Level, Integer endReleaseYear);
}
