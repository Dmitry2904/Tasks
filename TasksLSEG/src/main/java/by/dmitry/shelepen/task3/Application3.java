package by.dmitry.shelepen.task3;

import by.dmitry.shelepen.task3.config.AppConfig;
import by.dmitry.shelepen.task3.model.ReactorOutput;
import by.dmitry.shelepen.task3.service.O3ProductionDownloader;
import by.dmitry.shelepen.task3.service.O3ProductionInputStreamDownloader;
import by.dmitry.shelepen.task3.service.O3ProductionRestTemplateDownloader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Task 3.
 * Write an application that downloads and prints value for production on O3 (value in circle)
 * from page: <a href="https://www.okg.se/en">https://www.okg.se/en</a>
 * The output should look like the example below:
 * value:1384, time:2022-09-19 17:43
 */
public class Application3 {
    private static final Logger log = LogManager.getLogger(Application3.class);

    public static void main(String[] args) {
        Application3 app = new Application3();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        app.downloadsAndPrintValue(context.getBean(O3ProductionRestTemplateDownloader.class));
        app.downloadsAndPrintValue(context.getBean(O3ProductionInputStreamDownloader.class));
    }

    private void downloadsAndPrintValue(O3ProductionDownloader o3ProductionDownloader) {
        ReactorOutput reactorOutput = o3ProductionDownloader.getValueAndTime();

        String pattern = "yyyy-MM-dd HH:mm";
        String resultOutput = String.format("value:%d, time:%s", reactorOutput.getValue(),
                reactorOutput.getStringValueByPattern(pattern));
        log.info(resultOutput);
    }

}


