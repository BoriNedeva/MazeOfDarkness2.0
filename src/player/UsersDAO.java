package player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mysql.jdbc.Statement;

public class UsersDAO {
	DataSource dataSource;
	
	JdbcTemplate jdbc;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	/*public List<User> getUsers(){
		return this.jdbc.query("SELECT * FROM `users` AS users LEFT JOIN `userstatistics` AS stat on users.username=stat.user", new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int columnNo) throws SQLException {
				User user = new User();
				Statistics statistics = new Statistics();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				statistics.setHighScore(rs.getInt("high score"));
				statistics.setLevel(rs.getInt("level"));
				statistics.setWins(rs.getInt("wins"));
				user.setStatistics(statistics);
				return user;
			}
			
		});
	} *///nqma da ni trqbva sigurno
	
	public User getLoggedUser(String username)
	{
		String sql = "SELECT * FROM `users` AS U LEFT JOIN `userstatistics` AS stat on U.username=stat.user WHERE U.USERNAME = ?";
		User user =  (User)this.jdbc.queryForObject(sql, new Object[] { username }, new RowMapper<User>(){
			
			@Override
			public User mapRow(ResultSet rs, int rowNo) throws SQLException {
				User user = new User();
				Statistics statistics = new Statistics();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				statistics.setHighScore(rs.getInt("high score"));
				statistics.setLevel(rs.getInt("level"));
				statistics.setWins(rs.getInt("wins"));
				user.setStatistics(statistics);
				return user;
			}
		});
		return user;
	}
	
	public boolean checkUsernameInDB(String username)
	{
		String sql = "SELECT USERNAME FROM `users` AS U WHERE U.USERNAME = ?";
		List<String> users = this.jdbc.query(sql, new Object[] { username }, new RowMapper<String>(){
			
			@Override
			public String mapRow(ResultSet rs, int rowNo) throws SQLException {
				String user;
				user = rs.getString("username");
				return user;
			}
		});
		
		return !users.isEmpty();
	}
	
	public boolean checkPasswordInDB(String username, String password)
	{
		String sql = "SELECT PASSWORD FROM `users` AS U WHERE U.USERNAME = ?";
		String pass = (String)this.jdbc.queryForObject(sql, new Object[] { username }, new RowMapper<String>(){
			
			@Override
			public String mapRow(ResultSet rs, int rowNo) throws SQLException {
				String pass;
				pass = rs.getString("password");
				return pass;
			}
		});
		
		return pass.equals(password);
	}
}
