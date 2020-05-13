package A3InClassAndAtHome;

import lab2testing.domain.Nota;
import lab2testing.domain.Student;
import lab2testing.domain.Tema;
import lab2testing.repository.NotaXMLRepository;
import lab2testing.repository.StudentXMLRepository;
import lab2testing.repository.TemaXMLRepository;
import lab2testing.service.Service;
import lab2testing.validation.NotaValidator;
import lab2testing.validation.StudentValidator;
import lab2testing.validation.TemaValidator;
import lab2testing.validation.Validator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import lab2testing.domain.Pair;

/**
 * Unit test for simple App.
 */
public class AppTestAtHome {
    Service service;

    /**
     * Rigorous Test :-)
     */
    @Before
    public void doBefore() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme_test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note_test.xml");


        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("13");
        service.deleteTema("1");
        service.deleteNota(new Pair("13","1"));
    }

    @Test
    public void addStudent() {
        assertEquals(1, service.saveStudent("13", "aaa", 932));
    }

    @Test
    public void addStudentAndAssignment() {

        assertEquals(1, service.saveStudent("13", "aaa", 932));
        assertEquals(1, service.saveTema("1", "Scurt.", 10, 2));
    }

    @Test
    public void addStudentAndAssignmentAndGrade() {

        assertEquals(1, service.saveStudent("13", "aaa", 932));
        assertEquals(1, service.saveTema("1", "Scurt.", 10, 2));
        assertEquals(1, service.saveNota("13", "1", 9, 11, "bun"));
    }


}
