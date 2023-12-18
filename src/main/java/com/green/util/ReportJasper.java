package com.green.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportJasper {

    public void report(String jrxmlName, String exportFileName) throws URISyntaxException, IOException {

        String Path = "report.jrxml";
        URL reportPath = ReportJasper.class.getClassLoader().getResource(jrxmlName);

//        URL exportPath = ReportJasper.class.getClassLoader().getResource("reportGreen.pdf");

        URL exportPath = ReportJasper.class.getClassLoader().getResource(exportFileName);
        if (exportPath == null) {
            // Export file not found in classpath, create it in a temporary location
            exportPath = createExportFileInClasspath(exportFileName);
        }
        try {
            JasperReport report = JasperCompileManager.compileReport(Paths.get(reportPath.toURI()).toString());
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(Connection connection = DriverManager.getConnection("jdbc:mysql://34.16.190.220:3306/project", "orkhan", "1234Orkhan!"))
            {

                JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, connection);
                JasperExportManager.exportReportToPdfFile(jasperPrint, Paths.get(exportPath.toURI()).toString());
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static URL createExportFileInClasspath(String exportFileName) throws IOException, URISyntaxException {
        // Create the export file in a temporary location
        Path tempFilePath = Paths.get(ReportJasper.class.getClassLoader().getResource(".").toURI()).resolve(exportFileName);

        // Perform the actual export path creation
        try (OutputStream outputStream = new FileOutputStream(tempFilePath.toFile())) {
            InputStream inputStream = ReportJasper.class.getClassLoader().getResourceAsStream(exportFileName);
            if (inputStream != null) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
        return tempFilePath.toUri().toURL();
    }

}
