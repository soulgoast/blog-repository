package com.qunce.sharding;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qunce.sharding.domain.Course;
import com.qunce.sharding.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @ClassName StartTest
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/24 17:03
 * @ModifyDate 2020/9/24 17:03
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration // 开启web应用配置
@SpringBootTest
public class StartTest {

    @Resource
    private CourseMapper courseMapper;

    @Test
    public void addCourse() {
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setCname("Java").setUserId(100L + i).setStatus("Normal" + i);
            courseMapper.insert(course);
        }
    }

    @Test
    public void findCourse() {
        Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("cid", 476417513358360577L));
        System.out.println(course);
    }
}
