package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Процесс сериализации и десериализации.
 *
 * @author Andrey Shulgin
 */
public class MainStudent {
    public static void main(String[] args) throws Exception {
        Student student = new Student(
                3, "4-B", false, new Info("Alex", 18), 5, 3, 4
        );
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream("Info.xml")));
             FileReader reader = new FileReader("Info.xml")) {
            marshaller.marshal(student, writer);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Student result = (Student) unmarshaller.unmarshal(reader);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
