package core.util;

import core.driver.SingleDriver;
import core.dto.EnvironmentDto;
import core.dto.PropertyDto;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private static final Logger LOGGER = Logger.getLogger(FileUtil.class.getSimpleName());

    @Attachment
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) SingleDriver.webDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void createXmlEnvFile(EnvironmentDto environmentDto) {

        Element root = new Element("environment");
        for (PropertyDto property : environmentDto.getProperties()) {
            Element propertyElement = new Element("parameter");
            Element keyElement = new Element("key").setText(property.getKey());
            Element valueElement = new Element("value").setText(property.getValue());
            propertyElement.addContent(keyElement);
            propertyElement.addContent(valueElement);
            root.addContent(propertyElement);
        }
        Document document = new Document(root);
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

        try (FileWriter fileWriter = new FileWriter(Constants.ENV_FILE_PATH)) {
            xmlOutputter.output(document, fileWriter);
            LOGGER.info("XML file created successfully!");
        } catch (IOException e) {
            LOGGER.error("Can't create env file for allure", e.getCause());
        }
    }
}
