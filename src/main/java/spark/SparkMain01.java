package spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class SparkMain01 {
    public static void main(String[] args) {
        List<Double> inputData = new ArrayList<>();
        inputData.add(35.5);
        inputData.add(12.49943);
        inputData.add(90.32);
        inputData.add(20.32);

        Logger.getLogger("org.apache").setLevel(Level.WARN);

        SparkConf conf = new SparkConf()
                .setAppName("App")
                //.set("spark.ui.enabled", "false")
                .setMaster("local[*]");
        JavaRDD<Double> myRdd;
        try (JavaSparkContext sc = new JavaSparkContext(conf)) {
            myRdd = sc.parallelize(inputData);
            var result = myRdd.reduce(Double::sum);

            System.out.println("Result: " + result);
        }
    }
}
