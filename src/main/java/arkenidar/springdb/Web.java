package arkenidar.springdb;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Web {

    private JdbcTemplate jdbcTemplate;

    public Web(JdbcTemplate jdbcTemplateAutoWired) {
        jdbcTemplate = jdbcTemplateAutoWired;
    }

    @GetMapping("/")
    List<DBUser> index() {
        // return "test\n";

        // Map<String, Object> argMap = new HashMap<String, Object>();
        // argMap.put("mya", "x");
        // argMap.put("myb", "y");

        // namedParameterJdbcTemplate.query(sql, argMap, resultSetExtractor);

        List<DBUser> list;
        list = jdbcTemplate.query("select * from users", (rs, rowId) -> {
            return new DBUser(
                    rs.getString("username"),
                    rs.getString("password"));
        });
        return list;
    }

}
