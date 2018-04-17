package repository;

import model.Student;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentDBRepository implements Repository<Long, Student>{
    private static final String URL = "jdbc:postgresql://localhost:5432/bookstore";
    private static final String user = "postgres";
    private static final String password = "database";


    /**
     * Function that returns the Student instance from the database with the given ID
     * It executes an SQL command on the associated database which selects the
     * table rows from the students table, that have the value in the id column
     * equal to the specified ID
     *
     * @param id ID by which to search
     *           must be not null.
     * @return the Student instance
     */
    @Override
    public Optional<Student> findOne(Long id) {
        String sql = "select * from students where id=?";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("nume");
                int group = rs.getInt("grupa");

                Student student = new Student(id, name, group);

                return Optional.ofNullable(student);
            }

            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    /**
     * Function that returns an iterable on the List of Students from the database
     *
     * @return the Iterable on the students
     */
    @Override
    public Iterable<Student> findAll() {
        String sql = "select * from students";
        List<Student> students = new ArrayList<Student>();

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("nume");
                int group = rs.getInt("grupa");

                Student student = new Student(id, name, group);

                students.add(student);
            }
            return students;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Function that adds a student to the database
     * It executes an SQL insert command tat adds the attributes of the student
     * to the corresponding columns in the students table
     *
     * @param entity the student to be added
     *               must not be null.
     * @return An empty Optional if the student was successfully added
     */
    @Override
    public Optional<Student> save(Student entity) {
        String sql = "insert into students (id,nume,grupa) values (?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getGroup());
            statement.executeUpdate();

        } catch (SQLException e) {
            //TODO

        }
        return Optional.empty();
    }

    /**
     * Function that deletes a student from the database
     * It executes an SQL delete command that removes the student with
     * the given id
     *
     * @param id the ID of the student to be removed
     *           must not be null.
     * @return and empty optional for a successful delete
     */
    @Override
    public Optional<Student> delete(Long id) {
        String sql = "DELETE from students where id=?";
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
    public Optional<Student> update(Student entity) {
        String sql = "UPDATE students set nume=?, grupa=? WHERE id=?";

        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getGroup());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            //TODO

        }
        return Optional.empty();
    }
}
