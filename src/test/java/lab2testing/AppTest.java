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
public class AppTest 
{
    Service service;
    /**
     * Rigorous Test :-)
     */
    @Before
    public void doBefore(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }
    @Test
    public void studentIdIsEmpty()
    {
        assertEquals( service.saveStudent("","aaa",932),1 );
    }

    @Test
    public void studentIdIsOk()
    {
        assertEquals( service.saveStudent("13","aaa",932),0 );
    }

}
