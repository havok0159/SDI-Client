package repository;

import model.Assigment;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AssigmentDBRepository implements Repository<Long, Assigment>{
    private static final String URL = "jdbc:postgresql://localhost:5432/bookstore";
    private static final String user = "postgres";
    private static final String password = "database";


    /**
     * Function that returns the Assigment instance from the database with the given ID
     * It executes an SQL command on the associated database which selects the
     * table rows from the Assigments table, that have the value in the id column
     * equal to the specified ID
     *
     * @param id ID by which to search
     *           must be not null.
     * @return the Problem instance
     */
    @Override
    public Optional<Assigment> findOne(Long id) {
        String sql = "select * from assigments where id=";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Long studentId = rs.getLong("studentid");
                Long problemId = rs.getLong("problemid");
                double grade = rs.getDouble("grade");

                Assigment assigment = new Assigment(studentId, problemId, grade);
                assigment.setId(id);

                return Optional.ofNullable(assigment);
            }

            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    /**
     * Function that returns an iterable on the List of Assigment from the database
     *
     * @return the Iterable on the students
     */
    @Override
    public Iterable<Assigment> findAll() {
        String sql = "select * from assigments";
        List<Assigment> assigments = new ArrayList<Assigment>();

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long studentId = rs.getLong("studentid");
                Long problemId = rs.getLong("problemid");
                double grade = rs.getDouble("grade");

                Assigment assigment = new Assigment(studentId, problemId, grade);
                assigment.setId(id);

                assigments.add(assigment);
            }
            return assigments;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Function that adds a Assigment to the database
     * It executes an SQL insert command tat adds the attributes of the Assigment
     * to the corresponding columns in the Assigment table
     *
     * @param entity the Assigment to be added
     *               must not be null.
     * @return An empty Optional if the Problem was successfully added
     */
    @Override
    public Optional<Assigment> save(Assigment entity) {
        String sql = "insert into assigments (id, studentid, problemid, grade) values (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, entity.getId());
            statement.setLong(2, entity.getStudentId());
            statement.setLong(3,entity.getProblemId());
            statement.setDouble(4, entity.getGrade());
            statement.executeUpdate();

        } catch (SQLException e) {
            //TODO

        }
        return Optional.empty();
    }

    /**
     * Function that deletes a Assigment from the database
     * It executes an SQL delete command that removes the Assigment with
     * the given id
     *
     * @param id the ID of the Assigment to be removed
     *           must not be null.
     * @return and empty optional for a successful delete
     */
    @Override
    public Optional<Assigment> delete(Long id) {
        String sql = "DELETE from assigment where id=?";
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
     * Function that updates the Assigment with the ID of the Book entity passed as parameter
     * by replacing the fields of the existing Assigment with the ones of the parameter
     * It executes an SQL update command for the student with the aforementioned ID
     *
     * @param entity the Assigment to which to update
     *               must not be null.
     * @return an empty Optional for a successful update
     */
    @Override
    public Optional<Assigment> update(Assigment entity) {
        String sql = "UPDATE problems set studentid=?, problemid=?, grade=? WHERE id=?";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, entity.getStudentId());
            statement.setLong(2, entity.getProblemId());
            statement.setDouble(3, entity.getGrade());
            statement.setLong(4,entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            //TODO

        }
        return Optional.empty();
    }
}
