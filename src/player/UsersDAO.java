package player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mysql.jdbc.Statement;

public class UsersDAO {
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	/*public List<User> getUsers(){
		return this.jdbc.query("SELECT * FROM `users` AS users LEFT JOIN `userstatistics` AS stat on users.username=stat.user", new RowMapper<User>(){
+
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
	
	public boolean checkEmailInDB(String email)
	{
		String sql = "SELECT EMAIL FROM `users` AS U WHERE U.EMAIL = ?";
		List<String> emails = this.jdbc.query(sql, new Object[] { email }, new RowMapper<String>(){
			
			@Override
			public String mapRow(ResultSet rs, int rowNo) throws SQLException {
				String email;
				email = rs.getString("email");
				return email;
			}
		});
		
		return !email.isEmpty();
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
	
	public void updateStatistics(IPlayer player)
	{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = this.transactionManager.getTransaction(def);
		try
		{
			if(player.isHasWon())
			{
				int wins = player.getUser().getStatistics().getWins() + 1;
				this.jdbc.update("UPDATE `userstatistics` AS US SET WINS = ? WHERE US.USER = ?", new Object[] {wins, player.getUserName()});
				if(wins % Statistics.WINS_TO_LEVEL_UP == 0 && player.getUser().getStatistics().getLevel() < Statistics.MAX_LEVEL)	{
					int newLevel = player.getUser().getStatistics().getLevel() + 1;
					this.jdbc.update("UPDATE `userstatistics` AS US SET LEVEL = ? WHERE US.USER = ?", new Object[] {newLevel, player.getUserName()});
				}
			}
			
			if(player.getScore() > player.getUser().getStatistics().getHighScore()){
				this.jdbc.update("UPDATE `userstatistics` AS US SET `HIGH SCORE` = ? WHERE US.USER = ?", new Object[] {player.getScore(), player.getUserName()});
			}
			
			this.transactionManager.commit(status);
			System.out.println("updated");
		}
		catch(DataAccessException e){
			this.transactionManager.rollback(status);
			System.out.println("rollback");
		}
	}
	
	public void registerNewUser(String username, String password, String email)
	{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = this.transactionManager.getTransaction(def);
		try
		{
			this.jdbc.update("INSERT INTO `users`(`username`, `password`, `email`) VALUES (?,?,?)", new Object[] {username, password, email});
			this.jdbc.update("INSERT INTO `userstatistics`(`user`, `level`, `high score`,`wins`) VALUES(?,1,0,0)", new Object[] {username});
			
			this.transactionManager.commit(status);
			System.out.println("added");
		}
		catch(DataAccessException e){
		this.transactionManager.rollback(status);
		System.out.println("rollback reg");
	}
	}
}
