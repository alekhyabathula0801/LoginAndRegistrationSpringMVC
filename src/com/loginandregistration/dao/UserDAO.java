package com.loginandregistration.dao;

import com.loginandregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User validateUser(String emailId,String password) {
        String query = "select * from registration where emailId='"+emailId+"' and password='"+password+"'";
        List<User> usersData = jdbcTemplate.query(query, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUserName(resultSet.getString(1));
                user.setEmailId(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                return user;
            }
        });
        return usersData.size()>0 ? usersData.get(0) : null;
    }

    public boolean addUserToDataBase(String userName, String emailId, String password) {
            String query = "insert into registration values(?,?,?)";
            return jdbcTemplate.update(query,userName,emailId,password) == 1;
    }

    public int isEmailIdExist(String emailId){
        String sql = "SELECT COUNT(*) FROM registration WHERE emailId=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,emailId);
    }

}
