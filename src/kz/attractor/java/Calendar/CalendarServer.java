package kz.attractor.java.Calendar;

import com.sun.net.httpserver.HttpExchange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import kz.attractor.java.Day.Task;
import kz.attractor.java.Day.TaskDataModel;
import kz.attractor.java.Day.TaskType;
import kz.attractor.java.server.BasicServer;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.ResponseCodes;
import kz.attractor.java.utils.Utils;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CalendarServer extends BasicServer {
    private final static Configuration freemarker = initFreeMarker();

    public CalendarServer(String host, int port) throws IOException {
        super(host, port);
        registerGet("/", this:: calendarHandler);
        registerGet("/day", this::dayHandler);
        registerGet("/newTask", this:: newTaskHandlerGet);
        registerPost("/newTask", this:: newTaskHandlerPost);
    }

    private void newTaskHandlerPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        List<Task> tasks = TaskDataModel.readEmployersFile();

        String name = parsed.get("name");
        String description = parsed.get("description");
        TaskType type = ;


            Task task = new Task(name, description, type);
            tasks.add(task);
            TaskDataModel.writeEmployersFile(tasks);
            redirect303(exchange, "/day");
        }




    TaskDataModel getTaskDataModel(){
        return new TaskDataModel();
    }

    private void newTaskHandlerGet(HttpExchange exchange) {
        Path path = makeFilePath("newTask.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }



    private void dayHandler(HttpExchange exchange) {
       renderTemplate(exchange, "day.ftlh", getTaskDataModel());
    }

    private void calendarHandler(HttpExchange exchange) {
        Path path = makeFilePath("calendar.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }




    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File("data"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
    try {
        Template temp = freemarker.getTemplate(templateFile);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {
            temp.process(dataModel, writer);
            writer.flush();

            var data = stream.toByteArray();

            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
        }
    } catch (IOException | TemplateException e) {
        e.printStackTrace();
    }
}

    public static void redirect303(HttpExchange exchange, String path){
        try{
            exchange.getResponseHeaders().add("Location", path);
            exchange.sendResponseHeaders(303, 0);
            exchange.getRequestBody().close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
