package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.DefaultKeyedValue;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultKeyedValueDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.KeyedValueDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultXYDataset;

import javax.imageio.ImageIO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class GraphicsBuilder  {
    public static void drawGraphByGender(ArrayList<Student> students) {
        var dependencies = new HashMap<String, Integer>();
        dependencies.put("men", 0);
        dependencies.put("women", 0);
        for (var s: students) {
            if (s.getVkData() == null || s.getVkData().getSex() == 0) {
                continue;
            }
            if (s.getVkData().getSex() == 2) {
                int count = dependencies.containsKey("men") ? dependencies.get("men") : 0;
                dependencies.put("men", count + 1);
            }
            if (s.getVkData().getSex() == 1) {
                int count = dependencies.containsKey("women") ? dependencies.get("women") : 0;
                dependencies.put("women", count + 1);
            }
        }
        var ds = createPieDataset(dependencies);
        var chart = createChart(ds, "Students by gender");
        save(chart, "Students by gender.png");
    }

    public static void drawGraphByCity(ArrayList<Student> students) {
        var dependencies = new HashMap<String, Integer>();
        for (var s: students) {
            if (s.getVkData() == null || s.getVkData().getCity() == null) {
                continue;
            }
            var city = s.getVkData().getCity();

            int count = dependencies.containsKey(city) ? dependencies.get(city) : 0;
            dependencies.put(city, count + 1);

        }

        var ds = createPieDataset(dependencies);
        var chart = createChart(ds, "Students by city");
        save(chart, "Students by city.png");
    }


    private static PieDataset createPieDataset(HashMap<String, Integer> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (var str: data.keySet()) {
            dataset.setValue(str, data.get(str));
        }
        return dataset;
    }

//    private static KeyedValueDataset createKeyValuedDataset(HashMap<String, Integer> data) {
//        var dataset = new DefaultCategoryDataset();
//        dataset.setValue();
//        for (var str: data.keySet()) {
//            dataset.setValue(str, data.get(str));
//        }
//        return dataset.;
//    }


    private static JFreeChart createChart( PieDataset dataset, String graphName) {
        JFreeChart chart = ChartFactory.createPieChart(
                graphName,   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    public static void save(JFreeChart chart, String path) {

        var image = chart.createBufferedImage(1280,720);
        try {
            var outputFile = new File(path);
            ImageIO.write(image, "png", outputFile);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
