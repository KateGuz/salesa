package com.salesa.dao.mapper;

import com.salesa.entity.User;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class UserMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        //prepare
        UserMapper userMapper = new UserMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Kate");
        when(resultSet.getString("email")).thenReturn("email@gmail.com");
        when(resultSet.getString("password")).thenReturn("pass");
        when(resultSet.getString("phone")).thenReturn("9383749");
        byte[] bytes = {0, 0, 0, 1, 1, 1};
        ByteArrayInputStream avatar = new ByteArrayInputStream(bytes);
        when(resultSet.getBinaryStream("avatar")).thenReturn(avatar);
        when(resultSet.getString("status")).thenReturn("A");
        when(resultSet.getString("type")).thenReturn("U");
        when(resultSet.getInt("dislikeAmount")).thenReturn(1);

        //when
        User user = userMapper.mapRow(resultSet, 1);

        //then
        assertEquals(1, user.getId());
        assertEquals("Kate", user.getName());
        assertEquals("email@gmail.com", user.getEmail());
        assertEquals("pass", user.getPassword());
        assertEquals("9383749", user.getPhone());
        assertEquals(avatar, user.getAvatar());
        assertEquals("A", user.getStatus());
        assertEquals("U", user.getType());
        assertEquals(1, user.getDislikeAmount());

    }

}
