package com.aca.sabreFix.dao;

import com.aca.sabreFix.model.RepairTicket;
import com.aca.sabreFix.model.Type;
import com.aca.sabreFix.model.Weapon;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepairTicketDaoImpl implements RepairTicketDao{
    private static String selectAllRepairTickets =
            "SELECT * " +
                    "FROM repair_tickets";
    private static String selectRepairTicketsByType =
            "SELECT * " +
                    "FROM repair_tickets " +
                    "WHERE typeId = ? ";
    private static String selectRepairTicketsByLevel =
            "SELECT * " +
                    "FROM repair_tickets " +
                    "WHERE level = ? ";
    private static String selectRepairTicketsById =
            "SELECT * " +
                    "FROM repair_tickets " +
                    "WHERE id = ? ";
    private static String selectRepairTicketsByName =
            "SELECT * " +
                    "FROM repair_tickets " +
                    "WHERE name LIKE ? ";
    private static String insertRepairTicket =
            "INSERT INTO repair_tickets (name,level,typeId,phone,email,ticket_description) " +
                    "VALUES " +
                    "(?,?,?,?,?,?) ";
    private static String selectNewRepairTicketId =
            "SELECT LAST_INSERT_ID() AS repairTicketId ";
    private static String updateRepairTicketById =
            "UPDATE repair_tickets " +
                    "SET name = ?, " +
                    "level = ?, " +
                    "typeId = ?, " +
                    "phone = ?, " +
                    "email = ?, " +
                    "ticket_description = ? " +
                    "WHERE id = ?";
    private static String deleteRepairTicketById =
            "DELETE FROM repair_tickets " +
                    "WHERE id = ? ";
    private static String selectRepairTicketsByRangeOfLevel =
            "SELECT * FROM repair_tickets " +
                    "WHERE level >= ? " +
                    "AND level <= ? ";
    @Override
    public List<RepairTicket> getRepairTickets() {
        List<RepairTicket> myRepairTickets = new ArrayList<>();
        ResultSet result = null;
        Statement statement = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            statement = conn.createStatement();
            result = statement.executeQuery(selectAllRepairTickets);
            myRepairTickets = makeRepairTicket(result);
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
        return myRepairTickets;
    }

    private List<RepairTicket> makeRepairTicket(ResultSet result) throws SQLException {
        List<RepairTicket> myRepairTickets = new ArrayList<>();
        while (result.next()) {
            RepairTicket repairTicket = new RepairTicket();

            repairTicket.setId(result.getInt("id"));
            repairTicket.setName(result.getString("name"));
            repairTicket.setLevel(result.getInt("level"));
            repairTicket.setPhoneNumber(result.getString("phone"));
            repairTicket.setEmail(result.getString("email"));
            repairTicket.setDescription(result.getString("ticket_description"));

            String typeString = result.getString("typeId");
            repairTicket.setType(Type.convertStringToType(typeString));
            repairTicket.setUpdateTime(result.getObject("updateDateTime", LocalDateTime.class));
            repairTicket.setCreateTime(result.getObject("createDateTime", LocalDateTime.class));
            myRepairTickets.add(repairTicket);
        }
        return myRepairTickets;
    }

    @Override
    public List<RepairTicket> getRepairTicketsByType(Type typeValue) {
        List<RepairTicket> myRepairTickets = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectRepairTicketsByType);
            ps.setString(1, typeValue.toString());
            result = ps.executeQuery();
            myRepairTickets = makeRepairTicket(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return myRepairTickets;

    }

    @Override
    public List<RepairTicket> getRepairTicketsbyLevel(Integer levelValue) {
        List<RepairTicket> myRepairTickets = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectRepairTicketsByLevel);
            ps.setString(1, levelValue.toString());
            result = ps.executeQuery();
            myRepairTickets = makeRepairTicket(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return myRepairTickets;
    }

    @Override
    public List<RepairTicket> getRepairTicketsById(Integer idValue) {
        List<RepairTicket> myRepairTickets = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectRepairTicketsById);
            ps.setString(1, idValue.toString());
            result = ps.executeQuery();
            myRepairTickets = makeRepairTicket(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return myRepairTickets;
    }

    @Override
    public List<RepairTicket> getRepairTicketsByName(String name) {
        List<RepairTicket> myRepairTickets = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectRepairTicketsByName);
            String nameValue = "%" + name +"%";
            ps.setString(1, nameValue);
            result = ps.executeQuery();
            myRepairTickets = makeRepairTicket(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return myRepairTickets;
    }

    @Override
    public RepairTicket createRepairTicket(RepairTicket newRepairTicket) {
        PreparedStatement ps = null;
        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(insertRepairTicket);
            ps.setString(1, newRepairTicket.getName());
            ps.setInt(2, newRepairTicket.getLevel());
            ps.setString(3, newRepairTicket.getType().toString());
            ps.setString(4, newRepairTicket.getPhoneNumber());
            ps.setString(6, newRepairTicket.getDescription());
            ps.setString(5,newRepairTicket.getEmail());
            int rowCount = ps.executeUpdate();
            System.out.println("insert row count: " + rowCount);
            int newRepairTicketId = getNewRepairTicketId(conn);
            newRepairTicket.setId(newRepairTicketId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newRepairTicket;
    }

    private int getNewRepairTicketId(Connection conn) {
        ResultSet rs = null;
        Statement statement = null;
        int newRepairTicketId = 0;

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(selectNewRepairTicketId);
            while(rs.next()) {
                newRepairTicketId = rs.getInt("repairTicketId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newRepairTicketId;
    }

    @Override
    public RepairTicket updateRepairTicket(RepairTicket updateRepairTicket) {
        List<RepairTicket> repairTickets = getRepairTicketsById(updateRepairTicket.getId());

        if (repairTickets.size() > 0) {
            int updateRowCount = 0;
            PreparedStatement ps = null;

            Connection conn = MariaDbUtil.getConnection();
            try {
                ps = conn.prepareStatement(updateRepairTicketById);

                ps.setString(1, updateRepairTicket.getName());
                ps.setInt(2, updateRepairTicket.getLevel());
                ps.setString(3, updateRepairTicket.getType().toString());
                ps.setString(4, updateRepairTicket.getPhoneNumber());
                ps.setString(5,updateRepairTicket.getEmail());
                ps.setString(6, updateRepairTicket.getDescription());

                ps.setInt(7,updateRepairTicket.getId());


                updateRowCount = ps.executeUpdate();
                System.out.println("update row count: " + updateRowCount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return updateRepairTicket;
    }

    @Override
    public RepairTicket deleteRepairTicketById(Integer idValue) {
        List<RepairTicket> repairTickets = getRepairTicketsById(idValue);
        RepairTicket repairTicketToDelete = null;

        if (repairTickets.size() > 0) {
            repairTicketToDelete = repairTickets.get(0);
            int updateRowCount = 0;
            PreparedStatement ps = null;

            Connection conn = MariaDbUtil.getConnection();
            try {
                ps = conn.prepareStatement(deleteRepairTicketById);
                ps.setInt(1, idValue);
                updateRowCount = ps.executeUpdate();
                System.out.println("delete row count: " + updateRowCount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return repairTicketToDelete;
    }

    @Override
    public List<RepairTicket> getReport(Integer startLevel, Integer endLevel) {
        List<RepairTicket> myRepairTickets = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement ps = null;

        Connection conn = MariaDbUtil.getConnection();

        try {
            ps = conn.prepareStatement(selectRepairTicketsByRangeOfLevel);

            ps.setInt(1, startLevel);
            ps.setInt(2, endLevel);

            result = ps.executeQuery();
            myRepairTickets = makeRepairTicket(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myRepairTickets;
    }
}
