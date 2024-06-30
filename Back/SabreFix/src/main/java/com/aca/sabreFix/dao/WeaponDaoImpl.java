package com.aca.sabreFix.dao;

import com.aca.sabreFix.model.Type;
import com.aca.sabreFix.model.Weapon;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.StreamHandler;

public class WeaponDaoImpl implements WeaponDao {

    private static String selectAllWeapons =
            "SELECT id, name, level, typeId, updateDateTime, CreateDateTime " +
                    "FROM weapons ";

    private static String selectWeaponsByType =
            "SELECT id, name, level, typeId, updateDateTime, CreateDateTime " +
            "FROM weapons " +
            "WHERE typeId = ?";
    private static String selectWeaponsByLevel =
            "SELECT id, name, level, typeId, updateDateTime, CreateDateTime " +
                    "FROM weapons " +
                    "WHERE level = ?";
    private static String selectWeaponsById =
            "SELECT id, name, level, typeId, updateDateTime, CreateDateTime " +
                    "FROM weapons " +
                    "WHERE id = ?";
    private static String selectWeaponsByName =
            "SELECT id, name, level, typeId, updateDateTime, CreateDateTime " +
                    "FROM weapons " +
                    "WHERE name LIKE ? ";
    private static String insertWeapon =
            "INSERT INTO weapons (name,level,typeId) " +
                    "VALUES " +
                    "(?,?,?)";
    private static String updateWeaponById =
            "UPDATE weapons " +
                    "SET name = ?, " +
                    "level = ?, " +
                    "typeId = ? " +
                    "WHERE id = ? ";
    private static String deleteWeaponById =
            "DELETE FROM weapons " +
                    "WHERE id = ?";
    private static String selectNewWeaponId =
            "SELECT LAST_INSERT_ID() AS weaponId ";
    private static String selectWeaponsByRangeOfLevel =
            "SELECT id, name, level, typeId, updateDateTime, createDateTime " +
                    "FROM weapons " +
                    "WHERE level >= ? " +
                    "AND level <= ? ";
    @Override
    public List<Weapon> getWeapons() {
        List<Weapon> myWeapons = new ArrayList<>();
        ResultSet result = null;
        Statement statement = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            statement = conn.createStatement();
            result = statement.executeQuery(selectAllWeapons);
            myWeapons = makeWeapon(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                statement.close();
                result.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return myWeapons;
    }

    private List<Weapon> makeWeapon(ResultSet result) throws SQLException {
        List<Weapon> myWeapons = new ArrayList<>();
        while (result.next()) {
            Weapon weapon = new Weapon();

           weapon.setId(result.getInt("id"));
           weapon.setLevel(result.getInt("level"));
           weapon.setName(result.getString("name"));

           String typeString = result.getString("typeId");
           weapon.setType(Type.convertStringToType(typeString));
           weapon.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
           weapon.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
           myWeapons.add(weapon);
        }
        return myWeapons;
    }

    @Override
    public List<Weapon> getWeaponsByType(Type type) {
        List<Weapon> myWeapons = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectWeaponsByType);
            ps.setString(1, type.toString());
            result = ps.executeQuery();
            myWeapons = makeWeapon(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return myWeapons;

    }

    @Override
    public List<Weapon> getWeaponsByLevel(Integer level) {
        List<Weapon> myWeapons = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectWeaponsByLevel);
            ps.setString(1, level.toString());
            result = ps.executeQuery();
            myWeapons = makeWeapon(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return myWeapons;
    }

    @Override
    public List<Weapon> getWeaponsById(Integer WeaponId) {
        List<Weapon> myWeapons = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectWeaponsById);
            ps.setString(1, WeaponId.toString());
            result = ps.executeQuery();
            myWeapons = makeWeapon(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return myWeapons;
    }

    @Override
    public List<Weapon> getWeaponsByName(String name) {
        List<Weapon> myWeapons = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectWeaponsByName);
            String nameValue = "%" + name +"%";
            ps.setString(1, nameValue);
            result = ps.executeQuery();
            myWeapons = makeWeapon(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return myWeapons;
    }

    @Override
    public Weapon createWeapon(Weapon newWeapon) {
        PreparedStatement ps = null;
        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(insertWeapon);
            ps.setString(1, newWeapon.getName());
            ps.setInt(2, newWeapon.getLevel());
            ps.setString(3, newWeapon.getType().toString());
            int rowCount = ps.executeUpdate();
            System.out.println("insert row count: " + rowCount);
            int newWeaponId = getNewWeaponId(conn);
            newWeapon.setId(newWeaponId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newWeapon;
    }

    private int getNewWeaponId(Connection conn) {
        ResultSet rs = null;
        Statement statement = null;
        int newWeaponId = 0;

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(selectNewWeaponId);
            while(rs.next()) {
                newWeaponId = rs.getInt("weaponId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newWeaponId;

    }

    @Override
    public Weapon updateWeapon(Weapon updateWeapon) {
        List<Weapon> weapons = getWeaponsById(updateWeapon.getId());

        if (weapons.size() > 0) {
            int updateRowCount = 0;
            PreparedStatement ps = null;

            Connection conn = MariaDbUtil.getConnection();
            try {
                ps = conn.prepareStatement(updateWeaponById);

                ps.setString(1, updateWeapon.getName());
                ps.setInt(2, updateWeapon.getLevel());
                ps.setString(3, updateWeapon.getType().toString());
                ps.setInt(4, updateWeapon.getId());

                updateRowCount = ps.executeUpdate();
                System.out.println("update row count: " + updateRowCount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return updateWeapon;
    }

    @Override
    public Weapon deleteWeaponById(Integer WeaponIdValue) {
        List<Weapon> weapons = getWeaponsById(WeaponIdValue);
        Weapon weaponToDelete = null;

        if (weapons.size() > 0) {
            weaponToDelete = weapons.get(0);
            int updateRowCount = 0;
            PreparedStatement ps = null;

            Connection conn = MariaDbUtil.getConnection();
            try {
                ps = conn.prepareStatement(deleteWeaponById);
                ps.setInt(1, WeaponIdValue);
                updateRowCount = ps.executeUpdate();
                System.out.println("delete row count: " + updateRowCount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return weaponToDelete;
    }

    @Override
    public List<Weapon> getReport(Integer startLevel, Integer endLevel) {
        List<Weapon> myWeapons = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectWeaponsByRangeOfLevel);

            ps.setInt(1, startLevel);
            ps.setInt(2, endLevel);

            result = ps.executeQuery();
            myWeapons = makeWeapon(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myWeapons;
    }
}