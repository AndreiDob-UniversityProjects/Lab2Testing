package lab2testing;

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

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
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
        for (Student s : service.findAllStudents()) {
            service.deleteStudent(s.getID());
        }
    }

    //EC

    @Test
    public void studentIdIsEmpty() {
        assertEquals(2, service.saveStudent("", "aaa", 932));
    }

    @Test
    public void studentIdIsOk() {
        assertEquals(1, service.saveStudent("13", "aaa", 932));
    }

    @Test
    public void studentNameIsEmpty() {
        assertEquals(2, service.saveStudent("15", "", 932));
    }

    @Test
    public void studentNameIsOk() {
        assertEquals(1, service.saveStudent("16", "aaa", 932));
    }

    //BVA

    @Test
    public void studentGroupTooSmall() {
        assertEquals(2, service.saveStudent("15", "aaa", 110));
    }

    @Test
    public void studentGroupTooBig() {
        assertEquals(2, service.saveStudent("15", "aaa", 938));
    }

    @Test
    public void studentGroupOk() {
        assertEquals(1, service.saveStudent("15", "aaa", 932));
    }

    @Test
    public void studentGroupLowerBound() {
        assertEquals(1, service.saveStudent("15", "aaa", 111));
    }

    @Test
    public void studentGroupUpperBound() {
        assertEquals(1, service.saveStudent("15", "aaa", 937));
    }
}
