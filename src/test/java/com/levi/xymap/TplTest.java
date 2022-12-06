package com.levi.xymap;

import com.levi.xymap.dao.TplDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2022/12/6 17:45
 * @Version 1.0
 **/

public class TplTest {
    @Test
    public void testTpl() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession();
        TplDao tplDao = session.getMapper(TplDao.class);
        List<String> tplNames = tplDao.getTplNames();
        tplNames.forEach(tpl ->{
            System.out.println("模板："+tpl);
        });
    }
}
