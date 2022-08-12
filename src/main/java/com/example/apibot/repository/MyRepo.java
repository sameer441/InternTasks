package repository;

import com.example.apibot.bot.Stock_Bot;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MyRepo {

    final
    JdbcTemplate jdbcTemplate;

    public MyRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

        public int savetoDb(Stock_Bot stockobj) {
        String query = "INSERT INTO LONDONSTOCKS(COMPANYID,COMPANYNAME,COMPANYTITLE,DATETIME) VALUES (?,?,?,?)";
        return jdbcTemplate.update(query, stockobj.getCompanyId(), stockobj.getCompanyName(), stockobj.getCompanyTitle(),stockobj.getDateTime());
    }
}
