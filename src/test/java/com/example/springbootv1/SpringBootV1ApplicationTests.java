package com.example.springbootv1;

import com.example.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SpringBootV1ApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void mySqlTest() {
        String sql = "select name , course , grade from student";
        List<Student> studentList = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setName(resultSet.getString("name"));
                student.setCourse(resultSet.getNString("course"));
                student.setGrade(resultSet.getInt("grade"));
                return student;
            }
        });
        System.out.println("查询成功: ");
        for (Student student : studentList) {
            System.out.println("name : " + student.getName() + " ; course : " + student.getCourse() + " ; grade : " + student.getGrade());
        }
    }

    @Test
    void contextLoads() {
    }

}
