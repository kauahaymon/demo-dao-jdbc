package model.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    Connection conn = null;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO coursejdbc.department (Name) " +
                    "VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected error: no rows affected!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE coursejdbc.department " +
                    "SET Name = ? WHERE Id = ?"
            );
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM coursejdbc.department " +
                    "WHERE Id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                return new Department(rs.getInt("Id"), name);
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> listAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM coursejdbc.department");
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                Department dep = new Department(id, name);
                list.add(dep);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
