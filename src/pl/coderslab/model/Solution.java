package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class Solution {

	private int id;
	private int exercise_id;
	private int user_id;
	private Date created;
	private Date updated;
	private String description;

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Solution(Date created, Date updated) {
		super();
		this.created = created;
		this.updated = updated;
	}

	public int getId() {
		return id;
	}

	public Solution() {
		super();
	}

	public int getExcersie_id() {
		return exercise_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setExcersie_id(int excersie_id) {
		this.exercise_id = excersie_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	static public Solution[] loadAllSolutions(Connection conn) throws SQLException {

		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solutions";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution solution = new Solution();
			solution.id = resultSet.getInt("id");
			solution.created = resultSet.getDate("created");
			solution.updated = resultSet.getDate("updated");
			solution.exercise_id = resultSet.getInt("excercise_id");
			solution.user_id = resultSet.getInt("user_id");
			solution.description = resultSet.getString("description");
			solutions.add(solution);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
	
	static public Solution[] loadAllSolutions(Connection conn, int limit) throws SQLException {

		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solutions ORDER	BY created ASC LIMIT = ? ";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, limit);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution solution = new Solution();
			solution.id = resultSet.getInt("id");
			solution.created = resultSet.getDate("created");
			solution.updated = resultSet.getDate("updated");
			solution.exercise_id = resultSet.getInt("excercise_id");
			solution.user_id = resultSet.getInt("user_id");
			solution.description = resultSet.getString("description");
			solutions.add(solution);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
	
	
	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {

		String sql = "SELECT * FROM solutions where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = resultSet.getInt("id");
			loadedSolution.created = resultSet.getDate("created");
			loadedSolution.updated = resultSet.getDate("updated");
			loadedSolution.exercise_id = resultSet.getInt("exercise_id");
			loadedSolution.user_id = resultSet.getInt("user_id");
			loadedSolution.description = resultSet.getString("description");
			return loadedSolution;
		}
		return null;
	}
	
	public void delete(Connection conn) throws SQLException {

		if (this.id != 0) {

			String sql = "DELETE FROM solutions WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}
	
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO solutions(created, updated, exercise_id, user_id, description) VALUES (?, ?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setDate(1,  (java.sql.Date) this.created);
			preparedStatement.setDate(2,  (java.sql.Date) this.updated);
			preparedStatement.setInt(3, this.exercise_id);
			preparedStatement.setInt(4, this.user_id);
			preparedStatement.setString(5, this.description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE solutions SET created=?, updated=?, exercise_id=?, user_id=?, description=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setDate(1, (java.sql.Date) this.created);
			preparedStatement.setDate(2, (java.sql.Date) this.updated);
			preparedStatement.setInt(3, this.exercise_id);
			preparedStatement.setInt(4, this.user_id);
			preparedStatement.setString(5, this.description);
			preparedStatement.setInt(6, this.id);
			preparedStatement.executeUpdate();
		}
	}

	static public Solution[] loadAllByUserId(Connection conn, int  userId ) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM users u LEFT JOIN  solutins s ON u.id=s.user_id WHERE= u.id=?  ";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution solution = new Solution();
			solution.id = resultSet.getInt("id");
			solution.created = resultSet.getDate("created");
			solution.updated = resultSet.getDate("updated");
			solution.exercise_id = resultSet.getInt("exercise_id");
			solution.user_id = resultSet.getInt("user_id");
			solution.description = resultSet.getString("description");
			solutions.add(solution);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
	
	static public Solution[] loadAllByExerciseId(Connection conn, int  exerciseId ) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM exercises e LEFT JOIN  solutins s ON e.id=s.exercise_id WHERE= e.id=? ORDER BY created ASC ";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, exerciseId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution solution = new Solution();
			solution.id = resultSet.getInt("id");
			solution.created = resultSet.getDate("created");
			solution.updated = resultSet.getDate("updated");
			solution.exercise_id = resultSet.getInt("exercise_id");
			solution.user_id = resultSet.getInt("user_id");
			solution.description = resultSet.getString("description");
			solutions.add(solution);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
}
