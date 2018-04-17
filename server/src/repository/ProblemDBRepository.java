package repository;

import model.Problem;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProblemDBRepository implements Repository<Long, Problem>{
    private static final String URL = "jdbc:postgresql://localhost:5432/bookstore";
    private static final String user = "postgres";
    private static final String password = "database";


    /**
     * Function that returns the Problem instance from the database with the given ID
     * It executes an SQL command on the associated database which selects the
     * table rows from the Problems table, that have the value in the id column
     * equal to the specified ID
     *
     * @param id ID by which to search
     *           must be not null.
     * @return the Problem instance
     */
    @Override
    public Optional<Problem> findOne(Long id) {
        String sql = "select * from problems where id=?";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String desc = rs.getString("description");

                Problem problem = new Problem(id, desc);

                return Optional.ofNullable(problem);
            }

            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    /**
     * Function that returns an iterable on the List of Problems from the database
     *
     * @return the Iterable on the students
     */
    @Override
    public Iterable<Problem> findAll() {
        String sql = "select * from problems";
        List<Problem> problems = new ArrayList<Problem>();

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                String desc = rs.getString("description");

                Problem problem = new Problem(id, desc);

                problems.add(problem);
            }
            return problems;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Function that adds a Problem to the database
     * It executes an SQL insert command tat adds the attributes of the Problem
     * to the corresponding columns in the Problems table
     *
     * @param entity the Problem to be added
     *               must not be null.
     * @return An empty Optional if the Problem was successfully added
     */
    @Override
    public Optional<Problem> save(Problem entity) {
        String sql = "insert into problems (id, desc) values (?,?)";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getDescription());
            statement.executeUpdate();

        } catch (SQLException e) {
            //TODO

        }
        return Optional.empty();
    }

    /**
     * Function that deletes a Problem from the database
     * It executes an SQL delete command that removes the Problem with
     * the given id
     *
     * @param id the ID of the Problem to be removed
     *           must not be null.
     * @return and empty optional for a successful delete
     */
    @Override
    public Optional<Problem> delete(Long id) {
        String sql = "DELETE from problems where id=?";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Optional.empty();
    }

    /**
     * Function that updates the student with the ID of the Book entity passed as parameter
     * by replacing the fields of the existing student with the ones of the parameter
     * It executes an SQL update command for the student with the aforementioned ID
     *
     * @param entity the student to which to update
     *               must not be null.
     * @return an empty Optional for a successful update
     */
    @Override
    public Optional<Problem> update(Problem entity) {
        String sql = "UPDATE problems set desc=?, WHERE id=?";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, entity.getDescription());
            statement.setLong(2, entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            //TODO

        }
        return Optional.empty();
    }

}
