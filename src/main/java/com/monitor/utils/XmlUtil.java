package com.monitor.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;

@Slf4j
public class XmlUtil {


    public static Document readXml(File file) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            log.info("[readXml] error:{}", e.getMessage(), e);
        }
        return doc;
    }

    public static Document readXml(String resourcePath) {
        Document doc = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource(resourcePath);

            InputStream inputStream = classPathResource.getInputStream();
            File somethingFile = File.createTempFile("readXmlTempFile", ".txt");
            try {
                FileUtils.copyInputStreamToFile(inputStream, somethingFile);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(somethingFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            log.info("[readXml] error:{}", e.getMessage(), e);
        }
        return doc;
    }

    public static void writeXml(Document doc, String path) {
        //write the content into xml file
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File file = new File(path);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (Exception e) {
            log.info("[writeXml] error:{}", e.getMessage(), e);
        }
    }

    public static String readXmlToString(String resourcePath) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(resourcePath);

            InputStream inputStream = classPathResource.getInputStream();
            File somethingFile = File.createTempFile("readXmlTempFile", ".txt");
            try {
                FileUtils.copyInputStreamToFile(inputStream, somethingFile);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
            return FileUtils.readFileToString(somethingFile, "utf-8");
        } catch (Exception e) {
            log.info("[readXmlToString] error:{}", e.getMessage(), e);
            return null;
        }
    }

    public static void writeStringToXml(String xmlContent, String path) {
        try {
            File file = new File(path);
            FileUtils.write(file, xmlContent, "utf-8");
        } catch (Exception e) {
            log.info("[writeStringToXml] error:{}", e.getMessage(), e);
        }
    }

    public static Document convertStringToDoc(String xmlContent) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlContent));
            return db.parse(is);
        } catch (Exception e) {
            log.info("[convertStringToDoc] error:{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Copy an XML document, adding it as a child of the target document root
     * @param source Document to copy
     * @param target Document to contain copy
     */
    public static void copyDocument(Document source, Document target)
    {
        Node node = target.importNode(source.getDocumentElement(), true);

        target.getDocumentElement().appendChild(node);
    }
}
