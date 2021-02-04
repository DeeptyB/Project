package org.example.dao;

import org.example.model.User;
import org.example.util.DbUtil;
import org.example.util.QueryUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    @Override
    public int saveUser(User user) {
        int saved = 0;
        try(PreparedStatement ps = DbUtil.getConnection().prepareStatement(QueryUtil.SAVE_SQL);){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getMobile_no());
            ps.setDate(4, Date.valueOf(user.getDob()));
            ps.setBoolean(5, user.isEnable());
            ps.setDouble(6, user.getSalary());
            saved = ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return saved;
    }

    @Override
    public int updateUser(User user) {
        int updated = 0;
        try(PreparedStatement ps = DbUtil.getConnection().prepareStatement(QueryUtil.UPDATE_SQL);){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getMobile_no());
            ps.setDate(4, Date.valueOf(user.getDob()));
            ps.setBoolean(5, user.isEnable());
            ps.setDouble(6, user.getSalary());
            ps.setInt(7,user.getId());
            updated = ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return updated;

    }

    @Override
    public int deleteUser(int id) {
        int deleted = 0;
        try(PreparedStatement ps = DbUtil.getConnection().prepareStatement(QueryUtil.DELETE_SQL);){

            ps.setInt(1,id);
            deleted = ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return deleted;

    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        try(PreparedStatement ps = DbUtil.getConnection().prepareStatement(QueryUtil.GET_BY_ID_SQL);){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setEnable(rs.getBoolean("enable"));
                user.setMobile_no(rs.getLong("mobile_no"));
                user.setSalary(rs.getDouble("salary"));
                user.setDob(rs.getDate("dob").toLocalDate());

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        try(PreparedStatement ps = DbUtil.getConnection().prepareStatement(QueryUtil.LIST_SQL);){

            ResultSet rs = ps.executeQuery();
             while (rs.next()){
               User user = new User();
               user.setId(rs.getInt("id"));
               user.setUsername(rs.getString("user_name"));
               user.setPassword(rs.getString("password"));
               user.setEnable(rs.getBoolean("enable"));
               user.setMobile_no(rs.getLong("mobile_no"));
               user.setSalary(rs.getDouble("salary"));
               user.setDob(rs.getDate("dob").toLocalDate());
               userList.add(user);
             }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
