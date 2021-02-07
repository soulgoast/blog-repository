package com.qunce.code.generate.dao.frame;

import com.qunce.code.generate.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class MySqlDao {


    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> findSqlWithNativeSql(String sql, Class<T> clazz, Object... params) {
        if (ReflectUtils.isSimpleType(clazz)) {
            return jdbcTemplate.queryForList(sql, params, clazz);
        }
        List<String> lables = new LinkedList<>();
        Map<String, String> propertiesName = listPropertiesName(clazz);
        List<T> result = jdbcTemplate.query(sql, params, new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet resultSet, int j) throws SQLException {
                ResultSetMetaData rsd = resultSet.getMetaData();
                int count = rsd.getColumnCount();
                if (lables.size() == 0) {
                    for (int i = 1; i <= count; i++) {
                        String name = rsd.getColumnLabel(i);
                        lables.add(propertiesName.get(name));
                    }
                }
                Map<String, Object> temp = new HashMap<>();
                for (int i = 1; i <= count; i++) {
                    temp.put(lables.get(i - 1), resultSet.getObject(i));
                }
                return ReflectUtils.changeMapToBean(temp, clazz);
            }
        });
        return result;
    }

    private Map<String, String> listPropertiesName(Class<?> clazz) {
        List<Field> fields = ReflectUtils.listAllFields(clazz);
        Map<String, String> result = new HashMap<>();
        for (Field field : fields) {
            String value = field.getName();
            String key = value;
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                key = column.name();
            }
            result.put(key, value);
        }
        return result;
    }
}
